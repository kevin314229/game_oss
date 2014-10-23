<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="dict" uri="http://jcwx.oss.com/dict"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
	<div class="pageHeader">
		<s:form action="/zhxy/achieve/queryPlayerAchieve_index.action" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
				  <div class="searchDiv_left">
									<s:text name="player_names" />：
									<s:textfield id="nickName" name="nickName" cssClass="required "></s:textfield>
					</div>
			    	 <div class="searchButton2">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
						</ul>
					</div>
				</div>
		 </s:form>
	</div>
	<div class="pageContent"  >
	<div class="tabs" currentIndex="0" eventType="click" layoutH="30">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li class=""><a href="javascript:;"><span><s:text name="achievement_has_been_reached" /></span></a></li>
					<li class=""><a href="javascript:;"><span><s:text name="did_not_reach_achievement" /></span></a></li>
					<li class=""><a href="javascript:;"><span><s:text name="have_acquired_title" /></span></a></li>
					<li class=""><a href="javascript:;"><span><s:text name="title_not_available" /></span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" layoutH="80">
		
			<div id="container1" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="achievement" />ID</th>
			    		<th><s:text name="achievement_name" /></th>
						<!-- <th><s:text name="number_needed_to_complete" /></th>
						<th><s:text name="the_number_of_completed" /></th> -->
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="finished" var="oneRow"  status="offset">
			    		<tr>
							<td>
								<s:property value="#oneRow.achieveId"/>
							</td>
							<td>
								<s:property value="#oneRow.achieveName"/>
							</td>
							<%-- <td>
								<s:property value="#oneRow.finishCount"/>
							</td>
							<td>
								<s:property value="#oneRow.hadCount"/>
							</td> --%>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
			 </div> 
			 
			 <div id="container2" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
			    		<th><s:text name="achievement" />ID</th>
			    		<th><s:text name="achievement_name" /></th>
						<!-- <th><s:text name="number_needed_to_complete" /></th>
						<th><s:text name="the_number_of_completed" /></th> -->
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="unfinished" var="oneRow"  status="offset">
			    		<tr>
			    			<td>
								<s:property value="#oneRow.achieveId"/>
							</td>
							<td>
								<s:property value="#oneRow.achieveName"/>
							</td>
							<%-- <td>
								<s:property value="#oneRow.finishCount"/>
							</td>
							<td>
								<s:property value="#oneRow.hadCount"/>
							</td> --%>
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
			 </div> 
			 <div id="container3" style="min-width: 400px; height: 400px; margin: 0 auto">
				<table class="list" width="100%" >
			    	<thead>
			    	<tr>
						<th><s:text name="title" />id </th>
			    		<th><s:text name="title_name" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="finishedTitle" var="oneRow"  status="offset">
			    		<tr>
			    			<td>
								<s:property value="#oneRow.titleId"/>
							</td>
							<td>
								<s:property value="#oneRow.titleName"/>
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
						<th><s:text name="title" />id </th>
			    		<th><s:text name="title_name" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="unfinishedTitle" var="oneRow"  status="offset">
			    		<tr>
							<td>
								<s:property value="#oneRow.titleId"/>
							</td>
							<td>
								<s:property value="#oneRow.titleName"/>
							</td>
							
						</tr>
			    	</s:iterator>
			    	</tbody>
	    		</table>
			 </div> 
		</div>
	</div>
</div>
