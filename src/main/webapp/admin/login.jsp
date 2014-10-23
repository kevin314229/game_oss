<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title><s:text name="login_brilliant_and_operations_management_platform" /></title>
	<link href="<%=basePath%>/media/default/css/bootstrap-combined.min.css" rel="stylesheet" type="text/css">
	
	<script src="<%=basePath%>/media/default/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/layer/layer.min.js" type="text/javascript"></script>
 	<script src="<%=basePath%>/media/default/js/jquery.cookie.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript">
		
		function reloadImg()
		{
			document.getElementById("codes").src="<%=basePath%>/Kaptcha.jpgjjl"+"?radom="+Math.random();
		}
		
		$(function(){
		
			if($.cookie("savePassword")!=undefined){
				var auto = $.cookie("savePassword");
				if(auto=="true"){
					$("input[name='username']").val($.cookie("username"));
					$("input[name='password']").val($.cookie("password"));
					$("input[name='verifyPassword']").val($.cookie("verifyPassword"));
					$("input[name='savePassword']").attr("checked","checked");
					$("form").submit();
				}
			}
			
			var actionMsg = "${actionMsg}";
			if(actionMsg){
				$.layer({
				    type: 1,
				    closeBtn: true,
				    shade: [0],
				    fix: true,
				    border: [3, 0.3, '#0f0f0f'],
				    area: ['300px','250px'],
	   				shift: 'right-bottom',
				    time: 3,
				    page: {
						dom: '#id', 
						html: actionMsg,
						url: '',
						ok: function(datas){}
					}
				});
			}
		})
		
		


	</script>
	<style>
	p{
		color: red;
		margin: 0 auto;
	}
	
	</style>
  </head>
  <body>
  
  
  <div class="container"> 
    <div class="row"> 
    	
      <div class="span4 offset4 well"> 
	            <legend style="text-align:center;">
					<a href="login.action?request_locale=zh_CN">中文</a>
	<!--    		<a href="login.action?request_locale=zh_TW">台灣</a>-->
	    		<a href="login.action?request_locale=en_US">English</a>
	    		<a href="login.action?request_locale=ko_KR">한국</a>
	    		<a href="login.action?request_locale=ru">русский</a>
	    		<a href="login.action?request_locale=vi">Việt Nam</a>
				</legend> 
	            <div class="alert alert-error" style="display:none"> 
	                <a class="close" data-dismiss="alert" href="javascript:void(0)">×</a><s:text name="userpasserror"/>
	            </div> 
	            <form id="loginForm" method="post" action="login.action" accept-charset="UTF-8" onsubmit="return true;"> 
	            <input type="text" id="username" class="span4" name="username" placeholder="<s:text name="username"/>"> 
	            <input type="password" id="password" class="span4" name="password" placeholder="<s:text name="password"/>">
	            <input type="hidden" name="verifyPassword" value="true"/>
	           	<s:if test="@com.jcwx.game.common.CacheManager@SYSTEM_SET.verify">
		           	<div align="left">
		           	 	<input type="text" id="password" class="span3" style="width:256px" name="checkcode" placeholder="<s:text name="checkcode"/>" maxLength="2"> 
						<img style="height: 30px;vertical-align: top;width:100px;" src="<%=basePath%>/Kaptcha.jpgjjl" id="codes" onclick="javaScript:reloadImg();"></img>
					</div>
				
				</s:if>
				<s:if test="@com.jcwx.game.common.CacheManager@SYSTEM_SET.autoLogin">
					<s:text name="autoLogin"/>:<input type="checkbox" name="savePassword" value="true"/>
				</s:if>
	            	<input type="submit"id="loginSubmit" class="btn btn-info btn-block" value="<s:text name="login"/>"/>
	           
	            </form>     
	        </div> 
	    </div> 
	</div> 
  </body>
</html>
