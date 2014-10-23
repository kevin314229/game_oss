<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ include file="select.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>

<script type="text/javascript">
	
</script>
 
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
  
  <div class="pageContent sortDrag" selector="h1" layoutH="50">
	    
	     <div class="panel collapse">
			<h1>产出详情</h1>
			<div>
			    <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="output_to" /></th>
						<th><s:text name="number_of_outputs" /></th>
						<th>总占比 </th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="produceOperList" var="oneRow"  status="offset">
			    		<tr  >
							<td>
							
								<%--  <s:property value="@com.jcwx.game.common.constants.OperationLogConstant@maptype[#oneRow.operation]"/> --%>
								<%-- 	<dict:itemvalue
									gameId="${session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId}"
									type="1" itemValue="${oneRow.operation}"></dict:itemvalue> --%>
									<s:property value="#oneRow.operation"/>
							</td>
							<td>
								<s:property value="#oneRow.produceNum"/>
							</td>
							<td>
								<s:property value="#oneRow.percent"/>%
							</td>
						</tr>
			    	</s:iterator>
			    	<tr  >
							<td>
								<s:text name="total" />

							</td>
							
							<td>
								<s:property value= "produceSum"/>
							</td>
							<td>

							</td>
						</tr>
			    	</tbody>
	    		</table>
	    		</div>
	    </div>
	    
		<div class="panel collapse">
			<h1>消耗详情</h1>
			<div>
			    <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="point_of_consumption" /></th>
						<th><s:text name="consumption_quantity" /></th>
						<th>总占比 </th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="consumeOperList" var="oneRow"  status="offset">
			    		<tr  >
							<td>
							<%-- 	<dict:itemvalue
									gameId="${session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId}"
									type="1" itemValue="${oneRow.operation}"></dict:itemvalue> --%>
									 <s:property value="#oneRow.operation"/> 
							</td>
							<td>
							 <s:property value="#oneRow.produceNum"/> 
							</td>
							<td>
								<s:property value="#oneRow.percent"/>%
							</td>
						</tr>
	    			</s:iterator>
	    				<tr  >
							<td>
								<s:text name="total" />

							</td>
							<td>
								<s:property value= "consumeSum"/>
							</td>
							<td>
							</td>
						</tr>
	    			</tbody>
	    		</table>
	    	</div>
	    </div>
</div>