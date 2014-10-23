<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title><s:text name="online_administrator_list" /></title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>
	
	<script src="<%=basePath%>/media/default/js/jquery.pager.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/public.js"  type="text/javascript"></script>
	<script type="text/javascript" language="javascript">
		function deleteOssUser(sessionID) {
			if (confirm("<s:text name="are_you_sure_you_get_the_librarian_to_offline" />？")){
				self.location = "<%=basePath %>/admin/base/ossUser_kickOssUser.action?sessionID="+sessionID;
			}
		}
	</script>
  </head>
  
  <body>

  	<div id="divLeft">
		<b><s:text name="online_administrator_list" /></b>
		<span class="color-red"><s:property value="actionMsg" escape="false"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	<div id="divLeft">
       
	</div>


	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th><s:text name="the_administrator_account" /></th>
	    	    <th><s:text name="real-name" /></th>
	    	    <th>SessionID</th>
	    	    <th><s:text name="creation_time" /></th>
	    	    <th><s:text name="expiration_time" /></th>
	    	    <th>IP<s:text name="address" /></th>
	    	    <th><s:text name="log_on_to_way" /></th>
	    	    <th><s:text name="operation" /></th>
	    	</tr>
	    	<s:iterator value="adminOnlineSessionMap.keySet()" var="oneRow"  status="offset">
	    		<tr >
		    	   <td><s:property value="adminOnlineSessionMap.get(#oneRow).getAttribute(@com.jjl.rzjh.admin.constant.AdminSystemConstant@ADMIN_SYSTEM_SESSION_KEY).getOssUser().getUsername()"/> </td>
		    	   <td><s:property value="adminOnlineSessionMap.get(#oneRow).getAttribute(@com.jjl.rzjh.admin.constant.AdminSystemConstant@ADMIN_SYSTEM_SESSION_KEY).getOssUser().getRealnames()"/> </td>
		    	   <td><s:property value="#oneRow"/> </td>
		    	   <td>
		    	   	 	<s:bean name="java.util.Date" var="d1"><s:param name="time" value="adminOnlineSessionMap.get(#oneRow).getCreationTime()"/></s:bean>
		    	    	<s:bean name="java.util.Date" var="nowdate"/>
		    	   	  	<s:property value="@com.jjl.rzjh.common.DateService@parseDateToString(#d1)"/>
		    	   	  	[<span class="color-gr"><s:text name="online" /><s:property value="@com.jjl.rzjh.common.DateService@MinuteBetween(#d1,#nowdate)"/><s:text name="minutes" /></span>]
		    	   </td>
		    	   <td> 
		    	   		<s:bean name="java.util.Date" var="d2"><s:param name="time" value="adminOnlineSessionMap.get(#oneRow).getLastAccessedTime() + adminOnlineSessionMap.get(#oneRow).getMaxInactiveInterval()*1000 "/> </s:bean>
		    	   		<s:property value="@com.jjl.rzjh.common.DateService@parseDateToString(#d2)"/>
		    	   		[<s:text name="there_are" /><span class="color-red"><s:property value="@com.jjl.rzjh.common.DateService@MinuteBetween(#nowdate,#d2)"/></span><s:text name="minutes_after_that_date" />]
		    	   </td>
		    	   <td>
		    	   		<s:property value="adminOnlineSessionMap.get(#oneRow).getAttribute(@com.jjl.rzjh.admin.constant.AdminSystemConstant@ADMIN_SYSTEM_SESSION_KEY).getOssUser().getLastLoginIp()"/>
		    	   </td>
		    	   <td>
		    	   		<s:if test="adminOnlineSessionMap.get(#oneRow).getAttribute(@com.jjl.rzjh.admin.constant.AdminSystemConstant@ADMIN_SYSTEM_SESSION_KEY).getSessionId() == null"><s:text name="the_local_login" /></s:if>
		    	   		<s:else><s:text name="the_control_center_to_log_in" /></s:else>
		    	   </td>
		    	    <td> 
		    	    	<a href="javascript:deleteOssUser('${oneRow }')" style="color:green"><s:text name="kick_off" /></a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
   <div id="bottomPager"></div>
<s:if test='#request.actionMsg !=null && #request.actionMsg !="" '>
	<script language="javascript">
		alert("<s:property value="actionMsg" escape="false"/>");
	</script>
</s:if>	
  </body>
</html>
