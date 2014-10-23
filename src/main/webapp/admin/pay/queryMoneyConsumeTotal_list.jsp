<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
response.setHeader("Pragma","No-cache"); 

response.setHeader("Cache-Control","no-cache"); 

response.setDateHeader("Expires", 0);  
%>
<link href="<%=basePath%>/media/default/css/jquery.achieve.css"
	rel="stylesheet" type="text/css" media="all" />
	<script>
	var operationTypes = '<s:property value="resultJson" escape="false" />';
	var operationArray = (new Function("", "return " + operationTypes))();
	$("#summary2").achieve(operationArray, {
		left : -1,
		top : 5,
		width : 150,
		formatItem : function(row) {
			return row.name;
		},
		formatMatch2 : function(row) {

			return row.code + row.name + row.help;
		},
		formatResult : function(itemData, input, i) {
		
			$('#summary2' ).val(itemData[i].name+" ");
			$('#operation_total').val(itemData[i].code);
		}
	});

	// 下载excel
	function browseLogExcel(){
		 var url="<%=basePath%>/admin/pay/queryMoneyConsume_date.action";
		 queryForm.action=url;
		 queryForm.submit();
		<%-- /// queryForm.action="<%=basePath%>/admin/pay/queryMoneyConsume.action";  --%>
	}

	
</script>




<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" /> <input
		type="hidden" name="onePageNum" value="${onePageNum}" />
</form>

<s:include value="/admin/template/scriptMessage.jsp" />
<div class="pageHeader">
	<s:form action="queryMoneyConsume_TotalList" rel="pagerForm" method="post"
		id="queryForm" theme="simple" cssClass="pageForm required-validate"
		onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div class="searchDiv_left">
			<s:text name="account" />：<s:textfield name="loginName"
							size="16" id="loginName"></s:textfield>
							<s:text name="or_the_role_name" />：<s:textfield
							name="nickName" size="12" id="nickName"></s:textfield>
					<s:text name="statistical_time" />：<s:textfield
							id="beginDate" name="beginDate" maxlength="10" size="12"
							cssClass="required date"></s:textfield>
				<s:text name="to" /> <s:textfield id="endDate"
							name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
					
					<%-- 				<td>
					<s:text name="operation_type" />
					<s:select list="@com.jcwx.game.common.constants.OperationLogConstant@maptype" name="operation" listKey="key" listValue="value"></s:select>
				</td> --%>

					<s:text name="operation_type" /> <%-- <s:select
							list="@com.jcwx.game.common.constants.OperationLogConstant@maptype"
							name="operation" listKey="key" listValue="value"></s:select> --%>
						  <input type="hidden" class="summary" id="operation_total" name="operation" value="${operation}"
						index="1" maxlength="100" />		
						<input type="text" class="summary" id="summary2" name="operation1"
						value="${operation1}" index="1" maxlength="100" />
					<s:text name="consumption_type" /> <s:select
							list="@com.jcwx.game.common.constants.PytTypeConstant@maptype"
							name="target" listKey="key" listValue="getText(key)"></s:select>
							
					
			</div>
			

			<div class="searchButton2">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									<s:text name="the_query" />
								</button>
								
							</div>
							
						</div>
					&nbsp;&nbsp;
						<%-- <div class="buttonActive">
							
							<div class="buttonContent">
								
								<button type="button" onclick="browseLogExcel()">
									<s:text name="export" />Excel
								</button>
							</div>
						</div> --%>
					</li>
				</ul>
			</div>

		</div>
	</s:form>
</div>

<div class="pageContent">
	<table class="list" width="100%" layoutH="65">
		<thead>
			<tr>
				<th><s:text name="operating_time" /></th>
				<th><s:text name="consumption_quantity" /></th>
				<th><s:text name="operation_type" /></th>
				<th><s:text name="operation_details" /></th>
				<th><s:text name="account" /></th>
				<!-- 
	    	    <th><s:text name="the_player_name" /></th>
	    	     -->
				<th><s:text name="the_role_of" /></th>
				<th><s:text name="total_consumption" /></th>
				<th><s:text name="the_target" />2</th>
				<th><s:text name="the_number_of" />2</th>
				<th><s:text name="players_level" /></th>
				<th><s:text name="platform_identity" /></th>
				<th><s:text name="the_login_id" /></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="operationClassList" var="oneRow" status="offset">
				<tr>
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.createTime" /></td>
					<td><s:property value="#oneRow.number" /></td>
					<td><s:property
							value="#oneRow.operation" />
						<%-- <s:property value="@com.jcwx.game.common.constants.OperationLogConstant@getTypeLabel(#oneRow.operation)"/> --%>
					</td>
					<td><s:property value="#oneRow.operationDetail" /></td>
					<td><s:property value="#oneRow.loginName" /></td>
					<td><s:property value="#oneRow.nickName" /></td>
					<td><s:property value="consumeTotal" /></td>
					<td><s:property value="#oneRow.target2" /></td>
					<td><s:property value="#oneRow.number2" /></td>
					<td><s:property value="#oneRow.level" /></td>
					<td><s:property value="#oneRow.ptFlag" /></td>
					<td><s:if test="#onePay.loginFlag == 1">
							<s:text name="page_to_swim" />
						</s:if> <s:else>
							<s:text name="mobile_phone" />
						</s:else></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

	<s:include value="/admin/template/paging.jsp" />
</div>
