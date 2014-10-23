<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<script>
function sendRefreshData(){
	
	 ajaxTodo("<%=basePath%>/admin/assay/dayOperaStat_refreshMonth.action?port="+$("input[name='port']").val());
	
}

</script>
<div class="pageHeader">
		<s:form  action="dayOperaStat_query" namespace="/admin/assay" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				    <table class="searchContent">
					<tr>
						<td>
							<s:text name="statistical_time" />：
								<s:textfield name="beginTime" maxlength="10" size="12" cssClass="required date">
									<s:param name="value"><s:date name="beginTime" format="yyyy-MM-dd" /></s:param>
								</s:textfield>
						</td>
						<td>
							<s:text name="to" /> 
							<s:textfield name="endTime" maxlength="10" size="12" cssClass="required date">
								<s:param name="value"><s:date name="endTime" format="yyyy-MM-dd" /></s:param>
							</s:textfield>
						</td>
						<td>
							<s:text name="secondary_server_port" />：
								<s:textfield name="port">
								</s:textfield>
						</td>
					</tr>
					</table>
			    	<div class="subBar">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
							<li><a class="button" href="javascript:sendRefreshData()"><span><s:text name="refresh" /></span></a></li>
						</ul>
					</div>
				</div>
		</s:form>
</div>

<div class="pageContent">
	    <table class="list" width="100%" layoutH="90" >
	    	<thead>
	    	<tr>
				<th><s:text name="statDate" /></th>
				<th><s:text name="registeredUsers" /></th>
				<th><s:text name="login" /></th>
				<th><s:text name="landingRoles" /></th>
				<th><s:text name="activeUsers" /></th>
				<th><s:text name="netActiveUsers" /></th>
				<th><s:text name="payingUsers" /></th>
				<th><s:text name="addedPayingSubscribers" /></th>
				<th><s:text name="payTimes" /></th>
				<th><s:text name="payRate" /></th>
				<th><s:text name="registeredUsersPayRate" /></th>
				<th><s:text name="paidValue" /></th>
				<th><s:text name="signupValue" /></th>
				<th><s:text name="newPayRechargeAmount" /></th>
				<th><s:text name="rechargeAmount" /></th>
			</tr>
			</thead>
			<tbody>
	    	<s:iterator value="dayOperaStatList" var="oneRow"  status="offset">
	    		<tr>
		    	    <td>
		    	    	<s:property value="statDate"/>
		    	    </td>
		    	    <td>
		    	     	<s:property value="registeredUsers"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="login"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="landingRoles"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="activeUsers"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="netActiveUsers"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="payingUsers"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="addedPayingSubscribers"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="payTimes"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="payRate"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="registeredUsersPayRate"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="paidValue"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="signupValue"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="newPayRechargeAmount"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="rechargeAmount"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    		<tr>
		    	    <td>
		    	    	<s:text name="total" />
		    	    </td>
		    	    <td>
		    	     	<s:property value="dayOperaStat.registeredUsers"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.login"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.landingRoles"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.activeUsers"/>
		    	    </td>
		    	    <td>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.payingUsers"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.addedPayingSubscribers"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.payTimes"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.payRate"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.registeredUsersPayRate"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.paidValue"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.signupValue"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.newPayRechargeAmount"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="dayOperaStat.rechargeAmount"/>
		    	    </td>
		    	</tr>
	    	</tbody>
	    </table>
</div>
