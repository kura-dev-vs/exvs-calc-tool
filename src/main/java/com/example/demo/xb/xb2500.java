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

public class xb2500 {
	private String[][] ms= {
			{ "zeong", "ジオング", "赤い彗星" },
			{ "z", "z", "バランス_汎用" },
			{ "hyakushiki", "百式", "赤い彗星" },
			{ "theo", "ジ・O", "バランス_汎用" },
			{ "hambrabi", "ハンブラビ", "射撃_汎用1" },
			{ "baund_doc", "バウンド・ドック", "射撃_汎用1" },
			{ "zz", "ZZ", "ジャンク屋" },
			{ "qubeley", "キュベレイ", "射撃_汎用2" },
			{ "f91", "F91", "格闘_汎用2" },
			{ "gottrlatan", "ゴトラタン", "格闘_汎用2" },
			{ "shining", "シャイニング", "明鏡止水" },
			{ "shadow", "シュピーゲル", "ガンダムファイター" },
			{ "artron", "アルトロン", "射撃_汎用1" },
			{ "gis2", "トールギスⅡ", "射撃_汎用1" },
			{ "gis", "トールギス", "射撃_汎用1" },
			{ "ewdeath", "デスサイズヘル(EW版)", "格闘高補正" },
			{ "ewheavy", "ヘビーアームズ改(EW版)", "射撃_汎用1" },
			{ "dv", "Xディバイダー", "格闘_汎用1" },
			{ "x", "X", "ジャンク屋" },
			{ "freedom", "フリーダム", "SEED" },
			{ "pstrike", "Pストライク", "格闘_汎用1" },
			{ "providence", "プロヴィデンス", "SEED" },
			{ "justice", "ジャスティス", "SEED" },
			{ "legend", "レジェンド", "SEED" },
			{ "impulse", "インパルス", "SEED" },
			{ "akatsuki", "アカツキ", "射撃_汎用1" },
			{ "noir", "ストライクノワール", "バランス_汎用" },
			{ "cherudim", "ケルディム", "トランザム" },
			{ "arios", "アリオス", "トランザム" },
			{ "susanowo", "スサノオ", "格闘高補正" },
			{ "arche", "アルケー", "格闘高補正" },
			{ "raphael", "ラファエル", "トランザム" },
			{ "brave", "ブレイヴ", "トランザム" },
			{ "banshee", "バンシィ", "射撃_汎用2" },
			{ "age2", "AGE-2", "射撃_汎用1" },
			{ "age3", "AGE-3", "Xラウンダー" },
			{ "glansa", "AGE-1 フルグランサ", "Xラウンダー" },
			{ "hound", "AGE-2 DH", "宇宙海賊" },
			{ "zeydra", "ゼイドラ", "Xラウンダー" },
			{ "fawn", "フォーンファルシア", "Xラウンダー" },
			{ "gself", "G-セルフ", "バランス_汎用" },
			{ "fullcity", "グシオンリベイクフルシティ", "阿頼耶識" },
			{ "lupus", "バルバトスルぷス", "阿頼耶識" },
			{ "phenex", "フェネクス", "射撃_汎用2" },
			{ "gp03", "試作3号機", "射撃_汎用1" },
			{ "gp02", "試作2号機", "格闘高補正" },
			{ "atlas", "アトラス", "射撃_汎用1" },
			{ "fa", "フルアーマーガンダム", "射撃_汎用1" },
			{ "psycozaku", "サイコ・ザク", "格闘_汎用1" },
			{ "x3", "クロスボーンX3", "宇宙海賊" },
			{ "x1", "クロスボーンX1改", "宇宙海賊" },
			{ "x2", "クロスボーンX2改", "宇宙海賊" },
			{ "phantom", "ファントム", "宇宙海賊" },
			{ "ghina2", "ビギナ・ギナⅡ", "格闘_汎用2" },
			{ "redkai", "レッドフレーム改", "ジャンク屋" },
			{ "redrd", "レッドフレームRD", "ジャンク屋" },
			{ "blued", "フルーフレームD", "コーディネイター" },
			{ "amatsumina", "ゴールドフレーム天ミナ", "コーディネイター" },
			{ "overon", "オーヴェロン", "バランス_汎用" },
			{ "fenice", "ウイングフェニーチェ", "ガンプラ_バランス" },
			{ "sengoku", "戦国アストレイ", "ガンプラ_格闘" },
			{ "tryburning", "トライバーニング", "ガンプラ_格闘" },
			{ "lightning", "ライトニングFB", "ガンプラ_射撃" },
			{ "sterwinning", "スターウイニング", "ガンプラ_バランス" },
			{ "diver_ace", "ダイバーエース", "ガンプラ_格闘" },
			{ "zeromaru", "RX-零丸", "ガンプラ_バランス" },
			{ "avaranche", "アヴァランチエクシア", "トランザム" },
			{ "ex_eclipse", "EX エクリプス-F", "Gダイバー" },
			{ "ex_xenon", "EX ゼノン-F", "Gダイバー" },
			{ "ex_aios", "EX アイオス-F", "Gダイバー" },
			{ "ex_exilia", "EX エクセリア", "射撃_汎用1" },
			{ "night", "騎士ガンダム", "ラクロアの勇者" },
			{ "vicious", "ヴィシャス", "格闘高補正" },
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
    
	
	public xb2500(String ms_val,InputStream is) throws EncryptedDocumentException, IOException {
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
