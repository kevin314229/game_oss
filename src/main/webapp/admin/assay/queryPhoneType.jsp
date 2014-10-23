<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>




	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form action="queryPhoneType" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		
			<div class="searchDiv_left" > 
					<s:text name="mobile_phone_models" />：<s:textfield id="phoneType" name="phoneType" maxlength="10" size="12"></s:textfield>
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
	    	    <th width="50%"><s:text name="mobile_phone_models" /></th>
	    	    <th><s:text name="the_number_of" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
			<s:iterator value="OssPlayerPhoneTypeList" status="offsets">
			<tr>
		    	    <td><s:property value="type"/></td>
		    	    <td><s:property value="nub"/> </td>
		    </tr>
			</s:iterator>
			</tbody>
		</table>
	</div>
