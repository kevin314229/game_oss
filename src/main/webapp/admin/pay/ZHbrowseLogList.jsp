<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
response.setHeader("Pragma","No-cache"); 

response.setHeader("Cache-Control","no-cache"); 

response.setDateHeader("Expires", 0);  
%>
<link href="<%=basePath%>/media/default/css/jquery.achieve.css"
	rel="stylesheet" type="text/css" media="all" />
	<style>
	
	</style>
<script  type="text/javascript">
var itemIDs = '<s:property value="resultJson" escape="false" />';
var itemIDArray = (new Function("", "return " + itemIDs))();
$("#summary_itemname").achieve(itemIDArray, {
	left : -1,
	top : 5,
	width : 180,
	formatItem : function(row) {
		return row.name;
	},
	formatMatch2 : function(row) {

		return row.code + row.name + row.help;
	},
	formatResult : function(itemData, input, i) {
		$('#summary_itemname' ).val(itemData[i].name);
		$('#ossOperationLog_itemId' ).val(itemData[i].code);
	}
});


var dictDataIDs = '<s:property value="resultJson2" escape="false" />';
var dictDataIDArray = (new Function("", "return " + dictDataIDs))();
$("#summary_itemname1").achieve(dictDataIDArray, {
	left : -1,
	top : 5,
	width : 150,
	formatItem : function(row) {
		return row.name;
	},
	formatMatch2 : function(row) {

		return row.code + row.name + row.help;
	},
	formatResult : function(itemData, input, i) {
		$('#summary_itemname1' ).val(itemData[i].name);
		$('#OperationId' ).val(itemData[i].code);
	}
});
 function checkZHBrowLogList(){
	var summary_itemname1_SelectedRow = $('#summary_itemname1' )[0].selectedRow;
	var operationName= $('#summary_itemname1' )[0].value;
	var OperationId= $('#OperationId' )[0].value;
	
	//alert(summary_itemname1_SelectedRow==undefined);
	if(summary_itemname1_SelectedRow==undefined||summary_itemname1_SelectedRow.name!=$('#summary_itemname1' ).val()){
		if(operationName==null||operationName==""){
			$('#OperationId' )[0].value="";
		}else if(operationName!=""&&OperationId==""){
			alertMsg.error("<s:text name="select_the_type_of_operation_errors" />");
			return false;
		}
	}
	var summary_itemname_SelectedRow = $('#summary_itemname' )[0].selectedRow;
	var itemName= $('#summary_itemname' )[0].value;
	var itemId= $('#ossOperationLog_itemId' )[0].value;
//	alert(summary_itemname_SelectedRow +itemName+(summary_itemname_SelectedRow==undefined));
	if(summary_itemname_SelectedRow==undefined||summary_itemname_SelectedRow.name!=$('#summary_itemname' ).val()){
		if(itemName==null||itemName==""){
			$('#ossOperationLog_itemId' )[0].value="";
		}else if(itemId!=""&&itemId==""){
			alertMsg.error("<s:text name="select_the_item_name_wrong" />");
			return false;
		}
	}
	return true;
}
 
 function browseLogExcel(){
	 
	 //alert($("#QueryPlayerLog").serializeArray())
    $.ajax({
		    type: "post",//使用post方法访问后台
		    url: "${pageContext.request.contextPath}/zhxy/pay/ZHQueryPlayerLog_vilidateNum.action",
		    cache: false,
		    global:false,
		    dataType:"json",
			data:$("#QueryPlayerLog").serializeArray(),//要发送的数据
		    success: function(json){//msg为返回的数据，在这里做数据绑定
		    	if(json.statusCode==100){
		    		alertMsg.correct(json.message);
		    	}else{
		    		submitForm();
		    	}
		    		
		    
		    },
		    error:function(msg1,text){
		    	alert(msg1+text);
		    }
		    
		});   
	// var url="<%=basePath%>/zhxy/pay/ZHQueryPlayerLog_data.action";
	// QueryPlayerLog.action=url;
	 
	/*   $('#QueryPlayerLog')[0].onsubmit=function(event) {
		  return $(this).valid()&&checkZHBrowLogList()&&validateCallback(this,doMessage);
	  } */
	  
	// document.getElementById("QueryPlayerLog").submit();
	  
	<%--  
	 $('#QueryPlayerLog')[0].onsubmit=function(event) { 
		  return $(this).valid()&&checkZHBrowLogList()&&navTabSearch(this);
	  }  --%>
	  
	 // QueryPlayerLog.action="<%=basePath%>/zhxy/pay/ZHQueryPlayerLog_index.action";
 }
	 
 
 function doMessage(json){
	 alert(json)
	 if (json.statusCode != DWZ.statusCode.ok){  
		 alertMsg.correct(json.message);
	 }
 }
 
 function submitForm(){
	 var url="<%=basePath%>/zhxy/pay/ZHQueryPlayerLog_data.action";
	 QueryPlayerLog.action=url;
	 QueryPlayerLog.submit();
	 QueryPlayerLog.action="<%=basePath%>/zhxy/pay/ZHQueryPlayerLog_index.action";
 }
 
/*  validateCallback(this, doMessage) */
</script>
  <s:include value="/admin/template/scriptMessage.jsp"/>
  <form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>
