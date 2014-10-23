<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：buff系统管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：buff系统管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
		新增buff：
		<s:form action="addBaseBuff" namespace="/admin/skill" method="post" theme="simple">
		<table id="row" style="width:600px;">
		<tr>
		<td>buffID：</td>
		<td><s:textfield name="buffId" /></td>
		</tr>
		<tr>
		<td>buff名称：</td>
		<td><s:textfield name="name" /></td>
		</tr>
		<tr><td>buff图标：</td>
		<td><s:textfield name="icon" /></td>
		</tr>
		<tr>
		<td>buff提示信息：</td>
		<td>
			<s:textfield name="description" />
		</td>
		</tr>
		<tr>
		<td>光效文件名：</td>
		<td><s:textfield name="lightName"/></td>
		</tr>
		<tr>
		<td>属性改变方式：</td>
		<td>
			<s:select list="#{'0':'立即改变','1':'持续改变'}" name="changeType" listKey="key" listValue="value"></s:select>
		</td>
		</tr>
		<tr>
		<td>是否可删除：</td>
		<td>
		<s:select list="#{'0':'可以','1':'不可以'}" name="deleteFlag" listKey="key" listValue="value"></s:select>
		</td>
		</tr>
		<tr>
		<td>buff效果：</td>
		<td><s:textfield name="effect"/></td>
		</tr>
		<tr><td colspan="4"><s:submit value="新增" />
		<input name="Submit2" type="button" value="返 回" onClick="window.open('<%=basePath%>/admin/skill/queryBaseBuff.action','_self');">
		</td></tr>
		</table>
		</s:form>
	</div>
	<div id="divList">
	   
	 </div>
  </body>
</html>
