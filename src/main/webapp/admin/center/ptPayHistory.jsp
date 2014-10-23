<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<head>
<base href="<%=basePath%>">
<title><s:text name="data_analysis" />：<s:text name="kill" />boss<s:text name="ranking" /></title>
</head>
<div style="height:20px">
		 <span class="color-red"><s:property
				value="errorInfo" /></span> <span class="color-gr"><s:property
				value="successInfo" /></span>
</div>	
<div class="pageHead">
	<s:form action="ptPayHistory_index" method="post" theme="simple" id="queryRecoedForm" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">	
			<div class="searchDiv_left" > 
					<s:text name="statistical_time" />： 
					<s:textfield id="beginTime" name="beginTime" style="width:160px"  cssClass="required date" dateFmt="yyyy-MM-dd HH:mm:ss"></s:textfield>
					 
					<s:text name="to" /> 
					<s:textfield id="endTime" name="endTime" style="width:160px" cssClass="required date" dateFmt="yyyy-MM-dd HH:mm:ss">
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
		<table class="list" width="100%" layoutH="70">
			<thead>
				<tr>
			    <th colspan="7" style="text-align:center">
			    	 <s:text name="statistical_time_range" />：<s:text name="beginTime" /> <s:text name="to" /> <s:text name="endTime" />
			    </th>
			 	</tr>
				<tr  >
					<td>平台 </td>
					<td>人数</td>
					<td>充值总金额</td>
				</tr>
			</thead>
		<tbody>
		<s:iterator value="registerJsonArray" status="offsets" var="zboss">
			<tr <s:if test="#offsets.even == true"> class="even" </s:if> onClick="changeTRBgColor(this)">
			<td><s:property value="pt" /> </td>
			<td><s:property value="nub" /> </td>
			<td><s:property value="sum" /> </td>
			</tr>
		</s:iterator>
		</tbody>
		</table>
		
	</div>


