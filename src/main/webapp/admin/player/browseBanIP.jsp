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
	<s:form rel="pagerForm" action="banIP_browseBanIP" namespace="/admin/base" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		   		<div class="searchDiv_left">
					<s:text name="the_sorting" />：<s:select list="#{'DESC': getText('silence_over_time')+'↓','ASC': getText('silence_over_time')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
					<s:text name="state"/>：<s:select list="#{'-1': getText('all'),'0': getText('normal'),'1': getText('banned')}" label="" listKey="key" listValue="value" name="banState"></s:select>
				
					<s:text name="the_keyword" />：<s:textfield name="keyword"/>(IP)
				</div>
			
		    	<div class="searchButton2">
					<ul>
						<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
						<li><a class="button" target="dialog" href="<%=basePath %>/admin/base/banIP_toAddBanIP.action" rel="w_<s:text name="ban" />IP"><span><s:text name="add_the_banned" />IP</span></a></li>
					</ul>
				</div>
			</div>
	 </s:form>
	</div>
	
	
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="65" >
	    	<thead>
	    	<tr>
	    	    <th>IP</th>
	    	    <th><s:text name="seizure_of_state" /></th>
	    	    <th><s:text name="history_of_seizure_frequency" /></th>
	    	    <th><s:text name="banned_over_time" /></th>
	    	    <th><s:text name="operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="blackListList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
						 <s:property value="#oneRow.ip"/>
		    	    </td>
		    	    <td>
						 <s:if test="#oneRow.banState==0"><span class="color-gr"> getText('normal')</span></s:if>
						 <s:else><span class="color-red"> <s:text name="banned"/></span></s:else>
		    	    </td>
		    	    <td>
						 <s:property value="#oneRow.banTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.banEndTime"/>
		    	    </td>
		    	   <td>
		    	    	<s:if test="#oneRow.banState==0">
		    	    	    <a target="navTab" rel="w_<s:text name="ban" />IP" href="<%=basePath %>/admin/base/banIP_banIP.action?playerIP=${oneRow.ip}" style="color:#60A70C" title="<s:text name="the_default_add" />30<s:text name="day" />，<s:text name="please_go_to_the_add_in_setting_in_detail" />"> getText('banned')</a>
						</s:if>
		    	    	<s:else>
		    	    		<a target="navTab" rel="w_<s:text name="ban" />IP" href="<%=basePath %>/admin/base/banIP_UnbanIP.action?playerIP=${oneRow.ip}" style="color:#60A70C"><s:text name="remove" /></a>
						</s:else>
		    	    </td>
		    	    
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <page:list page="${page}"/>
</div>
