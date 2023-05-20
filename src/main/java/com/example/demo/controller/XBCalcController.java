package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.xb.Xb_calc;
import com.example.demo.xb.xb1500;
import com.example.demo.xb.xb2000;
import com.example.demo.xb.xb2500;
import com.example.demo.xb.xb3000;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes(types = Xb_calc.class) 
@RequestMapping
public class XBCalcController {
	//private XB_Calc calc;
	private Path filePath;
	private Path uploadPath;
	private InputStream is;
	private String MS_Name;
	private String[] iniBurst;
	private String[][] burst;
	//weapon{名前, ダメージ, 補正率, ダウン値, hit数, キャンセル補正, 格闘か射撃}
	private String[][] weapon;
	private String[] iniWeapon;
	//normal{コマンド,最初,最後}
	private String[][] normal;
	private String[] iniNormal;
	//swing{コマンド,最初,最後}
	private String[][] swing;
	private String[] iniSwing;
	
	private String[] dmg_com = new String[2];
	
	@ModelAttribute("xb_calc")
	public Xb_calc xb_calc() {
		return new Xb_calc();
	}
	
	//@RequestMapping(path = "/xb_calc", method = RequestMethod.POST)
	@RequestMapping("/xb_main")
	public String xb_main(@RequestParam("xb_cost") int xb_cost, @RequestParam("xb_ms") String ms_val, Model model,
			@ModelAttribute Xb_calc xb_calc,HttpSession httpSession)
			throws IOException {
		System.out.println(xb_cost);
		info_assets(ms_val, xb_cost, xb_calc, httpSession);
		System.out.println(httpSession.getId());

		model.addAttribute("ms_name", MS_Name);
		model.addAttribute("bursts", iniBurst);
		model.addAttribute("normals", iniNormal);
		model.addAttribute("swings", iniSwing);

		return "xb_main";
	}
	
	
	// fileを添付して行った場合
	@RequestMapping("/xb_main_file")
	public String xb_main_file(@RequestParam("xb_cost") int xb_cost, @RequestParam("xb_ms") String ms_val, Model model,
			@ModelAttribute Xb_calc xb_calc,HttpSession httpSession, @RequestParam("uploadFile") MultipartFile file)
			throws IOException {
		System.out.println(xb_cost);
		info_assets_file(ms_val, xb_cost, xb_calc, httpSession, file);
		System.out.println(httpSession.getId());

		model.addAttribute("ms_name", MS_Name);
		model.addAttribute("bursts", iniBurst);
		model.addAttribute("normals", iniNormal);
		model.addAttribute("swings", iniSwing);
		
		MS_Name = null; // 消しておくことで異なるファイルを選択した場合にMS_Nameがnullのままになりerrorになる

		return "xb_main";
	}

	
	// ファイルを添付しなかった場合
	private void info_assets(String val, int cost, @ModelAttribute Xb_calc xb_calc, HttpSession httpSession) throws IOException {
		if (cost == 3000) {
			filePath = Paths.get("src/main/resources/static/excel/XB_3000.xlsx");
			is = Files.newInputStream(filePath);
			xb3000 xb = new xb3000(val, is);
			MS_Name = xb.getName();
			burst = xb.getburst();
			iniBurst = xb.getiniBurst();
			weapon = xb.getweapon();
			iniWeapon = xb.getiniWeapon();
			normal = xb.getnormal();
			iniNormal = xb.getiniNormal();
			swing = xb.getswing();
			iniSwing = xb.getiniSwing();
			
		} else if (cost == 2500) {
			filePath = Paths.get("src/main/resources/static/excel/XB_2500.xlsx");
			is = Files.newInputStream(filePath);
			xb2500 xb = new xb2500(val, is);
			MS_Name = xb.getName();
			burst = xb.getburst();
			iniBurst = xb.getiniBurst();
			weapon = xb.getweapon();
			iniWeapon = xb.getiniWeapon();
			normal = xb.getnormal();
			iniNormal = xb.getiniNormal();
			swing = xb.getswing();
			iniSwing = xb.getiniSwing();
			
		} else if (cost == 2000) {
			filePath = Paths.get("src/main/resources/static/excel/XB_2000.xlsx");
			is = Files.newInputStream(filePath);
			xb2000 xb = new xb2000(val, is);
			MS_Name = xb.getName();
			burst = xb.getburst();
			iniBurst = xb.getiniBurst();
			weapon = xb.getweapon();
			iniWeapon = xb.getiniWeapon();
			normal = xb.getnormal();
			iniNormal = xb.getiniNormal();
			swing = xb.getswing();
			iniSwing = xb.getiniSwing();
			
		} else {
			filePath = Paths.get("src/main/resources/static/excel/XB_1500.xlsx");
			is = Files.newInputStream(filePath);
			xb1500 xb = new xb1500(val, is);
			MS_Name = xb.getName();
			burst = xb.getburst();
			iniBurst = xb.getiniBurst();
			weapon = xb.getweapon();
			iniWeapon = xb.getiniWeapon();
			normal = xb.getnormal();
			iniNormal = xb.getiniNormal();
			swing = xb.getswing();
			iniSwing = xb.getiniSwing();
		}
		
		xb_calc.setBurst(burst);
		xb_calc.setWeapon(weapon);
		xb_calc.setNormal(normal);
		xb_calc.setSwing(swing);
		xb_calc.setIniWeapon(iniWeapon);
	}
	
