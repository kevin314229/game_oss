<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>数据分析：商城销售分析</title>
<script src="<%=basePath%>/media/default/js/public.js"
	type="text/javascript"></script>
<link href="<%=basePath%>/media/default/css/default.css"rel="stylesheet" type="text/css">
<link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css" />

<script src="<%=basePath%>/media/default/js/jquery.ui.core.js"></script>
<script src="<%=basePath%>/media/default/js/jquery.ui.widget.js"></script>
<script src="<%=basePath%>/media/default/js/jquery.ui.datepicker.js"></script>
<script src="<%=basePath%>/media/default/js/jquery.pager.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/highcharts/highcharts.js"	type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/highcharts/modules/exporting.js" type="text/javascript"></script>	

</head>
<script type="text/javascript">
	$(function() {
		var data1 = [];
		var data2 = [];
		$("#beginTime").datepicker(
				{
					dateFormat : "yy-mm-dd",
					prevText : '上个月',
					nextText : '下个月',
					dayNames : [ '星期天', '星期一', '星期二', '星期三', '星期四', '星期五',
							'星期六' ],
					dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
					monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月',
							'八月', '九月', '十月', '十一月', '十二月' ],
					monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月',
							'七月', '八月', '九月', '十月', '十一月', '十二月' ]
				});
		$("#beginTime")
				.datepicker('setDate', '<s:property value="beginTime"/>');
		$("#endTime").datepicker(
				{
					dateFormat : "yy-mm-dd",
					prevText : '上个月',
					nextText : '下个月',
					dayNames : [ '星期天', '星期一', '星期二', '星期三', '星期四', '星期五',
							'星期六' ],
					dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
					monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月',
							'八月', '九月', '十月', '十一月', '十二月' ],
					monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月',
							'七月', '八月', '九月', '十月', '十一月', '十二月' ]
				});
		$("#endTime").datepicker('setDate', '<s:property value="endTime"/>');
		
		
		
		<s:set name="tmp" value="@com.jcwx.game.common.JSONService@JSONListToString(jsonList)"></s:set> 
		var json = <s:property value="#tmp" escape="false"/>;
		
		if(json!=null && json!=""){
			 $.each(json, function(i, n){
				 data1.push([n.hh+"", n.c]);
			 });
			 $('#container').highcharts({
		            chart: {
		                plotBackgroundColor: null,
		                plotBorderWidth: null,
		                plotShadow: false
		            },
		            title: {
		                text: ' 销售物品数量  '
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
		                        	return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage,2) +' %  ' + this.y+"个" ;  
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
		                text: ' 销售物品金额 '
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



<body>
<div >
	<span class="color-red"></span>

	<div id="divLeft">
		<b>数据分析：普通商城</b> <b>神秘商店</b> 
		
		<span class="color-red">
		
		
		</span>
	</div>
	
	<div id="divLeft">
		商城销售魔晶数： <s:property value="totalGold" /> &nbsp;&nbsp;&nbsp;&nbsp; 剩余总魔晶数： <s:property value="supGoldSum" /> 
		
	</div>

	<div id="divQuery">
		<s:form action="mallRecord_index" method="post" theme="simple"
			id="queryRecordForm">
	    	    	统计时间：<s:textfield id="beginTime" name="beginTime"
				maxlength="10" size="12"></s:textfield>
	    	    	至 <s:textfield id="endTime" name="endTime" maxlength="10"
				size="12"></s:textfield>
			<s:select name="selType"  list="#{'2':'全部','1':'vip用户','0':'普通用户'}"></s:select>
			<input type="submit" style="cursor: hand"  value="统计" />
		</s:form>
	</div>

	<div id="divList">
		<table id="row">
			<tr class="even" >
			<td>大区 </td>
			<td>物品名称</td>
			<td>物品单价</td>
			<td>数量</td>
			<td>金额</td>
			<td>占商城的百分比</td>
			</tr>
			
		<s:iterator value="mallRecordList" status="offsets" var="mallRecord">
			<tr>
			<td><s:property value="serverName"/> </td>
			<td><s:property value="goodName" /> </td>
			<td><s:property value="price" /><s:if test="isVip==1">(vip价格)</s:if></td>
			<td><s:property value="number" /> </td>
			<td><s:property value="goldSum" /> </td>
			<td><s:property value="scale" /></td>
			</tr>
		</s:iterator>
			<tr class="even">
				<td colspan="3"> 大区总计 </td>
				<td><s:property value="count" /> </td>
				<td><s:property value="sumGold" /> </td>
				<td>100</td>
			</tr>
		</table>
	</div>
	
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto">    </div>
		
	<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto"></div>	
	
	<div id="bottomPager"></div>
		
</div>
</body>
</html>
