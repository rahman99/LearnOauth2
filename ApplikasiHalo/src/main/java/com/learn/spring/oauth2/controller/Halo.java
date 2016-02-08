package com.learn.spring.oauth2.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Halo {

	@RequestMapping("/halo")
	public void halo(Model m){
		m.addAttribute("waktu", new Date());
	}
}
