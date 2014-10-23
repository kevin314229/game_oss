<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="payStat" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div class="searchDiv_left">
					<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
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
  	
	    <table class="list" width="100%" layoutH="90">
	    	<thead>
		    	<tr>
		    	    <th colspan="7" style="text-align:center">
		    	    	<s:text name="statistical_time_range" />：<s:property value="beginDate"/> 00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
		    	    </th>
		    	</tr>
		    	<tr>
		    	    <th><s:text name="date" /></th>
		    	    <th><s:text name="prepaid_phone_number" /></th>
		    	    <th><s:text name="prepaid_phone_users" /></th>
		    	    <th>ARPU<s:text name="value" /></th>
		    	    <th><s:text name="top-up_amount" /></th>
		    	</tr>
		    </thead>
		    <tbody>
	    	<s:iterator value="payHistoryStatList" var="oneStat"  status="offset">
	    		<tr 
	    		<s:if test=" #oneStat.month=='MAX' || #oneStat.day=='MAX' "> class="even" </s:if> 
	    		<s:elseif test="#offset.even == true"> class="odd" </s:elseif> >
		    	 <td align="left">
		    	    	<s:if test="#oneStat.day!='MAX'&&#oneStat.day==100">
		    	    		<s:property value="#oneStat.year"/><s:text name="in" /><s:property value="#oneStat.month"/><s:text name="month" />(<s:text name="total_monthly" />)	
		    	    	</s:if>
		    	    	<s:elseif test="#oneStat.day=='MAX'">
		    	    		<s:text name="total" />	 
		    	    	</s:elseif>  
						<s:else>  
						  	<s:property value="#oneStat.year"/>-<s:property value="#oneStat.month"/>-<s:property value="#oneStat.day"/>&nbsp;
						</s:else> 
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneStat.payNum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneStat.payPlayerNum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneStat.arpu"/>
		    	    </td>
		    	    <td>
		    	    	 <fmt:formatNumber value="${amountSum}" type="number" pattern="#,#00.00#" /> 
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
    </div>
