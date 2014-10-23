<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script type="text/javascript" language="javascript">
		
		function addOssDictType(){
			var tabId=$("ul.navTab-tab li.selected").attr("tabid");
			$.pdialog.open("<%=basePath%>/admin/dict/dict_initAddType.action" , tabId, "<s:text name="adding_a_dictionary" />", {width: 580, height: 250});
		}
		function updateDictType(){
			var typeId=$("#typeId").val();
			if(typeId==""){
				alertMsg.warn("<s:text name="please_choose_a_dictionary" />");
				return;
			}
			$.pdialog.open("<%=basePath%>/admin/dict/dict_initAddType.action?typeId="+typeId, "w_<s:text name="function_management" />", "<s:text name="modify_dictionary_type" />", {width: 580, height: 250});
		}
		
		
	 	function checkDictId(typeId,dictType){
			$("#typeId").val(typeId);
			$("#dictType_main").val(dictType);
			queryDictData(dictType);
		}
	 	
	 	function closeDiglog(json){
			  if (json.statusCode == DWZ.statusCode.ok){  
					alertMsg.correct(json.message);
					$.pdialog.closeCurrent();
				 	navTab.reload("<%=basePath %>/admin/dict/dict_index.action");  
			  }else{
					alertMsg.correct(json.message);
			  }
		}
		
	 	function deleteDictType(){
	 		var typeId=$("#typeId").val();
			if(typeId==""){
				alertMsg.warn("<s:text name="please_choose_a_dictionary" />");
				return;
			}
	 		 $.ajax({
		            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
		            url: "<%=basePath%>/admin/dict/dict_deleteType.action",
		            cache: false,
		            data: {typeId:typeId},//<s:text name="to_send_data" />
		            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
		            	alertMsg.correct("<s:text name="delete_data_dictionary_type_success" />！");
		            	////var navTab=$("ul.navTab-tab li.selected");
		            	navTab.reload("<%=basePath %>/admin/dict/dict_index.action");  
		            }
			   });
	 	}
	 	
	 	function queryDictData(typeId){
	 		/* var typeId=$("#typeId").val();
			if(typeId==""){
				alertMsg.warn("<s:text name="please_choose_a_dictionary" />");
				return;
			} */
	 		 $.ajax({
		            type: "post",//<s:text name="use" />post<s:text name="methods_to_access_the_background" />
		            url: "<%=basePath%>/admin/dict/dict_queryDictData.action",
		            cache: false,
		            data: {typeId:typeId},//<s:text name="to_send_data" />
		            success: function(msg){//msg<s:text name="for_the_data_returned" />，<s:text name="here_to_do_the_data_binding" />
		            	////var navTab=$("ul.navTab-tab li.selected");
		            	$("#data_list").html(msg);
		            }
			   });
	 	}
</script>

<s:include value="/admin/template/scriptMessage.jsp"/>
 
 
<div id="leftside" style="top:10px;">
			<div id="sidebar_s">
				<div class="collapse"  >
					<div class="toggleCollapse"><h2><s:text name="main_menu" /></h2> </div>
					<input type="hidden" id="typeId" name="typeId" />
					<input type="hidden" id="dictType_main" name="dictType" />
				</div>
			</div>
			<div id="sidebar"  layoutH="22" >
	<div class="toggleCollapse"><h2><s:text name="data_dictionary_type" /></h2> </div>
				<div class="accordion dwz-accordion" fillspace="sidebar">
					<div class="accordionContent" layoutH="52" >
					<div class="page unitBox" style="display: block; ">
						<div class="panelBar">
					<ul class="toolBar">
								<li class=""><a class="add" href="javascript:addOssDictType()"   height="261"><span><s:text name="add" /></span></a></li>
								<li class=""><a class="edit" href="javascript:updateDictType()" warn="<s:text name="please_select_a_job" />"><span><s:text name="modification" /></span></a></li>
								<li class=""><a class="delete" href="javascript:deleteDictType()" warn="<s:text name="please_select_a_job" />"><span><s:text name="delete" /></span></a></li>
					
					</ul>
					</div>	
					</div>
					<div style="float:left; display:block; margin:10px; overflow:auto;width:180px; border:solid 1px #CCC; line-height:21px; background:#FFF;"  layoutH="112">
						<ul class="tree treeFolder collapse">
									<s:iterator value="dictTypes" var="oneRow"  status="offset">
											<li><a    onclick="checkDictId(${oneRow.typeId},${oneRow.dictType})">${oneRow.dictName}</a></li>
									 </s:iterator>
							 
						</ul>
				</div>
					</div>
					
				</div>
			</div>
		</div>
	<div id="container" style="top:10px;width: 1465px; " layoutH="22">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- <s:text name="add_about_controlling_the_display" /> class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab" style="left: 0px; ">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon"><s:text name="data_list" /></span></span></a></li>
						</ul>
					</div>
				</div>
				<ul class="tabsMoreList" style="display: none; ">
					<li class=""><a href="javascript:;"><s:text name="data_list" /></a></li>
			 </ul>
				<div class="navTab-panel tabsPageContent layoutBox" layoutH="52" id="data_list">
			 		
				</div>
			</div>
		</div>