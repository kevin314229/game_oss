<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
  <head>
    <title>封魔-OSS</title>
    <style type="text/css">
	body {   
			margin-left: 0px;   
			margin-top: 0px;   
			margin-right: 0px;   
			margin-bottom: 0px;   
	}   
	</style>  
	<script language="JavaScript">
		var picArrow1 = '<%=basePath%>/media/default/images/middle_arrow_01.gif';
		var picArrow2 = '<%=basePath%>/media/default/images/middle_arrow_02.gif';

	　　function resizeFrames() {

			var ff = window.parent.window.document.getElementsByTagName("frameset")[1]; 
			
			if(ff.cols=="220,10,*"){
				ff.cols="0,10,*";
				window.document.getElementById("imgArrow").src=picArrow2;
				window.document.getElementById("imgArrow").title="显示左栏";				
			}else{
				ff.cols="220,10,*";
				window.document.getElementById("imgArrow").src=picArrow1;
				window.document.getElementById("imgArrow").title="隐藏左栏";
			}

	　　}
	</script>
</head>

<body bgcolor="#9ad1f7">

<div style="margin-top: 180px;">

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="middle"><img id=imgArrow src="<%=basePath%>/media/default/images/middle_arrow_01.gif" alt="隐藏左栏" style="cursor:hand" onclick="resizeFrames();"></td>
  </tr>
</table>

</div>
</body>
</html>
