<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent">
	<div class="pageFormContent nowrap">
		<s:text name="a_new_system_of_announcement" /><br />
		<s:form action="adminSysMsg_updateSysMsg" method="post" theme="simple"
			cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			
			<s:hidden name="systemNoticeID" value="%{systemNotice.systemNoticeId}"/>
			<s:textarea name="msgContent" cols="50" rows="5" value="%{systemNotice.content}" cssClass="required" maxlength="128"></s:textarea>
			<br />
			<dl>
				<dt>
					<s:text name="the_announcement_type" />：
				</dt>
				<dd>
					<label><input type="radio" name="type" value="1" <s:if test="systemNotice.type==1">checked="true" </s:if> /><s:text name="server_announcement"/></label>
					<label><input type="radio" name="type" value="2" <s:if test="systemNotice.type==2">checked="true" </s:if> /><s:text name="system_of_announcement"/></label>
				</dd>
			</dl>
			<dl>
				<dt style="width:200px">
				<s:text name="broadcast_interval_of_each" />
				
				<select id="delay" 
					name="delay"  class="required">
					<option value="1"  <s:if test="systemNotice.delay == 1">selected="selected"</s:if> >1<s:text name="minutes" /></option>
					<option value="15" <s:if test="systemNotice.delay == 15">selected="selected"</s:if> >0.25</option>
					<option value="30" <s:if test="systemNotice.delay == 30">selected="selected"</s:if>>0.5</option>
					<option value="45" <s:if test="systemNotice.delay == 45">selected="selected"</s:if>>0.75</option>
						<%
							for (int t = 1; t <= 100; t++) {
						%>
						<option value="<%=t%>* 60"    <s:if test="systemNotice.delay== <%=t%>* 60">selected="selected"</s:if>><%=t%></option>
						<%
							}
						%>
				</select><s:text name="hours_at_a_time" />
				</dt>
			</dl>
				<dl>	
						<dt>
							<s:text name="the_start_time" />
						</dt>
						<dd>
							<s:textfield id="start_Time" name="startDate" maxlength="16" size="12" style="width:120px" 
							readonly="true" cssClass="required date" dateFmt="yyyy-MM-dd HH:mm" /> 
						</dd>
					</dl>
			<dl>
				<dt>
					<s:text name="by_the_time" />
					
				</dt>
				<dd>
					<s:textfield id="endDate" name="endDate" maxlength="16" size="12" style="width:120px" dateFmt="yyyy-MM-dd HH:mm"
							readonly="true" cssClass="required date">
							<s:param name="value"><s:date name="systemNotice.noticeTime" format="yyyy-MM-dd HH:mm" /></s:param>
					</s:textfield> 
				</dd>
			</dl>
			<dl>
				<dt>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">
							<s:text name="confirm_the_change"/></button></div></div>
				</dt>
			</dl>
		</s:form>
	</div>
</div>	
