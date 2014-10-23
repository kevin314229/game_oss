<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<script>
	function closeDataDiglog(json){
		 if(json.message=='name'){
			   alertMsg.error("<s:text name="the_same_name_exists_dictionary" />，<s:text name="dictionary_name_must_be_unique" />！");
			  // document.getElementById("dictType").focus();
			   $("#dictName").val("");
			   $("#dictName")[0].focus();
			   return;
			 // document.getElementById("dictType").focus();
		  }
		  var typeId=$("#dictType").val();
		  if (json.statusCode == DWZ.statusCode.ok){  
				alertMsg.correct(json.message);
				$.pdialog.closeCurrent();
			 	//navTab.reload("<%=basePath %>/admin/dict/dict_index.action");  
			 	queryDictData(typeId);
		  }else{
				alertMsg.correct(json.message);
		  }
	}
	
	function validateUnique(){
		var dictName=$("#dictName").val();
		
	}
	
function validateDictData(){
		
 	 	var typeId=$("#dictType").val();
		//var typeName=$("#dictName").val();
		var dataName=$("#dictName").val();
 		 $.ajax({
	            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
	            url: "<%=basePath%>/admin/dict/dict_validateDictData.action",
	            cache: false,
	            data: {typeId:typeId,dataName:dataName},//<s:text name="to_send_data" />
	            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
	            if(msg=='success'){
	            		 return true;
	            	 }else if(msg=='type'){
	            		 alertMsg.error("<s:text name="the_existence_of_the_same_type_of_dictionary" />！");
	            		 return false;
	            	 }else if(msg=='name'){
	            		 alertMsg.error("<s:text name="the_same_value_exists_dictionary" />！");
	            		 return false;
	            	 }
	            },
	            error:function(msg){
	            	return false;
	            }
		   });  
		   
		   return true;
 	}
</script>
<div class="pageContent">
	<s:form action="dict_addData"   namespace="/admin/dict" method="post" theme="simple" 
	cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&validateCallback(this,closeDataDiglog)">
		<div class="pageFormContent nowrap" >

			<dl>
				<dt><s:text name="dictionary_name" />：</dt>
				<dd style="width: 100px">
					<input type="hidden" name="dictData.dictId" id="dictId"  value="<s:property value="dictData.dictId"/>"/>
					<input type="hidden" name="dictData.dictType" id="dictType"  value="<s:property value="typeId"/>"/>
					 <input type="text" name="dictData.dictName" id="dictName"  value="<s:property value="dictData.dictName"/>"  class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt><s:text name="dictionary_value" />：</dt>
				<dd style="width: 100px">
					<input type="text" name="dictData.dictValue" id="dictValue"  value="<s:property value="dictData.dictValue"/>"   class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><s:text name="sort_dictionary" />：</dt>
				<dd style="width: 100px">
					 <input type="text" name="dictData.orderSort" value="<s:property value="dictData.orderSort"/>"   maxlength="20"  />
				</dd>
			</dl>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" ><s:text name="submit" /></button></div></div></li>
				<!-- <li><div class="button"><div class="buttonContent"><button type="close" onclick="closeDiglog()"><s:text name="close" /></button></div></div></li> -->
			</ul>
		</div>
	</s:form>
	
</div>

