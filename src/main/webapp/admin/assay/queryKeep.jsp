<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
#queryKeep_tb1 td span,#queryKeep_tb2 td span{
 	color: green;
 }
</style>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
	<script type="text/javascript" language="javascript">
    	
	$(function() {
	
		
		 var dataObject = '<s:property value="jobJsonStr" escape="false" />';
		 dataObject =(new Function("","return "+dataObject))();
		    
		 $('#keepContainer').highcharts({
	            chart: {
	                type: 'line'
	            },
	            title: {
	                text: '<s:text name="retention_rates_analysis" />'
	            },
	            
	            xAxis: {
	                
	            },
	            yAxis: {
	                title: {
	                    text: '<s:text name="the_percentage" />'
	                }
	            },
	            legend:{
	            	enabled:true
	            },
	            tooltip: {
	                enabled: true, 
	                formatter: function() {
	                    return '<b>'+ this.series.name +'</b><br/><s:text name="the_first" />'+
	                        this.x +'<s:text name="day" />: '+ this.y +'%';
	                }
	            },
	            plotOptions: {
	                line: {
	                    dataLabels: {
	                        enabled: true   
	                    },
	                    enableMouseTracking: true
	                }
	            },
	            series: dataObject
	        });
		 
	});
	</script>
	
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="/admin/assay/queryKeep.action" namespace="/admin/assay" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
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
	
	
<div class="pageContent sortDrag" layoutH="50" selector="h1">
	<div class="panel collapse">
		<h1><s:text name="the_current_regional" /></h1>
		<div>
		    <table class="list" width="100%" id="queryKeep_tb1">
		    	<thead>
		    	<tr>
				 	<th><s:text name="the_date_of" /></th>
					<th><s:text name="on_the_day_of_registration_number" /></th>
					<th><s:text name="the_next_day_retained" /></th>
					<th><s:text name="on_the_third_day_of_retained" /></th>
					<th><s:text name="the_fourth_day_of_retained" /></th>
					<th><s:text name="the_fifth_day_of_retained" /></th>
					<th><s:text name="the_sixth_day_of_retained" /></th>
					<th><s:text name="on_the_seventh_day_of_retained" /></th>
					<th>第8日留存</th>
					<th><s:text name="14_days_left" /></th>
					<th><s:text name="30_days_left" /></th>  
						<%--<th><s:text name="the_date_of" />
				 <th><s:text name="on_the_day_of_registration_number" /></th>
						<th><s:text name="retention_times_a_day" /></th>
						<th><s:text name="retention_times_two_days" /></th>
						<th><s:text name="retention_times_three_days" /></th>
						<th><s:text name="retention_times_four_days" /></th>
						<th><s:text name="retention_times_five_days" /></th>
						<th><s:text name="retention_times_six_days" /></th>
						<th><s:text name="retention_times_seven_days" /></th>
						<th><s:text name="fourteenth_day_retention" /></th>
						<th><s:text name="retained_thirtieth" /></th> --%>
				</tr>
				</thead>
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
							<s:property value="#oneRow.day2"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day2/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day3"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day3/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day4"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day4/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day5"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day5/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day6"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day6/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day7"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day7/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
							<td>
							<s:property value="#oneRow.day8"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day7/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day14"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day14/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day30"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day30/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
					</tr>
		    	</s:iterator>
		    	</tbody>
		    </table>
		    </div>
	</div>
	
	<div class="panel collapse">
		<h1><s:text name="retention_analysis" /></h1>
		<div>
			<div id="keepContainer" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>
	<div class="panel collapse">
		<h1><s:text name="each_access_operators_in_detail" /></h1>
		<div>
		    <table class="list" width="100%" id="queryKeep_tb2">
		    	<thead>
		    	<tr>
					  <th><s:text name="the_date_of" /></th>
					<th><s:text name="operator" /></th>
					<th><s:text name="on_the_day_of_registration_number" /></th>
					<th><s:text name="the_next_day_retained" /></th>
					<th><s:text name="on_the_third_day_of_retained" /></th>
					<th><s:text name="the_fourth_day_of_retained" /></th>
					<th><s:text name="the_fifth_day_of_retained" /></th>
					<th><s:text name="the_sixth_day_of_retained" /></th>
					<th><s:text name="on_the_seventh_day_of_retained" /></th>
						<th>第8日留存</th>
					<th><s:text name="14_days_left" /></th>
					<th><s:text name="30_days_left" /></th> 
					
					<%-- <th><s:text name="the_date_of" />
					<th><s:text name="on_the_day_of_registration_number" /></th>
						<th><s:text name="retention_times_a_day" /></th>
						<th><s:text name="retention_times_two_days" /></th>
						<th><s:text name="retention_times_three_days" /></th>
						<th><s:text name="retention_times_four_days" /></th>
						<th><s:text name="retention_times_five_days" /></th>
						<th><s:text name="retention_times_six_days" /></th>
						<th><s:text name="retention_times_seven_days" /></th>
						<th><s:text name="fourteenth_day_retention" /></th>
						<th><s:text name="retained_thirtieth" /></th> --%>
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
							<s:property value="#oneRow.day2"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day2/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day3"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day3/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day4"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day4/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day5"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day5/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day6"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day6/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day7"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day7/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
							<td>
							<s:property value="#oneRow.day8"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day7/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day14"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day14/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
						<td>
							<s:property value="#oneRow.day30"/> &nbsp;&nbsp;<span class="color-gr">[<fmt:formatNumber value="${oneRow.day30/oneRow.day1 *100}" pattern="#.##" minFractionDigits="0" />%]</span>
						</td>
					</tr>
		    	</s:iterator>
		    	</tbody>
		   	</table>
	    </div>
	</div>
</div>
	
