<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script type="text/javascript" language="javascript">
	$(function() {
		var data1 = [];
		var data2 = [];
		
		<s:set name="tmp" value="@com.jcwx.game.common.JSONService@JSONListToString(jsonList)"></s:set> 
		var json = <s:property value="#tmp" escape="false"/>;
		if(json!=null && json!=""){
			 $.each(json, function(i, n){
				 data1.push([n.hh+"", n.c]);
			 });
			 $('#playerAssayContainer').highcharts({
		            chart: {
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: 'time login assay'
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
		                        	return '<b>'+ this.point.name +'<s:text name="when" /></b>: '+ Highcharts.numberFormat(this.percentage,2) +' %  ' + this.y+"<s:text name="a" />" ;  
		                        }
		                    }
		                }
		            },
		            series: [{
		                type: 'pie',
		                name: 'precentage',
		                data: data1
		            }]
		        });
		}
		
		<s:set name="tmp2" value="@com.jcwx.game.common.JSONService@JSONListToString(onlineList)"></s:set> 
		var json2 = <s:property value="#tmp2" escape="false"/>;
		if(json2!=null && json2!=""){
			 $.each(json2, function(i, n){
				 data2.push([n.temp+"", n.cc]);
			 });
						
			 $('#container2').highcharts({
		            chart: {
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: 'online time assay'
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
		                            return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage,2) +'% '+ this.y ;  
		                        }
		                    }
		                }
		            },
		            series: [{
		                type: 'pie',
		                name: 'num',
		                data: data2
		            }]
		        });
		}//
	});
	</script>
	
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form  action="/admin/assay/newPlayerAssay.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				   <div class="searchDiv_left">
							<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10"
														size="12" cssClass="required date"></s:textfield>
							<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10"
														size="12" cssClass="required date"></s:textfield>
					</div>
			    	<div class="searchButton2">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
						</ul>
					</div>
				</div>
		</s:form>
	</div>
	
	<div class="pageContent sortDrag" selector="h1"  layoutH="42" >
		<div class="panel collapse">
				<h1><s:text name="new_registration_number" />
						<span class="color-red"><s:text name="countPeople">
								<s:param name="value" value="countPeople" />
							</s:text></span>
							<b><s:text name="time_interval_to_register_and_login" />(<s:text name="the_log" />)<s:text name="distribution_table" /></b>
				</h1>
				<div>
					<table class="list" width="100%">
				    	<thead>
						<tr>
							<th width="300"><s:text name="hours" /></th>
							<th><s:text name="the_total_number_of_time_to_register" /></th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="firstLoginList" var="oneRow" status="offset">
							<tr >
								<td><s:property value="#oneRow.hh" /><s:text name="when" /></td>
								<td><s:property value="#oneRow.c" /></td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
					<div id="playerAssayContainer"	style="min-width: 400px; height: 400px;"></div>
				</div>
		</div>
	<div class="panel collapse">
			<h1><b><s:text name="new_registration_and_login" />(<s:text name="the_log" />)<s:text name="the_online_time_distribution_table" /></b></h1>
			<div>
				 <table class="list" width="100%">
			    	<thead>
					<tr>
						<th width="300"><s:text name="the_length" />(min)</th>
						<th><s:text name="the_number_of" /></th>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="onlineTimeList" var="oneRow" status="offset">
						<tr >
							<td><s:if test="#oneRow.temp1!=0">
									<s:property value="(#oneRow.temp1-1) *5" /> - <s:property
										value="#oneRow.temp1*5" />
								</s:if> <s:else>
				    	    		0
				    	    	</s:else></td>
							<td><s:property value="#oneRow.c" /></td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
				<div id="container2" style="min-width: 400px; height: 400px"></div>
			</div>
	</div>
</div>