<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>

<script type="text/javascript">
	
</script>
 
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
  
  <div class="pageContent sortDrag" selector="h1" layoutH="50">
	    
	    
		<div class="panel">
			<div>
			    <table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th>账号名</th>
			    		<th>角色名</th>
			    		<th>领取物品</th>
						<th>领取时间</th>
						<th>角色创建时间</th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="playerList" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								 <s:property value="#oneRow.loginName"/> 
							</td>
							<td>
							 <s:property value="#oneRow.nickName"/> 
							</td>
							<td  style="word-break:break-all; word-wrap:break-all;">
								 <s:property value="#oneRow.item"/> 
							</td>
							<td>
							 <s:property value="#oneRow.createTime"/> 
							</td>
							<td>
								  <s:property value="#oneRow.playerCreateTime"/> 
							</td>
							 
						</tr>
	    			</s:iterator>
	    			</tbody>
	    		</table>
	    	</div>
	    </div>
</div>