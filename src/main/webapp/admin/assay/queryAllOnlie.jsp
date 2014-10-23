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
		<s:form action="queryAllOnlie" namespace="/admin/assay" method="post" theme="simple"
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
		    	    <th colspan="2"> 
		    	    
		    	    	<s:text name="the_number_of_online_analysis" />: &nbsp${onlineCount}
		    	    </th>
		    	</tr>
		    	<tr>
		    	    <th><s:text name="regional" /></th>
		    	    <th><s:text name="the_number_of" /></th>
		    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="allOnlineDtoList" var="oneRow" status="offset"> 
	    		<tr  >
		    	    <td width="200px">
						<s:property value="#oneRow.serverName"/>
		    	    </td>
		    	    <td>
						<s:property value="#oneRow.allNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	 
	    	</tbody>
	    </table>
	    
	 </div>
	
   
