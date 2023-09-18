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
.tab1_div .toolbar .row_div .companyName_span,
.tab1_div .toolbar .row_div .trade_span,
.tab1_div .toolbar .row_div .contactName_span,
.tab1_div .toolbar .row_div .phone_span,
.tab1_div .toolbar .row_div .createTime_span,
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .companyName_inp,
.tab1_div .toolbar .row_div .contactName_inp,
.tab1_div .toolbar .row_div .phone_inp{
	width: 120px;
	height: 25px;
}
</style>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var companyManaPath=path+'companyMana/';
var phonePath=path+'phone/';
$(function(){
	initTradeCBB();
	initCreateTimeStartDTB();
	initCreateTimeEndDTB();
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

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var companyName=$("#toolbar #companyName").val();
			var tradeId=tradeCBB.combobox("getValue");
			var contactName=$("#toolbar #contactName").val();
			var phone=$("#toolbar #phone").val();
			var createTimeStart=createTimeStartDTB.datetimebox("getValue");
			var createTimeEnd=createTimeEndDTB.datetimebox("getValue");
			
			tab1.datagrid("load",{companyName:companyName,tradeId:tradeId,contactName:contactName,phone:phone,
				createTimeStart:createTimeStart,createTimeEnd:createTimeEnd});
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"公司列表",
		url:companyManaPath+"queryList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"name",title:"公司名",width:150},
			{field:"tradeId",title:"行业",width:150},
			{field:"contactName",title:"联系人",width:150},
			{field:"phone",title:"联系方式",width:150},
			{field:"createTime",title:"创建时间",width:150},
            {field:"id",title:"操作",width:150,formatter:function(value,row){
            	var str="<a href=\"detail?id="+value+"\">详情</a>&nbsp;&nbsp;";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{name:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"name",colspan:6});
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
				<span class="companyName_span">公司名：</span>
				<input type="text" class="companyName_inp" id="companyName" placeholder="请输入公司名"/>
				<span class="trade_span">行业：</span>
				<input id="trade_cbb"/>
				<span class="contactName_span">联系人：</span>
				<input type="text" class="contactName_inp" id="contactName" placeholder="请输入联系人"/>
				<span class="phone_span">联系方式：</span>
				<input type="text" class="phone_inp" id="phone" placeholder="请输入联系方式"/>
				<span class="createTime_span">创建时间：</span>
				<input id="createTimeStart_dtb"/>-
				<input id="createTimeEnd_dtb"/>
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