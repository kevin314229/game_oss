<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>    
<div class="pageHeader">
		<s:form action="/admin/player/banChat_addPlayerBanChat.action" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			<div class="searchBar">
			
				    <div class="searchDiv_left">
				    		<s:text name="the_player_role_name" />：
				    		<s:textfield name="nickName"></s:textfield>
				    	
							<s:text name="silence_of_the_time" />：<s:textfield name="banChatMinus" value="30"></s:textfield> 
							(<s:text name="unit" />：<s:text name="minutes" />，
							<s:text name="banned_advice" />30<s:text name="minutes_is_ok" />)<br/>
					</div>
					
			    	<div class="searchButton2">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="set_the_silence"/></button></div></div></li>
						</ul>
					</div>
			</div>
		</s:form>
</div>
	
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="50" >
	    	<thead>
	    	<tr>
	    		<th><s:text name="player_characters" />ID</th>
	    	    <th><s:text name="the_player_role_name" /></th>
	    	    <th><s:text name="silence_start_time" /></th>
	    	    <th><s:text name="how_many_minutes_of_silence" /></th>
	    	    <th><s:text name="operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="playerBanChatList" var="oneRow"  status="offset">
	    		<tr  >
	    			<td>
						 <s:property value="#oneRow.playerBaseId"/>
		    	    </td>
		    	    <td>
						 <s:property value="#oneRow.nickName"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.startTime"/>
		    	    </td>
		    	    <td>
						 <s:property value="#oneRow.banChatMinus"/>
		    	    </td>
		    	    <td>
						 <a target="navTab" href="<%=basePath%>/admin/player/banChat_deletePlayerBanChat.action?playerBaseId=${oneRow.playerBaseId}" style="color:#60A70C"><s:text name="remove_banned" /></a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</thead>
	    </table>
	 </div>
