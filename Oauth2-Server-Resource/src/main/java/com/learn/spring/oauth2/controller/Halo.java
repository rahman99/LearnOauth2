package com.learn.spring.oauth2.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Halo {

	@RequestMapping("/halo")
	public void halo(Model m){
		m.addAttribute("waktu", new Date());
	}
	
	@RequestMapping("/api/halo")
	@ResponseBody
	public Map<String, Object> haloApi(Map<String, Object> input){
		Map<String, Object> data = new HashMap<>();
		data.put("waktu halo", new Date());
		
		return data;
	}
}
