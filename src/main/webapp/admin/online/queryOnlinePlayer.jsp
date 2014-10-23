<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script type="text/javascript" language="javascript">
	
		var data1 = [];
		var data2 = [];
		$(document).ready(function() {
			
			<s:set name="tmp" value="@com.jcwx.game.common.JSONService@JSONListToString(addressJSonList)"></s:set> 
			var json = <s:property value="#tmp" escape="false"/>;
			
			if(json!=null && json!=""){
			 
				 $.each(json, function(i, n){
					 data1.push([n.t, n.n]);
				 });
							
				
				 $('#onlinePlayerContainer').highcharts({
			            chart: {
			                plotBackgroundColor: null,
			                plotBorderWidth: null,
			                plotShadow: false
			            },
			            title: {
			                text: 'IP area assay'
			            },
			            tooltip: {
			        	    pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>',  
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
			                            return '<b>'+ this.point.name + " " +this.y+'</b>: '+ Highcharts.numberFormat(this.percentage,2) +' %';
			                        }
			                    }
			                }
			            },
			            series: [{
			                type: 'pie',
			                name: 'peopleNum',
			                data: data1
			            }]
			        });
			}//
			
			<s:set name="tmp2" value="@com.jcwx.game.common.JSONService@JSONListToString(onlineJSonList)"></s:set> 
			var json2 = <s:property value="#tmp2" escape="false"/>;
			
			if(json2!=null && json2!=""){
			 
				 var data2_1 = [];
				 var data2_2 = [];
				 
				 $.each(json2, function(i, n){
					// data2.push([n.t+"s", n.n]);
					 data2_1.push(n.t+"'");
					 data2_2.push(n.n);
				 });
							
				
				 $('#container2').highcharts({
			            chart: {
			                type: 'column'
			            },
			            title: {
			                text: 'online time assay'
			            },
			            xAxis: {   
			                categories: data2_1
			            },
			            yAxis: {
			                min: 0,
			                title: {
			                    text: '<s:text name="the_number_of" />'
			                }
			            },
			            tooltip: {
			                headerFormat: '<span style="font-size:10px">{point.key}</span><table width="180px">',
			                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                    '<td style="padding:0"><b>{point.y} <s:text name="people" /></b></td></tr>',
			                footerFormat: '</table>',
			                shared: true,
			                useHTML: true
			            },
			            
			            plotOptions: {
			                column: {
			                    dataLabels: {
			                        enabled: true
			                    }
			                }
			            },
			            series: [{
			                name: '<s:text name="the_current_online_time" />',
			                data: data2_2
			    
			            }]
			        });
			}//
			
			
        });

	</script>
	
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		    <table class="searchContent">
				    <tr>
						<td>
							<s:text name="the_current_number_of_online_gamers" />：<s:property value="allNum"/> 
						</td>
					</tr>
					<%-- <tr>
						<td>
							<b><s:text name="with_online_registration" />：<s:text name="the_current_online_users" /></b>
						</td>
					</tr> --%>
					<tr>
						<td>
							<span class="color-gr"><s:text name="performance_analysis" />:<s:text name="remote_time-consuming" />:<s:property value="remoteRunTime"/>ms <s:text name="the_local_total_time" />:<s:property value="localRunTime"/>ms</span>
						</td>
					</tr>
			</table>
	</div>
  	<div class="pageContent sortDrag" selector="h1" layoutH="42">
		<div class="panel collapse">
			<h1><s:text name="the_current_online" />IP<s:text name="the_number" />：<s:property value="ips"/> 
			 <s:text name="this_brilliant_people_online" />：<s:property value="jcwxNum"/> </h1>
				<div id="onlinePlayerContainer" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
				<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
		</div>
		<div class="panel collapse">
			<h1><s:text name="current_online_users" /></h1>
			<div>
		    <table class="list" width="100%"  >
		    <thead>
		    	<tr>
		    	    <th><s:text name="login_account" /></th>
		    	    <th><s:text name="the_player_name" /></th>
		    	    <th><s:text name="currently_online_time" />(<s:text name="minutes" />)</th>
		    	    <th>IP</th>
		    	    <th>IP<s:text name="area" /></th>
		    	</tr>
		    </thead>
		    <tbody>
		    	<s:iterator value="playerOnlineInfoList" var="oneRow"  status="offset">
		    		<tr >
			    	    <td>
			    	    	<s:property value="#oneRow.userName"/>
			    	    </td> 
			    	    <td>
			    	    	<s:property value="#oneRow.playerName"/>
			    	    </td>
			    	    <td>
			    	    	<s:property value="#oneRow.onlineMinutes"/>
			    	    </td>
			    	    <td>
			    	    	<s:property value="#oneRow.lastLoginIP"/>
			    	    </td>
			    	     <td>
			    	    	<s:property value="#oneRow.address"/>
			    	    </td>
			    	</tr>
		    	</s:iterator>
		    	</tbody>
		    </table>
		    </div>
	    </div>
	 </div>

