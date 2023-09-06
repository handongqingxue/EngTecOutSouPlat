package com.engTecOutSouPlat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.service.*;

@Controller
@RequestMapping("/companyMana")
public class CompanyManaController {
	
	@Autowired
	private CompanyService companyService;
	public static final String MODULE_NAME="companyMana";
	
	@RequestMapping(value="/compList/list")
	public String goCompListList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/compList/list";
	}
	
	@RequestMapping(value="/queryList")
	@ResponseBody
	public Map<String, Object> queryList(String companyName,String address,String industry,String createTimeStart,String createTimeEnd,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = companyService.queryForInt(companyName,address,industry,createTimeStart,createTimeEnd);
			List<Company> companyList=companyService.queryList(companyName,address,industry,createTimeStart,createTimeEnd, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", companyList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
}
