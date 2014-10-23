<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.jcwx.game.admin.constant.AdminSystemConstant;"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<div class="pageContent">
    <table width="100%" height="100%;">
    	<tr>
    		<td align="center" valign="middle">
	    		<h1>管理员，您好，欢迎来到《<s:property value="#session.ADMIN_SYSTEM_USER_NAME.project.projectName"/> 》第<s:property value="#session.ADMIN_SYSTEM_USER_NAME.serverId"/>服务器管理平台！
	    		</h1> 
	    		<h1>当前运营商是<s:property value="#session.ADMIN_SYSTEM_USER_NAME.operationName"/></h1>
	    		<h1>当前服务区是<s:property value="#session.ADMIN_SYSTEM_USER_NAME.name"/></h1>
    		</td>
    	</tr>
    </table>
</div>