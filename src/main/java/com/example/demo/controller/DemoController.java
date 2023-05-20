package com.example.demo.controller;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.SetUploadfile;

@Controller
@RequestMapping("/")
public class DemoController {

    @RequestMapping("")
    public String index(Model model) {
        
        return "index";
    }
    
    @RequestMapping("about")
    public String about(Model model) {
        
        return "about";
    }
    
    @RequestMapping("datacor")
    public String datacor(Model model) throws IOException {
    	
    	int filecount = 20;
    	SetUploadfile.set(model,filecount);
    	
        return "datacor";
    }
    
    
 	@RequestMapping("fileUpload")
 	public String fileUpload(Model model, @RequestParam("uploadFile") MultipartFile file, @RequestParam("explain") String explain)
 			throws IOException {
 		String name = file.getOriginalFilename();
 		System.out.println(name);
 		String extension = name.substring(name.lastIndexOf("."));
 		if(extension.equals(".xlsx")) {
 			LocalDateTime nowDate = LocalDateTime.now();
 			DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
 			String formatNowDate = dtf3.format(nowDate);
 			String woext = name.substring(0,name.lastIndexOf('.'));
 			String newName = woext + "_" +formatNowDate;
 			textwrite(newName,explain);
 			Path uploadPath = Path.of("src/main/resources/static/upload", newName + ".xlsx");
 			Files.copy(file.getInputStream(), uploadPath);
 			
 		}
 		return "uploadAccepted";
 	}
 	
 	@RequestMapping("uploadAccepted")
    public String uploadAccepted(Model model) {
        
        return "uploadAccepted";
    }
 	
 	public void textwrite(String newName, String explain) {
 		try{
 			// FileWriterクラスのオブジェクトを生成する
            FileWriter file = new FileWriter("src/main/resources/static/uploadText/" + newName + ".txt");
            // PrintWriterクラスのオブジェクトを生成する
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
            
            //ファイルに書き込む
            pw.print(explain);
            
            //ファイルを閉じる
            pw.close();
 		}catch (IOException e){
 			e.printStackTrace();
 		}
 	}
    
    @RequestMapping("xb3000")
    public String xb3000(Model model) {
        
        return "xb3000";
    }
    
    @RequestMapping("xb2500")
    public String xb2500(Model model) {
        
        return "xb2500";
    }
    
    
    @RequestMapping("xb2000")
    public String xb2000(Model model) {
        
        return "xb2000";
    }
    
    
    @RequestMapping("xb1500")
    public String xb1500(Model model) {
        
        return "xb1500";
    }
    

    
    @PostMapping("/test")
    @ResponseBody
    public String m_note(@RequestParam String note) {
    	System.out.println("11");
    	if(note.equals("a")) {
    	}
    	else {
    		note = note+ note;
    	}
        return note;
    }
    


}