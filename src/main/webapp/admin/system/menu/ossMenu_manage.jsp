<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script type="text/javascript" language="javascript">
		
		function addOssMenu(){
			if($("#oss_menu_id").val()==""){
				alertMsg.warn("<s:text name="please_select_the_parent_node" />");
				return;
			}
			if($("#level").val()!=0){
				alertMsg.warn("<s:text name="can_not_manipulate_child_nodes" />");
				return;
			}
			$.pdialog.open("<%=basePath%>/admin/base/ossMenu_addIndex.action?parentMenuID="+$("#oss_menu_id").val()+"&name="+$("#menu_name").val() , "w_<s:text name="function_management" />", "<s:text name="add_functionality" />", {width: 480, height: 300});
		}
		function addOssMainMenu(){
			$.pdialog.open("<%=basePath%>/admin/base/ossMenu_addIndex.action?parentMenuID=0&name="+$("#menu_name").val() , "w_<s:text name="function_management" />", "<s:text name="add_functionality" />", {width: 480, height: 150});
		}
		function updateOssMenu(){
			if($("#oss_menu_id").val()==""){
				alertMsg.warn("<s:text name="please_select_the_node" />");
				return;
			}
			if($("#level").val()==0){
				alertMsg.warn("<s:text name="inoperable_root" />");
				return;
			}
			$.pdialog.open("<%=basePath%>/admin/base/ossMenu_editOssMenu.action?ossMenu.ossMenuID="+$("#oss_menu_id").val(), "w_<s:text name="function_management" />", "<s:text name="modify_the_function" />", {width: 480, height: 300});
		}
		
		function deleteOssMenu(){
			if($("#oss_menu_id").val()==""){
				alertMsg.warn("<s:text name="please_select_the_node" />");
				return;
			}
			if($("#level").val()==0){
				alertMsg.warn("<s:text name="inoperable_root" />");
				return;
			}
			  alertMsg.confirm(" <s:text name="are_you_sure_to_delete_the_selected_menu" />？", {
					okCall: function(){
						//<s:text name="single_synchronization_server_sends_a_request_for_each" />，<s:text name="real-time_feedback_and_information" />
						$.ajax({
				            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
				            url: "<%=basePath%>/admin/base/ossMenu_deleteOssMenu.action?ossMenu.ossMenuID="+$("#oss_menu_id").val(),
				            cache: false,
				            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
				            	alertMsg.correct("<s:text name="delete_menu_success" />！");
				            	////var navTab=$("ul.navTab-tab li.selected");
				            	navTab.reload("<%=basePath %>/admin/base/ossMenu_manage.action");  
				            }
					   });
					}
				});
			
		}
		
		
	 	function checkMenuId(id,name,level){
			$("#oss_menu_id").val(id);
			$("#menu_name").val(name);
			$("#level").val(level);
			/* $("#ossRoleId").attr("level",level); */
		}
	 	
	 	
		
</script>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="panelBar">
<input type="hidden" id="oss_menu_id"/>
<input type="hidden" id="menu_name">
<input type="hidden" id="level">
<ul class="toolBar">
			<li><a class="add"  href="javascript:addOssMainMenu()" width="510" height="261" ><span><s:text name="adding_a_menu" /></span></a></li>
			<li><a class="add"  href="javascript:addOssMenu()" width="510" height="261" ><span><s:text name="add_a_submenu" /></span></a></li>
			<li><a class="edit" href="javascript:updateOssMenu()"  warn="<s:text name="please_select_a_menu" />"><span><s:text name="modification" /></span></a></li>
			<li><a class="delete" href="javascript:deleteOssMenu()"  warn="<s:text name="please_select_a_menu" />"><span><s:text name="delete" /></span></a></li>
</ul>
</div>
<div class="pageContent">

<form method="post" action="#" class="pageForm required-validate" onsubmit="return validateCallback(this)" >
	
	<ul class="tree treeFolder" layoutH="42">
						 <s:iterator value="menuAllListTree" var="oneRow"  status="offset">
						    	<s:if test="#oneRow.pageUrl == null||#oneRow.pageUrl eq ''">
						    		 <s:if test="#offset.index != 0">
							    		      </ul>
						    		 </s:if>
						    		  <li><a href="javascript:checkMenuId('${oneRow.ossMenuID}','<s:property value="%{getText(#oneRow.name)}" />',0)" onclick="javascript:checkMenuId('${oneRow.ossMenuID}','<s:property value="%{getText(#oneRow.name)}" />',0)" ><s:property value="%{getText(#oneRow.name)}" /></a>
						    		  <ul >
						    	</s:if>
						    	<s:else><li> <a href="javascript:checkMenuId('${oneRow.ossMenuID}','<s:property value="%{getText(#oneRow.name)}" />',1)"   onclick="javascript:checkMenuId('${oneRow.ossMenuID}','<s:property value="%{getText(#oneRow.name)}" />',1)" ><s:property value="%{getText(#oneRow.name)}" /></a></li></s:else>
						  </s:iterator>
						  </ul>
					 </li>				
	</ul>


</form>
</div>
