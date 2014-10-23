<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="queryPayRank" rel="pagerForm" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div class="searchDiv_left">
					<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="date"></s:textfield>
			
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
			</div>
		 <div style="float:left;margin-left:15px;width:200px">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
			</ul>
		 </div>
		 
		</div>
	</s:form>
</div>

  <div class="pageContent">
	    <table  class="list" width="100%" layoutH="65">
	    	<thead>
	    	<tr>
	    	    <th colspan="12" style="text-align:center">
	    	    	<s:text name="statistical_time_range" />：
	    	    	<s:if test="beginDate != null && beginDate != ''">
	    	    		<s:property value="beginDate"/> 
	    	    	</s:if>
	    	    	<s:else>  
					  	2011-01-01
					</s:else> 
	    	    	00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
	    	    </th>
	    	</tr>
	    	<tr>
	    	    <th ><s:text name="ranking" /></th>
	    	    <th ><s:text name="account" />(<s:text name="platform" />#<s:text name="account" />)</th>
	    	    <th ><s:text name="top-up_amount" /></th>
	    	    <th ><s:text name="the_last_time_to_charge_a" /></th>
	    	    <th><s:text name="days_didn't_log_in_the_game" /></th>
	    	    <th><s:text name="prepaid_phone_number" /></th>
	    	    <th><s:text name="the_first_rush_of_time" /></th>
	    	    
	    	    <!-- 
	    	    <th ><s:text name="call_the_police" /></th>
	    	    <th ><s:text name="pay_cycle" />(<s:text name="minutes" />)</th>
	    	    <th ><s:text name="countries" /></th>
	    	    <th ><s:text name="level" /></th>
	    	    <th ><s:text name="tribal" /></th>
	    	     -->
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="payHistoryRankList" var="onePay" status="offset">
	    		<tr>
		    	    <td>
		    	    	<s:property value="#onePay.rank"/>
		    	    </td>
		    	    <td >
						<a target="dialog" width="900" height="800" href="<%=basePath%>/admin/base/playerInfo_viewPlayInfo.action?playerId=${onePay.playerId }" style="color:#60A70C;text-decoration: none"><s:property value="#onePay.loginName"/></a>
		    	    </td>
		    	    <!-- 
		    	    <td>
						<s:property value="#onePay.loginName"/>
		    	    </td>
		    	     -->
		    	    <td>
		    	    	<s:property value="#onePay.amountSum"/>
		    	    </td>
		    	    <!-- 
		    	    <td>
		    	    	<s:property value="#onePay.payNum"/>
		    	    </td>
		    	     -->
		    	    <!-- 
		    	    <td <s:if test="#onePay.lastPayToNowDays >= 7"> class="color-red" </s:if>>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#onePay.payTime"/>
		    	    </td>
		    	    --> 
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#onePay.lastPayTime"/>
		    	    	<s:if test="@com.jcwx.game.common.DateService@distanceNowDay(#onePay.lastPayTime)==0">
		    	    		[<span class="color-gr">24<s:text name="hours" /></span>]
		    	    	</s:if>
		    	    	<s:elseif test="@com.jcwx.game.common.DateService@distanceNowDay(#onePay.lastPayTime)<7">
		    	    		[<span class="color-gr"><s:property value="@com.jcwx.game.common.DateService@distanceNowDay(#onePay.lastPayTime)"/></span>]
		    	    	</s:elseif>
		    	    	<s:else>
		    	    		[<span class="color-red"><s:property value="@com.jcwx.game.common.DateService@distanceNowDay(#onePay.lastPayTime)"/></span>]
		    	    	</s:else>
		    	    	<s:if test="@com.jcwx.game.common.DateService@distanceNowDay(#onePay.lastPayTime)>=30"><span class="color-red"><s:text name="more_than_a_month" /></span></s:if>
		    	    </td>
		    	    
		    	    
		    	    <td class="color-red">
		    	    	<s:property value="#onePay.noLoginDays"/> 
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.payNum"/> 
		    	    </td>
		    	    
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#onePay.firstPayTime"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
    	 <s:include value="/admin/template/paging.jsp"/>
  </div>
