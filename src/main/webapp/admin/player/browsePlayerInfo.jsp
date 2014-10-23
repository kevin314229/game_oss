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
		<s:form rel="pagerForm" action="/admin/base/playerInfo_browsePlayerInfo.action" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
			   <div class="searchDiv_left">
							<s:text name="the_sorting" />：<s:select list="#{'DESC': getText('registration_time')+'↓','ASC': getText('registration_time')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
						 
							<s:text name="registration_time" />：
							<s:textfield id="beginTime" name="beginTime" maxlength="10" size="12" cssClass="required date">
								　<s:param name="value"><s:date name="beginTime" format="yyyy-MM-dd"/></s:param>  
							</s:textfield>
						 
							<s:text name="to" /> 
							<s:textfield id="endTime" name="endTime" maxlength="10" size="12" cssClass="required date">
								<s:param name="value"><s:date name="endTime" format="yyyy-MM-dd"/></s:param>
							</s:textfield>
						 
							<s:text name="the_keyword" />：<s:textfield name="keyword"/>(<s:text name="the_user_account" />)
						 
							<s:text name="platform_entrance" />
		
							<select  name="plat">
									<option value="0">-<s:text name="whole" />-</option>
									<s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossServersPt" var="oneRow"  status="offset">
										<s:if test="#request.plat == #oneRow.serverCode">
										<option value="<s:property value="#oneRow.serverCode" />" selected> 	
						    				<s:property value="#oneRow.serverProvider"/>
						    			</option>
										</s:if> 
										<s:else>
										<option value="<s:property value="#oneRow.serverCode" />"  > 	
						    				<s:property value="#oneRow.serverProvider"/> 
						    			</option>
										</s:else>
						    		</s:iterator>
							</select>
						 
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
	    <table class="list" width="100%" layoutH="64" >
	    	<thead>
	    	<tr>
	    	    <th><s:text name="the_player" />ID</th>
	    	    <th><s:text name="the_user_account" /></th>
	    	    <th><s:text name="registration_time" /></th>
	    	    <th><s:text name="the_last_login_time" /></th>
	    	    <th><s:text name="monster_crystal" /></th>
	    	    <th><s:text name="state" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="playerClassList" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
						 <s:property value="#oneRow.playerId"/>
		    	    </td>
		    	    <td>
		    	  		  <a target="dialog" width="900" height="800" href="<%=basePath%>/admin/base/playerInfo_viewPlayInfo.action?playerId=${oneRow.playerId }" style="color:green"><s:property value="#oneRow.loginName"/></a>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.playerCreateTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.lastLoginTime"/>
		    	    </td>
		    	      <td>
						 <s:property value="#oneRow.gold"/>
		    	    </td>
		    	    <td>
		    	    	<s:if test="oneRow.onlineStatus == 1"><s:text name="online" /></s:if>
						<s:else><s:text name="offline" /></s:else>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	   <s:include value="/admin/template/paging.jsp"/>
	 </div>
