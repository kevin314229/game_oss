<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="select.jsp" %>
<s:include value="/admin/template/changeCSS.jsp"/>
<script type="text/javascript">
	
</script>
 
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="query_kpi_day_report" rel="pagerForm" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
				 <div class="searchDiv_left">
		  		<%--  <s:text name="item" /> <s:text name="eye" />：
			<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="kpi_gameId"  onchange="changePtCodeOption(this.value,'kpi_ptId','kpi_areaId');"></s:select> --%>
				<input type="hidden" name="">
				<s:hidden name="gameId" id="kpi_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptCode" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="kpi_ptId"  onchange="initPtCodeParam('kpi_gameId',this.value,'kpi_areaId');"></s:select> 
					<s:text name="server" />：
				 
				<!--	<select id="areaId" name="areaId" onchange="checkStutKPI()"  multiple="true" size="5" >
						    <option value="0" >--%{getText('all_servers')}--</option>
							<s:iterator value="ossServerList" var="oneRow"  status="offset">
										<option value="<s:property value="#oneRow.id" />"  > 	
						    				<s:property value="#oneRow.name"/>
						    			</option>
					    	</s:iterator>
					</select>   -->
						  <s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="kpi_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> 
					<s:text name="start_time" />：	<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date" value="%{beginDate}"></s:textfield>
				<s:text name="end_time" />： <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield> 	
		</div>	
			 <div class="searchButton">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div>
		</div>
	</s:form>
