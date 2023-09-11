package com.engTecOutSouPlat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.service.*;

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
}
