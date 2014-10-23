<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<script>
function refreshMenu(json){
	 if (json.statusCode == DWZ.statusCode.ok){  
		alertMsg.correct("<s:text name="add_menu_success" />！");
		navTab.reload("<%=basePath %>/admin/base/ossMenu_manage.action");  
	}
	
}
</script>


<div class="pageContent">
	<s:form action="ossMenu_addOssMenu"   namespace="/admin/base" method="post" theme="simple" 
	cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&validateCallback(this,refreshMenu)">
		<input type="hidden" name="ossMenu.parentMenuID" value='<s:property value="parentMenuID"/>'> 
		<div class="pageFormContent nowrap" layoutH="60">
	<s:if test="parentMenuID!=0">
			<dl>
				<dt><s:text name="belongs_function" />：</dt>
				<dd style="width: 100px">
					 <input type="text" name="name" class="readonly" readonly="true"  value="<s:property value="name"/>" />
					<span class="info"></span>
				</dd>
		</dl>
			</s:if>	
			<dl>
				<dt><s:text name="function_name" />：</dt>
				<dd style="width: 100px">
					<input type="text" name="ossMenu.name" minlength="2" class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt><s:text name="type" />：</dt>
				<dd style="width: 100px">
					<select name="ossMenu.type">
						<option value="0"><s:text name="to_the_left_menu" /></option>
						<option value="1"><s:text name="page_button" /></option>
					</select>
				</dd>
			</dl>
			<s:if test="parentMenuID!=0">
			<dl>
				<dt>Action<s:text name="path" />     </dt>
				<dd style="width: 100px">
					<input type="text" name="ossMenu.pageUrl" size="40"  class="required">
				</dd>
			</dl>
			</s:if>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" ><s:text name="confirm_to_add" /></button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close"><s:text name="close" /></button></div></div></li>
			</ul>
		</div>
	</s:form>
	
</div>

