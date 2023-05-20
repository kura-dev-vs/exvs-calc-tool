package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.ui.Model;

public class SetUploadfile {
	//private int filecount;
	private static String file,fileName,exp;
	
	public static void set(Model model,int filecount) throws IOException {
		
		String uploadPath="src/main/resources/static/upload/";
		String textPath = "src/main/resources/static/uploadText/";
		
		File dir_excel = new File(uploadPath);
		File[] list_excel = dir_excel.listFiles();
		
		File dir_text = new File(textPath);
		File[] list_text = dir_text.listFiles();
		
		if(list_excel.length < filecount) {
			filecount = list_excel.length;
		}
		
		for(int i = 0;i<filecount;i++) {
			fileName = list_excel[i].getName();
			file = "upload/" + fileName;
			exp = fileToString(new File("src/main/resources/static/uploadText/" + list_text[i].getName()));
			
			model.addAttribute("file" + Integer.valueOf(i).toString(), file);
			model.addAttribute("fileName" + Integer.valueOf(i).toString(), fileName);
			model.addAttribute("exp" + Integer.valueOf(i).toString(), exp);
		}

	}
	
	public static String fileToString(File file) throws IOException {
		BufferedReader br = null;
		try {
			// ファイルを読み込むバッファドリーダを作成します。
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			// 読み込んだ文字列を保持するストリングバッファを用意します。
			StringBuffer sb = new StringBuffer();
			// ファイルから読み込んだ一文字を保存する変数です。
			int c;
			// ファイルから１文字ずつ読み込み、バッファへ追加します。
			while ((c = br.read()) != -1) {
				sb.append((char) c);
			}
			// バッファの内容を文字列化して返します。
			return sb.toString();
		} finally {
			// リーダを閉じます。
			br.close();
		}
	}

}
