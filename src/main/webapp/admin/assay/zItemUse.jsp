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
			<span class="color-red"></span>
			<div id="divLeft" style="height:18px">
				<b><s:text name="data_analysis" />：<s:text name="goods_consumption_analysis" /></b>
				<span class="color-red"><s:property value="errorInfo" /></span> 
				<span class="color-gr"><s:property value="successInfo" /></span>
			</div>
			<s:form action="zItemUse_index" method="post" theme="simple" id="queryRecordForm" onsubmit="return $(this).valid()&&navTabSearch(this);">
				<div class="searchBar">
				<div class="searchDiv_left">
								<s:text name="statistical_time" />：
								<s:textfield id="beginTime" name="beginTime" maxlength="10" size="12" cssClass="required date">
									<s:param name="value"><s:date name="beginTime" format="yyyy-MM-dd" /></s:param>
								</s:textfield>
							 
								<s:text name="to" /> 
								<s:textfield id="endTime" name="endTime" maxlength="10" size="12" cssClass="required date">
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
				<table id="row" class="list" width="100%" layoutH="70">
					<tr class="even" >
					<td><s:text name="region" /></td>
					<td><s:text name="product_name" /></td>
					<td><s:text name="the_number_of_players_using" /></td>
					<td><s:text name="use_the_number_of_roles" /></td>
					<td><s:text name="the_total_proportion_of_the_role_of_use" /></td>
					<td><s:text name="frequency_of_use" /></td>
					<td><s:text name="the_total_number_of_times_the_proportion" /></td>
					<td><s:text name="the_number_of_items_consumed" /></td>
					<td><s:text name="click_items" /></td>
					<td><s:text name="item_total_price" /></td>
					<td><s:text name="proportion_of_the_total_price" /></td>
					</tr>
				<s:iterator value="ossZItemUseList" status="offsets" var="ossZItemUse">
					<tr <s:if test="#offsets.even == true"> class="even" </s:if> onClick="changeTRBgColor(this)">
					<td><s:property value="serverName" /> </td>
					<td><s:property value="itemName" /> </td>
					<td><s:property value="playerNub" /> </td>
					<td><s:property value="plyaerBaseNub" /> </td>
					<td><s:property value="playerBaseScale" /> </td>
					<td>${ossZItemUse.cNub}</td>
					<td>${ossZItemUse.cNubScale}</td>
					<td><s:property value="itemSum" /> </td>
					<td><s:property value="price" /> </td>
					<td><s:property value="totleFee" /> </td>
					<td><s:property value="totleScale" /> </td>
					</tr>	
				</s:iterator>
				</table>			
		</div>
		

