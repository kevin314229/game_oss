<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="list_of_items" /></title>
<script type="text/javascript">
function insertProject(id) {
	self.location ="<%=basePath %>/admin/selectOssPorject.action?id="+id;
}

</script>
</head>
<body style="text-align: center;">

<div style="width: 800px;overflow: hidden;margin: 0px auto;">
		<h1><s:text name="please_select_an_item" /></h1> <br><br>
		<div >
		<s:iterator value="projectList"  var="oneRow"  status="offset">
			<div style="font-size: 55px;width: 50%;float: left;margin: 10px 0px;min-width:390px">  &nbsp; <a href="javascript:insertProject('${oneRow.projectId}')" style="color:green;text-decoration:none;"><s:property value="projectName"/> </a>&nbsp; </div>
		</s:iterator>
		</div>
</div>

</body>
</html>