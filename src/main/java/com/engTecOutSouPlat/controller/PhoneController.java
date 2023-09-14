package com.engTecOutSouPlat.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.service.*;
import com.engTecOutSouPlat.util.*;

@Controller
@RequestMapping("/phone")
public class PhoneController {

	@Autowired
	private NeedOutSouService needOutSouService;
	@Autowired
	private WXUserService wxUserService;
	@Autowired
	private ProOutSouService proOutSouService;
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value="/addOrEditWXUser")
	@ResponseBody
	public Map<String, Object> addOrEditWXUser(WXUser wxu) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			int count=wxUserService.addOrEdit(wxu);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "添加微信用户成功");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "添加微信用户失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}
	
	@RequestMapping(value="/submitNeedOutSou")
	@ResponseBody
	public Map<String, Object> submitNeedOutSou(NeedOutSou nos) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			int count=needOutSouService.add(nos);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "添加需求外包成功");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "添加需求外包失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}
	
	@RequestMapping(value="/submitProOutSou")
	@ResponseBody
	public Map<String, Object> submitProOutSou(ProOutSou pos) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			int count=proOutSouService.add(pos);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "添加提供外包成功");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "添加提供外包失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}
	
	@RequestMapping(value="/submitCompany")
	@ResponseBody
	public Map<String, Object> submitCompany(Company comp) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			int count=companyService.add(comp);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "添加公司成功");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "添加公司失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}
	
	@RequestMapping(value="/editCompany")
	@ResponseBody
	public Map<String, Object> editCompany(Company comp) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			int count=companyService.edit(comp);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "编辑公司成功");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "编辑公司失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}
	
	@RequestMapping(value="/getCompanyByOpenId")
	@ResponseBody
	public Map<String, Object> getCompanyByOpenId(String openId) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			Company company=companyService.getByOpenId(openId);
			if(company==null) {
				jsonMap.put("message", "no");
				jsonMap.put("info", "暂无公司");
			}
			else {
				jsonMap.put("message", "ok");
				jsonMap.put("company", company);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}
	
	@RequestMapping(value="/checkCompIfExist")
	@ResponseBody
	public Map<String, Object> checkCompIfExist(String openId) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			boolean exist=companyService.checkIfExist(openId);
			if(exist) {
				jsonMap.put("exist", true);
				jsonMap.put("message", "用户已添加公司");
			}
			else {
				jsonMap.put("exist", false);
				jsonMap.put("message", "用户未添加公司");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}

	@RequestMapping(value="/initTradeCBBData")
	@ResponseBody
	public Map<String, Object> initTradeCBBData() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> tradeMapList=new ArrayList<Map<String, Object>>();
		Map<String, Object> tradeMap=null;
		for (int i = 0; i < Constant.TRADE_NAME_ARR.length; i++) {
			tradeMap=new HashMap<String, Object>();
			tradeMap.put("id", i+1);
			tradeMap.put("name", Constant.TRADE_NAME_ARR[i]);
			
			tradeMapList.add(tradeMap);
		}
		
		if(tradeMapList.size()==0) {
			jsonMap.put("message", "no");
			jsonMap.put("info", "暂无行业");
		}
		else {
			jsonMap.put("message", "ok");
			jsonMap.put("tradeList", tradeMapList);
		}
		
		return jsonMap;
	}
}
