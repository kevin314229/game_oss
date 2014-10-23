<%@page import="java.math.BigDecimal"%>
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
   	<script>
		    	     	var lastSum=0.0;
		    	     	var lastSum1=0.0;
		    	     	var lastSum2=0.0;
		    	     	var lastSum3=0.0;
		    	     	var lastSum4=0.0;
	</script>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form action="/zhxy/assay/playerTaskAssay.action" namespace="/zhxy/assay" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
			     <div class="searchDiv_left">
						<s:text name="statistical_time" />：
						<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
					 
						<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
					<%-- 	<s:text name="task_types" />：<select id="type" name="type" >
							<option value="">-<s:text name="whole" />-</option>
							<option value="1" <s:if test="type==1" >selected</s:if> ><s:text name="the_main_task" /></option>
							<option value="2"  <s:if test="type==2" >selected</s:if>><s:text name="quests" /></option>
						</select> --%>
						<input type="hidden" value="1" name="type" id="type"/>
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
	    	       <th><s:text name="satisfy_the_condition_number" /></th>
	    	     <th><s:text name="meet_ratio" /></th>
	    	    <th> <s:text name="missed_number_of_tasks" /></th>
	    	    <th> <s:text name="missed_ratio" /></th>
	    	    <th> <s:text name="received_number_of_tasks" /></th>
	    	    <th> <s:text name="received_ratio" /></th>
	    	    <th> <s:text name="the_number_of_tasks_completed" /></th>
	    	    <th> <s:text name="completion_rates" /></th>
	    	    <th> <s:text name="submit_a_number_of_tasks" /></th>
	    	    <th> <s:text name="submit_ratio" /></th>
	    	 
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
		    	    	 
		    	     <s:property value="#oneRow.sum"/>
		    	    </td>
		    	    <td>
		    	   <s:if test="#offset.index==0">
		    	    	 -
		    	    	 <script>
		    	     	  lastSum=<s:property value="#oneRow.sum" />;
		    	     </script>
		    	    </s:if>
		    	    <s:else>
		    	    <s:if test="#oneRow.sum==0">
		    	    	 	<span> 0.00%</span>
		    	    	</s:if>
		    	    	<s:else>
		    	    		  <span id="allsum<s:property value='#offset.index' />"></span>
		    	    		     <script>
		    	     	  var sum=<s:property value="#oneRow.sum"/>;
		    	     	 
		    	     	  var sumRate =(sum/lastSum*100).toFixed(2);
		    	     	  $("#allsum<s:property value='#offset.index'/>")[0].innerHTML=sumRate+"%";
		    	     	  lastSum=sum;
		    	     </script>
		    	    	</s:else>
		    	   
		    	     
		    	  
		    	    </s:else>
		    	    </td>
		    	     <td>
		    	    	 <s:property value="#oneRow.sum1"/>
		    	    </td>
		    	    <td>
		    	    <s:if test="#offset.index==0">
		    	    	 -
		    	    	 <script>
		    	     	  lastSum1=<s:property value="#oneRow.sum1"/>;
		    	     </script>
		    	    </s:if>
		    	    <s:else>
		    	    
		    	    <s:if test="#oneRow.sum1==0">
		    	    	 	<span> 0.00%</span>
		    	    	</s:if>
		    	    	<s:else>
		    	    		  <span id="1sum<s:property value='#offset.index' />"></span>
		    	    		     <script>
		    	     	  var sum1=<s:property value="#oneRow.sum1"/>;
		    	     	  var sumRate1 =(sum1/lastSum1*100).toFixed(2);
		    	     	  $("#1sum<s:property value='#offset.index'/>")[0].innerHTML=sumRate1+"%";
		    	     	  lastSum1=sum1;
		    	     </script>
		    	    	</s:else>
		    	     
		    	    </s:else>
		    	    </td>
		    	    <td>
		    	    	 <s:property value="#oneRow.sum-#oneRow.sum1"/>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#offset.index==0">
		    	    	 -
		    	    	 <script>
		    	     	  lastSum2=<s:property value="#oneRow.sum-#oneRow.sum1"/>;
		    	     </script>
		    	    </s:if>
		    	    <s:else>
		    	    
		    	    <s:if test="#oneRow.sum-#oneRow.sum1==0">
		    	    	 	<span> 0.00%</span>
		    	    	</s:if>
		    	    	<s:else>
		    	    		  <span id="2sum<s:property value='#offset.index' />"></span>
		    	    		     <script>
		    	     	  var sum2=<s:property value="#oneRow.sum-#oneRow.sum1"/>;
		    	     	  var sumRate2 =(sum2/lastSum2*100).toFixed(2);
		    	     	  $("#2sum<s:property value='#offset.index'/>")[0].innerHTML=sumRate2+"%";
		    	     	  lastSum2=sum2;
		    	     </script>
		    	    	</s:else>
		    	    </s:else>
		    	    </td>
		    	      <td>
		    	    	 <s:property value="#oneRow.sum3+#oneRow.sum4"/>
		    	    </td>
		    	    <td> 
		    	    <s:if test="#offset.index==0">
		    	    	 -
		    	    	 <script>
		    	     	  lastSum4=<s:property value="#oneRow.sum3+#oneRow.sum4"/>;
		    	     </script>
		    	    </s:if>
		    	    <s:else>
		    	    
		    	    <s:if test="#oneRow.sum3+#oneRow.sum4==0">
		    	    	 	<span> 0.00%</span>
		    	    	</s:if>
		    	    	<s:else>
		    	    		  <span id="4sum<s:property value='#offset.index' />"></span>
		    	    		     <script>
		    	     	  var sum4=<s:property value="#oneRow.sum3+#oneRow.sum4"/>;
		    	     	  var sumRate4 =(sum4/lastSum4*100).toFixed(2);
		    	     	  $("#4sum<s:property value='#offset.index'/>")[0].innerHTML=sumRate4+"%";
		    	     	  lastSum4=sum4;
		    	     </script>
		    	    	</s:else>
		    	   
		    	     
		    	  
		    	    </s:else>
		    	    </td>
		    	      <td>
		    	    	 <s:property value="#oneRow.sum3"/>
		    	    </td>
		    	    <td>
		    	   <s:if test="#offset.index==0">
		    	    	 -
		    	    	 <script>
		    	     	  lastSum3=<s:property value="#oneRow.sum3"/>;
		    	     </script>
		    	    </s:if>
		    	    <s:else>
		    	    
		    	    <s:if test="#oneRow.sum3==0">
		    	    	 	<span> 0.00%</span>
		    	    	</s:if>
		    	    	<s:else>
		    	    		  <span id="3sum<s:property value='#offset.index' />"></span>
		    	    		     <script>
		    	     	  var sum3=<s:property value="#oneRow.sum3"/>;
		    	     	  var sumRate3 =(sum3/lastSum3*100).toFixed(2);
		    	     	  $("#3sum<s:property value='#offset.index'/>")[0].innerHTML=sumRate3+"%";
		    	     	  lastSum3=sum3;
		    	     </script>
		    	    	</s:else>
		    	    </s:else>
		    	    </td>
		    	  
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	</div>
   
