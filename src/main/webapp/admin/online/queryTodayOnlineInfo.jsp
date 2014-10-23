<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		   <div class="searchBar">
		    <table class="searchContent">
				    <tr>
						<td>
							<s:text name="the_last_time_login_players" />：(ID:<s:property value="lastLoginPlayerID"/> <s:text name="the_role_of" />:<s:property value="lastLoginPlayerName"/> ,
							<s:text name="account" />:<s:property value="lastLoginUsername"/> ,<s:text name="the_login_time" />:<s:date format="yyyy-MM-dd HH:mm:ss" name="lastLoginTime"/>)
						</td>
						<td>
							<s:text name="the_current_system_time" />：<s:date format="yyyy-MM-dd HH:mm:ss" name="systemTime"/>
						</td>
					</tr>
			</table>
			</div>
	</div>
	
	

	<div class="pageContent">
	    <table class="list" width="100%" layoutH="36" >
	    <thead>
    	<tr>
    	    <th><s:text name="statistical_time" /></th>
    	    <th><s:text name="real-time_online_number" /></th>
    	</tr>
    	</thead>
    	<tbody>
    	<s:iterator value="dataHistoryList" var="oneRow"  status="offset">
    		<tr  >
	    	    <td>
	    	    	<s:date format="yyyy-MM-dd HH:mm" name="#oneRow.dataHistoryTimeByID"/>
	    	    </td>
	    	    <td>
	    	    	<s:property value="#oneRow.onlinePlayerNum"/>
	    	    </td>
	    	</tr>
    	</s:iterator>
    	</tbody>
    </table>
 </div>
   
