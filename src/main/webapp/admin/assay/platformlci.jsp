<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>

  	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form rel="pagerForm" action="platformlci_index" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
						<div class="searchDiv_left">
							<s:text name="statistical_time" />：<s:textfield id="startTime" name="startTime" maxlength="10" size="12"  cssClass="required date"></s:textfield>

							<s:text name="to" /> <s:textfield id="endTime" name="endTime" maxlength="10" size="12"  cssClass="required date"></s:textfield>
	
							<s:if test="#session.ADMIN_SYSTEM_USER_NAME.ossUser.isOperator==0">
								<s:text name="platform_entrance" />:
								<select name="selectptId" >
									<s:iterator value="ossOperationList" var="oneRow"  status="offset">
									<s:if test="#request.selectptId == #oneRow.carrierOperator">
										<option value="<s:property value="#oneRow.carrierOperator" />" selected> 	
					    					<s:property value="#oneRow.operationName"/>
						    			</option>
									</s:if> 
									<s:else>
										<option value="<s:property value="#oneRow.carrierOperator" />"  > 	
						    				<s:property value="#oneRow.operationName"/> 
						    			</option>
						    			</s:else>
						    		</s:iterator>
								</select>
							</s:if>
							<s:else>
							<s:text name="regional" />：
						     <select name="ossServerId">
								<s:iterator value="ossServersList" var="oneRow"  status="offset">
									<s:if test="#request.ossServerId==#oneRow.id">
									<option value="<s:property value="#oneRow.id" />" selected> 	
					    				<s:property value="#oneRow.name"/>
					    			</option>
									</s:if> 
									<s:else>
									<option value="<s:property value="#oneRow.id" />"  > 	
					    				<s:property value="#oneRow.name"/>
					    			</option>
									</s:else>
				    			</s:iterator>
							</select>
							</s:else>
						</div>
		    	<div class="searchButton2">
					<ul>
						<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
					</ul>
				</div>
				</div>
		</s:form>
	</div>
	
	<div class="pageContent sortDrag" layoutH="42" selector="h1">
		<div class="panel collapse">
			<h1><s:property value="#session.ADMIN_SYSTEM_USER_NAME.operationName"/><s:text name="collect" /></h1>
			<div>
			    <table class="list" width="100%" >
			    	<thead>
					<tr>
					<td><s:text name="region" /></td>
					<td><s:text name="grade" /> </td>
					<td><s:text name="number_of_people" /></td>
					</tr>
					</thead>
					<tbody>
						<s:iterator value="platformAllList" status="offsets" var="item">
							<tr>
								<td><s:property value="#request.ossServerName"/> </td>
								<td><s:property value="%{#item.level}" /></td>
								<td><s:property value="%{#item.number}" /> </td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
		<div style="width:33%;float:left" >
			<table class="list" width="100%" >
			    	<thead>
					<tr class="even" >
						<td colspan="4"><s:property value="#session.ADMIN_SYSTEM_USER_NAME.operationName"/><s:text name="warrior" /></td>
					</tr>
					<tr class="even" >
						<td><s:text name="region" /> </td>
						<td><s:text name="profession" /> </td>
						<td><s:text name="grade" /></td>
						<td><s:text name="number_of_people" /></td>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="platformWarriorList" status="offsets" var="item">
						<tr>
							<td> <s:property value="#session.ADMIN_SYSTEM_USER_NAME.currentOssServer.name"/>	</td>
							<td>	<s:text name="warrior" />	</td>
							<td>	<s:property value="%{#item.level}"/> </td>
							<td>	<s:property value="%{#item.number}"/>	</td>
						</tr>
					</s:iterator>
					</tbody>
			</table>
		</div>
		<div style="width:33%;float:left" >
			 <table class="list" width="100%" >
		    	<thead>
				<tr class="even" >
					<td colspan="4"><s:property value="#session.ADMIN_SYSTEM_USER_NAME.operationName"/><s:text name="master" /></td>
				</tr>
				<tr class="even" >
					<td><s:text name="region" /> </td>
					<td><s:text name="profession" /> </td>
					<td><s:text name="grade" /></td>
					<td><s:text name="number_of_people" /></td>
					
				</tr>
				</thead>
				<tbody>
				<s:iterator value="platformMasterList" status="offsets" var="item">
					<tr>
						
						<td> <s:property value="#session.ADMIN_SYSTEM_USER_NAME.currentOssServer.name"/>	</td>
						<td>	<s:text name="master" />	</td>
						<td>	<s:property value="%{#item.level}"/>	</td>
						<td>	<s:property value="%{#item.number}"/>	</td>
						
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
		<div style="width:33%;float:left">
			 <table class="list" width="100%" >
		    	<thead>
				<tr class="even" >
					<td colspan="4"><s:property value="#session.ADMIN_SYSTEM_USER_NAME.operationName"/><s:text name="archer" /></td>
				</tr>
				<tr class="even" >
					<td><s:text name="region" /> </td>
					<td><s:text name="profession" /> </td>
					<td><s:text name="grade" /></td>
					<td><s:text name="number_of_people" /></td>
					
				</tr>
				</thead>
				<tbody>
				<s:iterator value="platformArcherList" status="offsets" var="item">
					<tr>
						
						<td> <s:property value="#session.ADMIN_SYSTEM_USER_NAME.currentOssServer.name"/>	</td>
						<td>	<s:text name="archer" />	</td>
						<td>	<s:property value="%{#item.level}"/></td>
						<td>	<s:property value="%{#item.number}"/>	</td>
						
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
		<div class="panel collapse">
			<h1><s:text name="statistics" /></h1>
			<div>
			<table class="list" width="100%" >
		    	<thead>
				<tr class="even" >
					<td><s:text name="date" /></td>
					<td><s:text name="platform" /> </td>
					<td><s:text name="enrollment" /> </td>
					<td><s:text name="the_registration_number" /></td>
					<td><s:text name="number_recharge" /></td>
					<td><s:text name="recharge_amount" /></td>
				</tr>
				</thead>
				<tbody>
					<s:iterator value="platformExpandList" status="offsets" var="item">
						<tr>
							
							<td> <s:property value="%{#item.ptDate}"/>	</td>
							<td>	<s:property value="#request.selectptName"/>	</td>
							<td>	<s:property value="%{#item.regNumber}"/>	</td>
							<td>	<s:property value="%{#item.loginNumber}"/>	</td>
							<td> <s:property value="%{#item.rechargeNumber}"/>	</td>
							<td>	<s:property value="%{#item.rechargeAmount}"/> 	</td>
							
						</tr>
					</s:iterator>
				</tbody>
			</table>
			</div>
		</div>
	</div>
