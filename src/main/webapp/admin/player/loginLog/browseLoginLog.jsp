<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		
			<b><s:text name="view_the_login_history_in_detail" />:</b><br/>
			<span class="color-red"><s:text name="players_account" />：${loginName}</span><br />
			<span class="color-red"><s:text name="the_player_name" />：${nickName} </span>
			<span class="color-red"><s:property value="errorInfo"/></span>
			<span class="color-gr"><s:property value="successInfo"/></span>
	
			<s:form action="browseLogin_browseLoginLog" namespace="/admin/base" method="post" theme="simple" id="baseFrom1" onsubmit="return dialogSearch(this)">
			<div class="searchBar">
					<div class="searchDiv_left">
							<s:hidden name="loginName" />
							<s:hidden name="nickName" />
							<s:hidden name="playerID" />
							<s:hidden name="currPageNO" id="currPageNO"></s:hidden>
							<s:text name="the_sorting" />：<s:select list="#{'DESC': getText('the_login_time')+'↓','ASC': getText('the_login_time')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
							<s:text name="statistical_time"/>：
							<s:textfield id="beginDate" name="beginDate" maxlength="20" size="12"  cssClass="required date" format="yyyy-MM-dd "></s:textfield>
					    	<s:text name="to" /> 
							<s:textfield id="endDate" name="endDate" maxlength="20" size="12" cssClass="required date" format="yyyy-MM-dd "></s:textfield>
							<s:text name="the_keyword" />：<s:textfield name="keyword"/>(IP)
					</div>
					<div style=" float:left;margin-left:10px;width:100px">
						<ul>
							<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
						</ul>
					</div>
			</div>
				
			 </s:form> 
		</div>
	<div class="pageContent" layoutH="100">
	    <table  class="list" width="100%">
	    
	    	<thead>	
	    	<tr>
	    	    <th><s:text name="the_log_number" /></th>
	    	    <th><s:text name="the_login" />IP</th>
	    	    <th><s:text name="the_login_time" /></th>
	    	</tr>
	    	</thead>
	    	
	    	<tbody>
	    	<s:iterator value="loginLogList" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
		    	    	<s:property value="#oneRow.loginLogID"/>
		    	    </td>
		    	    <td> 
		    	    	<s:property value="#oneRow.ip"/>
		    	    </td>
		    	    <td> 
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.loginTime"/>
		    	    	<s:if test="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.loginTime)==0">
		    	    		[<span class="color-gr">24<s:text name="hours" /></span>]
		    	    	</s:if>
		    	    	<s:elseif test="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.loginTime)<7">
		    	    		[<span class="color-gr"><s:property value="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.loginTime)"/></span>]
		    	    	</s:elseif>
		    	    	<s:else>
		    	    		[<span class="color-red"><s:property value="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.loginTime)"/></span>]
		    	    	</s:else>
		    	    	<s:if test="@com.jcwx.game.common.DateService@distanceNowDay(#oneRow.loginTime)>=30"><span class="color-red"><s:text name="more_than_a_month" /></span></s:if>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    	
	    </table>
	    
	 </div>

