<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:text name="operators_administrative_authorization" /></title>
   <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
   
  	
  <script type="text/javascript">
	  	function optionChange(obj){
	  		$("#selServer").empty();
	 		$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            dataType: "json",//<s:text name="return" />json<s:text name="the_format_of_the_data" />
	            url: "<%=basePath%>/admin/base/ossOperation_getOssServerListByOperationId.action",
	            cache: false,
	            data: "id="+obj.value,//<s:text name="to_send_data" />
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            	var myobj=eval(msg);
	            	for(var i=0;i<myobj.length;i++){
	            		$("#selServer").append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
	            	}
	            }
		   });
		}
	  	function checkForm(){
	  		var server=document.myForm.rightSideCartoonCharacters;
	  		var serverInfo="";
	  		for(var i=0;i<server.length;i++)  {
	  	        if(server[i].value!="") {
	  	        	serverInfo+=server[i].value+",";     
	  	        }
	  	    }
	  		document.getElementById("serverInfo").value= serverInfo.substring(0,serverInfo.length-1);
	  		document.forms[0].submit();
	  	}
  </script>
  	
  </head>
  
  <body>
  	<div id="container">
  		<div id="divLeft">
			<b><s:text name="system_management" />： <s:text name="operator_management_service_authorization" /></b><br/>
			<span class="color-red"><s:property value="errorInfo"/></span>
			<span class="color-gr"><s:text name="the_current_operator_is" />：<label style="color: red;"> <s:property value="ossOperation.operationName"/>  </label></span>
		</div>
		<br>
		<div id="divLeft">
	    <s:form name="myForm" action="ossOperation_updateAddOssServerOperation" namespace="/admin/base" method="post" theme="simple" >
		 <s:text name="platform_entrance" />： <s:select  onchange="optionChange(this)" list="ossOperationList" listKey="id" listValue="operationName"/>
		  <br> 
		   <table>
		   <tr>
		   <td width="150px"></td><td>
		   <s:optiontransferselect
			     label="Favourite Cartoons Characters"
			     id="selServer"
			     name="leftSideCartoonCharacters"
			     leftTitle="<s:text name="the_server_list" />"
			     rightTitle="<s:text name="allocated_server" />"
			     list="ossServerList"
			     listKey="serverId"
			     listValue="serverName"
			     cssStyle="width:150px"
			     multiple="true"
			     headerKey="headerKey"
			     headerValue="--- <s:text name="please_select_a" /> ---"
			     emptyOption="true"
			     doubleMultiple="true"
			     doubleCssStyle="width:150px"
			     doubleHeaderKey="doubleHeaderKey"
			     doubleList="ossOperationServerList"
			     doubleListKey="serverId"
			     doubleListValue="serverName"
			     doubleName="rightSideCartoonCharacters"
			     doubleEmptyOption="false"
			 /> 
		    <input id="serverInfo" name="serverInfo" type="hidden">
		     </td>
		   </tr>
		   <tr>
		   <td width="150px"></td><td>
			<s:hidden name="id"/>
			<input type="button" onclick="checkForm()" value="<s:text name="submit" />" />
			<input type="button" value="<s:text name="return" />" onclick="self.location='<%=basePath%>/admin/base/ossOperation_operationList.action'"/>
		   </td>
		   </tr>
		   </table>
		</s:form>
	    </div>
    </div>
    
	<s:if test='#request.actionMsg !=null && #request.actionMsg !="" '>
		<script language="javascript">
			alert("<s:property value="actionMsg" escape="false"/>");
		</script>
	</s:if>	
  </body>
</html>
