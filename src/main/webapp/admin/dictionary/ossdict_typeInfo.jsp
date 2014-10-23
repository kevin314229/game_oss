<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<script>
	function closeTypeDiglog(json){
		  if(json.message=='type'){
			   alertMsg.error("<s:text name="the_existence_of_the_same_type_of_dictionary" />，<s:text name="dictionary_type_must_be_unique" />！");
			  // document.getElementById("dictType").focus();
			   $("#dictType").val("");
			   $("#dictType")[0].focus();
			   return;
			 // document.getElementById("dictType").focus();
		  }else  if(json.message=='name'){
			  alertMsg.error("<s:text name="the_same_name_exists_dictionary" />，<s:text name="dictionary_name_must_be_unique" />！");
			  $("#dictName").val("");
			  document.getElementById("dictName").focus();
			  return;
		  }
		  
		  if (json.statusCode == DWZ.statusCode.ok){  
				alertMsg.correct(json.message);
				$.pdialog.closeCurrent();
			 	navTab.reload("<%=basePath %>/admin/dict/dict_index.action");  
			 	var typeId=$("#typeId").val();
			 	if(typeId!=null&&typeId!=""&&typeId!=undefined)
					queryDictData(typeId);
		  }else{
			//	alertMsg.correct(json.message);
		  }
	}
	
	function validateUnique(){
		var dictName=$("#dictName").val();
		
	}
	
	function validateDictType(){
		
 	 	var typeId=$("#dictType").val();
		var typeName=$("#dictName").val();
 		 $.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            url: "<%=basePath%>/admin/dict/dict_validateDictType.action",
	            cache: false,
	            data: {typeId:typeId,typeName:typeName},//<s:text name="to_send_data" />
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            if(msg=='success'){
	            	    alertMsg.correct("<s:text name="the_existence_of_the_same_type_of_dictionary" />！");
	            	    dictTypeForm.submit();
	            	 }else if(msg=='type'){
	            		 alertMsg.error("<s:text name="the_existence_of_the_same_type_of_dictionary" />！");
	            		 return false;
	            	 }else if(msg=='name'){
	            		 alertMsg.error("<s:text name="the_same_name_exists_dictionary" />！");
	            		 return false;
	            	 }
	            },
	            error:function(msg){
	            	return false;
	            }
		   });  
 	}
	function submitFormType(){
		var typeId=$("#dictType").val();
		var typeName=$("#dictName").val();
		alert(validateDictType());
		if(validateDictType()){
			dictTypeForm.submit();
		}
	}
	
	 
</script>
<div class="pageContent">
	<s:form action="dict_addType"   namespace="/admin/dict" method="post" theme="simple"  id="dictTypeForm" name="dictTypeForm"
	cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&validateCallback(this,closeTypeDiglog)">
		<input type="hidden" name="ossMenu.parentMenuID" value='<s:property value="parentMenuID"/>'> 
		<div class="pageFormContent nowrap" >

			<dl>
				<dt><s:text name="dictionary_name" />：</dt>
				<dd style="width: 100px">
				<input type="hidden" name="dictType.typeId" id="typeId"  value="<s:property value="dictType.typeId"/>"/>
					 <input type="text" name="dictType.dictName" id="dictName"  value="<s:property value="dictType.dictName"/>"  class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt><s:text name="dictionary_type" />：</dt>
				<dd style="width: 100px">

					<input type="text" name="dictType.dictType" id="dictType"
						<s:if test="dictType.typeId!=null"> readonly</s:if>
						value="<s:property value="dictType.dictType"/>" class="required"
						  /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><s:text name="sort_dictionary" />：</dt>
				<dd style="width: 100px">
					 <input type="text" name="dictType.orderSort" value="<s:property value="dictType.orderSort"/>"   maxlength="20"  />
				</dd>
			</dl>
			<dl>
				<dt><s:text name="dictionary_describes" /></dt>
				<dd style="width: 100px">
					<s:textarea name="dictType.dictDesc"></s:textarea>
				</dd>
			</dl>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit"  ><s:text name="submit" /></button></div></div></li>
				<!-- <li><div class="button"><div class="buttonContent"><button type="close" onclick="closeDiglog()"><s:text name="close" /></button></div></div></li> -->
			</ul>
		</div>
	</s:form>
	
</div>

