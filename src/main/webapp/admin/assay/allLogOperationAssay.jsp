<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
	<script type="text/javascript">
	
		var data1 = [];	
		$(function() {
			<s:set name="tmp" value="@com.jcwx.game.common.JSONService@JSONListToString(jsonList)"></s:set> 
			var json = <s:property value="#tmp" escape="false"/>;
			
			if(json!=null && json!=""){
			 
				 $.each(json, function(i, n){
					 data1.push([n.t, n.n]);
				 });
							
				
				 $('#allLogContainer').highcharts({
			            chart: {
			                plotBackgroundColor: null,
			                plotBorderWidth: null,
			                plotShadow: false
			            },
			            title: {
			                text: 'operation assay'
			            },
			            tooltip: {
			        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
			            	percentageDecimals: 1
			            },
			            plotOptions: {
			                pie: {
			                    allowPointSelect: true,
			                    cursor: 'pointer',
			                    dataLabels: {
			                        enabled: true,
			                        color: '#000000',
			                        connectorColor: '#000000',
			                        formatter: function() {
			                            return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage,2) +' %  ' + this.y ;  
			                        }
			                    }
			                }
			            },
			            series: [{
			                type: 'pie',
			                name: 'num',
			                data: data1
			            }]
			        });
			}
			
		});
	</script>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form action="/admin/assay/allLogOperationAssay.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				  <div class="searchDiv_left">
							<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" datefmt="yyyy-MM-dd HH:mm:ss" maxlength="20" size="20" cssClass="required date"></s:textfield>
						 
							 <s:text name="to" /> <s:textfield id="endDate" name="endDate" datefmt="yyyy-MM-dd HH:mm:ss" maxlength="20" size="20" cssClass="required date"></s:textfield>
						 
								<b><s:text name="analysis_of_the_operation_of_the_log_number_type_distribution" /></b> 
								<span class="color-gr">
								<s:text name="performance_analysis" />:<s:text name="remote_time-consuming" />:<s:property value="remoteRunTime"/>ms 
								<s:text name="the_local_total_time" />:<s:property value="localRunTime"/>ms
								</span>
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
			<h1></h1>
			<div>
				<div id="allLogContainer" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
			</div>
		</div>
		<div class="panel collapse">
			<h1></h1>
			<div>
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
						<th width="300"><s:text name="operation_type" /></th>
						<th width="300"><s:text name="operation_type" />cn</th>
						<th><s:text name="the_number_of" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="list" var="oneRow"  status="offset">
			    		<tr>
				    	    <td>
								<s:property value="#oneRow.p"/>
							</td>
							<td>
								<s:property value="#oneRow.cnName"/>
							</td>
							<td>
								<s:property value="#oneRow.c"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
				</table>
			</div>
	    </div>
	</div>

