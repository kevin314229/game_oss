<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<%@ taglib prefix="page" uri="http://jcwx.oss.com/page"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="page.currPageNO" value="${page.currPageNO}" />
	<input type="hidden" name="page.onePageNum" value="${page.onePageNum}" />
	<input type="hidden" name="orderField" value="${page.orderFlag}" /><!--【可选】查询排序字段-->
    <input type="hidden" name="page.orderFlag" value="${page.orderFlag}" /><!--【可选】升序|降序-->
	 
</form>

  <s:include value="/admin/template/scriptMessage.jsp"/>

	

	<div class="pageContent">
	<s:form rel="pagerForm" action="queryAllPlayer" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
	 </s:form>
	    <table class="list" width="100%" layoutH="28" >
	    	<thead>
	    	<tr>
	    	    <th><s:text name="the_player" />ID</th>
	    	    <th><s:text name="the_user_account" /></th>
	    	    <th class="${fn:toLowerCase(page.orderFlag)}" orderField="${fn:toUpperCase(page.orderFlag)=='ASC'?'DESC':'ASC'}"><s:text name="registration_time" /></th>
	    	    <th><s:text name="the_last_login_time" /></th>
	    	    <th><s:text name="monster_crystal_quantity" /></th>
	    	    
	    	    <!--  <th><s:text name="the_login" />IP</th>
	    	    <th><s:text name="players_level" /></th>-->
	    	    
	    	    <th><s:text name="state" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="playerClassList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
						 <s:property value="#oneRow.playerId"/>
		    	    </td>
		    	    <td>
		    	    	<a target="dialog" width="800" height="600" href="<%=basePath%>/admin/base/playerInfo_viewPlayInfo.action?playerId=<s:property value="#oneRow.playerId"/>" style="color:green" rel="<s:property value="#oneRow.playerId"/>" width="1000">	<s:property value="#oneRow.loginName"/></a>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd hh:mm:ss" name="#oneRow.playerCreateTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd hh:mm:ss" name="#oneRow.lastLoginTime"/>
		    	    </td>
		    	  <!--   <td>
		    	    	<s:property value="#oneRow.lastLoginIP"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.level"/>
		    	    </td> -->
		    	    
		    	      <td>
						 <s:property value="#oneRow.gold"/>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#oneRow.onlineStatus == 1"><s:text name="online" /></s:if>
						<s:else><s:text name="offline" /></s:else>
						
						 
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <page:list page="${page}"/>
	 </div>
