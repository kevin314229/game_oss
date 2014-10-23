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
        url: "<%=basePath%>/admin/base/hint_doAdd.action",
        cache: false,
        data: {"hint.hintKey":$("#hintKey").val(),"pageUrl":$("#hint_doAdd_pageUrl").val(),"hint.hintValue":$("#hintValue").val(),"basePath":$("#basePath").val()},
        success: function(msg){
	            if(msg==1){
					alertMsg.correct("添加成功!");
				} else {
					alertMsg.error("添加失败!");
				}
			}
		});
	}
</script>


<div class="pageContent">
	<s:form action="hint_doAdd" namespace="/admin/base"
		method="post" theme="simple" cssClass="pageForm required-validate"
		onsubmit="return $(this).valid()&&validateCallback(this,refreshMenu)">
		
		<input type="hidden" id="hintKey" name="hint.hintKey" value='<s:property value="hint.hintKey"/>'>
		<input type="hidden" id="hint_doAdd_pageUrl" name="pageUrl" value='<s:property value="pageUrl"/>'>
		<input type="hidden" id="basePath" name="basePath" value='<%=basePath%>'>
		<div class="uint" layoutH="38" width="100%">
					<s:textarea cols="105" rows="20" id="hintValue" name="hint.hintValue" 
					maxlength="300" cssClass="editor" tools="mfull"></s:textarea>
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

