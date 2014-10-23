<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/changeCSS.jsp"/>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">

  	<div id="divLeft"  >
		<b><s:text name="top-up_analysis"  /></b>  
		 <a target="navTab" href="<%=basePath%>/admin/pay/consumeAssay.action"><s:text name="to_the_consumption_analysis" /></a>  
		<span class="color-red"></span>
		<span class="color-gr"><s:text name="performance_analysis" />:<s:text name="remote_time-consuming" />:<s:property value="remoteRunTime"/>ms <s:text name="the_local_total_time" />:<s:property value="localRunTime"/>ms <s:text name="the_length_of_the_data" />:<s:property value="contentLength"/></span> 
	</div>
	
		<s:form action="payAssay" namespace="/zhxy/pay" method="post" theme="simple" onsubmit="return navTabSearch(this)">
		
			<div  >
			<div class="searchDiv_left ">
				<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"><s:date name="beginDate" format="yyyy-MM-dd"/></s:textfield>
				<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
				<s:text name="regional" />：<s:select list="ossServersList"  label="" listKey="id" listValue="name" name="selectArray"
						 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all')}"></s:select>
				</div>
				<div class="searchButton" id="searchButton"  >
					 <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="%{getText('the_query')}"/></button></div></div>
				</div>
			</div>
		 </s:form>
		 <div class="searchDiv_left" style="width:100%"><%-- <s:text name="related_statistics" />： --%> 
		 <s:text name="query_the_total_prepaid_phone_number" />
		 :<%-- <s:property value="countRmb"/> --%>
		 <fmt:formatNumber value="${countRmb}" type="number" pattern="#,#00.0#" />
		  </div>
</div>
	
	<div class="pageContent" layoutH="150">
		
	    <table class="list" width="100%">
	    	<thead>
	    	<tr>
	    	    <th colspan="12"> 
	    	    	<s:text name="top-up_analysis" />
	    	    </th>
	    	</tr>
	    	<tr>
	    	    <th ><s:text name="the_date_of" /></th>
	    	    <th ><s:text name="regional" /></th>
	    	    <th ><s:text name="total_amount_of_top-up" /></th>
	    	    <th ><s:text name="top-up_account_number" /></th>
	    	    <th ><s:text name="prepaid_phone_number" /></th>
	    	    <th ><s:text name="the_total_number_of_login" /></th>
	    	    <th ><s:text name="peak_number_online" /></th>
	    	    <th >Arpu</th>
	    	    <th >Arppu</th>
	    	    <th ><s:text name="active_pay_rate" /></th>
	    	    <th ><s:text name="a_single_amount_paid" /></th>
	    	    <th ><s:text name="temporary_data_identification" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="zpayDayDtoList" var="oneRow" status="offset"> 
	    	<s:if test="ptCode==null">
	    		<tr  >
		    	    <td >
						<fmt:formatDate value="${oneRow.id}" pattern="yyyy-MM-dd" />
		    	    </td>
		    	    <td >
						<s:property value="#oneRow.serverName"/>
		    	    </td>
		    	    <td >
						<fmt:formatNumber value="${oneRow.moneyTotal}" type="number" pattern="#,#00.0#" />
		    	    </td>
		    	    <td >
						<s:property value="#oneRow.payUserNum"/>
		    	    </td>
		    	    <td >
						<s:property value="#oneRow.payTimes"/>
		    	    </td>
		    	    <td >
						<s:property value="#oneRow.loginTotal"/>
		    	    </td>
		    	    <td >
						<s:property value="#oneRow.maxOnline"/>
		    	    </td>
		    	    <td >
						<fmt:formatNumber value="${oneRow.arpu}" type="number" pattern="#,#00.0#" />
		    	    </td>
		    	    <td >
						<fmt:formatNumber value="${oneRow.arppu}" type="number" pattern="#,#00.0#" />
		    	    </td>
		    	    <td >
						<fmt:formatNumber value="${oneRow.activePayRate }" type="percent"  minFractionDigits="2"  />
		    	    </td>
		    	    <td >
						<fmt:formatNumber value="${oneRow.singlePay}" type="number" pattern="#,#00.0#" />
		    	    </td>
		    	    <td > 
						<s:if test="#oneRow.tmpFlag"><span class="color-red"><s:text name="temporary" /></span></s:if> 
						<s:else><s:text name="a_formal" /></s:else>
		    	    </td>
		    	</tr>
		    	</s:if>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>


