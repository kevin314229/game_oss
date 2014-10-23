<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：跑环任务管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：跑环任务管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		新增跑环任务：
		<s:form action="addBaseTaskRepeat" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:600px;">
		<tr>
		<td>循环任务ID：</td>
		<td><s:textfield name="repeatId" /></td>
		</tr>
		<tr><td>每环任务数：</td>
		<td><s:textfield name="taskNumber" /></td>
		</tr>
		<tr>
		<td>清空类型：</td>
		<td><s:select list="#{'0':'每天','1':'周一','2':'周二','3':'周三','4':'周四','5':'周五','6':'周六','7':'周日'}" name="clearType" listKey="key" listValue="value"/>
		</td>
		</tr>
		<tr>
		<td>等级限制：</td>
		<td><s:textfield name="level"/></td>
		</tr>
		<tr><td colspan="4"><s:submit value="新增" />
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseTaskRepeat.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
