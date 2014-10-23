<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>没有权限</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
  </head>
  
  <body > 
  	<div id="divLeft">
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft"> 
	
	</div>
	<div id="topPager"> </div>
	<div id="divList"> 
	    <img src="<%=basePath %>/media/default/images/sorry.gif" />
	 </div>
	 <div align="center" >
	 <b>你没有操作此功能的权限，请联系超级管理员</b>
	 </div>
   <div id="bottomPager"></div>
 
  </body>
</html>
