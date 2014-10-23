<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
  	<s:head/>
  <script type="text/javascript">
	  	function optionChange(obj){
	  		$("#selServer").empty();
	 		$.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            dataType: "json",//<s:text name="return" />json<s:text name="the_format_of_the_data" />
	            url: "<%=basePath%>/admin/base/ossUser_getOssServerPt.action",
	            cache: false,
	            data: {"serverCode":obj.value,"username":"<s:property value="model.username"/>"},//<s:text name="to_send_data" />
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
	  	    if(!serverInfo){
	  	    	/*delete all*/
	  	   		document.getElementById("serverInfo").value = -1;
	  	    }else{
	  	    	document.getElementById("serverInfo").value= serverInfo.substring(0,serverInfo.length-1);
	  	    }
	  		return true;
	  	}
  </script>
  <s:include value="/admin/template/scriptMessage.jsp"/>
<div class="accountInfo">
	<div >
		<h2><s:text name="system_management" />： <s:text name="operator_management_service_authorization" /></h2>
		<s:text name="the_current_user_is" />：<label style="color: red;"> <s:property value="model.username"/>
	</div>
</div>
  
  	<div class="pageContent">
	    <s:form name="myForm" action="ossUser_updateAddOssServerOperation" namespace="/admin/base" 
	    method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return checkForm()&&$(this).valid()&&dialogSearch(this)">
	    <div layoutH="100">
		 <s:text name="platform_entrance" />： 
		 <s:select  onchange="optionChange(this)" list="#session.ADMIN_SYSTEM_USER_NAME.ossOperationList" listKey="carrierOperator" listValue="operationName"/>
			  <s:optiontransferselect
			     label="Favourite Cartoons Characters"
			     id="selServer"
			     name="leftSideCartoonCharacters"
			     leftTitle="%{getText('the_server_list')}"
			     rightTitle="%{getText('allocated_server')}"
			     list="ossServerLeftList"
			     listKey="id"
			     listValue="name"
			     cssStyle="width:150px"
			     multiple="true"
			     headerKey="headerKey"
			     doubleMultiple="true"
			     doubleCssStyle="width:150px"
			     doubleHeaderKey="doubleHeaderKey"
			     doubleList="ossServerList"
			     doubleListKey="id"
			     doubleListValue="name"
			     doubleName="rightSideCartoonCharacters"
			     doubleEmptyOption="false"
			 /> 
		    <input id="serverInfo" name="serverinfo" type="hidden">
		    <input id="username" name="username" type="hidden" value="<s:property value="model.username"/>">
		    </div>
		  	<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" ><s:text name="submit" /></button></div></div></li>
		    	</ul>
		    </div>
		</s:form>
    </div>
    
