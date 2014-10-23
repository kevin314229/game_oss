<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
#queryKeepPay_tb1 td span,#queryKeepPay_tb2 td span{
 	color: green;
 }
</style>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form action="/admin/assay/queryKeepPay.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				    <div class="searchDiv_left">
							<s:text name="statistical_time" />：
							<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
							 <s:text name="to" /> 
							 <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
						 
							<s:if test="#session.ADMIN_SYSTEM_USER_NAME.ossUser.isOperator==1">
						    <s:text name="regional" />：
						     <select name="selectArray">
									<s:iterator value="ossServersList" var="oneRow"  status="offset">
										<s:if test="selectArray==#oneRow.id">
										<option value="<s:property value="#oneRow.id" />" selected> 	
						    				<s:property value="#oneRow.name"/>
						    			</option>
										</s:if> 
										<s:else>
										<option value="<s:property value="#oneRow.id" />"  > 	
						    				<s:property value="#oneRow.name"/> 
						    			</option>
										</s:else>
						    		</s:iterator>
							</select>
						    </s:if>
						 </div>
			    	<div class="searchButton2">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
						</ul>
					</div>
				</div>
		</s:form>
</div>

	
	<div class="pageContent sortDrag" selector="h1" layoutH="50">
		<div class="panel collapse">
		<h1><s:text name="the_current_regional" /></h1>
			<div>
			    <table class="list" width="100%" id="queryKeepPay_tb1">
			    	<thead>
			    	<tr>
						<th><s:text name="the_date_of" /></th>
						<th><s:text name="on_the_day_of_registration_number" /></th>
						<th><s:text name="on_the_day_of_the_new" /></th>
						<th><s:text name="the_next_day_the_new" /></th>
						<th><s:text name="on_the_third_day_of_new" /></th>
						<th><s:text name="the_fourth_day_of_new" /></th>
						<th><s:text name="the_fifth_day_of_new" /></th>
						<th><s:text name="the_sixth_day_of_new" /></th>
						<th><s:text name="on_the_seventh_day_of_new" /></th>
						<th><s:text name="day_14_new" /></th>
						<th><s:text name="30_days_new" /></th>
					</tr>
					<thead>
					<tbody>
			    	<s:iterator value="keepList" var="oneRow"  status="offset">
			    		<tr >
				    	    <td>
				    	    	<s:date format="yyyy-MM-dd" name="#oneRow.id"/>
				    	    </td>
				    	    <td>
								<s:property value="#oneRow.day1"/>
							</td>
							<td>
								<s:property value="#oneRow.newPayDay1"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay1/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
							</td>
							<td>
								<s:property value="#oneRow.newPayDay2"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay2/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
							</td>
							<td>
								<s:property value="#oneRow.newPayDay3"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay3/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
							</td>
							<td>
								<s:property value="#oneRow.newPayDay4"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay4/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
							</td>
							<td>
								<s:property value="#oneRow.newPayDay5"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay5/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
							</td>
							<td>
								<s:property value="#oneRow.newPayDay6"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay6/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
							</td>
							<td>
								<s:property value="#oneRow.newPayDay7"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay7/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
							</td>
							<td>
								<s:property value="#oneRow.newPayDay14"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay14/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
							</td>
							<td>
								<s:property value="#oneRow.newPayDay30"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay30/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
			    </table>
		    </div>
		</div>
	
	
	<div class="panel collapse">
		<h1><s:text name="each_access_operators_in_detail" /></h1>
			<div>
				<table class="list" width="100%" id="queryKeepPay_tb2" >
				<thead>
		    	<tr>
					<th><s:text name="the_date_of" /></th>
					<th><s:text name="operator" /></th>
					<th><s:text name="on_the_day_of_registration_number" /></th>
					<th><s:text name="on_the_day_of_the_new" /></th>
					<th><s:text name="the_next_day_the_new" /></th>
					<th><s:text name="on_the_third_day_of_new" /></th>
					<th><s:text name="the_fourth_day_of_new" /></th>
					<th><s:text name="the_fifth_day_of_new" /></th>
					<th><s:text name="the_sixth_day_of_new" /></th>
					<th><s:text name="on_the_seventh_day_of_new" /></th>
					<th><s:text name="day_14_new" /></th>
					<th><s:text name="30_days_new" /></th>
				</tr>
				</thead>
				<tbody>
		    	<s:iterator value="allKeepList" var="oneRow"  status="offset">
		    		<tr >
			    	    <td>
			    	    	<s:date format="yyyy-MM-dd" name="#oneRow.id"/>
			    	    </td>
			    	    <td>
			    	    	<s:property value="#oneRow.carrierOperator"/>
			    	    </td>
			    	    <td>
							<s:property value="#oneRow.day1"/>
						</td>
						<td>
							<s:property value="#oneRow.newPayDay1"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay1/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.newPayDay2"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay2/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.newPayDay3"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay3/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.newPayDay4"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay4/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.newPayDay5"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay5/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.newPayDay6"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay6/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.newPayDay7"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay7/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.newPayDay14"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay14/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.newPayDay30"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.newPayDay30/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
					</tr>
		    	</s:iterator>
		    	</tbody>
		    </table>
		    </div>
	</div>
</div>
