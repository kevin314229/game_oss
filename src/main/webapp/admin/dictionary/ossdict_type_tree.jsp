<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<link href="<%=basePath%>/media/default/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>/media/default/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>/media/default/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=basePath%>/media/default/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>/media/default/css/zTreeStyle.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="<%=basePath%>/media/default/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="<%=basePath%>/media/default/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="<%=basePath%>/media/default/js/json2.js" type="text/javascript"></script>
<script src="<%=basePath%>/media/default/js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath%>/media/default/js/JSONeditor.js" type="text/javascript"></script>


<script src="<%=basePath%>/media/default/js/highcharts/highcharts.js"  type="text/javascript"></script>
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
<script src="<%=basePath%>/media/default/js/dwz.regional.zh.js" type="text/javascript"></script>
	