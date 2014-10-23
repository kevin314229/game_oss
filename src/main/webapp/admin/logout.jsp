<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
	<head>
		<%
		   session.invalidate();
		   for(int i=0;i<request.getCookies().length;i++){
		   		request.getCookies()[i].setMaxAge(-1);
		   }
		%>
		<script language="javascript">parent.location.href='<%=basePath%>/admin/login.jsp';</script> 
	</head>
	<body bgcolor="#ffffff">
	</body>
</html>
