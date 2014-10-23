<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/sta/select.jsp"/>
<script>
function addOssDictData(){
	var typeId=$("#dictType_main").val();
	var tabId=$("ul.navTab-tab li.selected").attr("tabid");
	$.pdialog.open("<%=basePath%>/admin/dict/dict_initAddData.action?typeId="+typeId , tabId, "<s:text name="add_dictionary_value" />", {width: 580, height: 200});
}
function updateDictData(){
	var tabId=$("ul.navTab-tab li.selected").attr("tabid");
	var select=$("input[type='checkbox'][name='dictIds']");
	var typeId=$("#dictType_main").val();
	var num=0;
	var dictId=0;
	for(var i=0;i<select.length;i++){
		if(select[i].checked){
			num++;
			dictId=select[i].value;
		}
	}
	if(num==0){
		alertMsg.warn("<s:text name="please_select_the_dictionary_data" />");
		return;
	}else if(num>1){
		alertMsg.warn("<s:text name="modify_the_data_dictionary_can_only_choose_one" />！");
		return;
	}
	$.pdialog.open("<%=basePath%>/admin/dict/dict_initAddData.action?dictId="+dictId+"&typeId="+typeId, tabId, "<s:text name="modify_the_dictionary_value" />", {width: 580, height: 200});
}
function deleteDictData(){
	var select=$("input[type='checkbox'][name='dictIds']");
	var dictIds = new Array();
	var dictStr="";
	var num=0;
	for(var i=0;i<select.length;i++){
		if(select[i].checked){
			dictIds[num++]=select[i].value;
			dictStr+=select[i].value+",";
		}
	}
	if(num==0){
		alertMsg.warn("<s:text name="please_select_the_data_to_be_deleted" />！");
		return;
	} 
	dictStr=dictStr.substring(0,dictStr.length-1);
		 $.ajax({
            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
            url: "<%=basePath%>/admin/dict/dict_deleteDictData.action",
            cache: false,
            data: {dictIdStr:dictStr},//<s:text name="to_send_data" />
            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
            	alertMsg.correct("<s:text name="delete_the_value_of_a_successful_dictionary" />！");
            	////var navTab=$("ul.navTab-tab li.selected");
            	var typeId=$("#dictType_main").val();
            	queryDictData(typeId);
            }
	   });
	}
</script>
<div class="page unitBox" style="display: block; ">
					<div class="panelBar">
						<input  type="hidden" id="dictId" name="dictId" />
					<ul class="toolBar">
								<li class=""><a class="add" href="javascript:addOssDictData()" width="510" height="261"><span><s:text name="add" /></span></a></li>
								<li class=""><a class="edit" href="javascript:updateDictData()" ><span><s:text name="modification" /></span></a></li>
								<li class=""><a class="delete" href="javascript:deleteDictData()"  ><span><s:text name="delete" /></span></a></li>
					</ul>
					</div>
				</div>
<div id="contentList">
	<table class="list" width="100%" layoutH="42" style="float:left">
		<thead>
			<tr>
				<th><input type="checkbox"  onchange="isChecked(this,'dictIds','dict_checkbox')"  ></th>
				<th><s:text name="dictionary_name" /></th>
				<th><s:text name="dictionary_value" /></th>
			</tr>
		</thead>
		<tbody id="dict_checkbox">
			 <s:iterator value="dictDatas" var="oneRow" status="offset">
				 <tr>
					<td> <input type="checkbox" name="dictIds" value="${oneRow.dictId}"
						></td>
					<td><s:property value="#oneRow.dictName"/> </td>
					<td><s:property value="#oneRow.dictValue"/> </td>
				</tr>
				</s:iterator>
						</tbody>
						</table>
	

 </div>