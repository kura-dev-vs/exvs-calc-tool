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

public class xb3000 {
	private String[][] ms= {
			{ "fazz", "フルアーマーZZガンダム", "ジャンク屋" },
			{ "ν", "νガンダム", "白い悪魔" },
			{ "sazabi", "サザビー", "赤い彗星" },
			{ "v2", "V2ガンダム", "射撃_汎用2" },
			{ "god", "ゴッドガンダム", "明鏡止水" },
			{ "master", "マスターガンダム", "明鏡止水" },
			{ "w0", "ウイングガンダムゼロ", "ゼロシステム" },
			{ "epyon", "ガンダムエピオン", "ゼロシステム" },
			{ "ew0", "ウイングガンダムゼロ(EW版)", "ゼロシステム" },
			{ "gis3", "トールギスⅢ", "射撃_汎用1" },
			{ "dx", "ガンダムDX", "ジャンク屋" },
			{ "virsagocb", "ガンダムヴァサーゴ・CB", "格闘_汎用1" },
			{ "turna", "∀ガンダム", "月光蝶" },
			{ "turnx", "ターンX", "月光蝶" },
			{ "destiny", "デスティニーガンダム", "SEED" },
			{ "strikefreedom", "ストライクフリーダムガンダム", "SEED" },
			{ "infinit_justice", "∞ジャスティスガンダム", "SEED" },
			{ "doubleo", "ダブルオーガンダム", "トランザム" },
			{ "reborns", "リボーンズガンダム", "トランザム" },
			{ "doubleoq", "ダブルオークアンタ", "トランザム" },
			{ "zabanya", "ガンダムサバーニャ", "トランザム" },
			{ "harute", "ガンダムハルート", "トランザム" },
			{ "faunicorn", "FAユニコーンガンダム", "射撃_汎用2" },
			{ "unicorn", "ユニコーンガンダム", "射撃_汎用2" },
			{ "norn", "バンシィ・ノルン", "射撃_汎用2" },
			{ "sinanju", "シナンジュ", "赤い彗星" },
			{ "agefx", "ガンダムAGE-FX", "Xラウンダー" },
			{ "legilus", "ガンダムレギルス", "Xラウンダー" },
			{ "p_self", "G-セルフ(パーフェクトパック)", "バランス_汎用" },
			{ "kabakali", "カバカーリー", "バランス_汎用" },
			{ "rex", "ガンダム・バルバトスルプスレクス", "阿頼耶識" },
			{ "bael", "ガンダム・バエル", "阿頼耶識" },
			{ "kimarisvidal", "ガンダム・キマリスヴィダール", "阿頼耶識" },
			{ "exs", "Ex-Sガンダム", "ALICE" },
			{ "hi_new", "Hi-νガンダム", "白い悪魔" },
			{ "nightingale", "ナイチンゲール", "赤い彗星" },
			{ "xi", "Ξガンダム", "射撃_汎用2" },
			{ "penelope", "ペーネロペー", "射撃_汎用2" },
			{ "fullcross", "X1フルクロス", "宇宙海賊" },
			{ "ster-build", "スタービルドストライク", "ガンプラ_バランス" },
			{ "hot", "ホットスクランブル", "ガンプラ_格闘" },
			{ "hws", "νガンダムHWS", "白い悪魔" },
			{ "fullsaber", "00ガンダムフルセイバー", "トランザム" },
			{ "sevensord", "00ガンダムセブンソード/G", "射撃_汎用1" },
			{ "jagdarche", "ヤークトアルケー", "トランザム" },
			{ "fukunew", "RX-93ff νガンダム", "白い悪魔" },
			{ "variant", "EXガンダム type-レオスⅡ Vs.", "Gダイバー" },
			{ "explosion", "N-EXTREMEガンダム エクスプロージョン", "格闘高補正" },
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
    
	
	public xb3000(String ms_val,InputStream is) throws EncryptedDocumentException, IOException {
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
