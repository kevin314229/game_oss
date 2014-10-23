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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="javascript:confirmRefresh({'url':'<%=basePath%>/zhxy/range/queryRange_index.action'});" ><span><s:text name="refresh_list" /></span></a></li>
		</ul>
	</div>
	<div class="tabs" currentIndex="0" eventType="click" layoutH="30">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li class=""><a href="javascript:;"><span><s:text name="personal_rating_list" /></span></a></li>
					<li><a href="javascript:;"><span><s:text name="personal_wealth_rankings" /></span></a></li>
					<li><a href="javascript:;" ><span><s:text name="personal_combat_power_rankings" /></span></a></li>
					<li><a href="javascript:;" ><span><s:text name="martial_honors_list" /></span></a></li>
					<li><a href="javascript:;" ><span><s:text name="martial_combat_power_rankings" /></span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" layoutH="80">
			<div id="container1" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="rank" /></th>
						<th><s:text name="rank_lift" /></th>
						<th><s:text name="role_name" /></th>
						<th><s:text name="grade" /></th>
						<th><s:text name="profession" /></th>
						<th><s:text name="martial" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="rangeLevel" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.rangeNumber"/>
							</td>
							<td>
							<s:if test="#oneRow.rangeChange==0"><s:text name="new" /></s:if>
							<s:elseif test="#oneRow.rangeChange>0"><s:text name="liter" /></s:elseif>
							<s:elseif test="#oneRow.rangeChange<0"><s:text name="drop" /></s:elseif>
								 
							</td>
							<td>
								<s:property value="#oneRow.name"/>
							</td>
							<td>
								<s:property value="#oneRow.level"/>
							</td>
							<td>
								<s:if test="#oneRow.occupation==1"><s:text name="monkey" /></s:if>
							<s:elseif test="#oneRow.occupation==2"><s:text name="gods" /></s:elseif>
							<s:elseif test="#oneRow.occupation==3"><s:text name="xuannv" /></s:elseif>
							</td>
							<td>
								<s:property value="#oneRow.army"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
			
			 </div>
			<div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="rank" /></th>
						<th><s:text name="rank_lift" /></th>
						<th><s:text name="role_name" /></th>
						<th><s:text name="wealth" /></th>
						<th><s:text name="profession" /></th>
						<th><s:text name="martial" /></th>
					</tr>
					</thead>
					<tbody>
			<s:iterator value="rangeSilver" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.rangeNumber"/>
							</td>
							<td>
							<s:if test="#oneRow.rangeChange==0"><s:text name="new" /></s:if>
							<s:elseif test="#oneRow.rangeChange>0"><s:text name="liter" /></s:elseif>
							<s:elseif test="#oneRow.rangeChange<0"><s:text name="drop" /></s:elseif>
								 
							</td>
							<td>
								<s:property value="#oneRow.name"/>
							</td>
							<td>
								<s:property value="#oneRow.silver"/>
							</td>
							<td>
								<s:if test="#oneRow.occupation==1"><s:text name="monkey" /></s:if>
							<s:elseif test="#oneRow.occupation==2"><s:text name="gods" /></s:elseif>
							<s:elseif test="#oneRow.occupation==3"><s:text name="xuannv" /></s:elseif>
							</td>
							<td>
								<s:property value="#oneRow.army"/>
							</td>
						</tr>
			    	</s:iterator>
			</tbody>
			</table>
			</div>	
			<div id="container3" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="rank" /></th>
						<th><s:text name="rank_lift" /></th>
						<th><s:text name="role_name" /></th>
						<th><s:text name="combat_power" /></th>
						<th><s:text name="profession" /></th>
						<th><s:text name="martial" /></th>
					</tr>
					</thead>
					<tbody>
			<s:iterator value="rangeFight" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.rangeNumber"/>
							</td>
							<td>
							<s:if test="#oneRow.rangeChange==0"><s:text name="new" /></s:if>
							<s:elseif test="#oneRow.rangeChange>0"><s:text name="liter" /></s:elseif>
							<s:elseif test="#oneRow.rangeChange<0"><s:text name="drop" /></s:elseif>
								 
							</td>
							<td>
								<s:property value="#oneRow.name"/>
							</td>
							<td>
								<s:property value="#oneRow.fight"/>
							</td>
							<td>
								<s:if test="#oneRow.occupation==1"><s:text name="monkey" /></s:if>
							<s:elseif test="#oneRow.occupation==2"><s:text name="gods" /></s:elseif>
							<s:elseif test="#oneRow.occupation==3"><s:text name="xuannv" /></s:elseif>
							</td>
							<td>
								<s:property value="#oneRow.army"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
			    	</table>
			</div>
			<div id="container4" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="rank" /></th>
						<th><s:text name="rank_lift" /></th>
						<th><s:text name="martial_name" /></th>
						<th><s:text name="martial_level" /></th>
						<th><s:text name="honor" /></th>
						<th><s:text name="number_martial" /></th>
					</tr>
					</thead>
					<tbody>
			<s:iterator value="rangeArmyProsperity" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.rangeNumber"/>
							</td>
							<td>
							<s:if test="#oneRow.rangeChange==0"><s:text name="new" /></s:if>
							<s:elseif test="#oneRow.rangeChange>0"><s:text name="liter" /></s:elseif>
							<s:elseif test="#oneRow.rangeChange<0"><s:text name="drop" /></s:elseif>
								 
							</td>
							<td>
								<s:property value="#oneRow.army"/>
							</td>
							<td>
								<s:property value="#oneRow.level"/>
							</td>
							<td>
								<s:property value="#oneRow.prosperity"/>
							</td>
							<td>
								<s:property value="#oneRow.armyNumber"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
			    	</table>
			</div>
			<div id="container5" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="rank" /></th>
						<th><s:text name="rank_lift" /></th>
						<th><s:text name="martial_name" /></th>
						<th><s:text name="grade" /></th>
						<th><s:text name="combat_power" /></th>
						<th><s:text name="number_martial" /></th>
					</tr>
					</thead>
					<tbody>
			<s:iterator value="rangeArmyBattle" var="oneRow"  status="offset">
			    		<tr  >
							<td>
								<s:property value="#oneRow.rangeNumber"/>
							</td>
							<td>
							<s:if test="#oneRow.rangeChange==0"><s:text name="new" /></s:if>
							<s:elseif test="#oneRow.rangeChange>0"><s:text name="liter" /></s:elseif>
							<s:elseif test="#oneRow.rangeChange<0"><s:text name="drop" /></s:elseif>
								 
							</td>
							<td>
								<s:property value="#oneRow.army"/>
							</td>
							<td>
								<s:property value="#oneRow.level"/>
							</td>
							<td>
							 <s:property value="#oneRow.battle"/>
							</td>
							<td>
								<s:property value="#oneRow.armyNumber"/>
							</td>
						</tr>
			    	</s:iterator>
			    	</tbody>
			    	</table>
			</div>
		</div>
	</div>
</div>
