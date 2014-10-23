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
<title><s:text name="the_data_analysis" />：<s:text name="store_sales_data" /></title>
<link href="<%=basePath%>/media/default/css/default.css"
	rel="stylesheet" type="text/css">
<link href="<%=basePath%>/media/default/css/jquery.ui.all.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css"
	type="text/css" />

<script src="<%=basePath%>/media/default/js/jquery.ui.core.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/jquery.ui.widget.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/public.js"
	type="text/javascript"></script>
</head>
<script type="text/javascript">
	$(function() {
		$("#beginDate").datepicker(
				{
					dateFormat : "yy-mm-dd",
					prevText : '<s:text name="last_month" />',
					nextText : '<s:text name="next_month" />',
					dayNames : [ '<s:text name="on_sunday" />', '<s:text name="on_monday" />', '<s:text name="on_tuesday" />', '<s:text name="on_wednesday" />', '<s:text name="on_thursday" />', '<s:text name="on_friday" />',
							'<s:text name="on_saturday" />' ],
					dayNamesMin : [ '<s:text name="day" />', '<s:text name="a" />', '<s:text name="the_second" />', '<s:text name="three" />', '<s:text name="four" />', '<s:text name="five" />', '<s:text name="six" />' ],
					monthNames : [ '<s:text name="in_january" />', '<s:text name="in_february" />', '<s:text name="in_march" />', '<s:text name="april" />', '<s:text name="in_may" />', '<s:text name="in_june" />', '<s:text name="the_month_of_july" />',
							'<s:text name="in_august" />', '<s:text name="september" />', '<s:text name="october" />', '<s:text name="in_november" />', '<s:text name="december" />' ],
					monthNamesShort : [ '<s:text name="in_january" />', '<s:text name="in_february" />', '<s:text name="in_march" />', '<s:text name="april" />', '<s:text name="in_may" />', '<s:text name="in_june" />',
							'<s:text name="the_month_of_july" />', '<s:text name="in_august" />', '<s:text name="september" />', '<s:text name="october" />', '<s:text name="in_november" />', '<s:text name="december" />' ]
				});
		$("#beginDate")
				.datepicker('setDate', '<s:property value="beginDate"/>');
		$("#endDate").datepicker(
				{
					dateFormat : "yy-mm-dd",
					prevText : '<s:text name="last_month" />',
					nextText : '<s:text name="next_month" />',
					dayNames : [ '<s:text name="on_sunday" />', '<s:text name="on_monday" />', '<s:text name="on_tuesday" />', '<s:text name="on_wednesday" />', '<s:text name="on_thursday" />', '<s:text name="on_friday" />',
							'<s:text name="on_saturday" />' ],
					dayNamesMin : [ '<s:text name="day" />', '<s:text name="a" />', '<s:text name="the_second" />', '<s:text name="three" />', '<s:text name="four" />', '<s:text name="five" />', '<s:text name="six" />' ],
					monthNames : [ '<s:text name="in_january" />', '<s:text name="in_february" />', '<s:text name="in_march" />', '<s:text name="april" />', '<s:text name="in_may" />', '<s:text name="in_june" />', '<s:text name="the_month_of_july" />',
							'<s:text name="in_august" />', '<s:text name="september" />', '<s:text name="october" />', '<s:text name="in_november" />', '<s:text name="december" />' ],
					monthNamesShort : [ '<s:text name="in_january" />', '<s:text name="in_february" />', '<s:text name="in_march" />', '<s:text name="april" />', '<s:text name="in_may" />', '<s:text name="in_june" />',
							'<s:text name="the_month_of_july" />', '<s:text name="in_august" />', '<s:text name="september" />', '<s:text name="october" />', '<s:text name="in_november" />', '<s:text name="december" />' ]
				});
		$("#endDate").datepicker('setDate', '<s:property value="endDate"/>');
	});
</script>



<body>
<div style="width: 1680px">
	<span class="color-red"></span>

	<div id="divLeft">
		<b><s:text name="the_data_analysis" />：<s:text name="novice_loss_analysis" /></b> <span class="color-red"><s:property
				value="errorInfo" /></span> <span class="color-gr"><s:property
				value="successInfo" /></span>
	</div>



	<div id="divQuery">
		<s:form action="newRecord_index" method="post" theme="simple"
			id="queryRecordForm">
	    	    	<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate"
				maxlength="10" size="12"></s:textfield>
	    	    	<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10"
				size="12"></s:textfield>
			<input type="submit" style="cursor: hand"  value="<s:text name="statistical" />" />
		</s:form>
	</div>

	<div id="divList">
		<table id="row">
		<s:iterator value="ossNewRecordsList" status="offsets">
			<tr class="even" >
			<td><s:property value="createDate" /></td>
			<s:iterator value="ossNewRecordList" status="offset" >
					<s:if test="step==1"><td><s:text name="began_to_visit" />fm</td></s:if> 
					<s:elseif test="step==2"><td><s:text name="complete_loading_page_for_the_first_time" /><br>【<s:text name="for_the_first_time_loading" />】</td></s:elseif> 
					<s:elseif test="step==3"><td>socket <s:text name="the_connection_is_successful" /></td></s:elseif> 
					<s:elseif test="step==4"><td><s:text name="to_create_the_role_interface" /></td></s:elseif>
					<s:elseif test="step==5"><td><s:text name="click_the_create_button_and_success" /><br>【<s:text name="complete_character_creation" />】</td></s:elseif>
					<s:elseif test="step==6"><td><s:text name="novice_drama_is_the_first_time" /></td></s:elseif> 
					<s:elseif test="step==7"><td><s:text name="complete_novice_battle" /></td></s:elseif>
					<s:elseif test="step==8"><td><s:text name="the_second_new_drama" /></td></s:elseif>
					<s:elseif test="step==9"><td><s:text name="complete_character_creation" /></td></s:elseif>
					<s:elseif test="step==10"><td><s:text name="the_third_new_drama" /> </td></s:elseif>
					<s:elseif test="step==11"><td><s:text name="complete" />load<s:text name="the_main_program" />，<s:text name="enter_the_game" /><br>【<s:text name="the_second_load" />】</td></s:elseif>
					<s:elseif test="step==12"><td><s:text name="the_first_task_to_accept" /></td></s:elseif>
					<s:elseif test="step==13"><td><s:text name="to_complete_the_gift_bag_open" /></td></s:elseif>
					<s:elseif test="step==14"><td><s:text name="the_first_task" /></td></s:elseif>
			</s:iterator>

			<td><s:text name="retention_rates" /> </td>
			</tr>
			
			<tr >
			<td><s:text name="the_total_number_of" /> </td>
			<s:iterator value="ossNewRecordList" status="offset">
					<td><s:property value="totalNub" /> </td>
					
			</s:iterator>
			<td> </td>
			</tr>
			
			<tr >
			<td><s:text name="the_number_of_loss" /></td>
			<s:iterator value="ossNewRecordList" status="offset">
					<td><s:property value="loseNub" /> </td>
			</s:iterator>
			<td> </td>
			</tr>
			
			<tr >
			<td><s:text name="turnover_rate" /></td>
			<s:iterator value="ossNewRecordList" status="offset">
					<td><s:property value="loseProbability" /> % </td>
			</s:iterator>
			<td> <s:property value="probability" /> %</td>
			</tr>
		</s:iterator>
		</table>
		
	</div>


</div>
</body>
</html>
