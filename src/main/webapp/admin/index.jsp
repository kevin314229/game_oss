<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import=" com.jcwx.game.common.BaseAdminContext,com.jcwx.game.admin.constant.AdminSystemConstant,org.apache.struts2.ServletActionContext" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
BaseAdminContext baseAdminContext = (BaseAdminContext) ServletActionContext.getRequest().getSession().getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY);
if (baseAdminContext == null) {
	response.sendRedirect("admin/login_toLogin.action");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="wonderful_wireless_carrier_management_system" /></title>

<link href="<%=basePath%>/media/default/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>/media/default/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>/media/default/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=basePath%>/media/default/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>/media/default/css/zTreeStyle.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>/media/default/css/jquery.achieve.css" rel="stylesheet" type="text/css" media="all" />
<link href="<%=basePath%>/media/default/css/ContextMenu.css" rel="stylesheet" type="text/css" />

<!--[if IE]>
<link href="<%=basePath%>/media/default/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="<%=basePath%>/media/default/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="<%=basePath%>/media/default/js/json2.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/jquery-1.8.3.js"></script>
<script src="<%=basePath%>/media/default/js/JSONeditor.js" type="text/javascript"></script>

<script src="<%=basePath%>/media/default/js/highcharts/highcharts.src.js"  type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/highcharts/modules/exporting.js"  type="text/javascript"></script>

<script src="<%=basePath%>/media/default/js/aop.min.js" type="text/javascript"></script>
	
<script src="<%=basePath%>/media/default/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/jquery.bgiframe.js" type="text/javascript"></script>


<script src="<%=basePath%>/media/default/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.util.date.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.drag.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.tree.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.accordion.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.tab.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.resize.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.dialog.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.pagination.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.database.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.effects.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.history.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.combox.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="<%=basePath%>/media/default/bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="<%=basePath%>/media/default/js/jquery.qtip-1.0.0-rc3.min.js" type="text/javascript"></script>

<script src="<%=basePath%>/media/default/js/dwz.regional.zh.js" type="text/javascript"></script>

<script src="<%=basePath%>/media/default/js/oss.menu.js" type="text/javascript"></script>

<script src="<%=basePath%>/media/default/js/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>


<script src="<%=basePath%>/media/default/js/jquery.contextMenu.js" type="text/javascript"></script>

<script src="<%=basePath%>/media/default/js/jquery.achieve.js" type="text/javascript"></script>
<script  type="text/javascript">
var tipMode = <s:property value="@com.jcwx.game.common.CacheManager@SYSTEM_SET.tip"/>;
 var basePath = "<%=basePath %>";
	function selectServer(id) {
		window.parent.location.href = "<%=basePath %>/admin/selectOssServer.action?id="+id; 
	}
	
	function selectServerPt(id) {
		window.parent.location.href = "<%=basePath %>/admin/selectOssServerPt.action?serverCode="+id; 
	}
	
	function confirmRefresh(op){
		if(!op.confirmMsg){
			navTab.reload(op.url,op);
		}else{
			alertMsg.confirm(op.confirmMsg,{okCall:function(){  
				navTab.reload(op.url,op);
			}});
		}
	}
	var initialIndex = true;
	var currentUsername = "<s:property value="#session.ADMIN_SYSTEM_USER_NAME.ossUser.username"/>";
	
$(function(){
	var menuTypes = '<s:property value="#session.ADMIN_SYSTEM_USER_NAME.menuAutoComplite" escape="false"/>';
	var menuArray = (new Function("", "return " + menuTypes))();
	$("#menuDir").achieve(menuArray, {
		left : -1,
		top : 5,
		width : 200,
		formatItem : function(row) {
			return row.name;
		},
		formatMatch2 : function(row) {
			return row.code + row.name + row.help;
		},
		formatResult : function(itemData, input, i) {
			navTab.openTab("w_"+itemData[i].name,"<%=basePath%>"+itemData[i].help,{title:itemData[i].name,});
		}
	});
})

$(function(){
	DWZ.init("<%=basePath%>/admin/dwz.frag_<s:property value="locale"/>.xml", {
		loginUrl:"<%=basePath%>/admin/login.jsp", loginTitle:"<s:text name="login" />",	
		statusCode:{ok:200, error:300, timeout:301}, 
		pageInfo:{pageNum:"currPageNO", numPerPage:"onePageNum",
			orderField:"orderField", orderDirection:"orderDirection",
			pageNumNew:"page.currPageNO",numPerPageNew:"page.onePageNum"
			,page:{orderFlag:"page.orderFlag"}
			}, 
		debug:false,	
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"<%=basePath%>/media/default/themes"}); // themeBase <s:text name="relative" />index<s:text name="theme_of_the_page" />base<s:text name="path" />
		}
	});
	
});

