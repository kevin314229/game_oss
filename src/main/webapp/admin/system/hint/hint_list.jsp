<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/sta/select.jsp" />
<script>

function updateDictData(){
	var tabId=$("ul.navTab-tab li.selected").attr("tabid");
	var select=$("input[type='checkbox'][name='hintIds']");
	var num=0;
	var hintStr=0;
	for(var i=0;i<select.length;i++){
		if(select[i].checked){
			num++;
			hintStr=select[i].value;
		}
	}
	if(num==0||num>1){
		alertMsg.warn("请选择一项！");
		return;
	}
	$.pdialog.open("<%=basePath%>/admin/base/hint_toUpdate.action?hint.hintId="+hintStr,tabId, "修改提示", {width: 720, height: 312});
}
function deleteDictData(){
	var select=$("input[type='checkbox'][name='hintIds']");
	var hintIds = new Array();
	var hintStr="";
	var num=0;
	for(var i=0;i<select.length;i++){
		if(select[i].checked){
			hintIds[num++]=select[i].value;
			hintStr+=select[i].value+",";
		}
	}
	if(num==0){
		alertMsg.warn("<s:text name="please_select_the_data_to_be_deleted" />！");
		return;
	} 
	hintStr=hintStr.substring(0,hintStr.length-1);
		 $.ajax({
            type: "post",
            url: "<%=basePath%>/admin/base/hint_doDel.action",
			cache : false,
			data : {"hintStr":hintStr},
			success : function(msg) {
				if(msg==1){
					alertMsg.correct("操作成功!");
				} else {
					alertMsg.error("操作失败!");
				}
				var menuId = $("#menuId").val();
				queryDictData(menuId);
			}
		});
	}
</script>
<div class="page unitBox" style="display: block;">
	<div class="panelBar">
		<input type="hidden" id="menuId" name="menuId" value="${hint.menuId}"/>
		<ul class="toolBar">
			<li class=""><a class="edit" href="javascript:updateDictData()"><span>修改</span></a></li>
			<li class=""><a class="delete"
				href="javascript:deleteDictData()"><span><s:text name="delete" /></span></a></li>
		</ul>
	</div>
</div>
<div id="contentList">
	<table class="list" width="100%" layoutH="42" style="float: left">
		<thead>
			<tr>
				<th><input type="checkbox" onchange="isChecked(this,'dictIds','dict_checkbox')"></th>
				<th>提示Key</th>
				<th>提示内容</th>
			</tr>
		</thead>
		<tbody id="dict_checkbox">
			<s:iterator value="hintList" var="oneRow" status="offset">
				<tr>
					<td><input type="checkbox" name="hintIds" value="${oneRow.hintId}"></td>
					<td><s:property value="#oneRow.hintKey" /></td>
					<td><s:property value="#oneRow.hintValue" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>


</div>