<div class="pageHeader">
	<s:form action="ZHQueryPlayerLog_index" rel="pagerForm" id="QueryPlayerLog" name="QueryPlayerLog" namespace="/zhxy/pay" method="post" theme="simple"  
	cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		       <div  class="searchDiv_left">
					<s:text name="account_number" />：
					<s:textfield name="operationLog.loginName" size="12"/> 
					<s:text name="role" />：
					<s:textfield name="operationLog.nickName" size="12"/>
					<s:text name="type_of_operation" />：
					<input type="text" class="summary" id="summary_itemname1" name="operationName" value="${operationName}"
						index="1" maxlength="100" />
						<input type="hidden"   id="OperationId" name="operationLog.operation" value="${operationLog.operation}"
						index="1" maxlength="100" />
					<s:text name="product_name" />：                  
						<input type="text" class="summary" id="summary_itemname" name="operationLog.itemName" value="${operationLog.itemName}"
						index="1" maxlength="100" />
						<input type="hidden"   id="ossOperationLog_itemId" name="operationLog.itemId" value="${operationLog.itemId}"
						index="1" maxlength="100" />
					<s:text name="change_type" />：
					<select name="operationLog.flowType">
						<s:if test="operationLog==null">
						<option value="-1" selected>--<s:text name="whole" />--</option>
						<option value="1"><s:text name="addition" /></option>
						<option value="2"><s:text name="reduction" /></option>
						</s:if>
						<s:elseif test="operationLog.flowType==null">
						<option value="-1" selected>--<s:text name="whole" />--</option>
						<option value="1"><s:text name="addition" /></option>
						<option value="2"><s:text name="reduction" /></option>
						</s:elseif>
						<s:elseif test="operationLog.flowType==1">
						<option value="-1">--<s:text name="whole" />--</option>
						<option value="1" selected><s:text name="addition" /></option>
						
						<option value="2"><s:text name="reduction" /></option>
						</s:elseif>
						<s:elseif test="operationLog.flowType==2">
						<option value="-1">--<s:text name="whole" />--</option>
						<option value="1" ><s:text name="addition" /></option>
						<option value="2" selected><s:text name="reduction" /></option>
						</s:elseif>
					</select>
					
					<s:text name="statistical_time" />：
					<s:textfield id="beginTime" name="beginDate" datefmt="yyyy-MM-dd HH:mm:ss" maxlength="20" size="20" cssClass="required date">
					</s:textfield>
				 
					<s:text name="to" /> 
					<s:textfield id="endTime" name="endDate" datefmt="yyyy-MM-dd HH:mm:ss" maxlength="20" size="20" cssClass="required date">
					</s:textfield>
				 </div>
	    	<div class="searchButton2">
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
				</ul>
				<ul>
					<li> <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="browseLogExcel()"><s:text name="export" />Excel</button></div></div></li>
				</ul>
			</div>
			</div>
	 </s:form>
</div>
	
	<div class="pageContent">
	    <table class="list" width="100%" layoutH="64" style="table-layout: fixed;">
	    <thead>
	    	<tr>
	    	    <th><s:text name="platform_logo" /></th>
	    	    <th><s:text name="players" />ID</th>
	    	    <th><s:text name="account" /></th>
	    	    <th><s:text name="role" />ID</th>
	    	    <th><s:text name="role_nickname" /></th>
	    	    <th width="80"><s:text name="the_current_character_level" /></th>
	    	    <th width="55"><s:text name="goods" />ID</th>
	    	    <th><s:text name="product_name" /></th>
	    	    <th><s:text name="change_type" /> </th>
	    	    <th><s:text name="quantity" /> </th>
	    	    <th><s:text name="type_of_operation" /></th>
	    	    <th width="20%"><s:text name="operating_instructions" /></th>
	    	    <th width="10%"><s:text name="created" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    		<s:iterator value="ossOperationLogList" var="oneRow">
	    		<tr>
	    			<td><s:property value="#oneRow.ptId"/></td>
	    			<td><s:property value="#oneRow.playerId"/></td>
		    		<td><s:property value="#oneRow.loginName"/></td>
		    		<td><s:property value="#oneRow.playerBaseId"/></td>
		    		<td><s:property value="#oneRow.nickName"/></td>
		    		<td><s:property value="#oneRow.level"/></td>
		    		<td><s:property value="#oneRow.itemId"/></td>
		    		<td><s:property value="#oneRow.itemName"/></td>
		    		<td>
		    			<s:if test="#oneRow.flowType==1">
		    				<s:text name="addition" />
		    			</s:if>
		    			<s:if test="#oneRow.flowType==2">
		    				<s:text name="reduction" />
		    			</s:if>
		    		
		    		</td>
		    		<td><s:property value="#oneRow.number"/></td>
		    		<td><s:property value="#oneRow.operation"/></td>
		    		<td><s:property value="#oneRow.operationDetail"/></td>
		    		<td><s:property value="#oneRow.createTime"/></td>
	    		</tr>
	    		</s:iterator>
	    	</tbody>
	    </table>
	    <s:include value="/admin/template/paging.jsp"/>
	 </div>
