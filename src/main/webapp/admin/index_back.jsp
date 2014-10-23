<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
  <head>   
    <title>《<s:text name="seal_the_magic" />》<s:text name="operations_management_platform" /></title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	

  </head>
  <FRAMESET rows="60,*,24" frameborder="NO" border="0" framespacing="0"  style="height:100%">
		<FRAME src="top.jsp" name="topFrame" scrolling="no" noresize="noresize"> 
	<FRAMESET name="contentFrameset" cols="220,10,*" border="0" frameborder="0" framespacing="0">
		<FRAME name="leftFrame" target="mainFrame" src="<%=basePath%>/admin/leftMenu.action" scrolling="auto" noresize>
        <frame src="middle.jsp" name="arrowFrame" scrolling="NO" noresize id="arrowFrame">
		<FRAME name="mainFrame" target="mainFrame" src="main.jsp" noresize>
	</FRAMESET>

	<FRAME src="bottom.jsp" scrolling="NO">


</FRAMESET>
  
</html>
