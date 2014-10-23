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

<%@ include file="select.jsp"%>
<s:include value="/admin/template/changeCSS.jsp"/>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" /> <input
		type="hidden" name="onePageNum" value="${onePageNum}" /> <input
		type="hidden" name="gameId" id="lostUserLevel_gameId"
		value="${gameId}" />

</form>
<div class="pageHeader">
	<s:form action="queryLostUser_Level" rel="pagerForm"
		namespace="/admin/queryLostUser" method="post" theme="simple"
		class="pageForm required-validate"
		onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div style="float: left;">
				<%-- 				<td><s:text name="project" />：<s:select list="#session.ADMIN_SYSTEM_USER_NAME.projectList" label=""
				listKey="projectId" listValue="projectName" name="gameId"
				id="lostUserLevel_gameId"  onchange="changePtIdOption(this.value,'lostUserLevel_ptId','lostUserLevel_areaId');"></s:select>
					</td>
 --%>
				<s:text name="platform" />：
				<s:select list="OssServersPt" label="" listKey="serverId"
					listValue="serverProvider" name="ptId" headerKey="-1"
					headerValue="%{getText('all_platforms')}" id="lostUserLevel_ptId"
					onchange="initPtIdParam('lostUserLevel_gameId',this.value,'lostUserLevel_areaId');"></s:select>



				<s:text name="regional" />
				：
				<s:select list="serverMap" label="" listKey="key" listValue="value"
					cssStyle="width:150px" name="selectArray" id="lostUserLevel_areaId"
					multiple="true" size="5" headerKey="-1" headerValue="--%{getText('all_servers')}--"></s:select>


				<s:text name="statistical_time" />
				：
				<s:textfield id="beginTime" name="beginTime" maxlength="10"
					size="12" cssClass="required date">
					<s:param name="value">
						<s:date name="beginTime" format="yyyy-MM-dd" />
					</s:param>
				</s:textfield>

				<s:text name="to" />
				<s:textfield id="endTime" name="endTime" maxlength="10" size="12"
					cssClass="required date">
					<s:param name="value">
						<s:date name="endTime" format="yyyy-MM-dd" />
					</s:param>
				</s:textfield>
				<s:param name="value">
					<s:date name="beginTime" format="yyyy-MM-dd" />
				</s:param>


				<s:text name="starting_level" /><input type="text" name="beginLevel" value="${beginLevel}"
					min="1" max="70" size="6" /> <s:text name="termination_level" /><input type="text"
					name="endLevel" value="${endLevel}" min="1" max="70" size="6" />

			</div>
					 <div class="searchButton">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div>
		</div>
		
	</s:form>
</div>



<div style="height: 100px; overflow: auto;" class="pageContent sortDrag"
	selector="h1" layouth="120">
	<table class="list" width="100%" layoutH="130">
		<thead>
			<tr id="src_lost">
				<th align="center" width="20"><s:text name="the_date_of" /></th>
			<!-- 	<th align="center" width="20"><s:text name="game_project" /></th> -->
				
				<script>
				var ibeginLevel=${beginLevel};
				var iendLevel=${endLevel};
				var itdsrc=document.getElementById("src_lost");
				for(var i=ibeginLevel;i<=iendLevel;i++)
					{
					var th=document.createElement("th");
					th.innerHTML="lv_"+i;
					itdsrc.appendChild(th);
					}
				
				var td2=document.createElement("th");
				td2.innerHTML="<s:text name="total" />";
				itdsrc.appendChild(td2);
				</script>
				
			</tr>
		</thead>
		<tbody>
			<%
				int num = 0;
			%>
			<s:iterator value="queryLostUserlevelList" var="oneRow"
				status="offset">
				<tr id="lost_id<%=num%>">
					<td><s:date format="yyyy-MM-dd" name="#oneRow.statisticsDate" />
					</td>
<%-- 					<td align="center"><s:property
							value="@com.jcwx.game.common.constants.GameConstant@gameMap[#oneRow.gameId]" />
					</td> --%>
					
					<script type="text/javascript">
		    	   var tdSrc = document.getElementById("lost_id<%=num%>");
		    		var str='<s:property value="#oneRow.finalString"/>';
		    		var begin='${beginLevel}';
		    		var end='${endLevel}';
		    		var arr = str.split("#");
		    		var totolLost=0;
		    		var td2 = document.createElement("td");
		    		for(var i=0;i<=end-begin;i++){
		    			var td = document.createElement("td");
		    			totolLost+=parseInt(arr[i]);
		    			if('${lengthofSelectArray}'==1&&'${firstElementOfSelectArray}'!=-1)
		    			{
		    				var level=parseInt(begin)+i;
		    				///alert(level);
		    				var a = document.createElement("a");
		    				a.target='dialog';
		    				a.setAttribute("max","true");
		    				a.href="<%=basePath%>/admin/queryLostUser/queryLostUser_LevelDetail.action?queryType=1&gameId=${gameId}&areaId=${firstElementOfSelectArray}&playerLevel="
										+ level
										+ "&statisticsDate=<s:date format='yyyy-MM-dd' name='#oneRow.statisticsDate' />";
								a.innerHTML = arr[level - 1];
								tdSrc.appendChild(td);
								td.appendChild(a);
							} else {
								td.innerHTML = arr[i];
								tdSrc.appendChild(td);
							}
						}
		    		td2.innerHTML =totolLost;
		    		tdSrc.appendChild(td2);
		    	
 					
		    		//alert(totolLost);
				
					<%
						num++;
					%>
					</script>
				</tr>
			</s:iterator>

		</tbody>
	</table>
	<%-- 	<s:include value="/admin/template/paging.jsp" /> --%>
</div>

