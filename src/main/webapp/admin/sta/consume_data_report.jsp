<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ include file="select.jsp" %>
<s:include value="/admin/template/changeCSS.jsp"/>
<script type="text/javascript">


function optionChangeConsumeData(obj){
		$("#consume_areaId").empty();
		$.ajax({
        type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
        url: "<%=basePath%>/admin/kpiReport/query_OssServerListByServerCode.action",
        cache: false,
        global:false,
        data: "ptId="+obj,//<s:text name="data_to_be_sent" />
        success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
        	var myobj=eval(msg);
        	$("#consume_areaId").append("<option value='-1'>%{getText('all_servers')}</option>");
        	for(var i=0;i<myobj.length;i++){
        		$("#consume_areaId").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
        	}
        	checkStutConsumeData();
        },
        error:function(msg1,text){
        	alert(msg1+text);
        }
        
   });
}

function checkStutConsumeData(){
	if($("#consume_areaId").val()==-1){
		document.getElementById("nickName").disabled=true;
    	document.getElementById("loginName").disabled=true;
    	document.getElementById("questionStatus").disabled=true;
	}else{
		document.getElementById("nickName").disabled=false;
    	document.getElementById("loginName").disabled=false;
    	document.getElementById("questionStatus").disabled=false;
	}
}

function expCSV(){
	var url = "<%=basePath%>/admin/consume/query_consume_data.action";
	queryDataForm.action=url;
	document.getElementById("queryDataForm").submit();
  	queryDataForm.action= "<%=basePath%>/admin/consume/query_consume_data_list.action";

}
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="query_consume_data_list" rel="pagerForm" method="post" name="queryDataForm" id="queryDataForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		 <div class="searchDiv_left">
		<%-- 	 <s:text name="item" /> <s:text name="eye" />：
			<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="consume_gameId"  onchange="changePtCodeOption(this.value,'consume_ptId','consume_areaId');"></s:select> --%>
				
			<s:hidden name="gameId" id="consume_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptId" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="consume_ptId"  onchange="initPtCodeParam('consume_gameId',this.value,'consume_areaId');"></s:select> 
					<s:text name="server" />：
			<s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="consume_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> 
			 <s:text name="start_time" />：	<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date" value="%{beginDate}"></s:textfield>
				<s:text name="end_time" />： <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
	<s:text name="login_name" />：	<s:textfield id="loginName" name="loginName"  size="12" ></s:textfield>
	<s:text name="role_name" />：	<s:textfield id="nickName" name="nickName" size="12"  ></s:textfield>
	<s:if test="gameId==2">
	<s:text name="ingot_sources" />：
	</s:if>
	<s:else>
	<s:text name="magic_crystal_source" />：
	</s:else>
	<select name="sourceType" id="sourceType">
	<option value="">--<s:text name="whole" />--</option>
	<option value="0" <s:if test="sourceType==0"> selected  </s:if> ><s:text name="prepaid_users" /></option>
	<option value="1" <s:if test="sourceType==1"> selected  </s:if>><s:text name="payment_systems" /></option>
	</select>
	</div>
				 	
					<!-- 
				 <select id="consume_areaId" name="areaId"  >
						    <option value="-1" >--%{getText('all_servers')}--</option>
							<s:iterator value="ossServerList" var="oneRow"  status="offset">
									<s:if test="areaId==#oneRow.id">
										<option value="<s:property value="#oneRow.id" />" selected> 	
						    				<s:property value="#oneRow.name"/>
						    			</option>
									</s:if> 
									<s:else>
										<option value="<s:property value="#oneRow.id" />"  > 	
						    				<s:property value="#oneRow.name"/>
						    			</option>
									</s:else>
					    	</s:iterator>
						  
					</select> -->
				<!--	<select id="areaId" name="areaId" onchange="checkStutKPI()"  multiple="true" size="5" >
						    <option value="0" >--%{getText('all_servers')}--</option>
							<s:iterator value="ossServerList" var="oneRow"  status="offset">
										<option value="<s:property value="#oneRow.id" />"  > 	
						    				<s:property value="#oneRow.name"/>
						    			</option>
					    	</s:iterator>
					</select>   -->
						  
		
		 <div class="searchButton">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="expCSV()"><s:text name="download" /></button></div></div></li>
			</ul>
		 </div>
		 
		</div>
	</s:form>
