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
        		//$('baseFrom2').attr('action','question_query');
            	$("#baseFrom1").submit();
        	}
        }
        
        function optionChange(obj){
	  		$("#serverId").empty();
	  		if(obj==-1){
	  			$("#serverId").append("<option value='-1'>--全部服务器--</option>");
	  			checkStut();
	  			return;
	  		}
	 		$.ajax({
	            type: "post",//使用post方法访问后台
	            dataType: "json",//返回json格式的数据
	            url: "<%=basePath%>/cjwz/base/question_getOssServerListByOperationId.action",
	            cache: false,
	            data: "ossServerId="+obj,//要发送的数据
	            success: function(msg){//msg为返回的数据，在这里做数据绑定
	            	var myobj=eval(msg);
	            	$("#serverId").append("<option value='-1'>--全部服务器--</option>");
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
	<s:form rel="pagerForm"  action="question_index" namespace="/cjwz/base" method="post" theme="simple" id="baseFrom2"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			<input type="hidden" id="opId" name="operationId">
			<input type="hidden" id="osId" name="ossServerId">
			<s:textfield id="begin" style="display:none" name="beginDate" maxlength="10" size="12"></s:textfield>
			<s:textfield id="end" style="display:none" name="endDate" maxlength="10" size="12"></s:textfield>
	</s:form>

	<s:form rel="pagerForm" action="question_query" namespace="/cjwz/base" method="post" theme="simple" id="baseFrom1" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		   <div class="searchDiv_left">
					平台：<select id="operationId" name="operationId" onchange="optionChange(this.value)">
								<option value="-1">--全部平台--</option>
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
				 
					服务器：
					<select id="serverId" name="ossServerId" onchange="checkStut()">
						    <option value="-1" >--全部服务器--</option>
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
				 
					帐号<s:textfield  id="loginName" name="loginName" maxlength="10" size="12" />
				 
					角色名<s:textfield  id="nickName" name="nickName" maxlength="10" size="12" />
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
	    	    <th >大区</th>
	    	    <th >平台</th>
	    	    <th >帐号</th>
	    	    <th><s:text name="the_player_name" /></th>
	    	    <th width="39">等级</th>
	    	    <th width="59">vip等级</th>
	    	    <th width="59">充值金额</th>
	    	    <th width="46"><s:text name="type_of_problem" /></th>
	    	    <th width="48"><s:text name="state_of_the_problem" /></th>
	    	    <th width="48"> <s:text name="creation_time" /></th>
	    	    <th width="350px" ><s:text name="problem_description" /></th>
	    	    <th width="250px">回复内容</th>
	    	    <th width="50">处理人帐号</th>
	    	    <th width="62">操作</th>
	      </tr>
	      </thead>
	      <tbody>
	    <s:iterator value="ossQuestionList" var="oneRow"  status="offset">
	       <tr  >
		    	<td><s:property value="#oneRow.questionId"/>
		    	<input type="hidden" id="questionId${questionId}${serverId}" value="${questionId}"> 
		    	<input type="hidden" id="serverId${questionId}${serverId}" value="${serverId}"></td>
		    	<td><s:property value="#oneRow.serverName"/> </td>
		    	<td><s:property value="#oneRow.serverCode"/> </td>
		    	<td><s:property value="#oneRow.loginName"/> </td>
		    	<td><s:property value="#oneRow.nickName"/> </td>
		    	<td><s:property value="#oneRow.level"/> </td>
		    	<td>VIP<s:property value="#oneRow.vipLevel"/></td>
		    	<td><s:property value="#oneRow.payMoney"/> </td>
		    	<td>
		    	
		    	</td>
		    	<td>
		    	<s:if test="questionStatus==1">
		    	已回复
		    	</s:if>
		    	<s:else>
		    	未回复
		    	</s:else>
		    	</td>
		    	<td><s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.queDate"/> </td>
		    	<td>
		    	<div style="display:none;" id="div${questionId}${serverId}" >
		    	<div style="width:348px; height:200px; overflow:auto; border:1px solid green;">
		    		<label id="question${questionId}${serverId}"></label><br>
		    	</div>
		    		<a style="color:green;float:right;" href="javascript:closeQuestion(${questionId},${serverId})">关闭记录</a>
		    	</div>
		    	<br>
		    	玩家 
				<s:if test="#phoneType!=null&&#phoneType!=''">
		    	  (${phoneType})
				</s:if>		    	
		    	：&nbsp;<s:property value="#oneRow.question"/> 
		    	<a style="color:green;" href="javascript:queryQuestion(${questionId},${serverId},'${playerId}')">查看记录</a>
		    	</td>
		    	<s:if test="#oneRow.questionStatus==0">
			    	<td>
			    	<label id="lbreplay${questionId}${serverId}"></label>
			    	<textarea title="点击回复" id="questionReply${questionId}${serverId}" style="height:15px;overflow-x:hidden"  onclick="opentext(this,${questionId},${serverId})" rows="1" cols="25"></textarea>
			    	<a id="replay${questionId}${serverId}" style="display:none" href="javascript:replyQuestion(${questionId},${serverId},1)" >回复</a>
			    	</td>
			    	<td><label id="lbUserID${questionId}${serverId}" style="display:none">
			    	<s:property value="#session.ADMIN_SYSTEM_USER_NAME.ossUser.username"/></label>
			    	<label id="startID${questionId}${serverId}"></label>
			    	 </td>
			    	<td>
			    	<a id="clo${questionId}${serverId}" href="javascript:replyQuestion(${questionId},${serverId},2)">关闭问题</a>
			    	<a id="upd${questionId}${serverId}" style="display:none;" href="javascript:retReplay(${questionId},${serverId})">修改回复</a>
			    	</td>
		    	</s:if>
		    	<s:else>
			    	<td><label id="lbreplay${questionId}${serverId}"><s:property value="#oneRow.questionReply"/></label> 
			    	<textarea title="点击回复" id="questionReply${questionId}${serverId}" style="height:15px;display:none;overflow-x:hidden" maxlength="256" onclick="opentext(this,${questionId},${serverId})" 
			    	rows="1" cols="25"><s:property value="#oneRow.questionReply"/></textarea>
			    	<a id="replay${questionId}${serverId}" style="display:none"  href="javascript:replyQuestion(${questionId},${serverId},1)" > 回复</a>
			    	</td>
			    	<td>
			    	<label id="lbUserID${questionId}${serverId}" style="display:none">
			    	<s:property value="#session.ADMIN_SYSTEM_USER_NAME.ossUser.username"/></label> 
			    	<label id="startID${questionId}${serverId}"> <s:property value="#oneRow.replyUserName"/> </label>
			    	</td>
			    	<td>
			    	<a id="clo${questionId}${serverId}" style="display:none" href="javascript:replyQuestion(${questionId},${serverId},2)">关闭问题</a>
			    	<a id="upd${questionId}${serverId}" href="javascript:retReplay(${questionId},${serverId},1)">修改回复</a>
			    	</td>
		    	</s:else>
		    	    
		    </tr>
	    </s:iterator>
	    	</tbody>
	    </table>
	    
	    <s:include value="/admin/template/paging.jsp"/>
  </div>

  
  <script type="text/javascript">
  
  	function retReplay(id,serverId){
  		document.getElementById("questionReply"+id+serverId).style.display="block";
  		document.getElementById("lbreplay"+id+serverId).style.display="none";
  	}
  
  	function opentext(obj,id,serverId){
  		obj.style.height="200px";
  		document.getElementById("replay"+id+serverId).style.display="block";
  	}
  	
  	function replyQuestion(id,serverId,status){
  		var questionId=$("#questionId"+id+serverId).val();
  		var questionReplay=$("#questionReply"+id+serverId).val();
  		if(questionReplay.replace(/[^\x00-\xFF]/g,'**').length>256){
			var lg=questionReplay.replace(/[^\x00-\xFF]/g,'**').length-256;
			alertMsg.warn("超出"+lg+"个字节");
			return;
		}
  			
  		var serverId=$("#serverId"+id+serverId).val();
  		if(questionReplay==""&&status==1){
  			alertMsg.warn("请输入回复信息");
  			return;
  		}
  		$.ajax({
            type: "post",//使用post方法访问后台
            //dataType: "json",//返回json格式的数据
            url: "<%=basePath%>/cjwz/base/question_update.action",
            cache: false,
            data: {"ossQuestion.questionId":questionId,"ossQuestion.questionReply":questionReplay,"ossQuestion.serverId":serverId},//要发送的数据
            success: function(msg){//msg为返回的数据，在这里做数据绑定
            	if(msg=="ok"){
            		alertMsg.correct('操作成功！');
            		document.getElementById("questionReply"+id+serverId).style.display="none";
            		document.getElementById("lbUserID"+id+serverId).style.display="block";
            		document.getElementById("lbreplay"+id+serverId).style.display="block";
            		document.getElementById("lbreplay"+id+serverId).innerHTML=questionReplay;
            		document.getElementById("replay"+id+serverId).style.display="none";
            		document.getElementById("upd"+id+serverId).style.display="block";
            		document.getElementById("clo"+id+serverId).style.display="none";
            		document.getElementById("startID"+id+serverId).style.display="none";
            	}else{
            		alertMsg.error("操作失败，请稍后再试..");
            	}
            }
	   });
  		
  		
  	}
  	
  	function queryQuestion(id,serverId,playerId){
  		var serverId=$("#serverId"+id+serverId).val();
  		$.ajax({
            type: "post",//使用post方法访问后台
            url: "<%=basePath%>/cjwz/base/question_queryQuestion.action",
            cache: false,
            data: {"ossQuestion.playerBaseId":playerId,"ossServerId":serverId},//要发送的数据
            success: function(msg){//msg为返回的数据，在这里做数据绑定
            	var qhtml="";
            	var myobj=eval(msg);
            	for(var i=0;i<myobj.length;i++){
            		var replay="";
            		var phoneType="";
            		if(myobj[i].questionReply!=null){
            			replay="<label style='color:green;'>(GM)"+myobj[i].replyUserName +"</label><br>&nbsp;&nbsp;&nbsp;&nbsp;"+myobj[i].questionReply+"<br>";
            		}
            		if(myobj[i].phoneType!=null){
            			phoneType="("+myobj[i].phoneType+")";
            		}
            		qhtml+=myobj[i].nickName+phoneType+"&nbsp;"+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+myobj[i].question+"<br>"+replay+"<br>";
            	}
            	document.getElementById("question"+id+serverId).innerHTML=qhtml;
            	document.getElementById("div"+id+serverId).style.display="block";
            	
            }
	   });
  	}
  	function closeQuestion(id,serverId){
  		document.getElementById("div"+id+serverId).style.display="none";
  	}
  	
  </script>
  
