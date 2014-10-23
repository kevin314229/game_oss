<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp" />
<script>

function doAddHint(){
		$.ajax({
        type: "post",
        url: "<%=basePath%>/admin/base/hint_doUpdate.action",
        cache: false,
        data:$('#hint_doUpdate').serialize(),
        success: function(msg){
	            if(msg==1){
					alertMsg.correct("操作成功!");
					queryDictData($("#menuId").val());
				} else {
					alertMsg.error("操作失败!");
				}
			}
		});
	}
</script>


<div class="pageContent">
	<s:form action="hint_doUpdate" id="hint_doUpdate" name="hint_doUpdate" namespace="/admin/base"
		method="post" theme="simple" cssClass="pageForm required-validate"
		onsubmit="return $(this).valid()&&validateCallback(this,refreshMenu)">
		
		<input type="hidden" id="hintId" name="hint.hintId" value='<s:property value="hint.hintId"/>'>
		<input type="hidden" id="hintKey" name="hint.hintKey" value='<s:property value="hint.hintKey"/>'>
		<input type="hidden" id="menuId" name="hint.menuId" value='<s:property value="hint.menuId"/>'>
		<div class="pageFormContent nowrap">
			
			<dl>
				<dt >
					提示Key ：
				</dt>
				<dd style="width: 250px">
					<s:property value="hint.hintKey"/>
				</dd>
			</dl>
			<dl>
				<dt >
					提示内容 ：
				</dt>
				<dd style="width: 250px">
					<s:textarea cols="80" rows="10" id="hintValue" name="hint.hintValue" maxlength="300"></s:textarea>
					<span class="info"></span>
				</dd>
			</dl>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="doAddHint()">
								<s:text name="confirm_to_add" />
							</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</s:form>

</div>

