<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>




	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form action="zBossDay_index" method="post" rel="pagerForm" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
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

	<div  class="pageContent">
		<table class="list" width="100%" layoutH="63">
		
			<thead>
				<tr>
				    <th colspan="7" style="text-align:center">
				    	  <s:text name="statistical_time_range" />：<s:date name="beginTime" format="yyyy-MM-dd"/> 00:00:00 <s:text name="to" /> <s:date name="endTime" format="yyyy-MM-dd"/> 23:59:59
				    </th>
				</tr>
				<tr class="even" >
					<td><s:text name="the_date_of" /> </td>
					<td><s:text name="the_time_of_death" /></td>
					<td><s:text name="participation" /></td>
					<td>boss<s:text name="hp" /></td>
					<td><s:text name="star_stone_inspiring" />（<s:text name="the_number_of" />）</td>
					<td><s:text name="monster_crystal_encouraged" />（<s:text name="the_number_of" />）</td>
					<td><s:text name="seconds" />CD（<s:text name="the_number_of" />）</td>
				</tr>
			</thead>
			<s:iterator value="ossZBossDayList" status="offsets" var="zboss">
				
				<tr>
				<td><s:date name="openTime"/> </td>
				<td><s:date name="bossOverTime" /> </td>
				<td><s:property value="joinNub" /> </td>
				<td><s:property value="totalBossBlood" /> </td>
				<td><s:property value="starNum" /> </td>
				<td><s:property value="goldNum" /> </td>
				<td><s:property value="cdNub" /> </td>
				</tr>
				
			</s:iterator>
		</table>
		
		<s:include value="/admin/template/paging.jsp"/>
	</div>

