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
<%@ include file="select.jsp" %>
<s:include value="/admin/template/changeCSS.jsp"/>
<style type="text/css">
 #queryLostCount_tb td span{
 	color:green;
 }
</style>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" /> <input
		type="hidden" name="onePageNum" value="${onePageNum}" />
		<input
		type="hidden" name="gameId" id="lostUserCount_gameId" value="${gameId}" />
</form>

<div class="pageHeader">
	<s:form action="queryLostUser_Count" rel="pagerForm"
		namespace="/admin/queryLostUser" method="post" theme="simple"
		onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div style="float:left;">
<%-- 					<td><s:text name="project" />：<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="lostUserCount_gameId"  onchange="changePtIdOption(this.value,'lostUserCount_ptId','lostUserCount_areaId');"></s:select>
					</td> --%>

					<s:text name="platform" />：<s:select list="OssServersPt" label=""
				listKey="serverId" listValue="serverProvider" name="ptId" headerKey="-1" headerValue="%{getText('all_platforms')}"   
				id="lostUserCount_ptId"  onchange="initPtIdParam('lostUserCount_gameId',this.value,'lostUserCount_areaId');"></s:select> 
					

					
					<s:text name="regional" />：
					<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="selectArray" id="lostUserCount_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="--%{getText('all_servers')}--" ></s:select>
			 
			
					<s:text name="statistical_time" />： <s:textfield
							id="beginTime" name="beginTime" maxlength="10" size="12"
							cssClass="required date">
							<s:param name="value">
								<s:date name="beginTime" format="yyyy-MM-dd" />
							</s:param>
						</s:textfield>
					<s:text name="to" /> <s:textfield id="endTime"
							name="endTime" maxlength="10" size="12" cssClass="required date">
							<s:param name="value">
								<s:date name="endTime" format="yyyy-MM-dd" />
							</s:param>
						</s:textfield> <s:param name="value">
							<s:date name="beginTime" format="yyyy-MM-dd" />
						</s:param>
				</div>
		 <div class="searchButton">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div>
		</div>
	</s:form>
</div>


