<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="page" uri="http://jcwx.oss.com/page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<script type="text/javascript">
		function blackUser(obj){
			if($("#endTime"+obj).val()==""){
				alertMsg.warn("请输入封禁时间 ");
				return;
			} 
			$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "<%=basePath %>/cjwz/player/playerManage_banUser.action",
	        cache: false,
	        data: {"loginName":obj,"endTime":$("#endTime"+obj).val()},
	        success: function(msg){
				if(msg==0){
					alertMsg.correct('操作成功');
				}else{
					alertMsg.warn("操作失败，请稍后再试..");
				}
	        }
		   }); 
		}
</script>


<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="page.currPageNO" value="${page.currPageNO}" />
	<input type="hidden" name="page.onePageNum" value="${page.onePageNum}" />
</form>

<s:include value="/admin/template/scriptMessage.jsp" />
<div class="pageHeader">
	<s:form rel="pagerForm" action="playerManage_banAccount"
		namespace="/cjwz/player" method="post" theme="simple"
		cssClass="pageForm required-validate"
		onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div class="searchDiv_left">

				<s:text name="state" />
				：
				<s:select list="#{'-1': '全部','0': '正常','1': '封禁'}" label=""
					listKey="key" listValue="value" name="playerStatus"></s:select>
				<s:text name="the_keyword" />
				：
				<s:textfield name="loginName" />
				(
				<s:text name="the_user_account" />
				)
				<s:text name="platform" />
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
					</li>
				</ul>
			</div>
		</div>
	</s:form>
</div>

<div class="pageContent">
	<table class="list" width="100%" layoutH="63">
		<thead>
			<tr>
				<th><s:text name="the_user" />ID</th>
				<th><s:text name="the_user_account" /></th>
				<th><s:text name="the_login_number" /></th>
				<th><s:text name="registration_time" /></th>
				<th><s:text name="the_last_login_time" /></th>
				<th><s:text name="online_status" /></th>
				<th><s:text name="banned_state" /></th>
				<th><s:text name="operation" />(封禁时间)</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="jsonArrayList" var="oneRow" status="offset">
				<tr>
					<td><s:property value="#oneRow.id" /></td>
					<td><a href="#"
						style="color: #60A70C; text-decoration: none"><s:property
								value="#oneRow.loginName" /></a></td>
					<td><s:property value="#oneRow.loginNumber" /></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.playerCreateTime" /></td>
					<td><s:date format="yyyy-MM-dd HH:mm:ss"
							name="#oneRow.lastLoginTime" /></td>
					<td><s:property value="#oneRow.onlineStatus" /></td>
					<td><s:if test="#oneRow.playerStatus==0">
							<span class="color-gr"><s:text name="normal" /></span>
						</s:if> <s:else>
							<span class="color-red"><s:text name="banned" /></span>
						</s:else></td>
					<td>
					<s:if test="#oneRow.playerStatus==1">
						<a target="navTab" rel=""  href="<%=basePath %>/cjwz/player/playerManage_unBanUser.action?loginName=${oneRow.loginName}">
						<s:text name="remove" />
						</a>
					</s:if> 
					<s:else>
						<input id="endTime${oneRow.loginName}" name="endTime"
						class="date textInput readonly" datefmt="yyyy-MM-dd HH:mm:ss"
						readonly="true" type="text">
		    	    	&nbsp;
		    	    	<a  href="javascript:blackUser('${oneRow.loginName}')"
						style="color: #60A70C"> <s:text name="banned" /></a>
					</s:else></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<page:list page="${page}" />
</div>
