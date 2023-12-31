<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
.tab1_div{
	margin-top:80px;
	margin-left: 220px;
	position: fixed;
}
.tab1_div .toolbar{
	height:32px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .username_span,
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .username_inp,
.add_div .username_inp,
.edit_div .username_inp,
.add_div .password_inp,
.add_div .password2_inp,
.add_div .nickName_inp,
.edit_div .nickName_inp{
	width: 120px;
	height: 25px;
}

.add_bg_div,
.edit_bg_div,
.upd_pwd_bg_div{
	width: 100%;
	height: 100%;
	background-color: rgba(0,0,0,.45);
	position: fixed;
	z-index: 9016;
	display:none;
}

.add_div{
	width: 500px;
	height: 350px;
	margin: 150px auto 0;
	background-color: #fff;
	border-radius:5px;
	position: absolute;
	left: 0;
	right: 0;
}
.edit_div,
.upd_pwd_div{
	width: 500px;
	height: 250px;
	margin: 150px auto 0;
	background-color: #fff;
	border-radius:5px;
	position: absolute;
	left: 0;
	right: 0;
}
</style>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript" src="<%=basePath %>resource/js/MD5.js"></script>
<script type="text/javascript">
var path='<%=basePath %>';
var userManaPath=path+'userMana/';
var dialogTop=10;
var dialogLeft=20;
var adddNum=0;
var editdNum=1;
var updNum=2;
$(function(){
	initAddLB();
	initSearchLB();
	initTab1();
	initAddDialog();//0
	initEditDialog();//1
	initUpdPwdDialog();//2
	
	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	var adddpw=$("body").find(".panel.window").eq(adddNum);
	var adddws=$("body").find(".window-shadow").eq(adddNum);
	
	var editdpw=$("body").find(".panel.window").eq(editdNum);
	var editdws=$("body").find(".window-shadow").eq(editdNum);
	
	var updpw=$("body").find(".panel.window").eq(updNum);
	var updws=$("body").find(".window-shadow").eq(updNum);

	var adddDiv=$("#add_div");
	adddDiv.append(adddpw);
	adddDiv.append(adddws);
	
	var editdDiv=$("#edit_div");
	editdDiv.append(editdpw);
	editdDiv.append(editdws);
	
	var updDiv=$("#upd_pwd_div");
	updDiv.append(updpw);
	updDiv.append(updws);
}

function initAddDialog(){
	$("#add_dialog_div").dialog({
		title:"添加用户",
		width:setFitWidthInParent("#add_div","add_dialog_div"),
		height:300,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkAdd();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openAddDialog(false);
           }}
        ]
	});

	$("#add_dialog_div table").css("width",(setFitWidthInParent("#add_div","add_dialog_table"))+"px");
	$("#add_dialog_div table").css("magin","-100px");
	$("#add_dialog_div table td").css("padding-left","40px");
	$("#add_dialog_div table td").css("padding-right","20px");
	$("#add_dialog_div table td").css("font-size","15px");
	$("#add_dialog_div table .td1").css("width","30%");
	$("#add_dialog_div table .td2").css("width","60%");
	$("#add_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(adddNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(adddNum).css("color","#000");
	$(".panel.window .panel-title").eq(adddNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(adddNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(adddNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(adddNum).css("border-color","#ddd");

	$("#add_dialog_div #ok_but").css("left","30%");
	$("#add_dialog_div #ok_but").css("position","absolute");

	$("#add_dialog_div #cancel_but").css("left","50%");
	$("#add_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function initEditDialog(){
	$("#edit_dialog_div").dialog({
		title:"编辑用户",
		width:setFitWidthInParent("#edit_div","edit_dialog_div"),
		height:200,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkEdit();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openEditDialog(false);
           }}
        ]
	});

	$("#edit_dialog_div table").css("width",(setFitWidthInParent("#edit_div","edit_dialog_table"))+"px");
	$("#edit_dialog_div table").css("magin","-100px");
	$("#edit_dialog_div table td").css("padding-left","40px");
	$("#edit_dialog_div table td").css("padding-right","20px");
	$("#edit_dialog_div table td").css("font-size","15px");
	$("#edit_dialog_div table .td1").css("width","30%");
	$("#edit_dialog_div table .td2").css("width","60%");
	$("#edit_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(editdNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(editdNum).css("color","#000");
	$(".panel.window .panel-title").eq(editdNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(editdNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(editdNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(editdNum).css("border-color","#ddd");

	$("#edit_dialog_div #ok_but").css("left","30%");
	$("#edit_dialog_div #ok_but").css("position","absolute");

	$("#edit_dialog_div #cancel_but").css("left","50%");
	$("#edit_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function initUpdPwdDialog(){
	$("#upd_pwd_dialog_div").dialog({
		title:"修改密码",
		width:setFitWidthInParent("#upd_pwd_div","upd_pwd_dialog_div"),
		height:200,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkUpdPwd();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openUpdPwdDialog(false);
           }}
        ]
	});

	$("#upd_pwd_dialog_div table").css("width",(setFitWidthInParent("#upd_pwd_div","upd_pwd_dialog_table"))+"px");
	$("#upd_pwd_dialog_div table").css("magin","-100px");
	$("#upd_pwd_dialog_div table td").css("padding-left","40px");
	$("#upd_pwd_dialog_div table td").css("padding-right","20px");
	$("#upd_pwd_dialog_div table td").css("font-size","15px");
	$("#upd_pwd_dialog_div table .td1").css("width","30%");
	$("#upd_pwd_dialog_div table .td2").css("width","60%");
	$("#upd_pwd_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(updNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(updNum).css("color","#000");
	$(".panel.window .panel-title").eq(updNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(updNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(updNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(updNum).css("border-color","#ddd");

	$("#upd_pwd_dialog_div #ok_but").css("left","30%");
	$("#upd_pwd_dialog_div #ok_but").css("position","absolute");

	$("#upd_pwd_dialog_div #cancel_but").css("left","50%");
	$("#upd_pwd_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			openAddDialog(true);
		}
	});
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var username=$("#toolbar #username").val();
			
			tab1.datagrid("load",{username:username});
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"用户列表",
		url:userManaPath+"queryUserList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"username",title:"用户名",width:150},
			{field:"nickName",title:"昵称",width:150},
			{field:"createTime",title:"创建时间",width:150},
            {field:"id",title:"操作",width:150,formatter:function(value,row){
            	var rowJson = JSON.stringify(row).replace(/"/g, '&quot;');
            	var str="<a onclick=\"openEditDialog(true,"+rowJson+")\">编辑</a>&nbsp;&nbsp;";
            	str+="<a onclick=\"openUpdPwdDialog(true,"+rowJson+")\">修改密码</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{username:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"username",colspan:4});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function openAddDialog(flag){
	if(flag){
		$("#add_bg_div").css("display","block");
	}
	else{
		$("#add_bg_div").css("display","none");
	}
}

function openEditDialog(flag,row){
	if(flag){
		$("#edit_bg_div").css("display","block");
		$("#edit_div #id").val(row.id);
		$("#edit_div #usernameOld").val(row.username);
		$("#edit_div #username").val(row.username);
		$("#edit_div #nickName").val(row.nickName);
	}
	else{
		$("#edit_bg_div").css("display","none");
		$("#edit_div #id").val("");
		$("#edit_div #usernameOld").val("");
		$("#edit_div #username").val("");
		$("#edit_div #nickName").val("");
	}
}

function openUpdPwdDialog(flag,row){
	if(flag){
		$("#upd_pwd_bg_div").css("display","block");
		$("#upd_pwd_div #id").val(row.id);
	}
	else{
		$("#upd_pwd_bg_div").css("display","none");
		$("#upd_pwd_div #id").val("");
	}
}

function checkAdd(){
	if(checkAddUsername()){
		if(checkAddPassword()){
			if(checkAddPassword2()){
				if(checkAddNickName()){
					addUser();
				}
			}
		}
	}
}

function checkEdit(){
	if(checkEditUsername()){
		if(checkEditNickName()){
			editUser();
		}
	}
}

function checkUpdPwd(){
	if(checkUpdPassword()){
		if(checkUpdPassword2()){
			updatePwd();
		}
	}
}

function addUser(){
	var password = $("#add_div #password").val();
	$("#add_div #password").val(MD5(password).toUpperCase());
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:userManaPath+"addUser",
		dataType: "json",
		data:formData,
		cache: false,
		processData: false,
		contentType: false,
		success: function (data){
			if(data.message=="ok"){
				alert(data.info);
				openAddDialog(false);
				tab1.datagrid("load");
			}
			else{
				alert(data.info);
			}
		}
	});
}

function editUser(){
	var formData = new FormData($("#form2")[0]);
	$.ajax({
		type:"post",
		url:userManaPath+"editUser",
		dataType: "json",
		data:formData,
		cache: false,
		processData: false,
		contentType: false,
		success: function (data){
			if(data.message=="ok"){
				alert(data.info);
				openEditDialog(false);
				tab1.datagrid("load");
			}
			else{
				alert(data.info);
			}
		}
	});
}

function updatePwd(){
	var id = $("#upd_pwd_div #id").val();
	var password = MD5($("#upd_pwd_div #password").val()).toUpperCase();
	$.post(userManaPath+"updatePwdById",
		{id:id,password:password},
		function(data){
			if(data.message=="ok"){
				alert(data.info);
				openUpdPwdDialog(false);
				tab1.datagrid("load");
			}
			else{
				alert(data.info);
			}
		}
	,"json");
}

function focusAddUsername(){
	var username = $("#add_div #username").val();
	if(username=="用户名不能为空"||username=="用户名已存在"){
		$("#add_div #username").val("");
		$("#add_div #username").css("color", "#555555");
	}
}

//验证用户名
function checkAddUsername(){
	var flag=false;
	var username = $("#add_div #username").val();
	if(username==null||username==""||username=="用户名不能为空"){
		$("#add_div #username").css("color","#E15748");
    	$("#add_div #username").val("用户名不能为空");
    	flag=false;
	}
	else if(username=="用户名已存在"){
		$("#add_div #username").css("color","#E15748");
    	$("#add_div #username").val("用户名已存在");
    	flag=false;
	}
	else{
		$.ajaxSetup({async:false});
		$.post(userManaPath+"checkUsernameIfExist",
			{username:username,flag:"add"},
			function(data){
				if(data.status==1)
			    	flag=true;
				else{
					$("#add_div #username").css("color","#E15748");
			    	$("#add_div #username").val(data.msg);
			    	flag=false;
				}
			}
		,"json");
	}
	return flag;
}

function focusEditUsername(){
	var username = $("#edit_div #username").val();
	if(username=="用户名不能为空"||username=="用户名已存在"){
		$("#edit_div #username").val("");
		$("#edit_div #username").css("color", "#555555");
	}
}

//验证用户名
function checkEditUsername(){
	var flag=false;
	var username = $("#edit_div #username").val();
	if(username==null||username==""||username=="用户名不能为空"){
		$("#edit_div #username").css("color","#E15748");
    	$("#edit_div #username").val("用户名不能为空");
    	flag=false;
	}
	else if(username=="用户名已存在"){
		$("#edit_div #username").css("color","#E15748");
    	$("#edit_div #username").val("用户名已存在");
    	flag=false;
	}
	else{
		$.ajaxSetup({async:false});
		var usernameOld = $("#edit_div #usernameOld").val();
		$.post(userManaPath+"checkUsernameIfExist",
			{username:username,usernameOld:usernameOld},
			function(data){
				if(data.status==1)
			    	flag=true;
				else{
					$("#edit_div #username").css("color","#E15748");
			    	$("#edit_div #username").val(data.msg);
			    	flag=false;
				}
			}
		,"json");
	}
	return flag;
}

//验证密码
function checkAddPassword(){
	var password = $("#add_div #password").val();
	if(password==null||password==""){
	  	alert("请输入密码");
	  	return false;
	}
	else
		return true;
}

//验证确认密码
function checkAddPassword2(){
	var flag=false;
	var password = $("#add_div #password").val();
	var password2 = $("#add_div #password2").val();
	if(password2==null||password2==""){
	  	alert("请输入确认密码");
		flag=false;
	}
	else if(password!=password2){
	  	alert("两次密码不一致");
		flag=false;
	}
	else
		flag=true;
	return flag;
}

//验证新密码
function checkUpdPassword(){
	var password = $("#upd_pwd_div #password").val();
	if(password==null||password==""){
	  	alert("请输入新密码");
	  	return false;
	}
	else
		return true;
}

//验证确认新密码
function checkUpdPassword2(){
	var flag=false;
	var password = $("#upd_pwd_div #password").val();
	var password2 = $("#upd_pwd_div #password2").val();
	if(password2==null||password2==""){
	  	alert("请输入确认新密码");
		flag=false;
	}
	else if(password!=password2){
	  	alert("两次密码不一致");
		flag=false;
	}
	else
		flag=true;
	return flag;
}

function focusAddNickName(){
	var nickName = $("#add_div #nickName").val();
	if(nickName=="昵称不能为空"){
		$("#add_div #nickName").val("");
		$("#add_div #nickName").css("color", "#555555");
	}
}

//验证昵称
function checkAddNickName(){
	var nickName = $("#add_div #nickName").val();
	if(nickName==null||nickName==""||nickName=="昵称不能为空"){
		$("#add_div #nickName").css("color","#E15748");
    	$("#add_div #nickName").val("昵称不能为空");
    	return false;
	}
	else
		return true;
}

function focusEditNickName(){
	var nickName = $("#edit_div #nickName").val();
	if(nickName=="昵称不能为空"){
		$("#edit_div #nickName").val("");
		$("#edit_div #nickName").css("color", "#555555");
	}
}

//验证昵称
function checkEditNickName(){
	var nickName = $("#edit_div #nickName").val();
	if(nickName==null||nickName==""||nickName=="昵称不能为空"){
		$("#edit_div #nickName").css("color","#E15748");
    	$("#edit_div #nickName").val("昵称不能为空");
    	return false;
	}
	else
		return true;
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "tab1_div":
		space=250;
		break;
	case "add_dialog_div":
	case "edit_dialog_div":
	case "upd_pwd_dialog_div":
		space=50;
		break;
	case "add_dialog_table":
	case "edit_dialog_table":
	case "upd_pwd_dialog_table":
		space=68;
		break;
	case "panel_window":
		space=355;
		break;
	}
	var width=$(parent).css("width");
	return width.substring(0,width.length-2)-space;
}
</script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<%@include file="../../inc/side.jsp"%>
	<div class="tab1_div" id="tab1_div">
		<div class="toolbar" id="toolbar">
			<div class="row_div">
				<span class="username_span">用户名：</span>
				<input type="text" class="username_inp" id="username" placeholder="请输入用户名"/>
				<a class="search_but" id="search_but">查询</a>
				<a class="add_but" id="add_but">添加</a>
			</div>
		</div>
		<table id="tab1">
		</table>
	</div>
	
	<div class="add_bg_div" id="add_bg_div">
		<div class="add_div" id="add_div">
			<div class="add_dialog_div" id="add_dialog_div">
			<form id="form1" name="form1" method="post" enctype="multipart/form-data">
				<table id="add_dialog_table">
				  <tr>
					<td class="td1" align="right">
						用户名
					</td>
					<td class="td2">
						<input type="text" class="username_inp" id="username" name="username" placeholder="请输入用户名" onfocus="focusAddUsername()" onblur="checkAddUsername()"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						密码
					</td>
					<td class="td2">
						<input type="password" class="password_inp" id="password" name="password" placeholder="请输入密码"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						确认密码
					</td>
					<td class="td2">
						<input type="password" class="password2_inp" id="password2" placeholder="请再次输入密码"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						昵称
					</td>
					<td class="td2">
						<input type="text" class="nickName_inp" id="nickName" name="nickName" placeholder="请输入昵称" onfocus="focusAddNickName()" onblur="checkAddNickName()"/>
					</td>
				  </tr>
				</table>
			</form>
			</div>
		</div>
	</div>
	
	<div class="edit_bg_div" id="edit_bg_div">
		<div class="edit_div" id="edit_div">
			<div class="edit_dialog_div" id="edit_dialog_div">
			<form id="form2" name="form2" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id"/>
				<table>
				  <tr>
					<td class="td1" align="right">
						用户名
					</td>
					<td class="td2">
						<input type="hidden" id="usernameOld"/>
						<input type="text" class="username_inp" id="username" name="username" placeholder="请输入用户名" onfocus="focusEditUsername()" onblur="checkEditUsername()"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						昵称
					</td>
					<td class="td2">
						<input type="text" class="nickName_inp" id="nickName" name="nickName" placeholder="请输入昵称" onfocus="focusEditNickName()" onblur="checkEditNickName()"/>
					</td>
				  </tr>
				</table>
			</form>
			</div>
		</div>
	</div>
	
	<div class="upd_pwd_bg_div" id="upd_pwd_bg_div">
		<div class="upd_pwd_div" id="upd_pwd_div">
			<div class="upd_pwd_dialog_div" id="upd_pwd_dialog_div">
			<form id="form2" name="form2" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id"/>
				<table>
				  <tr>
					<td class="td1" align="right">
						新密码
					</td>
					<td class="td2">
						<input type="password" class="password_inp" id="password" placeholder="请输入密码"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						确认新密码
					</td>
					<td class="td2">
						<input type="password" class="password2_inp" id="password2" placeholder="请再次输入新密码"/>
					</td>
				  </tr>
				</table>
			</form>
			</div>
		</div>
	</div>
	
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>