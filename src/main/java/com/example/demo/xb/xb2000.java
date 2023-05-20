package com.example.demo.xb;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.example.demo.CellUtils;

public class xb2000 {
	private String[][] ms= {
			{ "first", "ガンダム", "白い悪魔" },
			{ "g_armor", "Gメカ", "白い悪魔" },
			{ "gyan", "ギャン", "格闘_汎用1" },
			{ "char_gelgoog", "シャア専用ゲルググ", "赤い彗星" },
			{ "char_zaku2", "シャア専用ザクⅡ", "赤い彗星" },
			{ "mk2", "ガンダムMk-Ⅱ", "格闘_汎用1" },
			{ "messala", "メッサーラ", "バランス_汎用" },
			{ "gabthley", "ガブスレイ", "射撃_汎用1" },
			{ "marasai", "マラサイ", "射撃_汎用1" },
			{ "dijeh", "ディジェ", "白い悪魔" },
			{ "qubeley_mk2", "キュベレイMk-Ⅱ(プル)", "射撃_汎用2" },
			{ "zaku3kai", "ザクⅢ改", "格闘_汎用2" },
			{ "doven_wolf", "ドーベン・ウルフ", "格闘_汎用1" },
			{ "h_acguy", "アッガイ(ハマーン)", "バランス_汎用" },
			{ "r_z", "Z(ルー)", "バランス_汎用" },
			{ "jagd_doga", "ヤクト・ドーガ", "バランス_汎用" },
			{ "v", "ヴィクトリー", "射撃_汎用2" },
			{ "gedlav", "外ドラフ", "格闘_汎用2" },
			{ "dragon", "ドラゴン", "明鏡止水" },
			{ "maxtor", "マックスター", "明鏡止水" },
			{ "nobel", "ノーベル", "ガンダムファイター" },
			{ "sandkai", "サンドロック改", "射撃_汎用1" },
			{ "death_hell", "デスサイズヘル", "格闘_高補正" },
			{ "bertiga", "ベルティゴ", "射撃_汎用2" },
			{ "c_capule", "カプル(コレン)", "格闘高補正" },
			{ "strike", "ストライク", "SEED" },
			{ "blitz", "ブリッツ", "コーディネイター" },
			{ "forbidden", "フォビドゥン", "射撃_汎用2" },
			{ "calamity", "カラミティ", "射撃_汎用2" },
			{ "raider", "レイダー", "格闘_汎用2" },
			{ "aegis", "イージス", "SEED" },
			{ "g_zakuw", "ガナーザクウォーリア(ルナマリア)", "コーディネイター" },
			{ "gouf_ignited", "グフイグナイテッド(ハイネ)", "コーディネイター" },
			{ "strike_r", "ストライクルージュ", "SEED" },
			{ "gaia", "ガイア", "バランス_汎用" },
			{ "r_impulse", "インパルス(ルナマリア)", "コーディネイター" },
			{ "l_infinit", "∞ジャスティス(ラクス)", "SEED" },
			{ "ster_gazer", "スターゲイザー", "格闘_汎用1" },
			{ "throne_drei", "スローネドライ", "格闘_汎用1" },
			{ "exia", "エクシア", "トランザム" },
			{ "dynames", "デュナメス", "トランザム" },
			{ "virtue", "ヴァーチェ", "トランザム" },
			{ "qureous", "キュリオス", "トランザム" },
			{ "garazzo", "ガラッゾ", "トランザム" },
			{ "delta_plus", "デルタ⁺", "格闘_汎用1" },
			{ "kshatriya", "クシャトリヤ", "射撃_汎用2" },
			{ "rozen_zulu", "ローゼン・ズール", "射撃_汎用1" },
			{ "age1", "AGE-1", "Xラウンダー" },
			{ "farsia", "ファルシア", "Xラウンダー" },
			{ "g_arcane", "G-アルケイン", "射撃_汎用1" },
			{ "mack_knife", "マックナイフ", "バランス_汎用" },
			{ "montero", "モンテーロ", "射撃_汎用1" },
			{ "flauros", "フラウロス", "阿頼耶識" },
			{ "barbatos", "バルバトス", "阿頼耶識" },
			{ "kimaris", "キマリストルーパー", "格闘高補正" },
			{ "narrative", "ナラティブ", "格闘_汎用1" },
			{ "stein", "シナンジュ・スタイン", "格闘_汎用2" },
			{ "gp01", "試作1号機", "射撃_汎用1" },
			{ "gerbera", "ガーベラ・テトラ", "格闘_汎用1" },
			{ "d_acguy", "アッガイ(ダリル)", "格闘_汎用1" },
			{ "bd01", "BD1号機", "EXAM" },
			{ "pale", "ペイルライダー", "EXAM" },
			{ "v_gelgoog", "ゲルググ(ヴィンセント)", "格闘_汎用1" },
			{ "s_ifrit", "イフリート(シュナイド)", "格闘_汎用1" },
			{ "redflame", "レッドフレーム", "ジャンク屋" },
			{ "x_astray", "Xアストレイ", "射撃_汎用1" },
			{ "blueflame", "ブルーフレームセカンドL", "コーディネイター" },
			{ "amatsu", "ゴールドフレーム天", "コーディネイター" },
			{ "hyperion", "ハイペリオン", "コーディネイター" },
			{ "build_strike", "ビルドストライク", "ガンプラ_バランス" },
			{ "x_maou", "X魔王", "ガンプラ_バランス" },
			{ "a_zaku", "ザクアメイジング", "ガンプラ_格闘" },
			{ "j_zaku2", "ザクⅡ(ジョニー・ライデン)", "格闘_高補正" },
			{ "g_zaku2", "ザクⅡ(ジン・マツナガ)", "射撃_汎用1" },
			{ "xanadu", "ザナドゥ", "射撃_汎用1" },
			};
	private String SheetName;
	private String MS_Name;
	private String[][] burst;
	private String burst_type;
	private String[] iniBurst;
	// weaponの最後まで入っていた行
    private int weapon_line;
    // normalの最後まで入っていた行
    private int normal_line;
    // swingの最後まで入っていた行
    private int swing_line;
    //weapon{名前, ダメージ, 補正率, ダウン値, hit数, キャンセル補正, 格闘か射撃}
    private String[][] weapon;
    private String[] iniWeapon;
    //normal{コマンド,最初,最後}
    private String[][] normal;
    private String[] iniNormal;
    //swing{コマンド,最初,最後}
    private String[][] swing;
    private String[] iniSwing;
    
