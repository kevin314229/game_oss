<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="http://jcwx.oss.com/page" %>
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
	<s:form rel="pagerForm" action="queryPay" method="post" theme="simple" namespace="/zhxy/pay" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		 		<div class="searchDiv_left">
					<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
			
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
		
					<s:text name="login_name" />：<s:textfield name="loginName" size="16" id="loginName"></s:textfield>
			平台订单号：<s:textfield name="ptOrder" size="16" id="ptOrder"></s:textfield>
			
					<s:text name="or_the_role_name" />：<s:textfield name="nickName" size="12" id="nickName"></s:textfield>
					<s:text name="platform" />：<select name="ptCode">
								<option value="">--<s:text name="all" />--</option>
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
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
				</ul>
				</div>
			
			</div>
	 </s:form>
	</div>
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="45" >
	    	<thead>
	    	<tr>
	    	    <th colspan="13" style="text-align:center">
	    	    	<s:text name="statistical_time_range" />：
	    	    	<s:if test="beginDate != null && beginDate != ''">
	    	    		<s:property value="beginDate"/> 
	    	    		</s:if>
	    	    	<s:else>
					  	2011-01-01
					</s:else> 
	    	    	00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
	    	    </th>
	    	</tr>
	    	<tr>
	    		
	    	    <th><s:text name="top-up_completion_time" /></th>
	    	    <th><s:text name="top-up_amount" /></th>
	    	    <th><s:text name="account" /></th>
	    	    <th><s:text name="platform_for_order_no." /></th>
	    	    <th><s:text name="the_game_order_number" /></th>
	    	    <th><s:text name="the_order_status" /> (0<s:text name="normal" />，2<s:text name="abnormal" />)</th>
	    	    <th><s:text name="goods" />ID</th>
	    	    <th><s:text name="the_number" /></th>
	    	    <th><s:text name="the_order_note" /></th> 
	    	    <th><s:text name="platform_identity" /></th>
	    	    <%-- <th><s:text name="platform_regions" /></th> --%>
	    	    <th><s:text name="the_game_account" />ID</th>
	    	    <th><s:text name="game_characters" />ID</th>
	    	    
	  
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="payHistoryList" var="onePay" status="offset">
	    		<tr>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#onePay.completeTime"/>
		    	    </td>
		    	    <td>
						<s:property value="#onePay.rmb"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.ptLoginName"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.ptOrder"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.id"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.orderStatus"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#onePay.goodId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.goodNum"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#onePay.remark"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#onePay.ptCode"/>
		    	    </td>
		    	     <%-- <td>
		    	    	<s:property value="#onePay.ptArea"/> 
		    	    </td> --%>
		    	     <td>
		    	    	<s:property value="#onePay.playerId"/> 
		    	    </td>
		    	     <td>
		    	    	<s:property value="#onePay.playerBaseId"/> 
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <s:include value="/admin/template/paging.jsp"/>
<%-- 	    
		
		 --%>
		<%-- <%-- <page:list page="${page}" /> --%> 
		
<%-- 	<s:page obj="page"/>
	
	<s:page obj="pageX"/> --%>
 </div>

  