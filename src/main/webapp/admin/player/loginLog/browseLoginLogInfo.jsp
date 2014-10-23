<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
		<s:form rel="pagerForm" action="browseLogin_browseLoginLogInfo" accept-charset="utf-8" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return document.charset='utf-8'&&$(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
						<div class="searchDiv_left">
							<s:text name="the_sorting" />：<s:select list="#{'lastLoginDateDESC': getText('last_login_time')+'↓','lastLoginDateASC': getText('last_login_time')+'↑','loginAmountDESC': getText('the_login_number')+'↓','loginAmountASC': getText('the_login_number')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
				
							<s:text name="the_keyword" />：<s:textfield name="keyword"/>(<s:text name="players_account" />、<s:text name="the_player_name" />)
							登录时间：<s:textfield id="loginTime" name="loginTime" maxlength="10" size="12" cssClass="required date" readonly="true" > </s:textfield>
						</div>
		    	<div class="searchButton2">
					<ul>
						<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
					</ul>
				</div>
				</div>
		</s:form>
	</div>
	
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="65" >
	    	<thead>
	    	<tr>
	    	    <th><s:text name="the_player_number" /></th>
	    	    <th><s:text name="players_account" /></th>
	    	    <th><s:text name="the_player_name" /></th>
	    	    <th><s:text name="the_login_number" /></th>
	    	    <th><s:text name="history" />IP <s:text name="the_number" /></th>
	    	    <th><s:text name="last_login_time" />(<s:text name="from_today" />)</th>
	    	    <th><s:text name="the_last_login" />IP</th>
	    	    <th><s:text name="operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="loginLogInfoList" var="oneRow"  status="offset">
	    		<tr>
		    	    <td>
		    	    	<s:property value="#oneRow.playerID"/>
		    	    </td>
		    	    <td>
		    	    	<a target="dialog" width="900" height="800" href="<%=basePath%>/admin/base/playerInfo_viewPlayInfo.action?playerId=${oneRow.playerID }" style="color:green">	<s:property value="#oneRow.loginName"/></a>
		    	    </td>
		    	    <td> 
		    	    	<s:property value="#oneRow.nickName"/>
		    	    </td>
		    	    <td> 
		    	    	<span class="color-gr"><s:property value="#oneRow.loginAmount"/></span>
		    	    </td>
		    	    <td> 
		    	    	<span style="color:blue"><s:property value="#oneRow.historyIPAmount"/></span>
		    	    </td>
		    	    <td> 
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.lastLoginDate"/>&nbsp;
		    	    	<s:if test="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.lastLoginDate)==0">
		    	    		[<span class="color-gr">24<s:text name="hours" /></span>]
		    	    	</s:if>
		    	    	<s:elseif test="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.lastLoginDate)<7">
		    	    		[<span class="color-gr"><s:property value="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.lastLoginDate)"/></span>]
		    	    	</s:elseif>
		    	    	<s:else>
		    	    		[<span class="color-red"><s:property value="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.lastLoginDate)"/></span>]
		    	    	</s:else>
		    	    	<s:if test="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.lastLoginDate)>=30"><span class="color-red"><s:text name="more_than_a_month" /></span></s:if>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.lastLoginIP"/>
		    	    </td>
		    	    <td> 
		    	    	<a target="dialog" width="800" href="<%=basePath %>/admin/base/browseLogin_browseLoginLog.action?playerID=${oneRow.playerID}&keyword=${oneRow.lastLoginIP}&loginName=${oneRow.loginName}&nickName=${oneRow.nickName}" style="color:green"><s:text name="detailed" /></a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    	    <s:include value="/admin/template/paging.jsp"/>
	 </div>
	 
