<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script type="text/javascript">

var setting = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	data: {
		simpleData: {
			enable: true,
			idKey:"serverCode"
		}
	},
	view: {
		selectedMulti: false
	},
	callback: {
		onClick: zTreeOnClick,
		onCheck: zTreeOnCheck
	}

	
	
};


var zNodes =[
<s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossServersPt" var="oneRow" status="offset">
<s:if test="#offset.index>0">,</s:if>
	{serverCode:<s:property
		value="#oneRow.Id" /><s:property
	value="#oneRow.serverId" />,pId:0,name:"<s:property
	value="#oneRow.serverProvider" escape="false" />",nocheck:true}
	
	<s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossServers"
		var="oneRows" status="offset">
	
		<s:if test="#oneRow.serverCode == #oneRows.serverCode">
			,{serverCode:<s:property value="#oneRows.id" />,pId:<s:property
				value="#oneRow.Id" /><s:property
					value="#oneRow.serverId" />,name:"<s:property
					value="#oneRows.name" escape="false"/>"}
		</s:if>
	</s:iterator>
</s:iterator>];

var code;		
function setCheck() {
	var type = $("#level").attr("checked")? "level":"all";
	setting.check.radioType = type;
	showCode('setting.check.radioType = "' + type + '";');
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
}

function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}

$(document).ready(function(){
	var autoClose = '${autoClose}';
	if(autoClose){
		window.location.reload();
	}
	setCheck();			
	$("#level").bind("change", setCheck);
	$("#all").bind("change", setCheck);
	defaultSelectedNode();
});
function defaultSelectedNode(){
	var serverCode = '<s:property value="#session.ADMIN_SYSTEM_USER_NAME.currentOssServer.id"/>';
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var selectNodeObj = treeObj.getNodeByParam("serverCode", serverCode, null);
	treeObj.selectNode(selectNodeObj);
	treeObj.checkNode(selectNodeObj, true, null, $("#callbackTrigger").attr("checked"));
}

function zTreeOnClick(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    var callbackFlag = $("#callbackTrigger").attr("checked");
			zTree.checkNode(treeNode, true, null, callbackFlag);
			
};

function zTreeOnCheck(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			
};

function submitForm(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
	$("input[name='id']").val(nodes[0].serverCode);
	return true;
}
</script>
	<div class="pageContent">
		<s:form action="/admin/selectOssServer.action" onsubmit="return submitForm()&&dialogSearch(this)" method="post" theme="simple">
		<div class="pageFormContent nowrap" layoutH="60">
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
				<s:hidden name="id"/>
		</div>
		<div class="formBar">
			    <ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div></div></li>
			    </ul>
		 </div>
		 </s:form>
	</div>