    //private InputStream is;
    
    public String getName() {
    	return MS_Name;
    }
    
    // 覚醒の値をreturnする
    public String[][] getburst(){
        XB_Burst x = new XB_Burst();
        burst = x.get_burst_value(burst_type);
        return burst;
    }
    
    public String[] getiniBurst() {
    	iniBurst = new String[burst.length];
    	for(int i =0;i<burst.length;i++) {
    		iniBurst[i] = burst[i][0];
    	}
    	return iniBurst;
    }

    // 武装をreturn
    public String[][] getweapon(){
        return weapon;
    }
 
    public String[] getiniWeapon() {
    	iniWeapon = new String[weapon.length];
    	for(int i =0;i<weapon.length;i++) {
    		iniWeapon[i] = weapon[i][0];
    	}
    	return iniWeapon;
    }

    // 通常の武装のコマンドとかをreturnする
    public String[][] getnormal() {
        return normal;
    }
    
    public String[] getiniNormal() {
    	iniNormal = new String[normal.length-1];
    	for(int i =0;i<normal.length-1;i++) {
    		iniNormal[i] = normal[i][0];
    	}
    	return iniNormal;
    }

    // すかしコマンドをreturnする
    public String[][] getswing() {
        return swing;
    }
    
    public String[] getiniSwing() {
    	iniSwing = new String[swing.length-1];
    	for(int i =0;i<swing.length-1;i++) {
    		iniSwing[i] = swing[i][0];
    	}
    	return iniSwing;
    }
    
	
	public xb2000(String ms_val,InputStream is) throws EncryptedDocumentException, IOException {
		//this.is = is;
		for(int i =0;i<ms.length;i++) {
			if(ms_val.equals(ms[i][0])) {
				SheetName = ms[i][0];
				MS_Name = ms[i][1];
				burst_type = ms[i][2];
			}
		}
		
		Workbook excel = WorkbookFactory.create(is);
        Sheet sheet = excel.getSheet(SheetName);
        weapon_line = calcCellNum(sheet,0);
        normal_line = calcCellNum(sheet,8);
        swing_line = calcCellNum(sheet,12);
        System.out.println(weapon_line + " " + normal_line + " " + swing_line);
        weapon = new String[weapon_line][7];
        normal = new String[normal_line][3];
        swing = new String[swing_line][3];
        load_weapon(sheet);
        System.out.println("weapon_complete");
        load_normal(sheet);
        load_swing(sheet);
        excel.close();
	}
	
	private void load_weapon(Sheet sheet){
        for(int i = 1; i<weapon_line;i++){
            for(int j = 0;j<7;j++){
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(j);
                weapon[i-1][j]= CellUtils.getCellValue(cell);
            }
        }
    }

    private void load_normal(Sheet sheet){
        for(int i = 1; i<normal_line;i++){
            for(int j = 8;j<11;j++){
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(j);
                normal[i-1][j-8] = CellUtils.getCellValue(cell);
            }
        }
    }
    
    private void load_swing(Sheet sheet){
        for(int i = 1; i<swing_line;i++){
            for(int j = 12;j<15;j++){
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(j);
                swing[i-1][j-12] = CellUtils.getCellValue(cell);
            }
        }
    }

    // weapon, normal, swingのいずれかにおいてcellに入力されている範囲を調べる
    private int calcCellNum(Sheet sheet, int cellnum){
        int i = 0;
        Row row;
        Cell cell;
        while(true){
            row = sheet.getRow(i);
            // i行目まで値があるかを調べ,nullになったら一時的に行を作る(このメソッドが終わったらすぐに削除される)
            if(row == null){
                row = sheet.createRow(i);
            }
            cell = row.getCell(cellnum);
            // i行cellnum列がnullだったらbreakしwhileを抜ける
            if(cell == null) { break; }
            //　値が入っていないのにnull扱いにならない(一度入力して消したとか)場合をここで判定
            if(cell.getCellType() == CellType.BLANK) { break; }
            i++;
        }
        return i;
    }
}
