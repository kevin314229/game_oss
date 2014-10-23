<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script>

$(function(){

	var paramValue = eval("("+$.pdialog._current.data("param")+")");
	
	JSONeditor.start('tree','jform',paramValue,true);
	
	Opera=(navigator.userAgent.toLowerCase().indexOf("opera")!=-1)
	Safari=(navigator.userAgent.toLowerCase().indexOf("safari")!=-1)
	Explorer=(document.all && (!(Opera || Safari)))
	Explorer7=(Explorer && (navigator.userAgent.indexOf("MSIE 7.0")>=0))
		
	if(Explorer7 && location.href.indexOf("file:")!=0){prompt("This is just to get input boxes started in IE7 - who deems them unsecure.","I like input boxes...")}
});
function submitMessage(){
	var obj = {};
	obj["<s:property value="callBackObjName"/>"] = JSON.stringify(JSONeditor.treeBuilder.json);
	$.bringBack(obj);
}
</script>
<div class="pageContent" >
		<div class="pageFormContent" layoutH="55">
			<div id="tree"   style="float:left;width:200px"></div>
			<div id="jform"  style="float:left;width:300px"></div>
			
		</div>
		<div class="formBar">
					<ul>
						<li>
							<a class="button" href="javascript:;" onclick="submitMessage(this)">
							<span><s:text name="confirm_to_add"/></span></a>
						</li>
					</ul>
		</div>
</div>