	// ファイルを添付した場合
	private void info_assets_file(String val, int cost, @ModelAttribute Xb_calc xb_calc, HttpSession httpSession, MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		String extension = fileName.substring(fileName.lastIndexOf("."));
		if(extension.equals(".xlsx") == false) {
			return;
		}
		
		// ファイルの頭文字7文字を取得
		String woext = fileName.substring(0,7);
		
		if (cost == 3000) {
			if(woext.equals("XB_3000")){
				uploadPath = Path.of("src/main/resources/static/tmp", file.getOriginalFilename());
				Files.copy(file.getInputStream(), uploadPath);
				is = Files.newInputStream(uploadPath);
				xb3000 xb = new xb3000(val, is);
				MS_Name = xb.getName();
				burst = xb.getburst();
				iniBurst = xb.getiniBurst();
				weapon = xb.getweapon();
				iniWeapon = xb.getiniWeapon();
				normal = xb.getnormal();
				iniNormal = xb.getiniNormal();
				swing = xb.getswing();
				iniSwing = xb.getiniSwing();
			}
			
		} else if (cost == 2500) {
			if(woext.equals("XB_2500")){
				uploadPath = Path.of("src/main/resources/static/tmp", file.getOriginalFilename());
				Files.copy(file.getInputStream(), uploadPath);
				is = Files.newInputStream(uploadPath);
				xb2500 xb = new xb2500(val, is);
				MS_Name = xb.getName();
				burst = xb.getburst();
				iniBurst = xb.getiniBurst();
				weapon = xb.getweapon();
				iniWeapon = xb.getiniWeapon();
				normal = xb.getnormal();
				iniNormal = xb.getiniNormal();
				swing = xb.getswing();
				iniSwing = xb.getiniSwing();
			}
		} else if (cost == 2000) {
			if(woext.equals("XB_2000")){
				uploadPath = Path.of("src/main/resources/static/tmp", file.getOriginalFilename());
				Files.copy(file.getInputStream(), uploadPath);
				is = Files.newInputStream(uploadPath);
				xb2000 xb = new xb2000(val, is);
				MS_Name = xb.getName();
				burst = xb.getburst();
				iniBurst = xb.getiniBurst();
				weapon = xb.getweapon();
				iniWeapon = xb.getiniWeapon();
				normal = xb.getnormal();
				iniNormal = xb.getiniNormal();
				swing = xb.getswing();
				iniSwing = xb.getiniSwing();
			}
		} else {
			if(woext.equals("XB_1500")){
				uploadPath = Path.of("src/main/resources/static/tmp", file.getOriginalFilename());
				Files.copy(file.getInputStream(), uploadPath);
				is = Files.newInputStream(uploadPath);
				xb1500 xb = new xb1500(val, is);
				MS_Name = xb.getName();
				burst = xb.getburst();
				iniBurst = xb.getiniBurst();
				weapon = xb.getweapon();
				iniWeapon = xb.getiniWeapon();
				normal = xb.getnormal();
				iniNormal = xb.getiniNormal();
				swing = xb.getswing();
				iniSwing = xb.getiniSwing();
			}
		}
		
		
		/* 
		 * if アップロードされたファイルを使用した場合、そのファイルを削除する
		 *  */
		Files.delete(uploadPath);
		
		xb_calc.setBurst(burst);
		xb_calc.setWeapon(weapon);
		xb_calc.setNormal(normal);
		xb_calc.setSwing(swing);
		xb_calc.setIniWeapon(iniWeapon);
	}

	@PostMapping("/xb_start")
	@ResponseBody
	public String[] start(
			@RequestParam(value = "bur_idx", required = true) int bur_idx,
			@RequestParam(value = "con_idx", required = false) int[] con_idx,
			@RequestParam(value = "nor_idx", required = false) int[] nor_idx,
			@RequestParam(value = "swi_idx", required = false) int[] swi_idx,
			@RequestParam(value = "hit_val", required = false) String[] hit_val,
			@ModelAttribute Xb_calc xb_calc,HttpSession httpSession) {
		
		//dmg_com = calc.start(bur_idx, con_idx, nor_idx, swi_idx, hit_val);

		dmg_com = xb_calc.start(bur_idx, con_idx, nor_idx, swi_idx, hit_val);
		return dmg_com;
	}

	@PostMapping("/xb_hit")
	@ResponseBody
	public int[] get_hit(
			@RequestParam(value = "index", required = true) int index,
			@RequestParam(value = "this_or", required = true) String this_or, 
			@ModelAttribute Xb_calc xb_calc, HttpSession httpSession) {
		int hit;
		int weapon_no;
		this.weapon = xb_calc.getWeapon();
		this.normal = xb_calc.getNormal();
		this.swing = xb_calc.getSwing();
		if (this_or.equals("nor")) {
			weapon_no = (int) Math.floor(Double.parseDouble(normal[index][2]));
		} else {
			weapon_no = (int) Math.floor(Double.parseDouble(swing[index][2]));
		}
		hit = (int) Math.floor(Double.parseDouble(weapon[weapon_no - 2][4]));

		int[] hit_no = new int[hit];
		for (int i = hit; i > 0; i--) {
			hit_no[hit-i] = i;
		}
		
		return hit_no;
	}
	
	

}
