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
	height:96px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .contactName_span,
.tab1_div .toolbar .row_div .phone_span,
.tab1_div .toolbar .row_div .area_span,
.tab1_div .toolbar .row_div .companyName_span,
.tab1_div .toolbar .row_div .enginName_span,
.tab1_div .toolbar .row_div .trade_span,
.tab1_div .toolbar .row_div .otherTrade_span,
.tab1_div .toolbar .row_div .speciality_span,
.tab1_div .toolbar .row_div .createTime_span,
.tab1_div .toolbar .row_div .startDate_span,
.tab1_div .toolbar .row_div .endDate_span,
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .contactName_inp,
.tab1_div .toolbar .row_div .phone_inp,
.tab1_div .toolbar .row_div .area_inp,
.tab1_div .toolbar .row_div .companyName_inp,
.tab1_div .toolbar .row_div .enginName_inp,
.tab1_div .toolbar .row_div .otherTrade_inp,
.tab1_div .toolbar .row_div .speciality_inp{
	width: 120px;
	height: 25px;
}
</style>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var outSouManaPath=path+'outSouMana/';
var phonePath=path+'phone/';
var tradeList;
$(function(){
	initTradeCBB();
	initCreateTimeStartDTB();
	initCreateTimeEndDTB();
	initStartDateStartDB();
	initStartDateEndDB();
	initEndDateStartDB();
	initEndDateEndDB();
	initStateCBB();
	initSearchLB();
	initTab1();
});

function initTradeCBB(){
	var data=[];
	data.push({"value":"","text":"请选择"});
	$.post(phonePath+"initTradeCBBData",
		function(result){
			if(result.message=="ok"){
				tradeList=result.tradeList;
				for(var i=0;i<tradeList.length;i++){
					var trade=tradeList[i];
					data.push({"value":trade.id,"text":trade.name});
				}
				
				tradeCBB=$("#trade_cbb").combobox({
					valueField:"value",
					textField:"text",
					//multiple:true,
					data:data
				});
			}
		}
	,"json");
}

function getTradeNameById(tradeId){
	var tradeName;
	for(var i=0;i<tradeList.length;i++){
		var trade=tradeList[i];
		if(tradeId==trade.id){
			tradeName=trade.name;
			break;
		}
	}
	return tradeName;
}

function initCreateTimeStartDTB(){
	createTimeStartDTB=$("#createTimeStart_dtb").datetimebox({
        required:false
    });
}

function initCreateTimeEndDTB(){
	createTimeEndDTB=$("#createTimeEnd_dtb").datetimebox({
        required:false
    });
}

function initStartDateStartDB(){
	startDateStartDB=$("#startDateStart_db").datebox({
        required:false
    });
}

function initStartDateEndDB(){
	startDateEndDB=$("#startDateEnd_db").datebox({
        required:false
    });
}

function initEndDateStartDB(){
	endDateStartDB=$("#endDateStart_db").datebox({
        required:false
    });
}

function initEndDateEndDB(){
	endDateEndDB=$("#endDateEnd_db").datebox({
        required:false
    });
}

function initStateCBB(){
	var data=[];
	data.push({"value":"","text":"请选择"});
	data.push({"value":"1","text":"未外包"});
	data.push({"value":"2","text":"已外包"});
	
	stateCBB=$("#state_cbb").combobox({
		valueField:"value",
		textField:"text",
		//multiple:true,
		data:data
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
		title:"提供外包列表",
		url:outSouManaPath+"queryProList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"contactName",title:"联系人姓名",width:150},
			{field:"phone",title:"电话",width:150},
			{field:"area",title:"地区",width:150},
			{field:"companyName",title:"公司",width:150},
			{field:"enginName",title:"工程名",width:150},
			{field:"tradeId",title:"行业",width:150,formatter:function(value,row){
            	return getTradeNameById(value);
            }},
			{field:"otherTrade",title:"其他行业",width:150},
			{field:"speciality",title:"特长",width:150},
			{field:"createTime",title:"创建时间",width:150},
			{field:"startDate",title:"开始日期",width:150},
			{field:"endDate",title:"结束日期",width:150},
            {field:"state",title:"状态",width:100,formatter:function(value,row){
            	var stateName;
            	switch (value) {
				case 1:
					stateName="未外包";
					break;
				case 2:
					stateName="已外包";
					break;
				}
            	return stateName;
            }},
            {field:"id",title:"操作",width:150,formatter:function(value,row){
            	var str="<a href=\"detail?id="+value+"\">详情</a>&nbsp;&nbsp;";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{contactName:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"contactName",colspan:13});
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
				<span class="contactName_span">联系人姓名：</span>
				<input type="text" class="contactName_inp" id="contactName" placeholder="请输入联系人姓名"/>
				<span class="phone_span">电话：</span>
				<input type="text" class="phone_inp" id="phone" placeholder="请输入电话"/>
				<span class="area_span">地区：</span>
				<input type="text" class="area_inp" id="area" placeholder="请输入地区"/>
				<span class="companyName_span">公司：</span>
				<input type="text" class="companyName_inp" id="companyName" placeholder="请输入公司"/>
				<span class="enginName_span">工程名：</span>
				<input type="text" class="enginName_inp" id="enginName" placeholder="请输入工程名"/>
				<span class="trade_span">行业：</span>
				<input id="trade_cbb"/>
			</div>
			<div class="row_div">
				<span class="otherTrade_span">其他行业：</span>
				<input type="text" class="otherTrade_inp" id="otherTrade" placeholder="请输入其他行业"/>
				<span class="speciality_span">特长：</span>
				<input type="text" class="speciality_inp" id="speciality" placeholder="请输入特长"/>
				<span class="createTime_span">创建时间：</span>
				<input id="createTimeStart_dtb"/>-
				<input id="createTimeEnd_dtb"/>
				<span class="startDate_span">开始日期：</span>
				<input id="startDateStart_db"/>-
				<input id="startDateEnd_db"/>
			</div>
			<div class="row_div">
				<span class="endDate_span">结束日期：</span>
				<input id="endDateStart_db"/>-
				<input id="endDateEnd_db"/>
				<span class="state_span">状态：</span>
				<input id="state_cbb"/>
				<a class="search_but" id="search_but">查询</a>
			</div>
		</div>
		<table id="tab1">
		</table>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>