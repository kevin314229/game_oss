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
	<s:form rel="pagerForm" action="banAccounts_browseBanAccounts" namespace="/admin/base" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		   		<div class="searchDiv_left">
					<s:text name="the_sorting" />：<s:select list="#{'DESC': getText('registration_time')+'↓','ASC': getText('registration_time')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
					<s:text name="state" />：<s:select list="#{'-1': getText('all'),'0': getText('normal'),'1': getText('disable')}" label="" listKey="key" listValue="value" name="playerStatus"></s:select>
					<s:text name="the_keyword" />：<s:textfield name="keyword"/>(<s:text name="the_user_account" />)<s:text name="platform" />
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
	    <table class="list" width="100%" layoutH="90" >
	    	<thead>
	    	<tr>
	    		<th><s:text name="the_user" />ID</th>
	    	    <th><s:text name="the_user_account" /></th>
	    	    <th><s:text name="the_login_number" /></th>
	    	    <th><s:text name="registration_time" /></th>
	    	    <th><s:text name="the_last_login_time" /></th>
	    	    <th><s:text name="online_status" /></th>
	    	    <th><s:text name="banned_state" /></th>
	    	    <th><s:text name="operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="playerBankList" var="oneRow"  status="offset">
	    		<tr  >
	    			 <td>
						 <s:property value="#oneRow.playerId"/>
		    	    </td>
		    	    <td>
		    	    	 <a target="dialog" width="800" href="<%=basePath%>/admin/base/playerInfo_viewPlayInfo.action?playerId=${oneRow.playerId }" style="color:#60A70C;text-decoration: none"><s:property value="#oneRow.loginName"/></a>
		    	    </td>
		    	    <td>
						 <s:property value="#oneRow.loginNumber"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.playerCreateTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.playerLastLogoutTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.onlineStatus"/>
		    	    </td>
		    	      <td>
		    	    	 <s:if test="#oneRow.playerStatus==0"><span class="color-gr"><s:text name="normal"/></span></s:if>
						 <s:else><span class="color-red"><s:text name="banned" /></span></s:else>
		    	    </td>
		    	     <td>
		    	    	<s:if test="#oneRow.playerStatus==0">
		    	    	    <a target="navTab" rel="w_<s:text name="banned_account" />" href="<%=basePath %>/admin/base/banAccounts_banAccounts.action?playerId=${oneRow.playerId}" style="color:#60A70C"><s:text name="banned" /></a>
						</s:if>
		    	    	<s:else>
		    	    		<a target="navTab" rel="w_<s:text name="banned_account" />" href="<%=basePath %>/admin/base/banAccounts_unBanAccounts.action?playerId=${oneRow.playerId}"><s:text name="remove" /></a>
						</s:else>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <s:include value="/admin/template/paging.jsp"/>
	 </div>
