<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script type="text/javascript" language="javascript">
   		 
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
   		$(function(){
   			var activityDeploy=$("#activityDeploy").val();
   			var items=activityDeploy.split("#");
   			var activityIds=document.getElementsByName("activityId");
			for(var i=0;i<items.length;i++)   
			{   
				for(var j=0;j<activityIds.length;j++){
					if(items[i]==activityIds[j].value){
						activityIds[j].checked=true;
					} 
					continue;
				}
			}
   	   	});
	</script>
  <div class="pageContent" >
	     <s:form action="systemActivity_update" namespace="/admin/message" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)" >
		  	 <s:textarea type="text" cssStyle="display:none" id="systemActivityId" name="ossSystemActivity.systemActivityId" />
		  	 <div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="event_name" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="activityName" name="ossSystemActivity.activityName" cssClass="required" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    		<dt><s:text name="players_level_requirements" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="grade" name="ossSystemActivity.grade" value="0" cssClass="required"></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>  
		    	   <dt><s:text name="activity_on-line_time" />：</dt>
		    	    <dd>
		    	   		<s:textfield id="startTime" name="ossSystemActivity.startTime"  style="width:160px" 
							dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" > 
								<s:param name="value"><s:date name="ossSystemActivity.startTime" format="yyyy-MM-dd HH:mm:ss" /></s:param>
						</s:textfield> 
		    	    </dd>
		    	   
		    	</dl>
		    	
		    	<dl>
		    		<dt><s:text name="activities_offline_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="endTime" name="ossSystemActivity.endTime"  style="width:160px" 
									dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" > 
							<s:param name="value"><s:date name="ossSystemActivity.endTime" format="yyyy-MM-dd HH:mm:ss" /></s:param>
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
		    	    	<s:textarea type="text" cssStyle="display:none" id="activityDeploy" 
		    	    	cssClass="required" name="ossSystemActivity.activityDeploy" />
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="description" />：</dt>
		    	    <dd>
		    	    	<s:textarea id="describe" cols="40" rows="4"  name="ossSystemActivity.describe" cssClass="required">
		    	    	</s:textarea>
		    	    </dd>
		    	   
		    	</dl>
		    </div>
		    <div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_the_change"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
					</ul>
			</div>
	    </s:form>
    </div>
