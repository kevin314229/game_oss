<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

	
	<script type="text/javascript" language="javascript">
		
		$(document).ready(function() {
        	
        	checkStut();
        });

        
        function queryAllPlayer(){
        	$("#currPageNO").val(1);
        	if($("#serverId").val()==-1){
        		$("#opId").val($("#operationId").val());
        		$("#osId").val($("#serverId").val());
        		$("#begin").val($("#beginDate").val());
        		$("#end").val($("#endDate").val());
            	$("#baseFrom2").submit();
        	}else{
        		//$('baseFrom2').attr('action','questionNew_query');
            	$("#baseFrom1").submit();
        	}
        }
        
        function optionChange(obj){
	  		$("#serverId").empty();
	  		if(obj==-1){
	  			$("#serverId").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
	  			checkStut();
	  			return;
	  		}
	 		$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
	            dataType: "json",//<s:text name="return" />json<s:text name="data_format" />
	            url: "<%=basePath%>/admin/base/questionNew_getOssServerListByOperationId.action",
	            cache: false,
	            data: "ossServerId="+obj,//<s:text name="data_to_be_sent" />
	            success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
	            	var myobj=eval(msg);
	            	$("#serverId").append("<option value='-1'>--<s:text name="all_servers" />--</option>");
	            	for(var i=0;i<myobj.length;i++){
	            		$("#serverId").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
	            	}
	            	checkStut();
	            }
		   });
		}
        
        function checkStut(){
        	if($("#serverId").val()==-1){
        		document.getElementById("nickName").disabled=true;
            	document.getElementById("loginName").disabled=true;
            	document.getElementById("questionStatus").disabled=true;
        	}else{
        		document.getElementById("nickName").disabled=false;
            	document.getElementById("loginName").disabled=false;
            	document.getElementById("questionStatus").disabled=false;
        	}
        }
        
        
	</script>