$.validator.addMethod("jsonValid", function(value, element) {
			try{
				JSON.parse(value);
				return this.optional(element)||true;
			}catch(e){
				return false;
			}
}, "JSON<s:text name="failed_to_parse_data" />");




</script>


<style>
.tdwarp{
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
}
</style>
</head>

<body scroll="no">
<ul id="contextMenu" class="contextMenu" >
	<li id="edit" class="edit"><a>添加提示</a></li>
	<li id="delete" class="delete"><a>取消</a></li>
</ul>

	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a href="javascript:void(0)" style="font-size: 30px;color: white;margin: 5px;padding: 5px 5px 5px 5px;display: block;width: 800px;text-decoration: none;"><s:text name="brilliant_and_operations_management_system" /></a>
				<ul class="nav">
				
					<li><a width="740" height="460" target="dialog" href="<%=basePath%>/admin/selectServer.jsp">
					<s:property value="#session.ADMIN_SYSTEM_USER_NAME.operationName"/>->
					<s:property value="#session.ADMIN_SYSTEM_USER_NAME.name"/></a>
					</li>
					<li><a href="<%=basePath%>/admin/logout.action"><s:text name="quit" /></a></li>
				</ul>
				<!--
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected"><s:text name="blue" /></div></li>
					<li theme="green"><div><s:text name="green" /></div></li>
					<li theme="red"><div><s:text name="red" /></div></li>
					<li theme="purple"><div><s:text name="purple" /></div></li>
					<li theme="silver"><div><s:text name="silver" /></div></li>
					<li theme="azure"><div><s:text name="azure" /></div></li>
				</ul>
				-->
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar" style="width:300px">
				<div class="toggleCollapse"><h2><s:property value="#session.ADMIN_SYSTEM_USER_NAME.project.projectName"/>(<s:property value="#session.ADMIN_SYSTEM_USER_NAME.name"/>)</h2><div><s:text name="shrink" /></div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2>
						<span>Folder</span>
						<s:text name="search"/>
						<input type="text" id="menuDir"	index="1" maxlength="100" />
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
						  <s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossMenus" var="oneRow"  status="offset">
						    		<s:if test="#oneRow.pageUrl == null||#oneRow.pageUrl eq ''">
						    		   <s:if test="#offset.index != 0">
							    		      </ul>
							  				</li>
						    		   </s:if>
						    		   <li><span target="navTab" href="javascript:void(0)"><s:property value="%{getText(#oneRow.name)}" /></span>
						    		    <ul >
						    		</s:if>
						    		<s:else><li><a  target="navTab" href="<%=basePath%>${oneRow.pageUrl}.action" rel="w_<s:property value="%{getText(#oneRow.name)}" />"><s:property value="%{getText(#oneRow.name)}" /></a></li></s:else>
						  </s:iterator>
						  		 </ul>
							  </li>
							  				
						 </ul>
						
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- <s:text name="add_about_controlling_the_display" /> class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon"><s:text name="my_home" /></span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- <s:text name="disable_only_need_to_add_a_style" /> class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- <s:text name="disable_only_need_to_add_a_style" /> class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;"><s:text name="my_home" /></a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="pageContent" layoutH="0">
							<table width="100%" height="200px">
						    	<tr>
						    		<td align="center" valign="middle">
							    		<h2 style="font-size: 1.5em;"><s:text name="administrator" />:<s:property value="#session.ADMIN_SYSTEM_USER_NAME.ossUser.realnames"/>，<s:text name="hello" />，
							    		
							    		<s:text name="welcome_to" />
							    		<span style="color:red;font-size:30px;">《<s:property value="#session.ADMIN_SYSTEM_USER_NAME.project.projectName"/>》</span>
							    		<span style="color:blue;font-size:30px;"><s:property value="#session.ADMIN_SYSTEM_USER_NAME.currentOssServer.name"/></span>&nbsp;<s:text name="server_management_platform" />！
							    		</h2> 
						    		</td>
						    	</tr>
						    </table>
					    </div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
	<div id="footer">Copyright &copy; 2014 <s:text name="brilliant_and_copyright" /></div>
</body>
</html>