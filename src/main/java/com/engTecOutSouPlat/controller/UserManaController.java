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
@RequestMapping("/userMana")
public class UserManaController {

	@Autowired
	private UserService userService;
	public static final String MODULE_NAME="userMana";
	
	@RequestMapping(value="/userList/list")
	public String goUserListList(HttpServletRequest request) {
		
		//publicService.selectNav(request);
		
		return MODULE_NAME+"/userList/list";
	}
	
	@RequestMapping(value="/addUser")
	@ResponseBody
	public Map<String, Object> addUser(User user) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			int count=userService.add(user);
			if(count>0) {
				jsonMap.put("message", "ok");
				jsonMap.put("info", "添加用户成功");
			}
			else {
				jsonMap.put("message", "no");
				jsonMap.put("info", "添加用户失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return jsonMap;
		}
	}
	
	@RequestMapping(value="/queryUserList")
	@ResponseBody
	public Map<String, Object> queryUserList(String username,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = userService.queryForInt(username);
			List<User> userList=userService.queryList(username, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", userList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
}
