<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

	<FRAMESET name="contentFrameset" cols="200,10,*" border="0" frameborder="0" framespacing="0">
		<FRAME name="leftFrame" target="mainFrame" src="<%=basePath %>/admin/left.jsp" scrolling="auto" noresize>
        <frame src="<%=basePath %>/admin/middle.jsp" name="arrowFrame" scrolling="NO" noresize id="arrowFrame">
		<FRAME name="mainFrame" target="mainFrame" src="<%=basePath %>/admin/main.jsp" noresize>
	</FRAMESET> 