</div>

  
  <div class="pageContent sortDrag" layoutH="100" selector="h1">
		<div class="panel collapse">
			<h1><s:text name="statistics_server_by_day" /></h1>
			<div>
			 <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    		 <th width=70><s:text name="date" /></th>
	    	    
	    	    <th width=50><s:text name="the_number_of_rows_of_heavy_login" />(DAU)</th>
	    	      <th width=50>注册人数</th>
	    	    <th width=50><s:text name="re-scheduling" />IP<s:text name="number" /></th>
	    	    <!-- 
	    	    <th><s:text name="the_player_name" /></th>
	    	     -->
	    	    <th width=50><s:text name="new_registration_number" /></th>
	    	    <th width=50>活跃用户<s:text name="number" /></th>
	    	    <th width=50><s:text name="new_role_users" /></th>
	    	    <th width=50><s:text name="the_total_number_of_new_registration" /></th>
	    	  <!--   <th width=50><s:text name="the_next_day_login_to_rate" /></th> -->
	    	    <th width=50><s:text name="avg_length" />(<s:text name="point" />)</th>
	    	    <th width=60><s:text name="the_maximum_number_of_days" />(PCU)</th>
	    	    <th width=50><s:text name="the_average_number_of_online" />(CA)</th>
	    	    <th width=50><s:text name="the_day_is_filled_amount" /></th>
	    	    <th width=50><s:text name="prepaid_account_number" /></th>
	    	    <th width=50><s:text name="the_new_paying_accounts" /></th>
	    	    <th width=50><s:text name="the_amount_consumed_day" /></th>
	    	    <th width=50><s:text name="the_remaining_amount_of_the_day" /></th>
	    	    <th width=50><s:text name="the_number_of_days_of_consumption" /></th>
	    	    <th width=50><s:text name="consume" />ARPU</th>
	    	    <th width=50><s:text name="recharge" />ARPU</th>
	    	    <th width=50><s:text name="buy_props_number" /></th>
	    	    <th width=50><s:text name="quantity_to_buy_props" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="sumDayReports" var="oneRow" status="offset">
	    		<tr>
				<td colspan=1 style="text-align:center">
					<s:date format="yyyy-MM-dd" name="#oneRow.kpiDate"/><s:text name="total" />
				</td>
				 
		    	    <td>
		    	    	<s:property value="#oneRow.DAU"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.registeNum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.IPAU"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.newLoginNum"/>
		    	    </td>
		    	      <td>
		    	    	<s:property value="#oneRow.DAU-#oneRow.newLoginNum"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.newRoleNum"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.allNewLoginNum"/>
		    	    </td>
		    	    <%-- <td>
		    	    	<s:property value="#oneRow.nextLoginRate"/>%
		    	    </td> --%>
		    	    <td>
		    	   		<s:property value="#oneRow.avgOnlineTime"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.HPC"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.CA"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.allMoney"/>
		    	    </td>
		    	      <td>
		    	   		<s:property value="#oneRow.allPayNum"/>
		    	    </td>
		    	      <td>
		    	   		<s:property value="#oneRow.newPayNum"/>
		    	    </td>
		    	  
		    	    <td>
		    	   		<s:property value="#oneRow.consumeMoney"/>
		    	    </td>
		    	    <td>
		    	    <s:if test="#oneRow.remainMoney<0.0" >0</s:if>
		    	    <s:else ><s:property value="#oneRow.remainMoney"/></s:else>
		    	   		
		    	    </td>
		    	      <td>
		    	   		<s:property value="#oneRow.allConsumeNum"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.consumeARPU"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.payARPU"/>
		    	    </td>
		    	  
		    	    <td>
		    	   		<s:property value="#oneRow.buyPeopleNum"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.buyPropsNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	<tr>
	    	
	    	<td colspan=1 style="text-align:center">
					 <s:text name="total" />
				</td>
				 
		    	    <td>  <s:property value="loginSum.sumLoginName"/>
		    	    </td>
		    	    <td>
		    	  
		    	    </td>
		    	    <td>
		    	     <s:property value="loginSum.sumIp"/>
		    	    </td>
		    	      <td>
		    	    </td>
		    	     <td>
		    	    </td>
		    	     <td>
		    	    </td>
		    	    <%-- <td>
		    	    	<s:property value="#oneRow.nextLoginRate"/>%
		    	    </td> --%>
		    	    <td>
		    	    </td>
		    	    <td>
		    	    </td>
		    	    <td>
		    	    </td>
		    	    <td>
		    	 
		    	    </td>
		    	      <td>
		    	      
		    	    </td>
		    	      <td>
		    	         <s:property value="paySum.sumLoginName"/>
		    	    </td>
		    	  
		    	    <td>
		    	    </td>
		    	    <td>
		    	    
		    	   		
		    	    </td>
		    	      <td>
		    	    </td>
		    	    <td>
		    	   	 
		    	    </td>
		    	    <td>
		    	   		 
		    	    </td>
		    	  
		    	    <td>
		    	   		 
		    	    </td>
		    	     <td>
		    	   		 
		    	    </td>
	    	</tr>
	    	</tbody>
	    	</table>
	    	</div>
	    </div>
	    
		<div class="panel collapse">
			<h1><s:text name="server_statistics_by_day" /></h1>
			<div>
			 <table class="list" width="100%" style="height:200px" >
	    	<thead>
	    	<tr>
	    		 <th width=70><s:text name="date" /></th>
	    	    <th width=50><s:text name="game" /></th>
	    	    <th width=90><s:text name="server_region" /></th>
	    	    <th width=50><s:text name="the_number_of_rows_of_heavy_login" />(DAU)</th>
	    	         <th width=50>注册人数</th>
	    	    <th width=50><s:text name="re-scheduling" />IP<s:text name="number" /></th>
	    	    <!-- 
	    	    <th><s:text name="the_player_name" /></th>
	    	     -->
	    	    <th width=50><s:text name="new_registration_number" /></th>
	    	    <th width=50>活跃用户<s:text name="number" /></th>
	    	    <th width=50><s:text name="new_role_users" /></th>
	    	    <th width=50><s:text name="the_total_number_of_new_registration" /></th>
	    	    <th width=50><s:text name="the_next_day_active_users_login_to_rate" /></th>
	    	    <th width=50><s:text name="the_next_day_registered_users_login_to_rate" /></th>
	    	    <th width=50><s:text name="avg_length" />(<s:text name="point" />)</th>
	    	    <th width=60><s:text name="the_maximum_number_of_days" />(PCU)</th>
	    	    <th width=50><s:text name="the_average_number_of_online" />(CA)</th>
	    	    <th width=50><s:text name="the_day_is_filled_amount" /></th>
	    	    <th width=50><s:text name="prepaid_account_number" /></th>
	    	    <th width=50><s:text name="the_new_paying_accounts" /></th>
	    	    <th width=50><s:text name="the_amount_consumed_day" /></th>
	    	    <th width=50><s:text name="the_remaining_amount_of_the_day" /></th>
	    	    <th width=50><s:text name="the_number_of_days_of_consumption" /></th>
	    	    <th width=50><s:text name="consume" />ARPU</th>
	    	    <th width=50><s:text name="recharge" />ARPU</th>
	    	    
	    	    <th width=50><s:text name="buy_props_number" /></th>
	    	    <th width=50><s:text name="quantity_to_buy_props" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="dayReports" var="oneRow" status="offset">
	    		<tr>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd" name="#oneRow.kpiDate"/>
						
		    	    </td>
		    	     <td>
						<dict:itemvalue gameId="${oneRow.gameId }" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
							</td>
				<%-- <td>
				<s:property value="@com.jcwx.game.common.constants.PtServerConstant@ptTypeMap[#oneRow.ptId]"/>/<s:property value="#oneRow.ptId"/>
				</td> --%>
				<td>
				 	<dict:itemvalue gameId="${oneRow.gameId }" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				 </td>
		    	    <td>
		    	    	<s:property value="#oneRow.DAU"/>
		    	    </td>
		    	      <td>
		    	    	<s:property value="#oneRow.registeNum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.IPAU"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.newLoginNum"/>
		    	    </td>
		    	      <td>
		    	    	<s:property value="#oneRow.DAU-#oneRow.newLoginNum"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.newRoleNum"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.allNewLoginNum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.nextLoginRate"/>%
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.nextRegLoginRate"/>%
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.avgOnlineTime"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.HPC"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.CA"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.allMoney"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.allPayNum"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.newPayNum"/>
		    	    </td>
		    	    
		    	    <td>
		    	   		<s:property value="#oneRow.consumeMoney"/>
		    	    </td>
		    	    <td>
		    	   		   <s:if test="#oneRow.remainMoney<0.0" >0</s:if>
		    	    <s:else><s:property value="#oneRow.remainMoney"/></s:else>
		    	   		
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.allConsumeNum"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.consumeARPU"/>
		    	    </td>
		    	    <td>
		    	   		<s:property value="#oneRow.payARPU"/>
		    	    </td>
		    	   
		    	    <td>
		    	   		<s:property value="#oneRow.buyPeopleNum"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.buyPropsNum"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	     <s:include value="/admin/template/paging.jsp"/>
	    	</div>
	    </div>
</div>