<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：循环任务管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：循环任务管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		新增循环任务：
		<s:form action="addBaseTaskLoop" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:600px;">
		<tr>
		<td>环ID：</td>
		<td><s:textfield name="loopId" /></td>
		</tr>
		<tr>
		<td>环类型：</td>
		<td>
 		<s:select list="#{'1':'跑环','2':'循环'}" name="loopType" listKey="key" listValue="value"/>
		</td>
		</tr>
		<tr>
		<td>环可接次数：</td>
		<td><s:textfield name="recvNumber" /></td>
		</tr>
		<tr><td>环任务数：</td>
		<td><s:textfield name="taskNumber" /></td>
		</tr>
		<tr>
		<td>环奖励公式：</td>
		<td><s:textfield name="reward"/></td>
		</tr>
		<tr>
		<td>环掉落ID：</td>
		<td><s:textfield name="dropId"/></td>
		</tr>
		<tr>
		<td>最低等级限制：</td>
		<td><s:textfield name="minLevel"/></td>
		</tr>
		<tr>
		<td>最高等级限制：</td>
		<td><s:textfield name="maxLevel"/></td>
		</tr>
		
		<tr>
		<td>门派限制：</td>
		<td><s:textfield name="school"/></td>
		</tr>
		<tr>
		<td>可接时间段：</td>
		<td><s:textfield name="recvTime"/></td>
		</tr>
		<tr>
		<td>环可接日期：</td>
		<td><s:textfield name="recvDate"/></td>
		</tr>
		<tr>
		<td>清空时间点：</td>
		<td><s:textfield name="clearTime"/></td>
		</tr>
		<tr>
		<td>清空周期：</td>
		<td><s:select list="#{'0':'每天','1':'周一','2':'周二','3':'周三','4':'周四','5':'周五','6':'周六','7':'周日'}" name="clearType" listKey="key" listValue="value"/>
		</td>
		</tr>
		<tr>
		<td>任务ID库：</td>
		<td><s:textarea name="taskIdList" cols="50" rows="5"/></td>
		</tr>
		<tr><td colspan="4"><s:submit value="新增" />
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseTaskLoop.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
