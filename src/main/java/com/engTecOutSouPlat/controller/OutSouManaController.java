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
@RequestMapping("/outSouMana")
public class OutSouManaController {

	@Autowired
	private NeedOutSouService needOutSouService;
	@Autowired
	private ProOutSouService proOutSouService;
	public static final String MODULE_NAME="outSouMana";
	
	@RequestMapping(value="/needOutSou/list")
	public String goNeedOutSouList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/needOutSou/list";
	}
	
	@RequestMapping(value="/proOutSou/list")
	public String goProOutSouList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/proOutSou/list";
	}
	
	@RequestMapping(value="/queryNeedList")
	@ResponseBody
	public Map<String, Object> queryNeedList(String contactName,String phone,String area,String companyName,String enginName,Integer tradeId,String otherTrade,String speciality,String createTimeStart,String createTimeEnd,
			String startDateStart,String startDateEnd,String endDateStart,String endDateEnd,Integer state,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = needOutSouService.queryForInt(contactName,phone,area,companyName,enginName,tradeId,otherTrade,speciality,createTimeStart,createTimeEnd,startDateStart,startDateEnd,endDateStart,endDateEnd,state);
			List<NeedOutSou> needOutSouList=needOutSouService.queryList(contactName,phone,area,companyName,enginName,tradeId,otherTrade,speciality,createTimeStart,createTimeEnd,startDateStart,startDateEnd,endDateStart,endDateEnd,state, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", needOutSouList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryProList")
	@ResponseBody
	public Map<String, Object> queryProList(String contactName,String phone,String area,String companyName,String enginName,String tradeName,String otherTrade,String specialityName,String otherSpeciality,
			String createTimeStart,String createTimeEnd,String startDateStart,String startDateEnd,String endDateStart,String endDateEnd,Integer state,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = proOutSouService.queryForInt(contactName,phone,area,companyName,enginName,tradeName,otherTrade,specialityName,otherSpeciality,createTimeStart,createTimeEnd,startDateStart,startDateEnd,endDateStart,endDateEnd,state);
			List<ProOutSou> proOutSouList=proOutSouService.queryList(contactName,phone,area,companyName,enginName,tradeName,otherTrade,specialityName,otherSpeciality,createTimeStart,createTimeEnd,startDateStart,startDateEnd,endDateStart,endDateEnd,state, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", proOutSouList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
}
