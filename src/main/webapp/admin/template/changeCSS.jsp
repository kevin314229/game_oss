<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script type="text/javascript" language="javascript">
    	$(function(){
    			if(navigator.userAgent.indexOf("MSIE")>0) {  
    	        
    		   }  
    		   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){  
        		   
    		   }  
    		   if(isSafari=navigator.userAgent.indexOf("Safari")>0&&navigator.userAgent.indexOf("Chrome")<0) {
    			   $(".searchButton").css("margin-top","50px");
    		   }   
    		   if(isCamino=navigator.userAgent.indexOf("Camino")>0){  
    		       
    		   }  
    		   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){  
    		       
    		   }  
    		
    	});
</script>