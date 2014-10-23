<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>错误提示</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>
	
	<script src="<%=basePath%>/media/default/js/jquery.ui.core.js"></script>
	<script src="<%=basePath%>/media/default/js/jquery.ui.widget.js"></script>
	<script src="<%=basePath%>/media/default/js/jquery.ui.datepicker.js"></script>
	<script src="<%=basePath%>/media/default/js/jquery.pager.js"  type="text/javascript"></script>
	<script type="text/javascript" language="javascript">
	
	</script>
  </head>
  
  <body>
  	<div id="divLeft">
		<span class="color-red"></span>
		<span class="color-gr"></span>
	</div>
	
	<div id="divLeft">
		异常: <s:debug></s:debug>
		<s:property value="exception"/>
		
	</div>
	<table>
	<tr>
	<td rowspan="1"></td><td></td>
	<td></td>
	<td colspan="1"></td>
	<td colspan="1"></td>
	</tr>
	
	</table>
	
	
  </body>
</html>
