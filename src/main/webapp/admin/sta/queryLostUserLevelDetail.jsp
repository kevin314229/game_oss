<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<form id="pagerForm" action="queryLostUser_LevelDetail" namespace="/admin/queryLostUser" method="post" theme="simple" onsubmit="return &&navTabSearch(this)">
	<input type="hidden" name="gameId" />
	<input type="hidden" name="areaId" />
	<input type="hidden" name="statisticsDate" />
	<input type="hidden" name="playerLevel" />
</form>

<div class="pageContent" layoutH="10" selector="h1">
	<table class="list" width="100%">
		<thead>
			<tr>
				<th align="center"><s:text name="the_date_of" /></th>
				<!-- <th align="center"><s:text name="game_project" /></th> -->
				<th align="center"><s:text name="server_region" /></th>
				<th align="center"><s:text name="players" />ID</th>
				<th align="center"><s:text name="role" />ID</th>
				<th align="center"><s:text name="login_name" /></th>
				<th align="center"><s:text name="nickname" /></th>
				<th align="center"><s:text name="grade" /></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="queryLostUserLevelDetailList" var="oneRow"
				status="offset">
				<tr>
					<td><s:date format="yyyy-MM-dd" name="#oneRow.statisticsDate" />
					</td>
					<%-- <td align="center"><s:property
							value="@com.jcwx.game.common.constants.GameConstant@gameMap[#oneRow.gameId]" />
					</td> --%>
					<td align="center">
					 <dict:itemvalue gameId="${session.ADMIN_SYSTEM_USER_NAME.currentOssServer.projectId}" type="2"  itemValue="${oneRow.areaId }"></dict:itemvalue>
					<%-- <s:property value="@com.jcwx.game.common.constants.OssServerConstant@ossServerMap[#oneRow.areaId]" /> --%></td>
					<td align="left"><s:property value="#oneRow.playerId" /></td>
					<td align="left"><s:property value="#oneRow.playerBaseId" />
					</td>
					<td align="left"><s:property value="#oneRow.loginName" /></td>
					<td align="left"><s:property value="#oneRow.nickName" /></td>
					<td align="left"><s:property value="#oneRow.playerLevel" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

</div>