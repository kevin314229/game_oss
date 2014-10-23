<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
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
		<s:form rel="pagerForm" action="/zhxy/player/zhplayerInfo_browsePlayerBaseInfo.action" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				     <div class="searchDiv_left">
							<s:text name="the_sorting" />：<s:select list="#{'DESC': getText('level')+'↓','ASC': getText('level')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
						 
							<s:text name="the_keyword" />：<s:textfield name="keyword"/>(<s:text name="user_roles" />)
							 <s:text name="level" />：<s:textfield name="level" size="8"/>
							 物品：<s:textfield name="itemName" size="12"/>
							 <s:text name="platform_entrance" />：<select name="ptCode">
							
								<option value="">--<s:text name="whole" />--</option>
									<s:iterator value="ossOperationList" var="oneRows">
										<option value='<s:property value='#oneRows.carrierOperator'/>'
										 <s:if test='ptCode==#oneRows.carrierOperator'> selected </s:if> >
										 <s:property value="#oneRows.operationName"/>
										 </option>
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
	    <table class="list" width="100%" layoutH="62" >
	    	<thead>
	    	<tr>
	    	    <th><s:text name="role" />ID</th>
	    	    <th><s:text name="nickname" /></th>
	    	    <th><s:text name="platform" /></th>
	    	    <th><s:text name="level"/></th>
	    	    <th><s:text name="professional" /></th>
	    	    <th width="23%"><s:text name="operation" /></th>
	    	   
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="playerBaseClassList" var="oneRow"  status="offset">
	    		<tr >
		    	    <td>
						 <s:property value="#oneRow.playerBaseId"/>
		    	    </td>
		    	    <td>
						 <s:property value="#oneRow.nickName"/>
		    	    </td>
		    	     <td>
					 <dict:itemvalue gameId="${session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId}" type="4"  itemValue="${oneRow.carrierOperator }"></dict:itemvalue>

		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.level"/>
		    	    </td>
		    	     <td>
		    	    	<s:if test="#oneRow.occupation == 1"><s:text name="monkey" /></s:if>
		    	    	<s:if test="#oneRow.occupation == 2"><s:text name="gods" /></s:if>
		    	    	<s:if test="#oneRow.occupation == 3"><s:text name="xuannv" /></s:if>
		    	   </td>
		    	    <td>
		    	    	<a target="dialog" max="true" href="<%=basePath%>/zhxy/player/zhplayerInfo_viewPlayBaseInfo.action?playerBaseId=${oneRow.playerBaseId }" style="color:green"><s:text name="detailed" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
		    	    	<a target="dialog"  href="<%=basePath%>/zhxy/player/zhplayerInfo_playerFriendInfo.action?playerBaseId=${oneRow.playerBaseId }" style="color:green"><s:text name="friends_information" /> </a>
		    	    </td>
		    	   
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <div class="panelBar" >
			<s:include value="/admin/template/paging.jsp"/>
		</div>
	 </div>
