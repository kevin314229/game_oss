<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script type="text/javascript">
	$(function() {
		var data1 = [];
		var data2 = [];
		
		<s:set name="tmp" value="@com.jcwx.game.common.JSONService@JSONListToString(jsonList)"></s:set> 
		var json = <s:property value="#tmp" escape="false"/>;
		
		if(json!=null && json!=""){
			 $.each(json, function(i, n){
				 data1.push([n.hh+"", n.c]);
			 });
			 $('#mallrecordContainer').highcharts({
		            chart: {
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: ' <s:text name="distribution_of_items_number" />  '
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
		                        	return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage,2) +' %  ' + this.y+"<s:text name="a" />" ;  
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
		
		<s:set name="tmp2" value="@com.jcwx.game.common.JSONService@JSONListToString(goldJsonList)"></s:set> 
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
		                text: ' <s:text name="sales_amount_of_items" /> '
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
		} 
		
	});
	
	
	
	
	
</script>


<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="mallRecord_index" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   
		   <div class="searchBar">
		   <div>
				<s:text name="number_of_monster_crystal_mall_sales" />： <s:property value="totalGold" /> &nbsp;&nbsp;&nbsp;&nbsp; 
				<s:text name="the_remaining_to_the_total_number_of_the_magic_crystal" />： <s:property value="supGoldSum" /> 
			</div>
		    <table class="searchContent">
		    	<tr>
		    		<td>
		    			<s:text name="statistical_time" />：<s:textfield name="beginTime"
								maxlength="10" size="12" cssClass="required date">
								<s:param name="value"><s:date name="beginTime" format="yyyy-MM-dd" /></s:param>
								</s:textfield>
		    		</td>
		    		<td>
		    			<s:text name="to" /> 
		    			<s:textfield name="endTime" maxlength="10"	size="12"  cssClass="required date">
								<s:param name="value"><s:date name="endTime" format="yyyy-MM-dd" /></s:param>
								</s:textfield>
		    		</td>
		    	</tr>
		    
			</table>
	    	<div class="subBar">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
</div>

<div class="pageContent sortDrag" layoutH="80" selector="h1">
		<div class="panel collapse">
			<h1><s:text name="mall_data" /></h1>
	    <table class="list" width="100%"  >
	    <thead>
			<tr class="even" >
			<td><s:text name="regional" /> </td>
			<td><s:text name="item_name" /></td>
			<td style="color: "><s:text name="average_unit_price" /></td>
			<td><s:text name="the_number_of" /></td>
			<td><s:text name="a_total_of" /></td>
			<td><s:text name="general_stores_accounted_for" /></td>
			<td width="2"></td>
			<td>VIP<s:text name="the_unit_price" /></td>
			<td><s:text name="the_number_of" /></td>
			<td><s:text name="a_total_of" /></td>
			<td>vip<s:text name="sales_accounted_for" /></td>
			<td><s:text name="total_sales_accounted_for" /></td>
			<td width="2"></td>
			<td><s:text name="mysterious_store_price" /></td>
			<td><s:text name="the_number_of" /></td>
			<td><s:text name="a_total_of" /></td>
			<td><s:text name="mysterious_stores_accounted_for" /></td>
			<td><s:text name="total_sales_accounted_for" /></td>
			<td><s:text name="the_total_amount" /></td>
			<td><s:text name="the_total_percentage" /></td>
			</tr>
			</thead>
			<tbody>
		<s:iterator value="mallRecordList" status="offsets" var="mallRecord">
			<tr>
				<td><s:property value="serverName"/> </td>
				<td><s:property value="goodName" /> </td>
				<td><s:property value="price" /></td>
				<td><s:property value="number" /> </td>
				<td><s:property value="price*number" /> </td>
				<td><s:property value="scale" />% </td>
				<td width="2"></td>
				<td><s:property value="vipPrice" /></td>
				<td><s:property value="vipNumber" /> </td>
				<td><s:property value="vipPrice*vipNumber" /> </td>
				<td><s:property value="vipScale" />% </td>
				<td><s:property value="normalScale" />% </td>
				<td width="2"></td>
				<td><s:property value="secretPrice" /></td>
				<td><s:property value="secretNumber" /> </td>
				<td><s:property value="secretSum" /> </td>
				<td><s:property value="secretScale" />% </td>
				<td><s:property value="totalSecretScale" />% </td>
				<td><s:property value="goldSum" /> </td>
				<td><s:property value="totalScale" />% </td>
			</tr>
		</s:iterator>
			<tr class="even">
				<td colspan="3"> <s:text name="a_total_of" /> </td>
				<td><s:property value="count" /> </td>
				<td><s:property value="goldSum" /> </td>
				<td></td>
				<td></td>
				<td></td>
				<td><s:property value="vipCount" /> </td>
				<td><s:property value="vipGoldSum" /> </td>
				<td> </td>
				<td> </td>
				<td> </td>
				<td> </td>
				<td><s:property value="secretCount" /> </td>
				<td><s:property value="secretGoldSum" /> </td>
				<td> </td>
				<td> </td>
				<td><s:property value="sumGold" /> </td>
				<td>100</td>
			</tr>
			</thead>
			<tbody>
		</table>
		</div>
		
		<div class="panel collapse">
			<h1><s:text name="sales_of_goods" /></h1>
			<div id="mallrecordContainer" style="min-width: 400px; height: 400px; margin: 0 auto">    </div>
		</div>
		<div class="panel collapse">
			<h1><s:text name="the_amount_of_sales_items" /></h1>
			<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto"></div>	
		</div>
	</div>
