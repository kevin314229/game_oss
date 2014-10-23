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
		<s:form rel="pagerForm" action="/admin/base/playerInfo_browsePlayerBaseInfo.action" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				<div class="searchDiv_left">
							<s:text name="the_sorting" />：<s:select list="#{'DESC': getText('level')+'↓','ASC': getText('level')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
						 
							<s:text name="the_keyword" />：<s:textfield name="keyword"/>(<s:text name="user_roles" />)
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
	    <table class="list" width="100%" layoutH="63" >
	    	<thead>
	    	<tr>
	    	    <th><s:text name="role" />ID</th>
	    	    <th><s:text name="nickname" /></th>
	    	    <th><s:text name="level"/></th>
	    	    <th><s:text name="professional" /></th>
	    	    <th><s:text name="operation" /></th>
	    	   
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
						 <s:property value="#oneRow.level"/>
		    	    </td>
		    	     <td>
		    	    	<s:if test="#oneRow.occupation == 1"><s:text name="a_warrior" /></s:if>
		    	    	<s:if test="#oneRow.occupation == 2"><s:text name="the_mage" /></s:if>
		    	    	<s:if test="#oneRow.occupation == 3"><s:text name="the_archer" /></s:if>
		    	   </td>
		    	    <td>
		    	    	<a target="dialog" max="true" href="<%=basePath%>/admin/base/playerInfo_viewPlayBaseInfo.action?playerBaseId=${oneRow.playerBaseId }" style="color:green"><s:text name="detailed" /></a>
		    	    </td>
		    	   
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <div class="panelBar" >
			<s:include value="/admin/template/paging.jsp"/>
		</div>
	 </div>