<div class="pageContent" layoutH="150" selector="h1">
	<table class="list" width="100%" id="queryLostCount_tb">
		<thead>
			<tr>
				<th align="center"><s:text name="the_date_of" /></th>
				<th align="center"><s:text name="game_project" /></th>
				<!-- 	<th align="center"><s:text name="region" /></th> -->
				<th align="center"><s:text name="number_of_the_day_landing" /></th>
				<th align="center"><s:text name="the_next_day_drain" /></th>
				<th align="center"><s:text name="lost_2_times" /></th>
				<th align="center"><s:text name="times_the_loss_of_three_days" /></th>
				<th align="center"><s:text name="lost_24_times" /></th>
				<th align="center"><s:text name="lost_25_times" /></th>
				<th align="center"><s:text name="lost_26_times" /></th>
				<th align="center"><s:text name="seven_times_the_loss" /></th>
				<th align="center"><s:text name="zhou_loss" /></th>
				<th align="center"><s:text name="month_loss" /></th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="queryActiveUserList" var="oneRow" status="offset">
				<tr>
					<td><s:date format="yyyy-MM-dd" name="#oneRow.activeDate" />
					</td>
					<td><%-- <s:property
							value="@com.jcwx.game.common.constants.GameConstant@gameMap[#oneRow.gameId]" /> --%>
							 <dict:itemvalue gameId="${oneRow.gameId }" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
					
					</td>
					<%-- 						<td align="center">
						 <s:property value="@com.jcwx.game.common.constants.OssServerConstant@ossServerMap[#oneRow.areaId]"/>
						</td> --%>
					<td align="left"><s:property value="#oneRow.day0" /></td>
					<td align="left"><s:if test="#oneRow.day1!=null"><fmt:formatNumber value="${oneRow.day0-oneRow.day1}" /> &nbsp;&nbsp;
					<span
						class="color-gr">[<fmt:formatNumber value="${(oneRow.day0-oneRow.day1)/oneRow.day0 *100}" pattern="#.##" minFractionDigits="0" />%]
					</span></s:if><s:else>--</s:else>
					</td>
					<td align="left"><s:if test="#oneRow.day2!=null"> <fmt:formatNumber
							value="${oneRow.day0-oneRow.day2}" /> &nbsp;&nbsp;<span
						class="color-gr">[<fmt:formatNumber
								value="${(oneRow.day0-oneRow.day2)/oneRow.day0 *100}"
								pattern="#.##" minFractionDigits="0" />%]
					</span></s:if><s:elseif test="#oneRow.day2==null">--</s:elseif></td>
					<td align="left"><s:if test="#oneRow.day3!=null"><fmt:formatNumber
							value="${oneRow.day0-oneRow.day3}" /> &nbsp;&nbsp;<span
						class="color-gr">[<fmt:formatNumber
								value="${(oneRow.day0-oneRow.day3)/oneRow.day0 *100}"
								pattern="#.##" minFractionDigits="0" />%]
					</span></s:if><s:else>--</s:else></td>
					<td align="left"><s:if test="#oneRow.day4!=null"><fmt:formatNumber
							value="${oneRow.day0-oneRow.day4}" /> &nbsp;&nbsp;<span
						class="color-gr">[<fmt:formatNumber
								value="${(oneRow.day0-oneRow.day4)/oneRow.day0 *100}"
								pattern="#.##" minFractionDigits="0" />%]
					</span></s:if><s:else>--</s:else></td>
					<td align="left"><s:if test="#oneRow.day5!=null"><fmt:formatNumber
							value="${oneRow.day0-oneRow.day5}" /> &nbsp;&nbsp;<span
						class="color-gr">[<fmt:formatNumber
								value="${(oneRow.day0-oneRow.day5)/oneRow.day0 *100}"
								pattern="#.##" minFractionDigits="0" />%]
					</span></s:if><s:else>--</s:else></td>
					<td align="left"><s:if test="#oneRow.day6!=null"><fmt:formatNumber
							value="${oneRow.day0-oneRow.day6}" /> &nbsp;&nbsp;<span
						class="color-gr">[<fmt:formatNumber
								value="${(oneRow.day0-oneRow.day6)/oneRow.day0 *100}"
								pattern="#.##" minFractionDigits="0" />%]
					</span></s:if><s:else>--</s:else></td>
					<td align="left"><s:if test="#oneRow.day7!=null"><fmt:formatNumber
							value="${oneRow.day0-oneRow.day7}" /> &nbsp;&nbsp;<span
						class="color-gr">[<fmt:formatNumber
								value="${(oneRow.day0-oneRow.day7)/oneRow.day0 *100}"
								pattern="#.##" minFractionDigits="0" />%]
					</span></s:if><s:else>--</s:else></td>
					<td align="left"><s:if test="#oneRow.week1!=null"><fmt:formatNumber
							value="${oneRow.day0-oneRow.week1}" /> &nbsp;&nbsp;<span
						class="color-gr">[<fmt:formatNumber
								value="${(oneRow.day0-oneRow.week1)/oneRow.day0 *100}"
								pattern="#.##" minFractionDigits="0" />%]
					</span></s:if><s:else>--</s:else></td>
					<td align="left"><s:if test="#oneRow.month1!=null"><fmt:formatNumber
							value="${oneRow.day0-oneRow.month1}" /> &nbsp;&nbsp;<span
						class="color-gr">[<fmt:formatNumber
								value="${(oneRow.day0-oneRow.month1)/oneRow.day0 *100}"
								pattern="#.##" minFractionDigits="0" />%]
					</span></s:if><s:else>--</s:else></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

</div>
<%-- <s:include value="/admin/template/paging.jsp" /> --%>


