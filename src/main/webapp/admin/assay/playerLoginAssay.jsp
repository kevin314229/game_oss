<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>


	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form action="/admin/assay/playerLogin_index.action" method="post" id="queryForm" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		<div class="searchBar">
		
		<div class="searchDiv_left" > 
					<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date">
					</s:textfield>
				 
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date">
					</s:textfield>
				</div>
		
		 <div class="searchButton2">
			<ul>
				<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
			</ul>
		 </div> 
		</div>
	</s:form>
	
	</div>
	
	<div class="pageContent sortDrag" selector="h1" layoutH="55">
		<div  class="panel collapse">
			<h1><s:text name="rank_statistics" /></h1>
				<div>
				    <table class="list" width="100%">
					    <thead>
					    	<tr>
					    	    <th width="50%"><s:text name="level" /></th>
					    	    <th width="50%"><s:text name="number_of_characters" /></th>
					    	</tr>
					     </thead>
					     <tbody>
					    	<s:iterator value="gradeList" var="oneRow"  status="offset">
					    	<tr>
						    	<td>
						    	    <s:property value="#oneRow.grade"/>
						    	</td>
						    	<td>
						    	    <s:property value="#oneRow.num"/>
						    	</td>
						    </tr>
					    	</s:iterator>
					   </tbody>
				    </table>
			    </div>
		</div>
		<div class="panel collapse">
			<h1><s:text name="the_online_time_statistics" /></h1>
			<div>
			    <table class="list" width="100%">
			    	<thead>
			    	<tr>
						<th width="50%"><s:text name="the_length" />(min)</th>
						<th width="50%"><s:text name="the_number_of" /></th>
					</tr>
					</thead>
					<tbody>
			    	<s:iterator value="onlineTimelist" var="oneRow"  status="offset">
			    		<tr  >
				    	    <td>
				    	    	<s:if test="#oneRow.temp1!=0">
				    	    		<s:property value="(#oneRow.temp1-1)*120"/> - <s:property value="(#oneRow.temp1)*120"/>
				    	    	</s:if>
				    	    	<s:else>
				    	    		0
				    	    	</s:else>
				    	    </td>
				    	     <td>
				    	    	<s:property value="#oneRow.c"/>
				    	    </td>
				    	</tr>
			    	</s:iterator>
			    	</tbody>
			    </table>
		    </div>
		</div>
	
	
	<div  class="panel collapse">
		<h1><s:text name="the_task_for_statistical" /></h1>
	    <table width="100%" class="list">
	    	<thead>
	    	<tr>
	    	    <th width="30%"><s:text name="the_last_of_the_new_task" />ID</th>
	    	    <th width="30%"><s:text name="the_task_name" /></th>
	    	    <th width="30%"><s:text name="the_number_of" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="taskList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
		    	   	 <s:property value="#oneRow.taskId"/>
		    	    </td>
		    	    <td>
		    	    	 <s:property value="#oneRow.name"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.num"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	</div>
</div>
	






