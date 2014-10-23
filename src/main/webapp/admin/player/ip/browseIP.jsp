<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>

  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form rel="pagerForm" action="iPStats_browseIP" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
				<div class="searchDiv_left">
					<s:text name="the_sorting" />：<s:select list="#{'visitNumDESC': getText('visits')+'↓','visitNumASC': getText('visits')+'↑','lastLoginDateDESC': getText('last_login_time')+'↓','lastLoginDateASC': getText('last_login_time')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
		
					<s:text name="statistical_time" />：
					<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
		
					<s:text name="to" /> 
					<s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
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
	    	    <th><s:text name="the_serial_number" /></th>
	    	    <th>IP<s:text name="address" /></th>
	    	    <th><s:text name="last_login_time" /></th>
	    	    <th><s:text name="the_login_number" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ipstatsList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
		    	    	<s:property value="#offset.index + 1"/>
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
		    	    <td> 
		    	    	<s:property value="#oneRow.visitNum"/>
		    	    </td>
		    	    
		    	  
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	     <s:include value="/admin/template/paging.jsp"/>
	 </div>
