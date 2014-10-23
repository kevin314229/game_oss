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
		<s:form action="magicTree_index" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
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
				    <th colspan="7" style="text-align:center">
				    	 <s:text name="statistical_time_range" />：<s:date name="beginTime" format="yyyy-MM-dd"/> 00:00:00 <s:text name="to" /> <s:date name="endTime" format="yyyy-MM-dd"/> 23:59:59
				    </th>
				</tr>
				<tr class="even" >
					<td><s:text name="the_date_of" /> </td>
					<td><s:text name="making_it_to_participate_in_the_number_of_players" /></td>
					<td><s:text name="making_gold_coins" /></td>
					<td><s:text name="click_on_the_number_of_making_experience" /></td>
					<td><s:text name="making_the_star_stone" /></td>
					<td><s:text name="senior_blessing" /></td>
				</tr>
			</thead>
			<s:iterator value="ossZMagicTreeList" status="offsets" var="zMagicTree">
				<tr>
					<td><s:property value="idDate"/> </td>
					<td><s:property value="joininNub" /> </td>
					<td><s:property value="coinNub" /> </td>
					<td><s:property value="expNub" /> </td>
					<td><s:property value="starNub" /> </td>
					<td><s:property value="highNub" /> </td>
				</tr>
			</s:iterator>
		</table>
	</div>

