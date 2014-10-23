<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/changeCSS.jsp"/>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" /> <input
		type="hidden" name="onePageNum" value="${onePageNum}" /> <input
		type="hidden" name="compareDate"
		value="${compareDate}" />

</form>
<div class="pageHeader">
	<s:form action="ZHpayUserLastLoginInfo_index" rel="pagerForm"
		namespace="/zhxy/payUser" method="post" theme="simple"
		class="pageForm required-validate"
		onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		<div class="searchDiv_left">
			<s:text name="statistical_time" />：
			<s:textfield
				id="beginTime" name="compareDate" maxlength="10" size="12"
				cssClass="required date">
				<s:param name="value">
					<s:date name="compareDate" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</div>
		<div class="searchButton2">
			 <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div>
		</div>
		</div>
		
	</s:form>
</div>
<div class="pageContent" layoutH="150" selector="h1">
	<table class="list" width="100%" id="queryLostCount_tb">
		<thead>
			<tr>
				<th align="center"><s:text name="the_date_of" /></th>
				<th align="center"><s:text name="region" /></th>
				<th align="center"><s:text name="account" /></th>
				<th align="center"><s:text name="nickname" /></th>
				<th align="center"><s:text name="grade" /></th>
				<th align="center"><s:text name="the_total_cumulative_recharge" /></th>
				
				
			</tr>
		</thead>
		<tbody>
			<s:iterator value="payUserLastLoginInfoList" var="oneRow" status="offset">
			<tr>
				<td align="center"><s:property value="#oneRow.lastLoginTime" /></td>
				<td align="center">
				<dict:itemvalue gameId="${oneRow.gameId }" type="2"  itemValue="${oneRow.ptArea}"></dict:itemvalue>
				</td>
				<td align="center"><s:property value="#oneRow.loginName" /></td>
				<td align="center"><s:property value="#oneRow.nickName" /></td>
				<td align="center"><s:property value="#oneRow.level" /></td>
				<td align="center"><s:property value="#oneRow.totalPay" /></td>
			
				
				
			</tr>	

</s:iterator>
</tbody>
</table>
</div>