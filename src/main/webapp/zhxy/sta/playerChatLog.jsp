<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ include file="select.jsp" %>
<s:include value="/admin/template/changeCSS.jsp"/>
<script type="text/javascript">
	
	function optionChangeRecord(obj){
		$("#recprdAreaId").empty();
		$.ajax({
	    type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
	    url: "<%=basePath%>/admin/kpiReport/query_OssServerListByServerCode.action",
	    cache: false,
	    global:false,
	    data: "ptId="+obj,//<s:text name="data_to_be_sent" />
	    success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
	    	var myobj=eval(msg);
	    	$("#recprdAreaId").append("<option value='-1'>%{getText('all_servers')}</option>");
	    	for(var i=0;i<myobj.length;i++){
	    		$("#recprdAreaId").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
	    	}
	    	//checkStutKPI();
	    },
	    error:function(msg1,text){
	    	alert(msg1+text);
	    }
	    
	});
	}
	
	function checkStutKPI(){
	if($("#recprdAreaId").val()==-1){
		//document.getElementById("nickName").disabled=true;
		//document.getElementById("loginName").disabled=true;
		//document.getElementById("questionStatus").disabled=true;
	}else{
		//document.getElementById("nickName").disabled=false;
		//document.getElementById("loginName").disabled=false;
		//document.getElementById("questionStatus").disabled=false;
	}
	}
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="playerChatLog_index" rel="pagerForm" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div class="searchDiv_left">
				<%-- 	 <s:text name="item" /> <s:text name="eye" />：
			<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="record_gameId"  onchange="changePtCodeOption(this.value,'record_ptId','record_areaId');"></s:select> --%>
				<s:hidden name="gameId" id="record_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptId" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="record_ptId"  onchange="initPtCodeParam('record_gameId',this.value,'record_areaId');"></s:select> 
					<s:text name="server" />：
			<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="record_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> 
				
			  <s:text name="account_number" />：<s:textfield id="loginName" name="loginName" maxlength="25" size="12"  value="%{loginName}"></s:textfield>
				<s:text name="role_name" />： <s:textfield id="nickName" name="nickName" maxlength="25" size="12" ></s:textfield>
				<s:text name="chat" />： <s:textfield id="content" name="content" maxlength="25" size="18" ></s:textfield>
				<s:text name="start_time" />： <s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date" value="%{beginDate}"></s:textfield>
				<s:text name="end_time" />： <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
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
	    <table class="list" width="100%" layoutH="130">
	    	<thead>
	    	<tr>
	    	    <th><s:text name="server_region" /></th>
	    	   <th><s:text name="account" />(<s:text name="login_name" />)</th>
	    	    <th><s:text name="role_name" /></th>
	    	    <th><s:text name="chat" /></th>
	    	     <th><s:text name="recipient" /></th>
	    	      <th><s:text name="delivery_time" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="playerChatLogList" var="oneRow" status="offset">
	    		<tr>
		    	   	     
		    	   
		    	    <td>
		    	   		<s:property value="#oneRow.areaId"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.loginName"/>
		    	    </td>
		    	      <td>
		    	   		<s:property value="#oneRow.sender"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.content"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.recipient"/>
		    	    </td>
		    	      <td>
		    	   		<s:date format="yyyy-MM-dd hh:mm:ss" name="#oneRow.chatDate"/>
		    	    </td> 
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	     <s:include value="/admin/template/paging.jsp"/>
  </div>
