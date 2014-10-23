<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ include file="select.jsp" %>
<s:include value="/admin/template/changeCSS.jsp"/>
<s:include value="/admin/template/scriptMessage.jsp"/>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
<s:form action="playerBaseRank_info" rel="pagerForm" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<input type="hidden" name="gameId" value="${gameId}" />
		<input type="hidden" name="areaId" value="${areaId}" />		
		<input type="hidden" name="ptId" value="${ptId}" />		
		<input type="hidden" name="playerBaseId" value="${playerBaseId}" />		
 </s:form>
  <div class="pageContent">
	    <table class="list" width="100%" layoutH="28" >
	    	<thead>
	    	<tr>
	    	   <th><s:text name="game" /></th>
	    	     <th><s:text name="game_platform" /></th>
	    	    <th><s:text name="server_region" /></th>
	    	   <th><s:text name="account" />(<s:text name="login_name" />)</th>
	    	    <th><s:text name="role_name" /></th>
	    	    <th><s:text name="currency_recharge_platform" /></th>
	    	     <th><s:text name="the_total_amount_of_recharge" /></th>
	    	      <th><s:text name="recharge_time" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="payInfoList" var="oneRow" status="offset">
	    		<tr>
		    	   	     <td>
				<dict:itemvalue gameId="${oneRow.gameId }" type="3"  itemValue="${oneRow.gameId }"></dict:itemvalue>
				</td>
				<td>
				<dict:itemvalue gameId="${oneRow.gameId }" type="4"  itemValue="${oneRow.ptId }"></dict:itemvalue>
				<%-- <s:property value="@com.jcwx.game.common.constants.PtServerConstant@ptTypeMap[#oneRow.ptId]"/>/<s:property value="#oneRow.ptId"/> --%>
				</td>
				<td>
				<dict:itemvalue gameId="${oneRow.gameId }" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
				<%--  <s:property value="@com.jcwx.game.common.constants.OssServerConstant@ossServerMap[#oneRow.areaId]"/>  --%>
				 </td>
		    	   
		    	    <td>
		    	   		<s:property value="#oneRow.loginName"/>
		    	    </td>
		    	      <td>
		    	   		<s:property value="#oneRow.nickName"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.ptMoneyNum"/>
		    	    </td>
		    	     <td>
		    	   		<s:property value="#oneRow.moneyNum"/>
		    	    </td>
		    	      <td>
		    	   		<s:date format="yyyy-MM-dd hh:mm:ss" name="#oneRow.completeTime"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	      <s:include value="/admin/template/paging.jsp"/>
  </div>
