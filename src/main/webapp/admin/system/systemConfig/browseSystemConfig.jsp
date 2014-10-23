<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script>

	function onUpdateSystemConfig(systemKey,systemSubmit){
	 
		var url = '<%=basePath%>/admin/base/systemConfig_updateSystemConfig.action?systemKey='+systemKey+'&systemValue='+$(systemSubmit).parent().parent().find('input[name=systemValue]').val();
		console.log($(systemSubmit).parent().parent().find('input[name=systemValue]').val());
		confirmRefresh({'confirmMsg':'<s:text name="sure_to_modify?"/>','url':url});
			    		
	}
	
	function modifySystemConfig(systemKey,systemSubmit){
		$("#systemKey").val(systemKey);
		$("#systemValue").val($(systemSubmit).parent().parent().find('input[name=systemValue]').val());
		//systemConfig.submit();
		$("#configBtn").click();
	}
</script>
 <s:form id="systemConfig" name="systemConfig" action="systemConfig_updateSystemConfig" namespace="/admin/base" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&validateCallback(this,navTabAjaxDone)">
	     <input type="hidden" id="systemKey" name="systemKey" />
	     <input type="hidden" id="systemValue" name="systemValue" />
	     <input type="submit" id="configBtn" style="display:none"/>
</s:form>
	<div class="pageContent" layoutH="0">
		<div class="panelBar">
			<ul class="toolBar">
				<li>
					<a class="add" width="720" height="460" target="dialog" href="<%=basePath%>/admin/base/systemConfig_toAdd.action" target="dialog">
						<span><s:text name="new" /> <s:text name="increase" /></span>
					</a>
				</li>
			</ul>
		</div>
	    <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	    <th>Key</th>
	    	    <th><s:text name="maximum_number_of_online" />(<s:text name="a_negative_number" />-<s:text name="unlimited" />)</th>
	    	    <th><s:text name="describe" /></th>
	    	    <th><s:text name="operation" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="ossSystemParamList" var="oss" status="item">
		    	
		    	<s:form action="systemConfig_updateSystemConfig" namespace="/admin/base" method="post" theme="simple"  cssClass="pageForm required-validate" 
			    			onsubmit="return onUpdateSystemConfig(this)">
			    			
		    	<tr>
		    		<td> <s:property value="systemKey"/> <s:hidden id="systemKey" name="systemKey"></s:hidden>  </td>
		    		<td><s:textfield name="systemValue" size="5" style="height: 20px;width: 150px;font-size: 12px"/> </td>
		    		<td><s:property value="systemDesc" /> </td>
		    		<td>
		    		<a id="item<s:property value='#item.index'/>" class="button" 
					href="javascript:modifySystemConfig('<s:property value="systemKey"/>',item<s:property value='#item.index'/>);"><span><s:text name="modify_the" /></span></a>
		    		</td>
		    	</tr>
		    	</s:form>
	   		</s:iterator>
	   		</tbody>
	    </table>
	 </div>
