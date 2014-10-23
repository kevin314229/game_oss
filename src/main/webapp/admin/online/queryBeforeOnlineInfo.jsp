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
	<s:form rel="pagerForm" action="queryBeforeOnlineInfo" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
				    <table class="searchContent">
					<tr>
						<td>
							<s:text name="the_start_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
						</td>
						<td>
							<s:text name="the_end_of_time" />：<s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
						</td>
						<td>
							<s:text name="the_sorting" />：<s:select list="#{'DESC': getText('statistical_time')+'↓','ASC': getText('statistical_time')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
						</td>
					</tr>
					
					<tr>
						<td>
							<s:text name="the_current_statistical_time" />（<s:text name="the_highest_online" />：<span class="color-red"><s:property value="currMaxNum"/></span>；<s:text name="the_lowest_online" />：<span class="color-red"><s:property value="currMinNum"/></span>；<s:text name="the_average_online" />：<span class="color-gr"><s:property value="currAvgNum"/></span>）
						</td>
					</tr>
					<%-- <tr>
						<td>
							<s:text name="the_current_statistical_time" />（<s:text name="the_highest_online" />：<span class="color-red"><s:property value="currMaxNum"/></span>；<s:text name="the_lowest_online" />：<span class="color-red"><s:property value="currMinNum"/></span>；<s:text name="the_average_online" />：<span class="color-gr"><s:property value="currAvgNum"/></span>）
						</td>
					</tr> --%>
					</table>
		   			<div class="subBar">
							<ul>
								<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
							</ul>
					</div>
			</div>
	 </s:form>
	</div>
	
	
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="138" >
	    	<thead>
	    	<tr>
	    	    <th><s:text name="statistical_time" /></th>
	    	    <th><s:text name="real-time_online_number" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="dataHistoryList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm" name="#oneRow.dataHistoryTimeByID"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.onlinePlayerNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <s:include value="/admin/template/paging.jsp"/>
	</div>
   
