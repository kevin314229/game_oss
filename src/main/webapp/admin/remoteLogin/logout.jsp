<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
	<head>
		<title>注销</title>
		<%
		   session.invalidate();
		%>
		<script language="javascript">parent.location.href='<%=basePath%>/admin/login.jsp';</script> 
	</head>
	<body bgcolor="#ffffff">
	</body>
</html>
