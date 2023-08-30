package com.engTecOutSouPlat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outSouMana")
public class OutSouManaController {

	public static final String MODULE_NAME="outSouMana";
	
	@RequestMapping(value="/needOutSou/list")
	public String goNeedOutSouList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/needOutSou/list";
	}
}