</div>

<div class="pageContent"  >
	<div class="tabs" currentIndex="0" eventType="click" layoutH="30">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li class=""><a href="javascript:;"><span><s:text name="server_consumption_statistics" /></span></a></li>
					<li class=""><a href="javascript:;"><span>每日消费统计 </span></a></li>
					<li class=""><a href="javascript:;"><span><s:text name="consumer_detailed_record" /></span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" layoutH="80">
			<div id="container1" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
	    	<tr>
	    	    <th><s:text name="game" /></th>
	    	    <th><s:text name="server_region" /></th>
	    	     <th>消费角色数(去重)</th>
	    	    <!-- 
	    	    <th><s:text name="the_player_name" /></th>
	    	     -->
	    	    <th><s:text name="the_amount_of_consumption" /></th>
	    	    <th><s:text name="rmb" /></th>
	    	</tr>
	    	</thead>
	    	<s:iterator value="consumeAreaDatas" var="oneRow" status="offset">
	    		<tr>
		   	     <td>
		   	    <dict:itemvalue gameId="${oneRow.gameId }" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
				</td>
				<td>
				 <dict:itemvalue gameId="${oneRow.gameId }" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				 </td>
				 <td>
		    	    	<s:property value="#oneRow.consumeNum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.number"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.moneyNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    		</table>
			 </div>
			 	<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th>日期 </th>
						<th>使用角色数(不去重) </th>
						 <th>使用角色数(去重) </th> 
						<th>消耗元宝数</th>
						<th>ARPU(不去重)</th>
						<th>ARPU(去重)</th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="jsonArray" var="oneRow"  status="offset">
			    		<tr>
							<td>
								<s:property value="#oneRow.createTime"/>
							</td>
							<td>
								<s:property value="#oneRow.NUM"/>
							</td>
						  <td>
								<s:property value="#oneRow.NUM2"/>
							</td>  
							<td>
								<s:property value="#oneRow.sum"/>
							</td>
							<td>
								<%-- <s:property value="#oneRow.ARPU"/> --%>
								<fmt:formatNumber value="${oneRow.ARPU}" pattern="#.##" minFractionDigits="0" />
							</td>
							<td>
							<fmt:formatNumber value="${oneRow.sum/oneRow.NUM2}" pattern="#.##" minFractionDigits="0" />
								<s:property value="#oneRow.sum/#oneRow.NUM2"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
			 </div>
			 <div id="container3" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
	    	<tr>
	    	    <th style="width:80px"><s:text name="date" /></th>
	    	    <th><s:text name="game" /></th>
	    	     <th><s:text name="platform" /></th>
	    	    <th><s:text name="server_region" /></th>
	    	    <th><s:text name="account" />ID（<s:text name="login_name" />）</th>
	    	    <th><s:text name="role_name" /></th>
	    	    <!-- 
	    	    <th><s:text name="the_player_name" /></th>
	    	     -->
	    	    <th><s:text name="role" />ID</th>
	    	 <!--    <th><s:text name="profession" /></th>
	    	    <th><s:text name="sex" /></th> -->
	    	    <th><s:text name="point_of_consumption" /></th>
	    	     <th><s:text name="use" /></th>
	    	    <th><s:text name="the_amount_of_consumption" /></th>
	    	    <th><s:text name="rmb" /></th>
	    	    <th><s:text name="spending_time" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="consumeDatas" var="oneRow" status="offset">
	    		<tr>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd" name="#oneRow.consumeDate"/>
		    	    </td>
		    	 <td>
		    	  <dict:itemvalue gameId="${session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId}" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
				</td>
				<td>
				 <dict:itemvalue gameId="2" type="4"  itemValue="${oneRow.ptId }"></dict:itemvalue>
				</td>
				<td> <dict:itemvalue gameId="2" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				 <s:property value="@com.jcwx.game.common.constants.OssServerConstant@ossServerMap[#oneRow.areaId]"/>
				 </td>
		    	    <td>
		    	    	<s:property value="#oneRow.loginName"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.nickName"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.playerBaseId"/>
		    	    </td>
		    	   <%--    <td>
		    	    	<s:property value="#oneRow.career"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.sex"/>
		    	    </td> --%>
		    	     <td>
		    	     <s:property value="#oneRow.operation"/>
		    	    	<s:property value="@com.jcwx.game.common.constants.OperationLogConstant@maptype[#oneRow.operation]"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.operationDetail"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.number"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.moneyNum"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.createTime"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	
	    	</tbody>
	    		</table>
	    		 <s:include value="/admin/template/paging.jsp"/>
			 </div>
		</div>
	</div>
