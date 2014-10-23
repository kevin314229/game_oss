<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<s:include value="/admin/template/scriptMessage.jsp"/>
<s:include value="/admin/template/changeCSS.jsp"/>
<div class="pageHeader">
  	<div id="divLeft" style="height:16px">
		<b><s:text name="the_number_of_online_analysis" /></b>  
		<span class="color-red"></span>
		<span class="color-gr"><s:text name="performance_analysis" />:<s:text name="remote_time-consuming" />:<s:property value="remoteRunTime"/>ms <s:text name="the_local_total_time" />:<s:property value="localRunTime"/>ms <s:text name="the_length_of_the_data" />:<s:property value="contentLength"/></span> 
	</div>
		<s:form action="ZHqueryAllOnlie" namespace="/zhxy/assay" method="post" theme="simple"
		cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			<div class="searchBar">
				<div class="searchDiv_left">
				<s:text name="regional" />：<s:select list="ossServersList" label="" listKey="id" listValue="name" name="selectArray"
						 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all')}"></s:select>
				</div>
				<div class="searchButton">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="%{getText('the_query')}"/></button></div></div></li>
					</ul>
				</div>
			</div>
		 </s:form>
</div>	
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="60">
	    	<thead>
		    	<tr>
		    	    <th colspan="4" > 
		    	    	<s:text name="the_number_of_online_analysis" />
		    	    </th>
		    	</tr>
		    	<tr>
		    	    <th colspan="2"><s:text name="regional" /></th>
		    	    <th colspan="2"><s:text name="the_number_of" /></th>
		    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="allOnlineDtoList" var="oneRow" status="offset"> 
	    		<tr  >
		    	    <td width="200px" colspan="2">
						<s:property value="#oneRow.serverName"/>
		    	    </td>
		    	    <td colspan="2">
						<s:property value="#oneRow.allNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	 
	    	</tbody>
	    	
	    	<thead>
	    		<tr>
	    			<th width="25%"><s:text name="server" /></th>
	    			<th width="25%"><s:text name="operators" /></th>
	    			<th width="25%"><s:text name="operator_logo" /></th>
	    			<th width="25%"><s:text name="online" /></th>
	    		</tr>
	    	</thead>
	    	<tbody>
	    		<s:iterator value="ptList" var="onePt">
		    		<tr>
		    			<td><s:property value="#onePt.serverName"/></td>
		    			<td><s:property value="@com.jcwx.game.common.constants.PtServerConstant@ptTypeMap[#oneRow.carrierOperator]"/></td>
		    			<td><s:property value="#onePt.ptCode"/></td>
		    			<td><s:property value="#onePt.allNum"/></td>
		    		</tr>
	    		</s:iterator>
	    	</tbody>
	    </table>
	    
	 </div>
	
   
