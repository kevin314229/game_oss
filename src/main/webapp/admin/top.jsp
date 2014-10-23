<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.jcwx.game.admin.constant.AdminSystemConstant;"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
<title>-OSS</title>
<link href="<%=basePath%>/media/default/css/default.css"
	rel="stylesheet" type="text/css">

<script src="<%=basePath%>/media/default/js/jquery.pager.js"  type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/public.js"  type="text/javascript"></script>
<script language="JavaScript">
	function selectServer(id) {
		window.parent.location.href = "<%=basePath %>/admin/selectOssServer.action?id="+id; 
	}
	
	function selectServerPt(id) {
		window.parent.location.href = "<%=basePath %>/admin/selectOssServerPt.action?serverCode="+id; 
	}
	
	
	function closeWindow(){
		if(confirm('您确定要退出系统吗?')){
	  		//window.top.close();
	  		 window.parent.close()
	  	}
	}
	function logout(){
		if(confirm('您确定要注销登录吗?')){
			window.top.location.href='<%=basePath%>/admin/logout.action';
		}
	}
</script>
<style>
body{
font-size:17px;
}
a{
text-decoration:none;
color: blue;
}
</style>


<base target="leftFrame">
</head>
<body background="<%=basePath%>/media/default/images/topBg.gif" style="min-width:800px">
	<table width="99%" height="100%" border="0" cellspacing="0"
		cellpadding="0" align="center" style="margin:0px">
		<tr>
			<td rowspan="2" valign="middle" align="left" class="topinfo">封魔运营管理系统</td>
			<td align="right" height="30" class="topwelcome">您好！ <s:if
					test="#session.ADMIN_SYSTEM_USER_NAME!=null">
					<s:property
						value="#session.ADMIN_SYSTEM_USER_NAME.ossUser.username" />
				</s:if> ，欢迎登录《封魔》运营管理平台！第<s:property
					value="#session.ADMIN_SYSTEM_USER_NAME.ossUser.loginNum" />次登录系统！
			</td>


		</tr>
		<tr>
			<td align="right" height="30">
			<s:if test="#session.ADMIN_SYSTEM_USER_NAME.ossUser.isOperator==0">
			平台入口:
			<select onchange="selectServerPt(this.value)">
				<s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossServersPt" var="oneRow"  status="offset">
					<s:if test="#session.ADMIN_SYSTEM_USER_NAME.serverCode == #oneRow.serverCode">
					<option value="<s:property value="#oneRow.serverCode" />" selected> 	
	    				<s:property value="#oneRow.serverProvider"/>
	    			</option>
					</s:if> 
					<s:else>
					<option value="<s:property value="#oneRow.serverCode" />"  > 	
	    				<s:property value="#oneRow.serverProvider"/> 
	    			</option>
					</s:else>
	    		</s:iterator>
			</select> 
			<%-- <select onchange="selectServerPt(this.value)">
				<s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossOperationList" var="oneRow"  status="offset">
					<s:if test="#session.ADMIN_SYSTEM_USER_NAME.serverCode == #oneRow.carrierOperator">
					<option value="<s:property value="#oneRow.carrierOperator" />" selected> 	
	    				<s:property value="#oneRow.operationName"/>
	    			</option>
					</s:if> 
					<s:else>
					<option value="<s:property value="#oneRow.carrierOperator" />"  > 	
	    				<s:property value="#oneRow.operationName"/> 
	    			</option>
					</s:else>
	    		</s:iterator>
			</select> --%>
			&nbsp; 服务器:
			<select onchange="selectServer(this.value)" >
				<s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossServers" var="oneRow"  status="offset">
					<s:if test="#session.ADMIN_SYSTEM_USER_NAME.serverId == #oneRow.id">
					<option value="<s:property value="#oneRow.id" />" selected> 	
	    				<s:property value="#oneRow.name"/>
	    			</option>
					</s:if>
					<s:elseif test="#session.ADMIN_SYSTEM_USER_NAME.serverCode==#oneRow.serverCode">
					<option value="<s:property value="#oneRow.id" />"  > 	
	    				<s:property value="#oneRow.name"/>
	    			</option>
					</s:elseif>
	    		</s:iterator>
			</select>
			</s:if>
			
			&nbsp;&nbsp;
			<a href="javascript:self.parent.frames['topFrame'].logout();">注销登录</a>
			<a href="javascript:self.parent.frames['topFrame'].closeWindow();">退出</a></td>
		</tr>
	</table>
</body>
</html>
