<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/changeCSS.jsp"/>


 
	
	<div class="pageContent"  >
	<div class="tabs" currentIndex="0" eventType="click" layoutH="30">
		<div class="tabsContent" layoutH="50">
			<div id="container1" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th>账号</th>
						<th>角色名</th>
						<th>创建时间</th>
						<th>最后登录时间</th>
						<th>当前经验</th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="playerList" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.loginName"/>
							</td>
							<%-- <td>
								<s:if test="#oneRow.occupation==1"><s:text name="monkey" /></s:if>
							<s:elseif test="#oneRow.occupation==2"><s:text name="gods" /></s:elseif>
							<s:elseif test="#oneRow.occupation==3"><s:text name="xuannv" /></s:elseif>
							</td> --%>
							<td>
								<s:property value="#oneRow.nickName"/>
							</td>
							<td>
							<s:property value="#oneRow.createTime"/>
							</td>
							<td>
							<s:property value="#oneRow.logoutTime"/>
							</td>
							<td>
							<s:property value="#oneRow.EXP"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
			
			 </div>
			
		</div>
	</div>
</div>
