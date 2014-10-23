<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<form rel="pagerForm" method="post" rel="pagerForm" action="<%=basePath%>/admin/message/sendStage_search.action" onsubmit="return dialogSearch(this);">
	<div class="searchBar">
	<div class="searchDiv_left">
		<ul class="searchContent">
			<li style="width:200px">
				<s:hidden name="optType"/>
				<s:text name="name" />：<s:textfield name="nickName"/>
			</li>	  
		</ul>
		</div>
		<div class="searchButton2">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="inquiry" /></button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="list" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="value"><s:text name="goods" />ID</th>
				<th orderfield="name"><s:text name="name" /></th>
				<th orderfield="maxHeap"><s:text name="maximum_stack" /></th>
				<th width="80"><s:text name="find_back" /></th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="optList" var="oneRow"  status="offset">
			<tr>
				<td><s:property value="#oneRow.value"/></td>
				<td><s:property value="#oneRow.name"/></td>
				<td><s:property value="#oneRow.maxHeap"/></td>
				<td><a class="btnSelect" href="javascript:$.bringBack(
					{
						value:'<s:property value="#oneRow.value"/>', 
						name:'<s:property value="#oneRow.name"/>', 
						maxHeap:'<s:property value="#oneRow.maxHeap"/>'
					}
				)" 
				title="<s:text name="find_back" />"><s:text name="select" /></a></td>
			</tr>
		</s:iterator> 
		</tbody>
	</table>
	<s:include value="/admin/template/dialogPaging.jsp"/>
</div>
