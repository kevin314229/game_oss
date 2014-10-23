<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="http://jcwx.oss.com/page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="page.currPageNO" value="${page.currPageNO}" />
	<input type="hidden" name="page.onePageNum" value="${page.onePageNum}" />
</form>

<s:include value="/admin/template/scriptMessage.jsp"/>    
<div class="pageHeader">
		<s:form rel="pagerForm" action="/cjwz/player/playerManage_banPlayerChat.action" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
					<td>
				    <div class="searchDiv_left">
				    		<s:hidden name="playerStatus" value="1"></s:hidden>
				    		<s:text name="the_player_role_name" />：
				    		<s:textfield name="nickName"></s:textfield>
							<s:text name="silence_of_the_time" />： <input id="endTime${oneRow.loginName}" name="endTime"
							class="date textInput readonly" datefmt="yyyy-MM-dd HH:mm:ss"
							readonly="true" type="text">
							(<s:text name="unit" />：<s:text name="minutes" />，
							<s:text name="banned_advice" />30<s:text name="minutes_is_ok" />)<br/>
					</div>
					</td>
					<td>
			    	<div class="searchButton2">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="set_the_silence"/></button></div></div></li>
						</ul>
					</div>
					</td>
					</tr>
				</table>
					
			</div>
		</s:form>
</div>
	
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="65" >
	    	<thead>
	    	<tr>
	    		<th><s:text name="player_characters" />ID</th>
	    	    <th><s:text name="the_player_role_name" /></th>
	    	    <th>禁言结束时间</th>
	    	    <th><s:text name="operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="jsonArrayList" var="oneRow"  status="offset">
	    		<tr  >
	    			<td>
						 <s:property value="#oneRow.id"/>
		    	    </td>
		    	    <td>
						 <s:property value="#oneRow.nickName"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.endTime"/>
		    	    </td>
		    	    <td>
						 <a target="navTab" rel="w_<s:text name="banned_account" />"  href="<%=basePath%>/cjwz/player/playerManage_banPlayerChat.action?playerStatus=0&playerId=${oneRow.id}" style="color:#60A70C"><s:text name="remove_banned" /></a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	
	    </table>
	    <page:list page="${page}" />
	 </div>
