<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<li>
	       	 <a class="add"  width="740" height="490" target="dialog"  href="<%=basePath%>/admin/base/ossUser_addOssUserIndex.action" ><span><s:text name="new" /> <s:text name="increase" /></span></a>
			</li>
		</ul>
	</div>
	
	    <table class="list" width="100%" layoutH="42" style="table-layout: fixed;">
	    	<thead>
		    	<tr>
		    	    <th><s:text name="account" /></th>
		    	    <th><s:text name="last_login_time" /></th>
		    	    <th><s:text name="the_last_login" />IP</th>
		    	    <th><s:text name="the_login_number" /></th>
		    	    <th><s:text name="creation_time" /></th>
		    	    <th><s:text name="real-name" /></th>
		    	    <th><s:text name="state" /></th>
		    	    <th><s:text name="operators_administrator" /></th>
		    	    <th><s:text name="assigned_roles" /></th>
		    	    <th><s:text name="operation" /></th>
		    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossUserList" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
		    	    	<s:property value="#oneRow.username"/>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#oneRow.lastLoginTime==null"><s:text name="never_login" /></s:if>
		    	    	<s:else>
		    	    		<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.lastLoginTime"/>
		    	    	</s:else>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.lastLoginIp"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.loginNum"/>
		    	    </td>
		    	    <td>
		    	   		 <s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.createTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.realnames"/>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#oneRow.status==1"><span class="color-gr"><s:text name="normal" /></span></s:if>
		    	    	<s:if test="#oneRow.status==0"><span class="color-red"><s:text name="suspended" /></span></s:if>
		    	    </td>
		    	    <td width="23px">
		    	    	<s:if test="#oneRow.isOperator==1"><span class="color-gr"><s:text name="is" /></span></s:if>
		    	    </td>
		    	     <td>
		    	    	<s:if test="#oneRow.ossRoleList!=null">
		    	    		<s:iterator value="#oneRow.ossRoleList" var="obj">
		    	    			<p class="color-gr"><s:property value="#obj.roleName"/></p>
		    	    		</s:iterator>
		    	    	</s:if>
		    	    </td>
		    	    <td> 
		    	    	<a class="btnAttach" title="<s:text name="setting_server" />" width="740" height="490" target="dialog" href="<%=basePath %>/admin/base/ossUser_addOssServerOperationIndex.action?username=${oneRow.username }" style="color:green"><s:text name="setting_server" /></a>
		    	    	<a class="btnEdit" width="740" height="490" target="dialog" href="<%=basePath %>/admin/base/ossUser_editOssUser.action?username=${oneRow.username }" style="color:green"><s:text name="modify_the" /></a>
		    	    	<a class="btnDel" href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_the_administrator" />？',
		    	      'url':'<%=basePath %>/admin/base/ossUser_delOssUser.action','data':{'username': '${oneRow.username }'}});"><s:text name="delete" /></a>
		    	    </td>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
</div>