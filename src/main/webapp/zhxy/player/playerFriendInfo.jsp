<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

	<div class="pageContent" layoutH="40">
	

	 <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	    <th width="25%"> <s:text name="role_name" /> </th>
	    	    <th width="25%"> <s:text name="role" />ID </th>
	    	    <th width="25%"> <s:text name="grade" /> </th>
	    	    <th width="25%"> <s:text name="profession" /> </th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="playerFriendInfo" var="oneRow"  status="offset">
	    	<tr>
	    	   <td><s:property value="nickName"/> </td>
	    	   <td><s:property value="playerBaseId"/></td>
	    	   <td><s:property value="level"/></td>
	    	   <td>
	    	    
	    	    <s:if test="#oneRow.occupation == 1"><s:text name="monkey" /> </s:if>
		    	<s:if test="#oneRow.occupation == 2"><s:text name="gods" /></s:if>
		    	<s:if test="#oneRow.occupation == 3"><s:text name="xuannv" /> </s:if>
	    	    
	    	    </td>
	    	</tr>
	    	</s:iterator>
	    	</tbody>
	  </table>
	  
	 </div>
	 
