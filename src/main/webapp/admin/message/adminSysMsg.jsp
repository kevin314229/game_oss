<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent" >
				<div class="panelBar">
					<ul class="toolBar">
						<li>
				       	 <a width="770" height="340" class="add" target="dialog"  href="<%=basePath%>/admin/message/adminSysMsg_toAddSysMsg.action" ><span><s:text name="new" /> <s:text name="increase" /></span></a>
						</li>
					</ul>
				</div>
	    <table class="list" width="100%"  >
	    	<thead>
			<tr>
				<th><s:text name="the_announcement" />ID</th>
				<th><s:text name="the_server_name" /></th>
				<th><s:text name="the_announcement_type" /></th>
				<th><s:text name="content_of_the_announcement" /></th>
				<th><s:text name="creation_time" /></th>
				<th><s:text name="the_time_interval" /></th>
				<th><s:text name="the_broadcast_time" /></th>
				<th><s:text name="operation" /></th>
			</tr>
			</thead>
	    	<tbody>
			<s:iterator value="systemNoticeList" var="oneRow" status="offset">
				<tr >
					<td><s:property value="#oneRow.systemNoticeId" />
					</td>
					<td><s:property value="#oneRow.serverName" /></td>
					<td><s:property value="#oneRow.type" /></td>
					<td><s:property value="#oneRow.content" /></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.createTime" /></td>
					<td>
					<%--<s:if test="(#oneRow.delay == 15)  || (#oneRow.delay == 30) || (#oneRow.delay == 45)"><s:property value="#oneRow.delay" /><s:text name="minutes" /></s:if> --%>
					<s:if test="#oneRow.delay<60"><s:property value="#oneRow.delay" /><s:text name="minutes" /></s:if>
					<s:else><s:property value="#oneRow.delay / 60" /><s:text name="hours" /></s:else>
					</td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.noticeTime" /></td>
				  <td> 
		    	    	<a target="dialog" width="720" height="310" class="btnEdit" mask="true" href="<%=basePath %>/admin/message/adminSysMsg_editSystemNoticeId.action?systemNoticeID=${oneRow.systemNoticeId }" style="color:green" title="<s:text name="modify_the" />"><s:text name="modify_the" /></a>
		    	    	<a target="ajaxTodo" class="btnDel" href="<%=basePath %>/admin/message/adminSysMsg_delSystemNotice.action?systemNoticeID=${oneRow.systemNoticeId }" style="color:green" title="<s:text name="delete" />"><s:text name="delete" /></a>
		    	    </td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
</div>
