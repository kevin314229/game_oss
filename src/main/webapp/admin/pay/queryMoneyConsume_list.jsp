<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	
%>


<script>
	var operationTypes = '<s:property value="resultJson" escape="false" />';
	var operationArray = (new Function("", "return " + operationTypes))();
	$("#summary1").achieve(operationArray, {
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
			$('#summary1' ).val(itemData[i].name+" ");
			$('#operation').val(itemData[i].code);
		}
	});
</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" /> <input
		type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
<s:include value="/admin/template/scriptMessage.jsp" />
<div class="pageHeader">
	<s:form action="queryMoneyConsume_List" rel="pagerForm" method="post"
		id="queryForm" theme="simple" cssClass="pageForm required-validate"
		onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
				<div class="searchDiv_left">
					<s:text name="login_name" />：<s:textfield
							name="loginName" size="16" id="loginName"></s:textfield>
					<s:text name="or_the_role_name" />：<s:textfield
							name="nickName" size="12" id="nickName"></s:textfield>
					<s:text name="statistical_time" />：<s:textfield
							id="beginDate" name="beginDate" maxlength="10" size="12"
							cssClass="required date"></s:textfield>
					<s:text name="to" /> <s:textfield id="endDate"
							name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
					
					<s:text name="operation_type" /> <%-- <s:select
							list="@com.jcwx.game.common.constants.OperationLogConstant@maptype"
							name="operation" listKey="key" listValue="value"></s:select> --%>
				  <input type="hidden" class="summary" id="operation" name="operation" value="${operation}"
						index="1" maxlength="100" />
						<input type="text" class="summary" id="summary1" name="operation1" value="${operation1}"
						index="1" maxlength="100" />
				<%-- 	<s:text name="consumption_type" /> <s:select
							list="@com.jcwx.game.common.constants.PytTypeConstant@maptype"
							name="target" listKey="key" listValue="getText(key)"></s:select> --%>
						物品名称：<s:select  name="itemId" list="#{2990003:'魔晶',2990002:'礼券',2990001:'金币'}" theme="simple" headerKey="" headerValue="全部"></s:select>
	
						增加类型：
						<s:select  name="flowType" list="#{1:'增加',2:'减少'}" theme="simple" headerKey="" headerValue="全部"></s:select>
	
							</div>
			
			<div style="float: left; margin-left: 15px">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									<s:text name="the_query" />
								</button>
							</div>
						</div>
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
				<th><s:text name="the_target" />2</th>
				<th><s:text name="the_number_of" />2</th>
				<th><s:text name="level" /></th>
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
					<td>
						<%-- <s:property value="@com.jcwx.game.common.constants.OperationLogConstant@getTypeLabel(#oneRow.operation)"/> --%>
						<s:property
							value="#oneRow.operation" />
					</td>
					<td><s:property value="#oneRow.operationDetail" /></td>
					<td><s:property value="#oneRow.loginName" /></td>
					<td><s:property value="#oneRow.nickName" /></td>
					<td><s:property value="#oneRow.target2" /></td>
					<td><s:property value="#oneRow.number2" /></td>
					<td><s:property value="#oneRow.level" /></td>
					<td><s:property value="#oneRow.ptFlag" /></td>
					<td><s:if test="#onePay.loginFlag == 1">
							<s:text name="page_to_swim" />
						</s:if> <else> <s:text name="mobile_phone" /></else></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<s:include value="/admin/template/paging.jsp" />





</div>
