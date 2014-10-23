<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
   <style>
 #task_Body  span{
   	color:green;
  }
   </style> 
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="/admin/assay/playerTaskAssay.action" namespace="/admin/assay" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
			     <div class="searchDiv_left">
						<s:text name="statistical_time" />：
						<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
					 
						<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
					</div> 
		    	<div class="searchButton2">
					<ul>
						<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
					</ul>
				</div>
			</div>
	 </s:form>
</div>	

<div class="pageContent">
	    <table class="list" width="100%" layoutH="50" >
	    	<thead>
	    	<tr>
	    	    <th><s:text name="the_last_of_the_new_task" />ID</th>
	    	    <th><s:text name="the_task_name" /></th>
	    	    <th> 未接任务数</th>
	    	    <th> 未接比率</th>
	    	    <th> 已接任务数</th>
	    	    <th> 已接比率</th>
	    	    <th>汇总</th>
	    	     <th>汇总比率</th>
	    	</tr>
	    	</thead>
	    	<tbody id="task_Body">
	    	<s:iterator value="taskList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
		    	   	 <s:property value="#oneRow.taskId"/>
		    	    </td>
		    	    <td>
		    	    	 <s:property value="#oneRow.name"/>
		    	    </td>
		    	     <td>
		    	    	 <s:property value="#oneRow.sum1"/>
		    	    </td>
		    	    <td>
		    	    <s:if test="#offset.index==0">
		    	    	 -
		    	    </s:if>
		    	    <s:else>
		    	     <span> <s:property value="#oneRow.rate1"/>%</span>
		    	    </s:else>
		    	    </td>
		    	    <td>
		    	    	 <s:property value="#oneRow.sum2"/>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#offset.index==0">
		    	    	 -
		    	    </s:if>
		    	    <s:else>
		    	     	<span><s:property value="#oneRow.rate2"/>%</span>
		    	    </s:else>
		    	    </td>
		    	    <td>
		    	    	 
		    	     <s:property value="#oneRow.sum"/>
		    	    </td>
		    	    <td>
		    	    <s:if test="#offset.index==0">
		    	    		-
		    	    </s:if>
		    	    <s:else>
		    	     <span> <s:property value="#oneRow.rateSum"/></span>
		    	    </s:else>
		    	    	
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	</div>
   
