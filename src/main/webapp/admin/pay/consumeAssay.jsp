<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">

  	<div id="divLeft">
  		<b><s:text name="consumption_analysis" /></b>  <a target="navTab" href="<%=basePath%>/admin/pay/payAssay.action"><s:text name="turn_to_top-up_analysis" /></a> 
		<span class="color-red"></span>
		<span class="color-gr"><s:text name="performance_analysis" />:<s:text name="remote_time-consuming" />:<s:property value="remoteRunTime"/>ms <s:text name="the_local_total_time" />:<s:property value="localRunTime"/>ms <s:text name="the_length_of_the_data" />:<s:property value="contentLength"/></span> 
	</div>
	
		<s:form action="consumeAssay" namespace="/admin/pay" method="post" theme="simple"  onsubmit="return navTabSearch(this)">
			<div class="searchBar">
				<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"><s:date name="beginDate" format="yyyy-MM-dd"/></s:textfield>
				<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
				<s:text name="regional" />：<s:select list="ossServersList"  label="" listKey="id" listValue="name" name="selectArray"
						 multiple="true" size="10" headerKey="-1" headerValue="%{getText('all')}"></s:select>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="%{getText('the_query')}"/></button></div></div></li>
					</ul>
				</div>
			</div>
		 </s:form>
</div>
		 
	<div class="pageContent">
		
	    <table class="list" width="100%" layoutH="228">
	    	<thead>
	    	<tr>
	    	     <th colspan="12"> 
	    	    	<s:text name="consumption_analysis" />
	    	    </th>
	    	</tr>
	    	<tr>
	    	   <th><s:text name="the_date_of" /></th>
	    	    <th><s:text name="regional" /></th>
	    	    <th><s:text name="total_amount_of_consumption" /></th>
	    	    <th><s:text name="consumption_account_number" /></th>
	    	    <th><s:text name="use_the_number" /></th>
	    	    <th><s:text name="a_single_consumption_amount" /></th>
	    	    <th>acpu</th>
	    	    <th>acppu</th>
	    	    <th><s:text name="retained_the_magic_number_of_crystal" /></th>
	    	    <th><s:text name="temporary_data_identification" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="zconsumeDayDtoList" var="oneRow" status="offset"> 
	    		<tr  >
		    	     <td>
						<fmt:formatDate value="${oneRow.id}" pattern="yyyy-MM-dd" />
		    	    </td>
		    	    <td>
						<s:property value="#oneRow.serverName"/>
		    	    </td>
		    	    <td>
						<fmt:formatNumber value="${oneRow.consumeGoldTotal}" type="number" pattern="#,#00.0#" />
		    	    </td>
		    	    <td>
						<s:property value="#oneRow.consumeUserNum"/>
		    	    </td>
		    	    <td>
						<s:property value="#oneRow.consumeTimes"/>
		    	    </td>
		    	    <td>
		    	    	<fmt:formatNumber value="${oneRow.singleConsume}" type="number" pattern="#,#00.0#" />
		    	    </td>
		    	    <td>
						<fmt:formatNumber value="${oneRow.acpu}" type="number" pattern="#,#00.0#" />
		    	    </td>
		    	    <td>
						<fmt:formatNumber value="${oneRow.acppu}" type="number" pattern="#,#00.0#" />
		    	    </td>
		    	    <td> 
						<fmt:formatNumber value="${oneRow.allGoldTotal}" type="number" pattern="#,#00.0#" />
		    	    </td>
		    	    <td> 
						<s:if test="#oneRow.tmpFlag"><span class="color-red"><s:text name="temporary" /></span></s:if> 
						<s:else><s:text name="a_formal" /></s:else>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
