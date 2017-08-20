package com.baixinping.cvtepro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	//系统首页模块
	@RequestMapping("/")
	public String login(){
		return "login";
	}
	
}
