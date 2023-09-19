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
.add_div .password_inp,
.add_div .nickName_inp{
	width: 120px;
	height: 25px;
}

.add_bg_div{
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
</style>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var userManaPath=path+'userMana/';
var dialogTop=10;
var dialogLeft=20;
var adddNum=0;
$(function(){
	initAddLB();
	initSearchLB();
	initTab1();
	initAddDialog();//0
	
	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	var adddpw=$("body").find(".panel.window").eq(adddNum);
	var adddws=$("body").find(".window-shadow").eq(adddNum);

	var adddDiv=$("#add_div");
	adddDiv.append(adddpw);
	adddDiv.append(adddws);
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
        	   checkCphToClient();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openAddDialog(false);
           }}
        ]
	});

	$("#add_dialog_div table").css("width",(setFitWidthInParent("#add_div","input_cph_dialog_table"))+"px");
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
			var contactName=$("#toolbar #contactName").val();
			var phone=$("#toolbar #phone").val();
			var area=$("#toolbar #area").val();
			var companyName=$("#toolbar #companyName").val();
			var enginName=$("#toolbar #enginName").val();
			var tradeName=$("#toolbar #tradeName").val();
			var otherTrade=$("#toolbar #otherTrade").val();
			var speciality=$("#toolbar #speciality").val();
			var createTimeStart=createTimeStartDTB.datetimebox("getValue");
			var createTimeEnd=createTimeEndDTB.datetimebox("getValue");
			var startDateStart=startDateStartDB.datebox("getValue");
			var startDateEnd=startDateEndDB.datebox("getValue");
			var endDateStart=endDateStartDB.datebox("getValue");
			var endDateEnd=endDateEndDB.datebox("getValue");
			var state=stateCBB.combobox("getValue");
			
			tab1.datagrid("load",{contactName:contactName,phone:phone,area:area,companyName:companyName,enginName:enginName,
				tradeName:tradeName,otherTrade:otherTrade,speciality:speciality,createTimeStart:createTimeStart,createTimeEnd:createTimeEnd,
				startDateStart:startDateStart,startDateEnd:startDateEnd,endDateStart:endDateStart,endDateEnd:endDateEnd,state:state});
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
            	var str="<a href=\"detail?id="+value+"\">编辑</a>&nbsp;&nbsp;";
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

function focusAddUsername(){
	var username = $("#add_div #username").val();
	if(username=="用户名不能为空"){
		$("#add_div #username").val("");
		$("#add_div #username").css("color", "#555555");
	}
}

//验证用户名
function checkAddUsername(){
	var username = $("#add_div #username").val();
	if(username==null||username==""||username=="用户名不能为空"){
		$("#add_div #deveTool").css("color","#E15748");
    	$("#add_div #deveTool").val("用户名不能为空");
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
		space=50;
		break;
	case "input_cph_dialog_table":
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
				<table>
				  <tr>
					<td class="td1" align="right">
						用户名
					</td>
					<td class="td2">
						<input type="text" class="username_inp" id="username" placeholder="请输入用户名" onfocus="focusAddUsername()" onblur="checkAddUsername()"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						密码
					</td>
					<td class="td2">
						<input type="password" class="password_inp" id="password" placeholder="请输入密码"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						确认密码
					</td>
					<td class="td2">
						<input type="password" class="password_inp" id="password" placeholder="请再次输入密码"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						昵称
					</td>
					<td class="td2">
						<input type="text" class="nickName_inp" id="nickName" placeholder="请输入昵称"/>
					</td>
				  </tr>
				</table>
			</div>
		</div>
	</div>
	
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>