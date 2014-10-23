<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<b><s:text name="check_the_system_menu" /></b>
		<span class="color-red"><s:text name="warning" />：<s:text name="the_developers_do_not_operate_this_function" /></span>
		<span class="color-gr">
			<s:if test="@com.jjl.rzjh.common.SystemConfig@devMode == false"><s:text name="modify_the_function_has_been_blocked" /></s:if>
		</span>
</div>


	<div class="pageContent">
		
	      <table class="list" width="100%" layoutH="42">
	      	<thead>
	    	<tr>
	    	    <th width="90"><s:text name="level_1_menu" /></th>
	    	    <th><s:text name="the_secondary_menu_list" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="menuAllListTree" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
		    	    	<s:property value="#oneRow.name"/>
		    	    </td>
		    	    <td>
		    	    	<s:iterator value="#oneRow.childOssMenu" var="towRow" status="st">
		    	    		<s:property value="#towRow.name"/> -- [<a href='<%=basePath %>/admin/base/ossMenu_editSecondOssMenuMapping.action?ossMenuID=${towRow.ossMenuID }' style="color:green;text-decoration: none;"><s:text name="management" /></a>]
		    	    		-- <s:text name="route_map" />-> 
		    	    		<s:iterator value="@com.jjl.rzjh.common.JSONService@stringToJSONList(#towRow.pageUrl)" var="threeRow">
		    	    		[<span class="color-red"><s:property value="@com.jjl.rzjh.common.JSONService@getJSONValueByKey(#threeRow,'url')"/></span>
		    	    		 <span class="color-gr"><s:property value="@com.jjl.rzjh.common.JSONService@getJSONValueByKey(#threeRow,'desc')"/></span>]
		    	    		</s:iterator>
		    	    		<br/>
		    	    	</s:iterator>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
