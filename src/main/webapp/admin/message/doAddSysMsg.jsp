<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>

<div class="pageContent" >
		<s:form action="adminSysMsg_addSysMsg" method="post" theme="simple" 
			 cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this);" >
			<div class="pageFormContent nowrap" layoutH="60" >
				
				    <dl>
					<dt>
							<s:text name="a_new_system_of_announcement" />
					</dt>
						<dd>
							<s:textarea name="msgContent" cols="50" rows="5"  cssClass="required" maxlength="80"></s:textarea>
						</dd>
					</dl>
					<dl>	
						<dt>
							<s:text name="the_announcement_type" />：
							</dt>
							<dd>
							<label><input type="radio" name="type" value="1" checked="true" /><s:text name="server_announcement"/></label>
							<label><input type="radio" name="type" value="2" /><s:text name="system_of_announcement"/></label>
							</dd>
					</dl>
					<dl>	
						<dt >
							<s:text name="broadcast_interval_of_each" />
						</dt>
						<dd>
							<select id="delay"
								name="delay" class="required">
								<option value="1">1<s:text name="minutes" /></option>
								<option value="15">0.25</option>
								<option value="30">0.5</option>
								<option value="45">0.75</option>
									<%
									    int tt;
										for (int t = 1; t <= 100; t++) {
											tt = t *60;
									%>
									<option value="<%=tt%>"><%=t%></option>
									<%
										}
									%>
								</select><s:text name="hours_at_a_time" />
						</dd>
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
							<s:textfield id="endDate" name="endDate" maxlength="16" size="12" style="width:120px" 
							readonly="true" cssClass="required date" dateFmt="yyyy-MM-dd HH:mm" /> 
						</dd>
					</dl>
				</div>
		    	<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_to_add"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
					</ul>
				
				</div>
		</s:form>
</div>