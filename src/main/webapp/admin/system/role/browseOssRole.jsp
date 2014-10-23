<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script type="text/javascript" language="javascript">
		
		function deleteOssRole() {
			if($("#ossRoleId").val()==""){
				alertMsg.warn("<s:text name="please_select_the_job_you_want_to_delete" />");
				return;
			}
			alertMsg.confirm("<s:text name="are_you_sure_you_want_to_delete_this_role" />？",{
				okCall:function(){
					ajaxTodo("<%=basePath%>/admin/base/ossRole_delOssRole.action?ossRoleID="+$("#ossRoleId").val());
				}
				
			});
		}
		function addOssRole(){
			if($("#ossRoleId").val()==""){
				alertMsg.warn("<s:text name="please_select_the_parent_node" />");
				return;
			}
			$.pdialog.open("<%=basePath%>/admin/base/ossRole_addIndex.action?ossRoleID="+$("#ossRoleId").val(), "w_<s:text name="role_management" />", "<s:text name="add_role" />", {width: 480, height: 300});
		}
		function updateOssRole(){
			if($("#ossRoleId").val()==""){
				alertMsg.warn("<s:text name="please_select_the_node" />");
				return;
			}
			$.pdialog.open("<%=basePath%>/admin/base/ossRole_editOssRole.action?ossRoleID="+$("#ossRoleId").val(), "w_<s:text name="role_management" />", "<s:text name="modify_the_role" />", {width: 480, height: 300});
		}
		
		function setOssMenu(){
			if($("#ossRoleId").val()==""){
				alertMsg.warn("<s:text name="please_select_the_node" />");
				return;
			}
			if($("#level").val()==0){
				alertMsg.warn("<s:text name="inoperable_root" />");
				return;
			}
			//var lvl = $("#ossRoleId").attr("level")?"&level="+$("#ossRoleId").attr("level"):"&level=1";
			$.pdialog.open("<%=basePath%>/admin/base/ossRole_modifyOssRoleMenu.action?ossRoleID="+$("#ossRoleId").val()+"&level="+$("#level").val(), "w_<s:text name="role_management" />", "<s:text name="set_permissions" />", {width: 950, height: 550});
		}
		
		function setOssUser(){
			if($("#ossRoleId").val()==""){
				alertMsg.warn("<s:text name="please_select_the_node" />");
				return;
			}
			$.pdialog.open("<%=basePath%>/admin/base/ossRole_addRoleUser.action?ossRoleID="+$("#ossRoleId").val(), "w_<s:text name="role_management" />", "<s:text name="set_staff_positions" />", {width: 750, height: 450});
		}
		
		function checkRoleId(id,level){
			$("#ossRoleId").val(id);
			$("#level").val(level);
			//$("#ossRoleId").attr("level",level);
		}
		
</script>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="panelBar">
<input type="hidden" id="ossRoleId">
<input type="hidden" id="level">
<ul class="toolBar">
			<li><a class="add"  href="javascript:addOssRole()" width="510" height="261" ><span><s:text name="add" /></span></a></li>
			<li><a class="edit" href="javascript:updateOssRole()"  warn="<s:text name="please_select_a_job" />"><span><s:text name="modification" /></span></a></li>
			<li><a title="<s:text name="do_you_really_want_to_delete_these_records" />?" href="javascript:deleteOssRole()" class="delete"><span><s:text name="delete" /></span></a></li>
			<li><a class="icon" href="javascript:setOssMenu()"  title="<s:text name="set_permissions" />"><span><s:text name="set_permissions" /></span></a></li>
			<li><a class="icon" href="javascript:setOssUser()"  title="<s:text name="set_permissions" />"><span><s:text name="set_staff_positions" /></span></a></li>
</ul>
</div>
<div class="pageContent">
<p> <s:text name="system_administrator" /></p>
<form method="post" action="#" class="pageForm required-validate" onsubmit="return validateCallback(this)">

<ul id="treeString" class="tree treeFolder collapse" >
	<s:property value="treeString" escape="false"/>  
</ul>


</form>
</div>
