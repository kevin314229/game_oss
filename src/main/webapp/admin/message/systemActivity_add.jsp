<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script type="text/javascript" language="javascript">
   		function checkForm(o) {
			if($('#activityName').val() == ''){
				alert("<s:text name="event_name_can_not_be_empty" />"); return false;
			}
			if($("#grade").val() == ''){
				alert("<s:text name="players_now_rank_can_not_be_empty" />"); return false;
			}
			if($("#startTime").val() == ''){
				alert("<s:text name="please_enter_the_event_start_time" />"); return false;
			}
			if($("#endTime").val() == ''){
				alert("<s:text name="please_enter_the_event_end_time" />"); return false;
			}
			if($("#activityDeploy").val()==""){
				alert("<s:text name="please_select_event_details" />"); return false;
			}
			if($("#describe").val() == ''){
				alert("<s:text name="activity_description_can_not_be_empty" />"); return false;
			}
			return true;
		}
		
   		function checkBox(){
   			var activityDeploy="";
			var activityIds=document.getElementsByName("activityId");
			for(var i=0;i<activityIds.length;i++)   
			{   
				if(activityIds[i].checked){
					activityDeploy+=activityIds[i].value+"#";
				}   
			}
			if(activityDeploy==""){
				document.getElementById("activityDeploy").value="";
			}else{
				document.getElementById("activityDeploy").value=activityDeploy.substring(0,activityDeploy.length-1);
			}
   		}
   		
	</script>
	<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent">
	     <s:form action="systemActivity_doAdd" namespace="/admin/message" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)" >
	     <div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="event_name" />：</dt>
		    	    <dd >
		    	    	<s:textfield  id="activityName" name="ossSystemActivity.activityName" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	 <dt><s:text name="players_level_requirements" />：</dt>
		    	    <dd >
		    	    	<s:textfield  id="grade" name="ossSystemActivity.grade" value="0"></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	   <dt><s:text name="activity_on-line_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="startTime" name="ossSystemActivity.startTime"  style="width:160px" 
							dateFmt="yyyy-MM-dd 00:00:00" cssClass="required date" readonly="true" > 
								<s:param name="value"><s:date name="ossSystemActivity.startTime" format="yyyy-MM-dd 00:00:00" /></s:param>
						</s:textfield> 
		    	    </dd>
		    	   
		    	</dl>
		    	
		    	<dl>
		    	<dt><s:text name="activities_offline_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="endTime" name="ossSystemActivity.endTime"  style="width:160px" 
									dateFmt="yyyy-MM-dd 23:59:59" cssClass="required date" readonly="true" > 
							<s:param name="value"><s:date name="ossSystemActivity.endTime" format="yyyy-MM-dd 23:59:59" /></s:param>
						</s:textfield> 
		    	    </dd>
		    	</dl>
		    	<dl>
		    	 <dt><s:text name="sub-activity_options" />：</dt>
		    	    <dd>
		    	    	
		    	    	<s:iterator value="systemChildActivityList"  var="oneRow"  status="offset">
						<s:if test="#offset.index%5==0"><br> </s:if>
						<input name="activityId" type="checkbox" onchange="checkBox()" value="${id}"><s:property value="menuName"/>&nbsp;
						</s:iterator>
		    	    	<input type="hidden"  id="activityDeploy" name="ossSystemActivity.activityDeploy" />
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="description" />：</dt>
		    	    <dd>
		    	    	<s:textarea id="describe" cols="40" rows="4"  name="ossSystemActivity.describe" >
		    	    	</s:textarea>
		    	    </dd>
		    	   
		    	</dl>
		    </div>
		    	<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_to_add"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
					</ul>
				</div>
	    </s:form>
	    </div>
