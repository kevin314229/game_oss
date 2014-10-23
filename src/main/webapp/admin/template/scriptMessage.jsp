<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script type="text/javascript" language="javascript">
    	$(function(){
	    	var warnMsg = "<s:property value="errorInfo" escape="false"/>";
	    	var successMsg = "<s:property value="successInfo" escape="false"/>";
	    	var actionMsg = "<s:property value="actionMsg" escape="false"/>";
    		if(successMsg){
    			alertMsg.correct(successMsg);
    		}else if(warnMsg){
    			alertMsg.error(warnMsg);
    		}else if(actionMsg){
    			alertMsg.correct(actionMsg);
    		}
    		
    	});
</script>