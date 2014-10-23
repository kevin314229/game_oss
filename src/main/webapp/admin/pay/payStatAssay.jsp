<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="payStat_assay" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
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
	    <table class="list" width="100%" layoutH="65">
	    	<thead>
	    	<tr>
	    	    <th colspan="8" style="text-align:center">
	    	    	<s:text name="statistical_time_range" />：<s:property value="beginDate"/> 00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
	    	    </th>
	    	</tr>
	    	<tr>
	    	    <th><s:text name="top-up_interval" /> </th>
	    	    <th><s:text name="prepaid_phone_number" /> </th>
	    	    <th><s:text name="prepaid_phone_users" /> </th>
	    	    <th>ARPU</th>
	    	    <th><s:text name="top-up_amount" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="payHistoryStatList" var="oneStat"  status="offset">
	    		<tr>
	    		<s:if test="year==1"><td>1-50<s:text name="yuan" /></td></s:if> 
	    		<s:elseif test="year==2"><td>50-100<s:text name="yuan" /></td></s:elseif> 
	    		<s:elseif test="year==3"><td>100-300<s:text name="yuan" /></td></s:elseif> 
	    		<s:elseif test="year==4"><td>300-500<s:text name="yuan" /></td></s:elseif> 
	    		<s:elseif test="year==5"><td>500-1000<s:text name="yuan" /></td></s:elseif> 
	    		<s:elseif test="year==6"><td>1000<s:text name="yuan_of_above" /></td></s:elseif>
	    		<s:else><td><s:text name="a_total_of" /></td></s:else>
	    	    <td><s:property value="payNum"/> </td>
	    	    <td><s:property value="payPlayerNum"/></td>
	    	    <td><s:property value="arpu"/></td>
	    	    <td><s:property value="amountSum"/></td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	 </div>
   
