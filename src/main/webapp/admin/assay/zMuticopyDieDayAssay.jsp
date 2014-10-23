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
<title><s:text name="the_data_analysis" />：<<s:text name="elite_underground_city" />><s:text name="death_level_statistics" /></title>
<link href="<%=basePath%>/media/default/css/default.css"
	rel="stylesheet" type="text/css">
<link href="<%=basePath%>/media/default/css/jquery.ui.all.css"
	rel="stylesheet" type="text/css">

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
		$("#beginTime").datepicker(
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
		$("#beginTime")
				.datepicker('setDate', '<s:property value="beginTime"/>');
		$("#endTime").datepicker(
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
		$("#endTime").datepicker('setDate', '<s:property value="endTime"/>');
	});
</script>



<body>
	<div >
	<span class="color-red"></span>

	<div id="divLeft">
		<b><s:text name="the_data_analysis" />：<<s:text name="elite_underground_city" />><s:text name="a_copy_of_the_death_of_statistical_analysis" /></b> <span class="color-red"><s:property
				value="errorInfo" /></span> <span class="color-gr"><s:property
				value="successInfo" /></span>
	</div>
	<a href="<%=basePath%>/admin/assay/copynub_index.action"><s:text name="go_to_the" /><<s:text name="elite_underground_city" />><s:text name="enter_the_number_of_analysis" /></a>
	<div id="divQuery">
		<s:form action="copynub_muticopydie" method="post" theme="simple"
			id="queryRecordForm">
	    	    	<s:text name="statistical_time" />：<s:textfield id="beginTime" name="beginTime"
				maxlength="10" size="12"></s:textfield>
	    	    	<s:text name="to" /> <s:textfield id="endTime" name="endTime" maxlength="10"
				size="12"></s:textfield>
			<input type="submit" style="cursor: hand"  value="<s:text name="statistical" />" />
		</s:form>
	</div>

	<div id="divList">
		<table id="row">
		
			<tr class="even" >
			<td><s:text name="the_date_of" /> </td>
			<td>10<s:text name="copy_number_of_death" /></td>
			<td>20<s:text name="copy_number_of_death" /></td>
			<td>30<s:text name="copy_number_of_death" /></td>
			<td>40<s:text name="copy_number_of_death" /></td>
			<td>50<s:text name="copy_number_of_death" /></td>
			<td>60<s:text name="copy_number_of_death" /></td>
			<td>70<s:text name="copy_number_of_death" /></td>
			</tr>
			
		<s:iterator value="muticopyDies" status="offsets">
			<tr>
			<td><s:property value="dateTime"/> </td>
			<td><s:property value="tenDie" /> </td>
			<td><s:property value="twentyDie" /> </td>
			<td><s:property value="thirtyDie" /> </td>
			<td><s:property value="fortyDie" /> </td>
			<td><s:property value="fiftyDie" /> </td>
			<td><s:property value="sixtyDie" /> </td>
			<td><s:property value="seventyDie" /> </td>
			</tr>
		</s:iterator>
		</table>
	</div>
</div>
</body>
</html>
