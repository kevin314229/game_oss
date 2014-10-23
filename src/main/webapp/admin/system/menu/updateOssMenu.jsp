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
		alertMsg.correct("<s:text name="modify_menu_success" />！");
		navTab.reload("<%=basePath %>/admin/base/ossMenu_manage.action");  
	}
	
}
</script>

<div class="pageContent">
	<s:form action="ossMenu_updateOssMenu"   namespace="/admin/base" method="post" theme="simple" 
	cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&validateCallback(this,refreshMenu)">
		<div class="pageFormContent nowrap" >

			<dl>
				<dt><s:text name="function_name" />：</dt>
				<dd style="width: 100px">
					<input type="text" name="ossMenu.name" value='<s:property value="%{getText(ossMenu.name)}" />' minlength="2" class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt><s:text name="type" />：</dt>
				<dd style="width: 100px">
					<select name="ossMenu.type" >
						<s:if  test="ossMenu.type==0" >				
							<option value="0"><s:text name="to_the_left_menu" /></option>
							<option value="1"><s:text name="page_button" /></option>
						</s:if>
						<s:else>
							<option value="1"><s:text name="page_button" /></option>
							<option value="0"><s:text name="to_the_left_menu" /></option>
						</s:else>					
					</select>
				</dd>
			</dl>
			
			<dl>
				<dt><s:text name="a_menu" />：</dt>
				<dd style="width: 100px">
					<select name="ossMenu.parentMenuID">
						<s:iterator value="ossMenuList" var="oneRow">
							<option value='<s:property value="ossMenuID"/>' <s:if test='ossMenu.parentMenuID==ossMenuID'> selected="selected" </s:if > >
							<s:property value="%{getText(#oneRow.name)}" /></option>
							
						</s:iterator>
					</select>
					<span class="info"></span>
					 <input type="hidden" name="id"   value='<s:property value="ossMenu.parentMenuID"/>' />
					 <input type="hidden" name="ossMenu.ossMenuID" value="<s:property value="ossMenu.ossMenuID"/>">
				</dd>
			</dl>
			
			<dl>
				<dt>Action<s:text name="path" /></dt>
				<dd style="width: 100px">
					<input type="text" name="ossMenu.pageUrl" value='<s:property value="ossMenu.pageUrl"/>' size="40"  class="required">
				</dd>
			</dl>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" ><s:text name="confirm_the_changes" /></button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close"><s:text name="close" /></button></div></div></li>
			</ul>
		</div>
	</s:form>
	
</div>

