<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>


<script type="text/javascript">
    	$(function () {
		
		    var dataObject = '<s:property value="data" escape="false" />';
		    
		    dataObject =(new Function("","return "+dataObject))();
		    
	        $('#highcharContainer').highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: '<s:text name="hour" />'
            },
            xAxis: {
                categories: []
            },
            yAxis: {
                min: 0,
                title: {
                    text: '<s:text name="enrollment" />'
                },
                stackLabels: {
                    enabled: false,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
            },
            legend: {
                align: 'right',
                x: 1,
                verticalAlign: 'top',
                y: 1,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function() {
                    return this.y;
                }
            },
            plotOptions: {
                column: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            series: dataObject
        });
	        
	    });
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="queryPlayerRegisterStatByHour" rel="pagerForm" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
				<div class="searchDiv_left">
							<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
				
							<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
				
							<s:text name="all_the_total_number_of_registered_users" />：<s:property value="allRegisterNum"/>
				</div>
	    	<div class="searchButton2">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
	</div>

	<div class="pageContent sortDrag"  layoutH="35" selector="h1">
		<div class="panel collapse">
			<h1><s:text name="daily_level_analysis" /></h1>
			<div>
		    <table class="list" width="100%" layoutH="400">
		    <thead>
		    	<tr>
		    	    <th colspan="8" style="text-align:center">
		    	    	<s:text name="statistical_time_range" />：<s:property value="beginDate"/> 00:00:00 <s:text name="to" /> <s:property value="endDate"/> 23:59:59
		    	    </th>
		    	</tr>
		    	<tr>
		    	    <th><s:text name="date" /></th>
		    	    <th><s:text name="when" /></th>
		    	    <th><s:text name="the_number_of_registered" /></th>
		    	</tr>
		    	</thead>
		    	<tbody>
		    	<s:iterator value="statList" var="oneStat" status="offset">
		    		<tr>
			    	    <td>
			    	    	<s:if test="#oneStat.year==100">
			    	    		<s:text name="all_the_results_a_total_of" />
			    	    	</s:if>  
							<s:else>  
							  	<s:property value="#oneStat.year"/>&nbsp;-
							</s:else> 
							
							<s:if test="#oneStat.month==100">
			    	    		<s:text name="in_total" />
			    	    	</s:if>  
							<s:else>  
							  	<s:property value="#oneStat.month"/>&nbsp;-
							</s:else> 
							<s:if test="#oneStat.day==100">
			    	    		<s:text name="month_total" />
			    	    	</s:if>  
							<s:else>  
							  	<s:property value="#oneStat.day"/>&nbsp;
							</s:else> 
			    	    </td>
			    	    <td>
			    	    	<s:if test="#oneStat.hour==100">
			    	    		<s:text name="_a_total_of" />
			    	    	</s:if>  
							<s:else>  
							  	<s:property value="#oneStat.hour"/>&nbsp;
							</s:else> 
			    	    	
			    	    </td>
			    	    <td>
			    	    	<s:property value="#oneStat.countNum"/>
			    	    </td>
			    	</tr>
		    	</s:iterator>
		    	</tbody>
		    </table>
		    <s:include value="/admin/template/paging.jsp"/>
		</div>
		</div>
		<div class="panel collapse">
			<h1><s:text name="timeshare_registration_statistics" /></h1>
			<div id="highcharContainer" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		</div>
	 </div>
