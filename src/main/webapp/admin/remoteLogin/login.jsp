<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	//System.out.println(basePath);
%>
<html>
  <head>
    <title>登录《石器部落》运营管理平台</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
    <script language="javascript">
		function refreshCheckCode(o){
		 	//重载验证码
		 	var timenow = new Date().getTime();
		 	o.src="<%=basePath%>/Kaptcha.jpgjjl?d="+timenow;
		 
		 	//超时执行;
		 	setTimeout(function(){
		  		o.src="<%=basePath%>/Kaptcha.jpgjjl?d="+timenow;
		 	},20);
		}
		
		function checkForm(o) {
			if(o.username.value == ''){
				alert("请输入账号！"); return false;
			}
			if(o.password.value == ''){
				alert("请输入密码！"); return false;
			}
			if(o.checkcode.value == ''){
				alert("请输入验证码！"); return false;
			}
			return true;
		}
	</script>
  </head>
  <body>
  <s:form action="login" method="post" theme="simple" onsubmit="return checkForm(this);">
  	<div style="margin-top: 200px;">
	  	<table style="width:400px;" align="center" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td colspan="2" align="center">
				<FONT color="#00ff00"><s:property value="successInfo"/></FONT><FONT color="#ff0000"><s:property value="errorInfo"/></FONT>
			</td>
		  </tr>
		</table>
		<table style="width:460px;" align="center" border="0" cellspacing="0" cellpadding="0" id="row">
		  <tr>
		    <th colspan="2" style="font-size:16px;">登录《石器部落》运营管理平台</th>
		  </tr>
		  <tr>
			<td align="right" width="30%">账&nbsp;&nbsp;号：</td>
			<td align="left">
				<s:textfield name="username" maxlength="16"></s:textfield>
			</td>
		  </tr>
		  <tr>
			<td align="right">密&nbsp;&nbsp;码：</td>
			<td align="left">
				<s:password name="password" maxlength="16"></s:password>
			</td>
		  </tr>
		  <tr>
			<td align="right">验证码：</td>
			<td align="left">
				<s:textfield name="checkcode" size="10" maxlength="5"></s:textfield>输入下面图片中的文字
			</td>
		  </tr>
		  <tr>
			<td align="right"></td>
			<td align="left">
				<img src="<%=basePath%>/Kaptcha.jpgjjl" style="cursor:hand" alt="看不清，点击换一张" id="randImg" onclick="refreshCheckCode(this);">&nbsp;
				<a href="javascript:refreshCheckCode(document.getElementById('randImg'));">换一张</a>
			</td>
		  </tr>
		</table>
		<table style="width:400px;" align="center" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td colspan="2" align="center">
				<s:submit value="登录" style="cursor:hand"></s:submit>
		    	<s:reset value="重置" style="cursor:hand"></s:reset>
			</td>
		  </tr>
		</table>
  	</div>
  </s:form>
  </body>
</html>
