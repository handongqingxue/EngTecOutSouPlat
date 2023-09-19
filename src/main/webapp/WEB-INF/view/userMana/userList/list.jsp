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
.tab1_div .toolbar .row_div .username_inp{
	width: 120px;
	height: 25px;
}
</style>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var userManaPath=path+'userMana/';
$(function(){
	initAddLB();
	initSearchLB();
	initTab1();
});

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
		
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

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "tab1_div":
		space=250;
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
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>