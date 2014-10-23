<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<title>页脚</title>
<link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
<base target="mainFrame">
</head>

<body bgcolor="#FFCC00">

<table width="100%" height="24"  border="0" cellspacing="0" cellpadding="0" id="footer">
  <tr>
    <td valign="middle"><span>web996.net 版权所有</span></td>
  </tr>
</table>

</body>
</html>