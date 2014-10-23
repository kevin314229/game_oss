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
		<s:form action="zAmuletDay_index" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
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
				    <th colspan="10" style="text-align:center">
				    	 <s:text name="statistical_time_range" />：<s:date name="beginTime" format="yyyy-MM-dd"/> 00:00:00 <s:text name="to" /> <s:date name="endTime" format="yyyy-MM-dd"/> 23:59:59
				    </th>
				</tr>
				<tr class="even" >
					<td><s:text name="the_date_of" /> </td>
					<td><s:text name="the_number_of_writing_the_rune" /></td>
					<td><s:text name="the_number_of_writing_the_rune_rewrite_key" /></td>
					<td><s:text name="write_the_rune_consumption_of_gold" /></td>
					<td><s:text name="invite_the_rune_master" />（<s:text name="the_number_of" />）</td>
					<td><s:text name="green_rune" />（<s:text name="the_number_of" />）</td>
					<td><s:text name="blue_rune" />（<s:text name="the_number_of" />）</td>
					<td><s:text name="purple_runes" />（<s:text name="the_number_of" />）</td>
					<td><s:text name="gold_runes" />（<s:text name="the_number_of" />）</td>
					<td><s:text name="rune_essence" />（<s:text name="the_number_of" />）</td>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="ossZAmuletDayList" status="offsets" var="zAmuletDay">
				
				<tr>
					<td><s:date name="dateTime" format="yyyy-MM-dd"/> </td>
					<td><s:property value="amuletNub" /> </td>
					<td><s:property value="amuletCount" /> </td>
					<td><s:property value="silverNub" /> </td>
					<td><s:property value="inviteNub" /> </td>
					<td><s:property value="greenNub" /> </td>
					<td><s:property value="blueNub" /> </td>
					<td><s:property value="purpleNub" /> </td>
					<td><s:property value="goldenNub" /> </td>
					<td><s:property value="essenceNub" /> </td>
				</tr>
				
			</s:iterator>
			</tbody>
		</table>
		
	</div>

