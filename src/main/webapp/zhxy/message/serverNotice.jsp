<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<style>
#myHiddenDiv {
	display: none;
}

.popup {
	border: 1px solid #ccc;
	text-align: center;
	background: url(images/b1-bg06.gif) repeat-x left top #fff;
	padding-bottom: 20px;
}
/* .popup a:hover{ text-decoration:none; color:#fff;} */
.popup-header {
	height: 24px;
	padding-top: 20px;
	height: 38px;
	line-height: 32px;
}

.popup-header h2 {
	font-size: 14px;
	width: 100%;
	text-align: center;
}

.popup-body {
	width: 100%;
	padding-top: 8px;
}

.popup-body strong {
	display: block;
	text-align: center;
	font-size: 14px;
	font-weight: normal;
	margin-bottom: 5px;
}

.con {
	padding: 10px;
	width: 692px;
	margin: 0 auto 20px auto;
	overflow: auto;
	border: 1px solid #a4c9e3;
}

.con p {
	text-indent: 2em;
	line-height: 18px;
	margin-bottom: 10px;
}

.con b {
	text-indent: 2em;
}

a.close {
	color: #fff;
	font-size: 12px;
	font-weight: 700;
	width: 156px;
	height: 24px;
	line-height: 24px;
	text-align: center;
	margin: 0 auto;
}
</style>

<script src="<%=basePath%>/media/default/js/jquery.jmpopups-0.5.1.js"></script>
<s:include value="/admin/template/scriptMessage.jsp" />


<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<li><a width="800" height="600" class="add"
				href="<%=basePath%>/admin/message/serverNotice_toAdd.action"
				target="dialog"><span><s:text name="new" /> <s:text
							name="increase" /></span></a></li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="42">
		<thead>
			<tr>
				<th><s:text name="description" /></th>
				<th width="120"><s:text name="created" /></th>
				<th width="120"><s:text name="modified" /></th>
				<th width="120"><s:text name="created" /></th>
				<th width="120"><s:text name="modified_by" /></th>
				<th width="80"><s:text name="modify_the" /></th>
				<th width="80"><s:text name="delete_operation" /></th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="serverNoticeList" var="oneRow" status="offset">
				<tr>
					<td><s:property value="#oneRow.notiveContent" /></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.createTime" /></td>
					<td><s:property value="#oneRow.updateTime" /></td>
					<td><s:property value="#oneRow.createUserId" /></td>
					<td><s:property value="#oneRow.updateUserId" /></td>
					<td><a width="800" height="600"
						href="<%=basePath%>/admin/message/serverNotice_toUpdate.action?serverNotice.noticeId=${oneRow.noticeId}"
						class="btnEdit" target="dialog"><s:text name="modify_the" /></a></td>
					<td><a href="javascript:confirmRefresh({'confirmMsg':'<s:text name="are_you_sure_you_want_to_delete_the_goods" />？',
		    	     		 'url':'<%=basePath %>/admin/message/serverNotice_doDelete.action','data':{'serverNotice.noticeId':${oneRow.noticeId}}});"
						class="btnDel"> <span><s:text name="delete" /></span></a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</div>

