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
	
	function optionChangePay(obj){
		$("#payAreaId").empty();
		$.ajax({
	    type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
	    url: "<%=basePath%>/admin/kpiReport/query_OssServerListByServerCode.action",
	    cache: false,
	    global:false,
	    data: "ptId="+obj,//<s:text name="data_to_be_sent" />
	    success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
	    	var myobj=eval(msg);
	    	$("#payAreaId").append("<option value='-1'>%{getText('all_servers')}</option>");
	    	for(var i=0;i<myobj.length;i++){
	    		$("#payAreaId").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
	    	}
	    	//checkStutKPI();
	    },
	    error:function(msg1,text){
	    	alert(msg1+text);
	    }
	    
	});
	}
	
	function checkStutKPI(){
	if($("#payAreaId").val()==-1){
		//document.getElementById("nickName").disabled=true;
		//document.getElementById("loginName").disabled=true;
		//document.getElementById("questionStatus").disabled=true;
	}else{
		//document.getElementById("nickName").disabled=false;
		//document.getElementById("loginName").disabled=false;
		//document.getElementById("questionStatus").disabled=false;
	}
	}
	
	function openRecordList(){
		var url = "<%=basePath%>/admin/payReport/payReport_recordList.action";
		queryPayForm.action=url;
	//	queryPayForm.target="navTab";
		queryPayForm.submit();
		queryPayForm.action= "<%=basePath%>/admin/payReport/payReport_index.action";
	}
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="payReport_index" rel="pagerForm" method="post" id="queryPayForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			 <div class="searchDiv_left">
				<%-- 	 <s:text name="item" /> <s:text name="eye" />：
			<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="pay_gameId"  onchange="changePtCodeOption(this.value,'pay_ptId','pay_areaId');"></s:select> --%>
				<s:hidden name="gameId" id="pay_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptId" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="pay_ptId"  onchange="initPtCodeParam('pay_gameId',this.value,'pay_areaId');"></s:select> 
					<s:text name="server" />：
			<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="pay_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> 
			 <s:text name="platform_identity" />：<select name="ptCode">
								<option value="">--<s:text name="all" />--</option>
									<s:iterator value="ossOperationList" var="oneRows">
										<option value='<s:property value='#oneRows.carrierOperator'/>'
										 <s:if test='ptCode==#oneRows.carrierOperator'> selected </s:if> >
										 <s:property value="#oneRows.operationName"/>
										 </option>
									</s:iterator>
							  </select>	
			  <s:text name="login_name" />：	<s:textfield id="loginName" name="loginName" maxlength="25" size="12"  value="%{loginName}"></s:textfield>
				</div>
				 <div class="searchButton">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="%{getText('the_query')}"/></button></div></div></li>
					</ul>
				</div>
			</div>
	</s:form>
</div>

  
   <div class="pageContent sortDrag" layoutH="100" selector="h1">
		<div class="panel collapse">
			<h1><s:text name="server_statistics_recharge" /></h1>
			<div>
			 <table class="list" width="100%" >
	    	 <thead>
	    	<tr>
	    	    <th><s:text name="game" /></th>
	    	    <th><s:text name="server_region" /></th>
	    	    <!-- 
	    	    <th><s:text name="the_player_name" /></th>
	    	        <th><s:text name="currency_recharge_platform" /></th>
	    	     -->
	    	
	    	    <th><s:text name="the_total_amount_of_recharge" /></th>
	    	</tr>
	    	</thead>
	    	<s:iterator value="payAreaList" var="oneRow" status="offset">
	    		<tr>
		    	<td>
		    	 <dict:itemvalue gameId="${session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId}" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
				<%-- <s:property value="@com.jcwx.game.common.constants.GameConstant@gameMap[#oneRow.gameId]"/>  --%>
				</td>
				<td>
				 <dict:itemvalue gameId="${session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId}" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				<%--  <s:property value="@com.jcwx.game.common.constants.OssServerConstant@ossServerMap[#oneRow.areaId]"/>  --%>
				 		<%-- <s:property value="#oneRow.areaId"/> --%>
				 </td>
		    	      <%--  <td>
		    	   		<s:property value="#oneRow.ptMoneyNum"/>
		    	    
		    	    </td> --%>
		    	     <td>
		    	   		<s:property value="#oneRow.moneyNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</table>
	    	</div>
	    </div>
	    
		<div class="panel collapse">
			<h1><s:text name="top-ranking_record" /></h1>
			<div>
			<table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	  
	    	    <th><s:text name="game" /></th>
	    	     <th><s:text name="game_platform" /></th>
	    	    <th><s:text name="server_region" /></th>
	    	   <th><s:text name="account" />(<s:text name="login_name" />)</th>
	    	 <!--     <th><s:text name="currency_recharge_platform" /></th> -->
	    	     <th><s:text name="the_total_amount_of_recharge" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="payRankList" var="oneRow" status="offset">
	    		<tr>
		    	 	     <td>
		    	 	      <dict:itemvalue gameId="${oneRow.gameId }" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
				</td>
				<td>
				 <dict:itemvalue gameId="${oneRow.gameId }" type="4"  itemValue="${oneRow.ptId }"></dict:itemvalue>
				</td>
				<td>
				 <dict:itemvalue gameId="${oneRow.gameId }" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				 </td>
		    	   
		    	    <td>
		    	   	<a href="<%=basePath%>/admin/payReport/payReport_recordList.action?loginName=<s:property value='#oneRow.loginName' />&gameId=<s:property value='#oneRow.gameId' />&ptId=<s:property value='#oneRow.ptId' />&areas=<s:property value='#oneRow.areaId' />" target="navTab"><s:property value="#oneRow.loginName"/></a>	
		    	    </td>
		    	 <%--     <td>
		    	   		<s:property value="#oneRow.ptMoneyNum"/>
		    	    </td> --%>
		    	     <td>
		    	   		<s:property value="#oneRow.moneyNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	
	    	</tbody>
	    </table>
	     <s:include value="/admin/template/paging.jsp"/>
	    	</div>
	    </div>
</div>
