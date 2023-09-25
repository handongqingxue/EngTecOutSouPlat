package com.engTecOutSouPlat.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.service.*;
import com.engTecOutSouPlat.util.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

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
	//https证书下载与配置:https://www.tencentcloud.com/zh/document/product/1007/50805
	public static final String SERVICE_URL="https://api.weixin.qq.com/";
	
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

	@RequestMapping(value="/deleteNeedOutSou",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteNeedOutSou(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		PlanResult plan=new PlanResult();
		String json=null;
		try {
			int count=needOutSouService.deleteByIds(ids);
			if(count==0) {
				plan.setStatus(0);
				plan.setMsg("删除需求外包失败");
				json=JsonUtil.getJsonFromObject(plan);
			}
			else {
				plan.setStatus(1);
				plan.setMsg("删除需求外包成功");
				json=JsonUtil.getJsonFromObject(plan);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			plan.setStatus(0);
			plan.setMsg("删除需求外包失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		finally {
			return json;
		}
	}
	
	@RequestMapping(value="/editNeedOutSou")
	@ResponseBody
	public Map<String, Object> editNeedOutSou(NeedOutSou nos) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			int count=needOutSouService.edit(nos);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "编辑需求外包成功");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "编辑需求外包失败");
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

	@RequestMapping(value="/deleteProOutSou",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteProOutSou(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		PlanResult plan=new PlanResult();
		String json=null;
		try {
			int count=proOutSouService.deleteByIds(ids);
			if(count==0) {
				plan.setStatus(0);
				plan.setMsg("删除提供外包失败");
				json=JsonUtil.getJsonFromObject(plan);
			}
			else {
				plan.setStatus(1);
				plan.setMsg("删除提供外包成功");
				json=JsonUtil.getJsonFromObject(plan);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			plan.setStatus(0);
			plan.setMsg("删除提供外包失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		finally {
			return json;
		}
	}
	
	@RequestMapping(value="/editProOutSou")
	@ResponseBody
	public Map<String, Object> editProOutSou(ProOutSou pos) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			int count=proOutSouService.edit(pos);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "编辑提供外包成功");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "编辑提供外包失败");
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

	@RequestMapping(value="/getNOSListByOpenId")
	@ResponseBody
	public Map<String, Object> getNOSListByOpenId(String openId) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			List<NeedOutSou> needOutSouList=needOutSouService.getListByOpenId(openId);
			
			if(needOutSouList.size()==0) {
				jsonMap.put("status", "no");
				jsonMap.put("message", "暂无数据");
			}
			else {
				jsonMap.put("status", "ok");
				jsonMap.put("list", needOutSouList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/getNeedOutSou")
	@ResponseBody
	public Map<String, Object> getNeedOutSou(String id) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		NeedOutSou nos=needOutSouService.selectById(id);
		if(nos==null) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "暂无数据");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("needOutSou", nos);
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/getPOSListByOpenId")
	@ResponseBody
	public Map<String, Object> getPOSListByOpenId(String openId) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			List<ProOutSou> proOutSouList=proOutSouService.getListByOpenId(openId);
			
			if(proOutSouList.size()==0) {
				jsonMap.put("status", "no");
				jsonMap.put("message", "暂无数据");
			}
			else {
				jsonMap.put("status", "ok");
				jsonMap.put("list", proOutSouList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/getProOutSou")
	@ResponseBody
	public Map<String, Object> getProOutSou(String id) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		ProOutSou pos=proOutSouService.selectById(id);
		if(pos==null) {
			jsonMap.put("status", "no");
			jsonMap.put("message", "暂无数据");
		}
		else {
			jsonMap.put("status", "ok");
			jsonMap.put("proOutSou", pos);
		}
		
		return jsonMap;
	}

	@RequestMapping(value="/jscode2session")
	@ResponseBody
	public Map<String, Object> jscode2session(String jsCode, String grantType) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			Map parames = new HashMap<String, String>();
	        parames.put("appid", Constant.APPID);  
	        parames.put("secret", Constant.SECRET);  
	        parames.put("js_code", jsCode);
	        parames.put("grant_type", grantType);  
	        
	        JSONObject resultJO = doHttp("sns/jscode2session",parames);
	        String openId = resultJO.getString("openid");
	        String sessionKey = resultJO.getString("session_key");
	        
	        jsonMap.put("openId", openId);
	        jsonMap.put("sessionKey", sessionKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}

	@RequestMapping(value="/coverToPfx")
	@ResponseBody
	public Map<String, Object> coverToPfx() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		PFXUtil.coverToPfx();
		
		return jsonMap;
	}

	//https://www.cnblogs.com/aeolian/p/7746158.html
	//https://www.cnblogs.com/bobc/p/8809761.html
	public JSONObject doHttp(String method, Map<String, Object> paramMap) throws IOException {
		// 构建请求参数  
        StringBuffer paramsSB = new StringBuffer();
		if (paramMap != null) {  
            for (Entry<String, Object> e : paramMap.entrySet()) {
            	paramsSB.append(e.getKey());  
            	paramsSB.append("=");  
            	paramsSB.append(e.getValue());  
            	paramsSB.append("&");  
            }  
            paramsSB.substring(0, paramsSB.length() - 1);  
        }  
		
		StringBuffer sbf = new StringBuffer(); 
		String strRead = null; 
		URL url = new URL(SERVICE_URL+method);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");//请求post方式
		connection.setDoInput(true); 
		connection.setDoOutput(true); 
		//header内的的参数在这里set    
		//connection.setRequestProperty("key", "value");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect(); 
		
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8"); 
		//OutputStream writer = connection.getOutputStream(); 
		String paramsStr = paramsSB.toString();
		//System.out.println("paramsStr==="+paramsStr);
		writer.write(paramsStr);
		writer.flush();
		InputStream is = connection.getInputStream(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		while ((strRead = reader.readLine()) != null) {
			sbf.append(strRead); 
			sbf.append("\r\n"); 
		}
		reader.close();
		
		connection.disconnect();
		String result = sbf.toString();
		System.out.println("result==="+result);
		JSONObject resultJO = new JSONObject(result);
		return resultJO;
	}
}
