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

		var dataObject = '<s:property value="jobJsonStr" escape="false" />';
		dataObject = (new Function("", "return " + dataObject))();

		$('#arenaDayContainer')
				.highcharts(
						{
							chart : {
								type : 'line'
							},
							title : {
								text : '<s:text name="arena_analysis_and_data_analysis" />'
							},
							credits : {
								enabled : false
							//<s:text name="the_lower_right_corner_is_not_shown" />LOGO 
							},
							xAxis : {
								categories : [
										'<s:text name="a_single_match_the_total_number" />',
										'<s:text name="many_people_match_the_total_number" />',
										'<s:text name="many_people_match_the_total_number_rewrite_key" />',
										'<s:text name="victory_always_session" />' ], //x<s:text name="axis_label_name" /> 
							},
							yAxis : {
								title : {
									text : ''
								}
							},
							legend : {
								enabled : true
							},
							tooltip : {
								enabled : true,
								formatter : function() {
									return '<b>'
											+ this.series.name
											+ '</b><br/><s:text name="the_first" />'
											+ this.x
											+ '<s:text name="day" />: '
											+ this.y + '%';
								}
							},
							plotOptions : {
								line : {
									dataLabels : {
										enabled : true
									},
									enableMouseTracking : true
								}
							},
							series : dataObject
						});
	});
</script>

		<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form action="arena_index" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		
			<div class="searchDiv_left" > 
					<s:text name="statistical_time" />：<s:textfield id="beginTime" name="beginTime" maxlength="10" size="12" cssClass="required date">
						<s:param name="value"><s:date name="beginTime" format="yyyy-MM-dd" /></s:param>
					</s:textfield>
			 
					<s:text name="to" /> <s:textfield id="endTime" name="endTime" maxlength="10" size="12" cssClass="required date">
						<s:param name="value"><s:date name="endTime" format="yyyy-MM-dd" /></s:param>
					</s:textfield>
			 </div>
		
		 <div class="searchButton2">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="statistical"/></button></div></div></li>
				<li> <div class="buttonActive" style="margin-left:10px"><div class="buttonContent"><button type="button" onclick="javascript:location.href='<%=basePath%>/admin/assay/arena_export.action'"><s:text name="export"/>Excel </button></div></div></li>
			
			</ul>
		 </div>
		 
		   
		 
		</div>
	</s:form>
	
	</div>
	

	<div class="pageContent sortDrag" selector="h1" layoutH="50">
		<div class="panel collapse">
			<h1></h1>
			<div>
				<table class="list" width="100%">
					<thead>

						<tr class="even">
							<td><s:text name="the_date_of" /></td>
							<td><s:text name="the_total_number_of" /></td>
							<td><s:text
									name="a_single_match_the_total_number" /></td>
							<td><s:text
									name="a_single_match_the_total_number_rewrite_key" /></td>
							<td><s:text name="a_single_match_reality" /></td>
							<td><s:text
									name="many_people_match_the_total_number" /></td>
							<td><s:text
									name="many_people_match_the_total_number_rewrite_key" /></td>
							<td><s:text name="multiplayer_match_reality" /></td>
							<td>1<s:text name="fighting_spirit_fighting" />
							</td>
							<td>1<s:text name="fight_fighting_spirit" /></td>
							<td>3<s:text name="fighting_spirit_fighting" /></td>
							<td>3<s:text name="fight_fighting_spirit" /></td>
							<td><s:text name="victory_always_session" /></td>
							<td><s:text name="failure_always_session" /></td>
							<td><s:text name="war" />/<s:text name="bow" />/<s:text
									name="method_respectively_games" /></td>
							<td><s:text name="war" />/<s:text name="bow" />/<s:text
									name="negative_field_method_respectively" /></td>
							<td><s:text name="consumption_of_small" />/<s:text
									name="number_of_big_horn" /></td>
							<td><s:text name="keep_a_small" />/<s:text
									name="number_of_big_horn" /></td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="arenaDays" status="offsets">
							<tr>
								<td><s:property value="dateTime" /></td>
								<td><s:property value="totalCount" /></td>
								<td><s:property value="totalPersonSmatch" />
								</td>
								<td><s:property value="totalCountSmatch" />
								</td>
								<td><s:property value="smatchLiveview" /></td>
								<td><s:property value="multiPersonMatch" />
								</td>
								<td><s:property value="multiCountMatch" />
								</td>
								<td><s:property value="mmatchLiveview" /></td>
								<td><s:property value="personFight" /></td>
								<td><s:property value="countFight" /></td>
								<td><s:property value="threePersonFight" />
								</td>
								<td><s:property value="threeCountFight" />
								</td>
								<td><s:property value="winCount" /></td>
								<td><s:property value="failCount" /></td>
								<td><s:property value="warriorWin" />/<s:property
										value="archerWin" />/<s:property value="masterWin" /></td>
								<td><s:property value="warriorLose" />/<s:property
										value="archerLose" />/<s:property value="masterLose" /></td>
								<td><s:property value="consumeSmartHorn" />/<s:property
										value="consumeLargeHorn" /></td>
								<td><s:property value="retentionSmartHorn" />/<s:property
										value="retentionLargeHorn" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>

		<div class="panel collapse">
			<h1></h1>
			<div>
				<div id="arenaDayContainer"
					style="min-width: 400px; height: 400px; margin: 0 auto"></div>
			</div>

		</div>
	</div>