</div>










<%-- 
  <div class="pageContent sortDrag" layoutH="100" selector="h1">
  
		<div class="panel collapse">
			<h1><s:text name="server_consumption_statistics" /></h1>
			<div>
			 <table class="list" width="100%" >
	    	 <thead>
	    	<tr>
	    	    <th><s:text name="game" /></th>
	    	    <th><s:text name="server_region" /></th>
	    	    <!-- 
	    	    <th><s:text name="the_player_name" /></th>
	    	     -->
	    	    <th><s:text name="the_amount_of_consumption" /></th>
	    	    <th><s:text name="rmb" /></th>
	    	</tr>
	    	</thead>
	    	<s:iterator value="consumeAreaDatas" var="oneRow" status="offset">
	    		<tr>
		   	     <td>
		   	    <dict:itemvalue gameId="${oneRow.gameId }" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
				</td>
				<td>
				 <dict:itemvalue gameId="${oneRow.gameId }" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				 </td>
		    	    <td>
		    	    	<s:property value="#oneRow.number"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.moneyNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</table>
	    	</div>
	    </div>
	    
		<div class="panel collapse">
			<h1><s:text name="consumer_detailed_record" /></h1>
			<div>
			 <table class="list" width="100%"  >
	    	<thead>
	    	<tr>
	    	    <th style="width:80px"><s:text name="date" /></th>
	    	    <th><s:text name="game" /></th>
	    	     <th><s:text name="platform" /></th>
	    	    <th><s:text name="server_region" /></th>
	    	    <th><s:text name="account" />ID（<s:text name="login_name" />）</th>
	    	    <th><s:text name="role_name" /></th>
	    	    <!-- 
	    	    <th><s:text name="the_player_name" /></th>
	    	     -->
	    	    <th><s:text name="role" />ID</th>
	    	 <!--    <th><s:text name="profession" /></th>
	    	    <th><s:text name="sex" /></th> -->
	    	    <th><s:text name="point_of_consumption" /></th>
	    	     <th><s:text name="use" /></th>
	    	    <th><s:text name="the_amount_of_consumption" /></th>
	    	    <th><s:text name="rmb" /></th>
	    	    <th><s:text name="spending_time" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="consumeDatas" var="oneRow" status="offset">
	    		<tr>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd" name="#oneRow.consumeDate"/>
		    	    </td>
		    	 <td>
		    	  <dict:itemvalue gameId="${session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId}" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
				<s:property value="@com.jcwx.game.common.constants.GameConstant@gameMap[#oneRow.gameId]"/> 
				</td>
				<td>
				 <dict:itemvalue gameId="2" type="4"  itemValue="${oneRow.ptId }"></dict:itemvalue>
				<s:property value="@com.jcwx.game.common.constants.PtServerConstant@ptTypeMap[#oneRow.ptId]"/>/<s:property value="#oneRow.ptId"/>
				</td>
				<td> <dict:itemvalue gameId="2" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				 <s:property value="@com.jcwx.game.common.constants.OssServerConstant@ossServerMap[#oneRow.areaId]"/>
				 </td>
		    	    <td>
		    	    	<s:property value="#oneRow.loginName"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.nickName"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.playerBaseId"/>
		    	    </td>
		    	      <td>
		    	    	<s:property value="#oneRow.career"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.sex"/>
		    	    </td>
		    	     <td>
		    	     <s:property value="#oneRow.operation"/>
		    	    	<s:property value="@com.jcwx.game.common.constants.OperationLogConstant@maptype[#oneRow.operation]"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.operationDetail"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.number"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.moneyNum"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.createTime"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	
	    	</tbody>
	    </table>
	     <s:include value="/admin/template/paging.jsp"/>
	    	</div>
	    </div>
	    
	    
</div> --%>
