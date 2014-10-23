<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：NPC管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：NPC管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		编辑NPC：
		<s:form action="updateBaseNpc" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:600px;">
		<tr>
		<td>NPCID：</td>
		<td><s:textfield name="npcId" /></td>
		</tr>
		<tr>
		<td>NPC名称：</td>
		<td><s:textfield name="name" /></td>
		</tr>
		<tr><td>模型路径：</td>
		<td><s:textfield name="module" /></td>
		</tr>
		<tr>
		<td>日常对话：</td>
		<td><s:textfield name="dailyTalk" /></td>
		</tr>
		<tr>
		<td>功能代号：</td>
		<td><s:select list="#{'1':'装备店','2':'仓库','3':'杂货店'}" name="functionId" listKey="key" listValue="value"/>
		</td>
		</tr>
		<tr><td colspan="4"><s:submit value="确认修改" />
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryNpc.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
