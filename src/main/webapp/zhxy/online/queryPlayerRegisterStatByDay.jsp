<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script type="text/javascript">

function RegisterByDayExpCSV(){
	var url = "<%=basePath%>/zhxy/online/ZHqueryPlayerRegisterStatByDaydata.action";
	registQueryForm.action=url;
	registQueryForm.submit();
	registQueryForm.action= "<%=basePath%>/zhxy/online/ZHqueryPlayerRegisterStatByDay.action";

}
</script>

<s:include value="/admin/template/scriptMessage.jsp" />
<div class="pageHeader">
	<s:form action="ZHqueryPlayerRegisterStatByDay" method="post"
		theme="simple" cssClass="pageForm required-validate" id="registQueryForm" name="registQueryForm"
		onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div class="searchDiv_left">
				<s:text name="statistical_time" />
				：
				<s:textfield id="beginDate" name="beginDate" maxlength="10"
					size="12" cssClass="required date"></s:textfield>

				<s:text name="to" />
				<s:textfield id="endDate" name="endDate" maxlength="10" size="12"
					cssClass="required date"></s:textfield>

				<s:text name="all_the_total_number_of_registered_users" />
				：
				<s:property value="allRegisterNum" />
			</div>
			<div class="searchButton2">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									<s:text name="statistical" />
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="RegisterByDayExpCSV()"><s:text name="export" />EXCEL</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</s:form>
</div>

<div class="pageContent">
	<table class="list" width="100%" layoutH="40">
		<thead>
			<tr>
				<th colspan="8" style="text-align: center"><s:text
						name="statistical_time_range" />：<s:property value="beginDate" />
					00:00:00 <s:text name="to" /> <s:property value="endDate" />
					23:59:59</th>
			</tr>
			<tr>
				<th><s:text name="years" /></th>
				<th><s:text name="month" /></th>
				<th><s:text name="day" /></th>
				<th><s:text name="the_number_of_registered" /></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="statList" var="oneStat">
				<tr>
					<td><s:if test="#oneStat.year==100">
							<s:text name="all_the_results_a_total_of" />
						</s:if> <s:else>
							<s:property value="#oneStat.year" />&nbsp;
						</s:else></td>
					<td><s:if test="#oneStat.month==100">
							<s:text name="in_total" />
						</s:if> <s:else>
							<s:property value="#oneStat.month" />&nbsp;
						</s:else></td>
					<td><s:if test="#oneStat.day==100">
							<s:text name="month_total" />
						</s:if> <s:else>
							<s:property value="#oneStat.day" />&nbsp;
						</s:else></td>
					<td><s:property value="#oneStat.countNum" /></td>
				</tr>
			</s:iterator>
		</tbody>

		<thead>
			<tr>
				<th><s:text name="date" /></th>
				<th><s:text name="operators" /></th>
				<th><s:text name="operator_logo" /></th>
				<th><s:text name="enrollment" /></th>
			</tr>
		</thead>

		<tbody>
			<s:iterator value="ptList" var="oneRow">
				<tr>
					<td><s:property value="#oneRow.year" />-<s:property value="#oneRow.month" />-<s:property value="#oneRow.day" /></td>
					<td><s:property
							value="@com.jcwx.game.common.constants.PtServerConstant@ptTypeMap[#oneRow.carrierOperator]" /></td>
					<td><s:property value="#oneRow.carrierOperator" /></td>
					<td><s:property value="#oneRow.countNum" /></td>
				</tr>
			</s:iterator>
		</tbody>

	</table>


</div>


