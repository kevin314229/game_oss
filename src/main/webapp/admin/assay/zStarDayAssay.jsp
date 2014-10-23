<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>





	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form action="zStarDay_index" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		
			<div class="searchDiv_left" > 
					<s:text name="statistical_time" />：<s:textfield id="beginTime" name="beginTime" maxlength="10" size="12" cssClass="required date">
						<s:param name="value"><s:date name="beginTime" format="yyyy-MM-dd" /></s:param>
					</s:textfield>
					<s:text name="to" /> <s:textfield id="endTime" name="endTime" maxlength="10" size="12" cssClass="required date">
						<s:param name="value"><s:date name="endTime" format="yyyy-MM-dd" /></s:param>
					</s:textfield>
				 </div>
		
		 <div class="searchButton2">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
			</ul>
		 </div>
		 
		</div>
	</s:form>
	
	</div>

	<div class="pageContent">
		<table class="list" width="100%" layoutH="50">
			<thead>
				<tr>
				    <th colspan="8" style="text-align:center">
				    	 <s:text name="statistical_time_range" />：<s:date name="beginTime" format="yyyy-MM-dd"/> 00:00:00 <s:text name="to" /> <s:date name="endTime" format="yyyy-MM-dd"/> 23:59:59
				    </th>
				</tr>
				<tr class="even" >
					<td><s:text name="the_date_of" /> </td>
					<td><s:text name="participation" /></td>
					<td><s:text name="call_the_number" /></td>
					<td><s:text name="ordinary_transport_number_instead" /></td>
					<td><s:text name="go_luck_number_instead" /></td>
					<td><s:text name="the_total_number_of_consumption_coupon" /></td>
					<td><s:text name="the_total_number_of_the_magic_crystal" /></td>
					<td><s:text name="total_number_of_star_stone_is_produced" /></td>
				</tr>
			</thead>
			<s:iterator value="ossZStarDayList" status="offsets" var="zTaskDay">
				<tr>
				<td><s:property value="dateTime"/> </td>
				<td><s:property value="starNub" /> </td>
				<td><s:property value="callCount" /> </td>
				<td><s:property value="commonCount" /> </td>
				<td><s:property value="godCount" /> </td>
				<td><s:property value="bindGoldNum" /> </td>
				<td><s:property value="goldNum" /> </td>
				<td><s:property value="starNum" /> </td>
				</tr>
			</s:iterator>
		</table>
	</div>
