<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="select.jsp" %>
<s:include value="/admin/template/changeCSS.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	
</script>
 
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="query_getPtKPIDayReport" rel="pagerForm" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
				 <div class="searchDiv_left">
				<input type="hidden" name="">
				<s:hidden name="gameId" id="kpi_every_gameId"/>
			<s:text name="game_platform" />： <s:select list="OssServersPt" label=""
				listKey="serverCode" listValue="serverProvider" name="ptCode" headerKey="" headerValue="%{getText('all_platforms')}"   
				id="kpi_every_ptId"  onchange="initPtCodeParam('kpi_every_gameId',this.value,'kpi_every_areaId');"></s:select> 
					<s:text name="server" />：
				 
						  <s:select list="serverMap" label="" listKey="key" listValue="value" cssStyle="width:150px" name="areas" id="kpi_every_areaId"
			 multiple="true" size="5" headerKey="-1" headerValue="%{getText('all_servers')}"      ></s:select> 
				
				<%-- 来自平台：<select name="ptCode">
								<option value="-1">--全部平台--</option>
									<s:iterator value="ossOperationList" var="oneRows">
										<option value='<s:property value='#oneRows.carrierOperator'/>'
										 <s:if test='ptCode==#oneRows.carrierOperator'> selected </s:if> >
										 <s:property value="#oneRows.operationName"/>
										 </option>
									</s:iterator>
							  </select> --%>
					<s:text name="start_time" />：	<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="date" value="%{beginDate}"></s:textfield>
				<%-- <s:text name="end_time" />： <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="date"></s:textfield> 	 --%>
		</div>	
			 <div class="searchButton">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div>
		</div>
	</s:form>
</div>

  
 <div class="pageContent" layoutH="150" selector="h1">
			 <table class="list" width="100%" style="height:200px" >
	    	<thead>
	    	<tr>
	    		 <th width=70>渠道</th>
	    	    <th width=50>DAU</th>
	    	    <th width=50>活跃用户<s:text name="number" /></th>
	    	    <th width=50>新登录用户</th>
	    	    <th width=50>次日登录率</th>
	    	    <th width=50>三日留存</th>
	    	    <th width=50>七日留存</th>
	    	   
	    	    <th width=50><s:text name="prepaid_account_number" /></th>
	    	    <th width=50><s:text name="the_new_paying_accounts" /></th>
	    	    <th width=50>付费率</th>
	    	    <th width=50>总充值金额</th>
	    	    <th width=50>充值ARPPU</th>
	    	    <th width=50>充值ARPU</th>
	    	    <th width=50>LTV</th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="dayReports" var="oneRow" status="offset">
	    		<tr>
	    		 <td>
		    	    	 
						<s:property value="#oneRow.ptId"/>
		    	    </td>
		    	     <td>
		    	    	 
						<s:property value="#oneRow.DAU"/>
		    	    </td>
		    	     <td>
						<s:property value="#oneRow.DAU-#oneRow.newLoginNum"/>
		    	    </td>
		    	     <td>
						<s:property value="#oneRow.newLoginNum"/>
		    	    </td>
		    	     <td>
		    	     <s:if test="#oneRow.DAU==0||#oneRow.DAU==null" >0.0</s:if>
		    	     <s:else>
		    	     	 <fmt:formatNumber value="${oneRow.nextLoginNum/oneRow.DAU}" pattern="#.##" minFractionDigits="0" /> 
		    	     </s:else>
		    	    </td>
		    	     <td>
		    	      <s:if test="#oneRow.DAU==0||#oneRow.DAU==null" >0.0</s:if>
		    	     <s:else>
		    	     	<fmt:formatNumber value="${oneRow.loginNumDay3/oneRow.DAU}" pattern="#.##" minFractionDigits="0" />  
		    	     </s:else>
		    	    </td>
		    	     <td>
		    	      <s:if test="#oneRow.DAU==0||#oneRow.DAU==null" >0.0</s:if>
		    	     <s:else>
						<fmt:formatNumber value="${oneRow.loginNumDay7/oneRow.DAU}" pattern="#.##" minFractionDigits="0" />		    	     
		    	     </s:else>
		    	    </td>
		    	     <td>
		    	    	 
						<s:property value="#oneRow.allPayNum"/>
		    	    </td>
		    	     <td>
						<s:property value="#oneRow.newPayNum"/>
		    	    </td>
		    	     <td>
		    	      <s:if test="#oneRow.DAU==0||#oneRow.DAU==null" >0.0</s:if>
		    	     <s:else>
		    	     	<fmt:formatNumber value="${oneRow.allPayNum/oneRow.DAU}" pattern="#.##" minFractionDigits="0" />  
		    	     </s:else>
		    	    </td>
		    	    <td>
						<s:property value="#oneRow.allMoney"/>
		    	    </td>
		    	     <td>
		    	      <s:if test="#oneRow.allPayNum==0||#oneRow.allPayNum==null" >0.0</s:if>
		    	     <s:else>
		    	     	<fmt:formatNumber value="${oneRow.allMoney/oneRow.allPayNum}" pattern="#.##" minFractionDigits="0" /> 
		    	     </s:else>
		    	    </td>
		    	     <td>
		    	      <s:if test="#oneRow.DAU==0||#oneRow.DAU==null" >0.0</s:if>
		    	     <s:else>
		    	    	 <fmt:formatNumber value="${oneRow.allMoney/oneRow.DAU}" pattern="#.##" minFractionDigits="0" /> 
		    	     </s:else>
		    	    </td>
		    	      <td>
		    	      	 <s:if test="#oneRow.newLoginNum==0||#oneRow.newLoginNum==null" >0.0</s:if>
			    	     <s:else>
			    	     	<fmt:formatNumber value="${oneRow.newPayMoney/oneRow.newLoginNum}" pattern="#.##" minFractionDigits="0" />  
			    	     </s:else>
						 
		    	    </td>
		    	   <%--  <td>
		    	    	<s:date format="yyyy-MM-dd" name="#oneRow.kpiDate"/>
						
		    	    </td>
		    	     <td>
						<dict:itemvalue gameId="${oneRow.gameId }" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
							</td>
				<td>
				<s:property value="@com.jcwx.game.common.constants.PtServerConstant@ptTypeMap[#oneRow.ptId]"/>/<s:property value="#oneRow.ptId"/>
				</td>
				<td>
				 	<dict:itemvalue gameId="${oneRow.gameId }" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				 </td>
		    	     <td>
		    	   		<s:property value="#oneRow.buyPropsNum"/>
		    	    </td> --%>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	   <%--   <s:include value="/admin/template/paging.jsp"/> --%>
</div>