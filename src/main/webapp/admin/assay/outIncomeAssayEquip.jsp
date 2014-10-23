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
    <title><s:text name="analysis_of_the_operation_of_the_log_number_type_distribution" /></title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>
	
	<script src="<%=basePath%>/media/default/js/jquery.ui.core.js"></script>
	<script src="<%=basePath%>/media/default/js/jquery.ui.widget.js"></script>
	<script src="<%=basePath%>/media/default/js/jquery.ui.datepicker.js"></script>
	<script src="<%=basePath%>/media/default/js/highcharts/highcharts.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/highcharts/modules/exporting.js"  type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$("#beginDate").datepicker({dateFormat:"yy-mm-dd",prevText:'<s:text name="last_month" />',nextText:'<s:text name="next_month" />',dayNames:['<s:text name="on_sunday" />', '<s:text name="on_monday" />', '<s:text name="on_tuesday" />', '<s:text name="on_wednesday" />', '<s:text name="on_thursday" />', '<s:text name="on_friday" />', '<s:text name="on_saturday" />'],dayNamesMin:['<s:text name="day" />', '<s:text name="a" />', '<s:text name="the_second" />', '<s:text name="three" />', '<s:text name="four" />', '<s:text name="five" />', '<s:text name="six" />'],monthNames:['<s:text name="in_january" />','<s:text name="in_february" />','<s:text name="in_march" />','<s:text name="april" />','<s:text name="in_may" />','<s:text name="in_june" />','<s:text name="the_month_of_july" />','<s:text name="in_august" />','<s:text name="september" />','<s:text name="october" />','<s:text name="in_november" />','<s:text name="december" />'],monthNamesShort:['<s:text name="in_january" />','<s:text name="in_february" />','<s:text name="in_march" />','<s:text name="april" />','<s:text name="in_may" />','<s:text name="in_june" />','<s:text name="the_month_of_july" />','<s:text name="in_august" />','<s:text name="september" />','<s:text name="october" />','<s:text name="in_november" />','<s:text name="december" />']});
			$("#beginDate").datepicker('setDate', '<s:property value="beginDate"/>' );
			$("#endDate").datepicker({dateFormat:"yy-mm-dd",prevText:'<s:text name="last_month" />',nextText:'<s:text name="next_month" />',dayNames:['<s:text name="on_sunday" />', '<s:text name="on_monday" />', '<s:text name="on_tuesday" />', '<s:text name="on_wednesday" />', '<s:text name="on_thursday" />', '<s:text name="on_friday" />', '<s:text name="on_saturday" />'],dayNamesMin:['<s:text name="day" />', '<s:text name="a" />', '<s:text name="the_second" />', '<s:text name="three" />', '<s:text name="four" />', '<s:text name="five" />', '<s:text name="six" />'],monthNames:['<s:text name="in_january" />','<s:text name="in_february" />','<s:text name="in_march" />','<s:text name="april" />','<s:text name="in_may" />','<s:text name="in_june" />','<s:text name="the_month_of_july" />','<s:text name="in_august" />','<s:text name="september" />','<s:text name="october" />','<s:text name="in_november" />','<s:text name="december" />'],monthNamesShort:['<s:text name="in_january" />','<s:text name="in_february" />','<s:text name="in_march" />','<s:text name="april" />','<s:text name="in_may" />','<s:text name="in_june" />','<s:text name="the_month_of_july" />','<s:text name="in_august" />','<s:text name="september" />','<s:text name="october" />','<s:text name="in_november" />','<s:text name="december" />']});
			$("#endDate").datepicker('setDate', '<s:property value="endDate"/>' );
		});
		function queryAllPlayer(){
	        	$("#currPageNO").val(1);
	            $("#baseFrom1").submit();
	    }
	</script>
  </head>

<body>
	<div id="divLeft">
		<b><s:text name="equipment_cost_and_output" /></b> <a href="<%=basePath%>/admin/assay/outIncomeAssay_index.action"><s:text name="gold_consumption_and_output" /></a>
		<span class="color-gr"><s:text name="performance_analysis" />:<s:text name="remote_time-consuming" />:<s:property value="remoteRunTime"/>ms <s:text name="the_local_total_time" />:<s:property value="localRunTime"/>ms</span>
	</div>
	<div id="divLeft">
		<s:form action="/admin/assay/outIncomeAssay_equip.action" namespace="/admin/assay" method="post" theme="simple" id="baseFrom1">
		<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12"></s:textfield>
	           <s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12"></s:textfield>
			<input type="button" style="cursor: hand" name="query" value="<s:text name="the_query" />"
				onclick="queryAllPlayer();" />
		</s:form>
	</div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    		<th><s:text name="the_date_of" /></th>
				<th><s:text name="orange_outfit_output" /></th>
				<th><s:text name="orange_with_total_consumption" /></th>
				<th><s:text name="purple_with_output" /></th>
				<th><s:text name="purple_with_total_consumption" /></th>
				<th>PC<s:text name="orange_outfit_output" /></th>
				<th>pc<s:text name="orange_with_total_consumption" /></th>
				<th>PC<s:text name="purple_with_output" /></th>
				<th>pc<s:text name="purple_with_total_consumption" /></th>
				<th><s:text name="mobile_phone_orange_output" /></th>
				<th><s:text name="mobile_phone_orange_consumption" /></th>
				<th><s:text name="mobile_phone_purple_output" /></th>
				<th><s:text name="mobile_phone_purple_consumption" /></th>
				<th><s:text name="temporary_data_identification" /></th>
			</tr>
	    	<s:iterator value="consumeOutPutList" var="oneRow"  status="offset">
	    		<tr  >
	    			<td>
						<s:date name="id" format="yyyy-MM-dd"/>
		    	    </td>
					 <td>
						<s:property value="#oneRow.orangeOutTotal"/>
					</td>
					<td>
						<s:property value="#oneRow.orangeIncTotal"/>
					</td>
					<td>
						<s:property value="#oneRow.purpleOutTotal"/>
					</td>
					<td>
						<s:property value="#oneRow.purpleIncTotal"/>
					</td>
					<td>
						<s:property value="#oneRow.orangeOutpc"/>
					</td>
					<td>
						<s:property value="#oneRow.orangeIncpc"/>
					</td>
					<td>
						<s:property value="#oneRow.purpleOutpc"/>
					</td>
					<td>
						<s:property value="#oneRow.purpleIncpc"/>
					</td>
					<td>
						<s:property value="#oneRow.orangeOutmo"/>
					</td>
					<td>
						<s:property value="#oneRow.orangeIncmo"/>
					</td>
					<td>
						<s:property value="#oneRow.purpleOutmo"/>
					</td>
					<td>
						<s:property value="#oneRow.purpleIncmo"/>
					</td>
					<td> 
						<s:if test="#oneRow.tmpFlag"><span class="color-red"><s:text name="temporary" /></span></s:if> 
						<s:else><s:text name="a_formal" /></s:else>
		    	    </td>
				</tr>
	    	</s:iterator>
	    </table>
	</div>
<div id="bottomPager"></div>
<s:if test='#request.actionMsg !=null && #request.actionMsg !="" '>
<script type="text/javascript">
	alert("<s:property value="actionMsg" escape="false"/>");
</script>
</s:if>	

</body>
</html>
