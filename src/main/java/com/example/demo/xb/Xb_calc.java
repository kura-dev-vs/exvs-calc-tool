package com.example.demo.xb;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Xb_calc implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String[][] burst;
	//weapon{名前, ダメージ, 補正率, ダウン値, hit数, キャンセル補正, 格闘か射撃}
	private String[][] weapon;
	private String[] iniWeapon;
	//normal{コマンド,最初,最後}
	private String[][] normal;
	//swing{コマンド,最初,最後}
	private String[][] swing;
	
	private String[] dmg_com = new String[2];

	/*
	public XB_Calc(String[][] burst, String[][] weapon, String[][] normal, String[][] swing, String[] iniWeapon) {
		this.burst = burst;
		this.weapon = weapon;
		this.normal = normal;
		this.swing = swing;
		this.iniWeapon = iniWeapon;
	}
	*/

	public String[] start(int bur_idx, int[] con_idx, int[] nor_idx, int[] swi_idx, String[] hit_val) {
		
		String str = "";
        String app_con = "";

        int fight = Integer.parseInt(burst[bur_idx][1]); // 格闘のダメージ補正
        int shoot = Integer.parseInt(burst[bur_idx][2]); // 射撃のダメージ補正

        int fight_down,shoot_down,attack_down;;

        if( bur_idx == 0){
            // 非覚醒時のダウン補正
            fight_down = 1000;
            shoot_down = 1000;
        }
        else if(bur_idx == 2){
            // F覚醒時のダウン補正
            fight_down = 700;
            shoot_down = 900;
        }
        else{
            // その他の覚醒時のダウン補正
            fight_down = 900;
            shoot_down = 900;
        }

        int type; //格闘か射撃の覚醒ダメージ補正

        int pos_com;
        int pos_con;
        int pos_first,pos_finish;
        //int hit_count; // hit回数が武装の最大値以外の場合(n hit)と表記するのに使用
        //String hit_str;

        double damage,rev,down;
        damage = 0; //ダメージ
        rev = 1000; //補正
        down = 0; //ダウン値

        double surplus = 0; // 350超過分
        int cancel = 100; // ｷｬﾝｾﾙ補正

        int k = 0;
        //loop:while((spi_command[k].getSelectedItem().equals("")) == false){
        loop:while(((nor_idx[k] != 0) || (swi_idx[k] != 0)) && ((k < 20))){
        	if(nor_idx[k] != 0) {
        		pos_com = nor_idx[k];
                pos_first = (int)Double.parseDouble(normal[pos_com][1]) - 2;
                pos_finish = (int)Double.parseDouble(normal[pos_com][2]) - 2;
        	}
        	else {
        		pos_com = swi_idx[k];
                pos_first = (int)Double.parseDouble(swing[pos_com][1]) - 2;
                pos_finish = (int)Double.parseDouble(swing[pos_com][2]) - 2;
        	}
         
            pos_con = con_idx[k];
            if(pos_con == 0){app_con = "≫";} // BD
            if(pos_con == 1){app_con = ">";} // ステップ
            if(pos_con == 2){app_con = "→";} // ｷｬﾝｾﾙ
            if(pos_con == 3){app_con = "";} // 派生

            str = str + app_con;

            for(int i = pos_first; i <= pos_finish; i++){

                // 格闘か射撃か
                if(weapon[i][6].equals("s")){
                    type = shoot;
                    attack_down = shoot_down;
                }
                else{
                    type = fight;
                    attack_down = fight_down;
                }

                // ｷｬﾝｾﾙ補正
                if(pos_con == 2){
                    cancel = 100-(int)Double.parseDouble(weapon[i][5]);
                    if(bur_idx == 3){
                        cancel = 100;
                    }
                }

                // コマンド表に追加
                str = str + weapon[i][0];

                // 最終段のhit回数のみspinnerで選択した値に変更する
                int hit_num = (int)Double.parseDouble(weapon[i][4]);
                if(i == pos_finish){
                    hit_num = Integer.parseInt (hit_val[k]);
                    //System.out.println(hit_num);
                }

                // hit回数分計算
                for(int j = 0;j<hit_num;j++){

                    // hit数が武装の上限に達しなかった場合そのhit数をcommandに表記する
                    /* 未使用
                    hit_count = j;
                    hit_str = "(" + hit_count + "hit)";
                    if(j == (int)Double.parseDouble(weapon[i][4])){
                        hit_str = "";
                    }
                     */

                    if(damage>350) {
                        damage = damage + Math.ceil(Double.parseDouble(weapon[i][1]) * rev / 1000 * type / 100 * cancel / 100 * 25 / 100);
                    }
                    else {
                        damage = damage + Double.parseDouble(weapon[i][1]) * rev / 1000 * type / 100 * cancel / 100;
                        if(damage > 350) {
                            surplus = (damage - 350) * 25 / 100;
                            surplus = Math.ceil(surplus);
                            damage = 350 + surplus;
                        }
                    }
                    damage = Math.ceil(damage);
                    rev = rev - Double.parseDouble(weapon[i][2]) * 10;
                    if(rev < 100) {
                        rev = 100;
                    }
                    down = down + Double.parseDouble(weapon[i][3]) * 1000 * attack_down/1000;
                    if(down >= 5000) {
                        break loop;
                    }
                }

            }
            k++;
        }
        dmg_com[0] = damage + " / " + down / 1000 + " / " + rev / 10;
        dmg_com[1] = str;
        
		return dmg_com;
	}
}