<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>

  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form rel="pagerForm"  action="questionNew_index" namespace="/admin/base" method="post" theme="simple" id="baseFrom2"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			<input type="hidden" id="opId" name="operationId">
			<input type="hidden" id="osId" name="ossServerId">
			<s:textfield id="begin" style="display:none" name="beginDate" maxlength="10" size="12"></s:textfield>
			<s:textfield id="end" style="display:none" name="endDate" maxlength="10" size="12"></s:textfield>
	</s:form>

	<s:form rel="pagerForm" action="questionNew_query" namespace="/admin/base" method="post" theme="simple" id="baseFrom1" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		   <div class="searchDiv_left">
					<s:text name="platform" />：<select id="operationId" name="operationId" onchange="optionChange(this.value)">
								<option value="-1">--<s:text name="all_platforms" />--</option>
								<s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossServersPt" var="oneRow"  status="offset">
									<s:if test="operationId==#oneRow.serverId">
									<option value="<s:property value="#oneRow.serverId" />" selected> 	
					    				<s:property value="#oneRow.serverProvider"/>
					    			</option>
					    			</s:if>
					    			<s:else>
									<option value="<s:property value="#oneRow.serverId" />"  > 	
					    				<s:property value="#oneRow.serverProvider"/>
					    			</option>
									</s:else>
					    		</s:iterator>
						</select> 
				 
					<s:text name="server" />：
					<select id="serverId" name="ossServerId" onchange="checkStut()">
						    <option value="-1" >--<s:text name="all_servers" />--</option>
							<s:iterator value="ossServerList" var="oneRow"  status="offset">
									<s:if test="ossServerId==#oneRow.id">
										<option value="<s:property value="#oneRow.id" />" selected> 	
						    				<s:property value="#oneRow.name"/>
						    			</option>
									</s:if> 
									<s:else>
										<option value="<s:property value="#oneRow.id" />"  > 	
						    				<s:property value="#oneRow.name"/>
						    			</option>
									</s:else>
					    	</s:iterator>
						  
					</select>
				 
					<s:text name="state" />：<s:select list="#{'': getText('all'),'0': getText('did_not_return'),'1': getText('reply')}" label="" listKey="key" listValue="value" name="questionStatus" id="questionStatus"></s:select>
				 
					<s:text name="account_number" /><s:textfield  id="loginName" name="loginName" maxlength="10" size="12" />
				 
					<s:text name="role_name" /><s:textfield  id="nickName" name="nickName" maxlength="10" size="12" />
					<s:text name="statistical_time" />：
					<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date">
						 <s:param name="value" ><s:date name="beginDate" format="yyyy-MM-dd" /></s:param> 
					</s:textfield>
				 
					<s:text name="to" /> 
					<s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date">
						<s:param name="value" ><s:date name="endDate" format="yyyy-MM-dd" /></s:param>
					</s:textfield>
				</div>
	    	<div class="searchButton2">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="button" onClick="queryAllPlayer();"><s:text name="the_query"/></button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
	</div>

	<div class="pageContent">
	    <table class="list" width="100%" layoutH="64" >
	    	<thead>
	    	<tr>
	    	    <th width="35">ID</th>
	    	    <th ><s:text name="region" /></th>
	    	    <th ><s:text name="platform" /></th>
	    	    <th ><s:text name="account_number" /></th>
	    	    <th><s:text name="the_player_name" /></th>
	    	    <th width="39"><s:text name="grade" /></th>
	    	    <th width="59">vip<s:text name="grade" /></th>
	    	    <th width="59"><s:text name="recharge_amount" /></th>
	    	    <th width="46"><s:text name="type_of_problem" /></th>
	    	    <th width="48"><s:text name="state_of_the_problem" /></th>
	    	    <th width="48"> <s:text name="creation_time" /></th>
	    	    <th width="350px" ><s:text name="problem_description" /></th>
	    	    <th width="250px"><s:text name="reply" /></th>
	    	    <th width="50"><s:text name="deal_with_people_account" /></th>
	    	    <th width="62"><s:text name="operating" /></th>
	      </tr>
	      </thead>
	      <tbody>
	    <s:iterator value="ossQuestionNewList" var="oneRow"  status="offset">
	       <tr  >
		    	<td><s:property value="#oneRow.questionId"/>
		    	<input type="hidden" id="questionId${questionId}" value="${questionId}"> 
		    	<input type="hidden" id="serverId${questionId}" value="${serverId}"></td>
		    	<td><s:property value="#oneRow.serverName"/> </td>
		    	<td><s:property value="#oneRow.serverCode"/> </td>
		    	<td><s:property value="#oneRow.loginName"/> </td>
		    	<td><s:property value="#oneRow.nickName"/> </td>
		    	<td><s:property value="#oneRow.level"/> </td>
		    	<td>VIP<s:property value="#oneRow.vipLevel"/> </td>
		    	<td><s:property value="#oneRow.payMoney"/> </td>
		    	<td>
		    	
		    	</td>
		    	<td>
		    	<s:if test="questionStatus==1">
		    	<s:text name="replied" />
		    	</s:if>
		    	<s:else>
		    	<s:text name="no_reply" />
		    	</s:else>
		    	</td>
		    	<td><s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.queDate"/> </td>
		    	<td>
		    	<div style="display:none;" id="div${questionId}" >
		    	<div style="width:348px; height:200px; overflow:auto; border:1px solid green;">
		    		<label id="question${questionId}"></label><br>
		    	</div>
		    		<a style="color:green;float:right;" href="javascript:closeQuestion(${questionId})"><s:text name="off_the_record" /></a>
		    	</div>
		    	<br>
		    	<s:text name="players" /> 
				<s:if test="#phoneType!=null&&#phoneType!=''">
		    	  (${phoneType})
				</s:if>		    	
		    	：&nbsp;<s:property value="#oneRow.question"/> 
		    	<a style="color:green;" href="javascript:queryQuestion(${questionId},${playerBaseId})"><s:text name="view_record" /></a>
		    	</td>
		    	<s:if test="#oneRow.questionStatus==0">
			    	<td>
			    	<label id="lbreplay${questionId}"></label>
			    	<textarea title="<s:text name="click_to_reply" />" id="questionReplay${questionId}" style="height:15px"  onclick="opentext(this,${questionId})" rows="1" cols="25"></textarea>
			    	<a id="replay${questionId}" style="display:none" href="javascript:replyQuestion(${questionId},1)" ><s:text name="reply" /></a>
			    	</td>
			    	<td><label id="lbUserID${questionId}" style="display:none">
			    	<s:property value="#session.ADMIN_SYSTEM_USER_NAME.ossUser.username"/></label>
			    	<label id="startID${questionId}"></label>
			    	 </td>
			    	<td>
			    	<a id="clo${questionId}" href="javascript:replyQuestion(${questionId},2)"><s:text name="close_problem" /></a>
			    	<a id="upd${questionId}" style="display:none;" href="javascript:retReplay(${questionId})"><s:text name="modify_reply" /></a>
			    	</td>
		    	</s:if>
		    	<s:else>
			    	<td><label id="lbreplay${questionId}"><s:property value="#oneRow.questionReplay"/></label> 
			    	<textarea title="<s:text name="click_to_reply" />" id="questionReplay${questionId}" style="height:15px;display:none;" maxlength="256" onclick="opentext(this,${questionId})" rows="1" cols="25">
			    	<s:property value="#oneRow.questionReplay"/>
			    	</textarea>
			    	<a id="replay${questionId}" style="display:none"  href="javascript:replyQuestion(${questionId},1)" > <s:text name="reply" /></a>
			    	</td>
			    	<td>
			    	<label id="lbUserID${questionId}" style="display:none">
			    	<s:property value="#session.ADMIN_SYSTEM_USER_NAME.ossUser.username"/></label> 
			    	<label id="startID${questionId}"> <s:property value="#oneRow.replyUserName"/> </label>
			    	</td>
			    	<td>
			    	<a id="clo${questionId}" style="display:none" href="javascript:replyQuestion(${questionId},2)"><s:text name="close_problem" /></a>
			    	<a id="upd${questionId}" href="javascript:retReplay(${questionId},1)"><s:text name="modify_reply" /></a>
			    	</td>
		    	</s:else>
		    	    
		    </tr>
	    </s:iterator>
	    	</tbody>
	    </table>
	    
	    <s:include value="/admin/template/paging.jsp"/>
  </div>

  
  <script type="text/javascript">
  
  	function retReplay(id){
  		document.getElementById("questionReplay"+id).style.display="block";
  		document.getElementById("lbreplay"+id).style.display="none";
  	}
  
  	function opentext(obj,id){
  		obj.style.height="200px";
  		document.getElementById("replay"+id).style.display="block";
  	}
  	
  	function replyQuestion(id,status){
  		var questionId=$("#questionId"+id).val();
  		var questionReplay=$("#questionReplay"+id).val();
  		if(questionReplay.replace(/[^\x00-\xFF]/g,'**').length>256){
			var lg=questionReplay.replace(/[^\x00-\xFF]/g,'**').length-256;
			alertMsg.warn("<s:text name="exceed" />"+lg+"<s:text name="bytes" />");
			return;
		}
  			
  		var serverId=$("#serverId"+id).val();
  		if(questionReplay==""&&status==1){
  			alertMsg.warn("<s:text name="please_enter_a_reply_message" />");
  			return;
  		}
  		$.ajax({
            type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
            //dataType: "json",//<s:text name="return" />json<s:text name="data_format" />
            url: "<%=basePath%>/admin/base/questionNew_update.action",
            cache: false,
            data: {"ossQuestionNew.questionId":questionId,"ossQuestionNew.questionReplay":questionReplay,"ossQuestionNew.serverId":serverId},//<s:text name="data_to_be_sent" />
            success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
            	if(msg=="OK"){
            		alertMsg.correct('<s:text name="successful_operation" />！');
            		document.getElementById("questionReplay"+id).style.display="none";
            		document.getElementById("lbUserID"+id).style.display="block";
            		document.getElementById("lbreplay"+id).style.display="block";
            		document.getElementById("lbreplay"+id).innerHTML=questionReplay;
            		document.getElementById("replay"+id).style.display="none";
            		document.getElementById("upd"+id).style.display="block";
            		document.getElementById("clo"+id).style.display="none";
            		document.getElementById("startID"+id).style.display="none";
            	}else{
            		alertMsg.error("<s:text name="operation_failed" />，<s:text name="please_try_again_later" />..");
            	}
            }
	   });
  		
  		
  	}
  	
  	function queryQuestion(id,playerBaseId){
  		var serverId=$("#serverId"+id).val();
  		$.ajax({
            type: "post",//<s:text name="use" />post<s:text name="way_to_access_the_background" />
            url: "<%=basePath%>/admin/base/questionNew_queryQuestionNew.action",
            cache: false,
            data: {"ossQuestionNew.playerBaseId":playerBaseId,"ossServerId":serverId},//<s:text name="data_to_be_sent" />
            success: function(msg){//msg<s:text name="for_the_returned_data" />，<s:text name="where_do_data_binding" />
            	var qhtml="";
            	var myobj=eval(msg);
            	for(var i=0;i<myobj.length;i++){
            		var replay="";
            		var phoneType="";
            		if(myobj[i].questionReplay!=null){
            			replay="<label style='color:green;'>(GM)"+myobj[i].replyUserName +"</label><br>&nbsp;&nbsp;&nbsp;&nbsp;"+myobj[i].questionReplay+"<br>";
            		}
            		if(myobj[i].phoneType!=null){
            			phoneType="("+myobj[i].phoneType+")";
            		}
            		qhtml+=myobj[i].nickName+phoneType+"&nbsp;"+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+myobj[i].question+"<br>"+replay+"<br>";
            	}
            	document.getElementById("question"+id).innerHTML=qhtml;
            	document.getElementById("div"+id).style.display="block";
            	
            }
	   });
  	}
  	function closeQuestion(id){
  		document.getElementById("div"+id).style.display="none";
  	}
  	
  </script>
  
