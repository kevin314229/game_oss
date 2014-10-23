<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<script type="text/javascript" language="javascript">
		
	 	function checkHintId(typeId,dictType,type){
			$("#typeId").val(typeId);
			$("#dictType_main").val(dictType);
			queryDictData(typeId);
		}
	 	
	 	function closeDiglog(json){
			  if (json.statusCode == DWZ.statusCode.ok){  
					alertMsg.correct(json.message);
					$.pdialog.closeCurrent();
				 	navTab.reload("<%=basePath%>/admin/dict/dict_index.action");  
			  }else{
					alertMsg.correct(json.message);
			  }
		}
		
	 	
	 	
	 	function queryDictData(menuID){
	 		 $.ajax({
		            type: "post",
		            url: "<%=basePath%>/admin/base/hint_list.action",
		            cache: false,
		            data: {"hint.menuId":menuID},
		            success: function(msg){
		            	$("#data_list").html(msg);
		            }
			   });
	 	}


	 	
	 	
</script>

<s:include value="/admin/template/scriptMessage.jsp" />

<div id="leftside" style="top: 10px;">
	<div id="sidebar_s">
		<div class="collapse">
			<div class="toggleCollapse">
				<h2>
					<s:text name="main_menu" />
				</h2>
			</div>
			<input type="hidden" id="typeId" name="typeId" /> <input
				type="hidden" id="dictType_main" name="dictType" />
		</div>
	</div>
	<div id="sidebar" layoutH="22">
		<div class="toggleCollapse">
			<h2>
				页面菜单
			</h2>
		</div>
		<div class="accordion dwz-accordion" fillspace="sidebar">
			<div class="accordionContent" layoutH="52">
					<ul class="tree treeFolder" >
						 <s:iterator value="menuAllListTree" var="oneRow"  status="offset">
						    	<s:if test="#oneRow.pageUrl == null||#oneRow.pageUrl eq ''">
						    		 <s:if test="#offset.index != 0">
							    		      </ul>
						    		 </s:if>
						    		  <li><a href="javascript:checkHintId('${oneRow.ossMenuID}','<s:property value="%{getText(#oneRow.name)}" />',0)" onclick="javascript:checkHintId('${oneRow.ossMenuID}','<s:property value="%{getText(#oneRow.name)}" />',0)" ><s:property value="%{getText(#oneRow.name)}" /></a>
						    		  <ul >
						    	</s:if>
						    	<s:else><li> <a href="javascript:checkHintId('${oneRow.ossMenuID}','<s:property value="%{getText(#oneRow.name)}" />',1)"   onclick="javascript:checkHintId('${oneRow.ossMenuID}','<s:property value="%{getText(#oneRow.name)}" />',1)" ><s:property value="%{getText(#oneRow.name)}" /></a></li></s:else>
						  </s:iterator>
						  </ul>
					 </li>				
				</ul>
			</div>

		</div>
	</div>
</div>
<div id="container" style="top: 10px; width: 1465px;" layoutH="22">
	<div id="navTab" class="tabsPage">
		<div class="tabsPageHeader">
			<div class="tabsPageHeaderContent">
				<!-- <s:text name="add_about_controlling_the_display" /> class="tabsPageHeaderMargin" -->
				<ul class="navTab-tab" style="left: 0px;">
					<li tabid="main" class="main"><a href="javascript:;"><span><span
								class="home_icon">提示信息 </span></span></a></li>
				</ul>
			</div>
		</div>
		<ul class="tabsMoreList" style="display: none;">
			<li class=""><a href="javascript:;"><s:text name="data_list" /></a></li>
		</ul>
		<div class="navTab-panel tabsPageContent layoutBox" layoutH="52"
			id="data_list"></div>
	</div>
</div>