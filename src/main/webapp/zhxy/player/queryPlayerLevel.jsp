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

<div class="pageHeader">
	<s:form action="zhplayerInfo_queryPlayerLevel" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
			<div class="searchDiv_left">
					<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" readonly="true" size="12" cssClass="required date"></s:textfield>
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" readonly="true" cssClass="required date"></s:textfield>
			</div>
			<div class="searchButton2">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
			</div>
		</div>
	</s:form>
</div>

 
	
	<div class="pageContent"  >
	<div class="tabs" currentIndex="0" eventType="click" layoutH="30">
		<div class="tabsContent" layoutH="50">
			<div id="container1" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th>等级</th>
						<th>角色数</th>
						<th>详情</th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="levelList" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.level"/>
							</td>
							<%-- <td>
								<s:if test="#oneRow.occupation==1"><s:text name="monkey" /></s:if>
							<s:elseif test="#oneRow.occupation==2"><s:text name="gods" /></s:elseif>
							<s:elseif test="#oneRow.occupation==3"><s:text name="xuannv" /></s:elseif>
							</td> --%>
							<td>
								<s:property value="#oneRow.levelNum"/>
							</td>
							<td>
							 <a target="dialog"   width="870" height="660"  href="<%=basePath%>/zhxy/player/zhplayerInfo_queryPlayerListByLevel.action?level=${oneRow.level }&beginDate=${beginDate}&endDate=${endDate}" style="color:green"><s:text name="detailed" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
			
			 </div>
			
		</div>
	</div>
</div>
