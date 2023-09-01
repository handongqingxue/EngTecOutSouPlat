package com.engTecOutSouPlat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.service.*;

@Controller
@RequestMapping("/outSouMana")
public class OutSouManaController {

	@Autowired
	private NeedOutSouService needOutSouService;
	public static final String MODULE_NAME="outSouMana";
	
	@RequestMapping(value="/needOutSou/list")
	public String goNeedOutSouList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/needOutSou/list";
	}
}
