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
		<s:form action="zTaskDay_index" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		
			<div class="searchDiv_left" > 
					<s:text name="statistical_time" />：
					<s:textfield id="beginTime" name="beginTime" maxlength="10" size="12" cssClass="required date">
						<s:param name="value"><s:date name="beginTime" format="yyyy-MM-dd" /></s:param>
					</s:textfield>
				 
					<s:text name="to" /> 
					<s:textfield id="endTime" name="endTime" maxlength="10" size="12" cssClass="required date">
						<s:param name="value"><s:date name="endTime" format="yyyy-MM-dd" /></s:param>
					</s:textfield>
				 &nbsp; &nbsp;
					<a href="<%=basePath %>/admin/assay/copynub_muticopydie.action"><s:text name="go_to_the" /><<s:text name="elite_underground_city" />><s:text name="death_level_analysis" /></a>
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
		<table class="list" width="100%" layoutH="50">
			<thead>
				<tr>
				    <th colspan="7" style="text-align:center">
				    	    	<s:text name="statistical_time_range" />：<s:date name="beginTime" format="yyyy-MM-dd"/> 00:00:00 <s:text name="to" /> <s:date name="endTime" format="yyyy-MM-dd"/> 23:59:59
				    </th>
				</tr>
				<tr class="even" >
					<td><s:text name="the_date_of" /> </td>
					<td><s:text name="a_copy_of_the_number_of_open" /></td>
					<td><s:text name="a_single_open_number" /></td>
					<td><s:text name="open_the_number_two" /></td>
					<td><s:text name="three_open_quantity" /></td>
					<td><s:text name="participate_in_the_total_number_of" /></td>
				</tr>
			</thead>
			<s:iterator value="copynubDays" status="offsets">
				<tr>
					<td><s:property value="dateTime"/> </td>
					<td><s:property value="startNum" /> </td>
					<td><s:property value="singleNum" /> </td>
					<td><s:property value="twoNum" /> </td>
					<td><s:property value="threeNum" /> </td>
					<td><s:property value="allNum" /> </td>
				</tr>
			</s:iterator>
		</table>
	</div>

