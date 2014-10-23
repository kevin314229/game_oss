<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
    
<div class="pageContent">
		<s:form action="sendSysMsg_sendSysMsg" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
				    <table class="searchContent">
				    <tr style="height:20px">
				    	<td>
				    		<s:text name="immediately_send_system_announcement_to_all_online_players" />(<s:text name="no_more_than" />200<s:text name="a_character" />)<br/>
				    	</td>
				    </tr>
					<tr>
						<td>
							<s:textarea name="systemMessage" id="systemMessage" cssClass="required" cols="70" rows="7" maxLength="200"></s:textarea>
						</td>
					</tr>
					</table>
					
			    	<div style="padding-left: 400px;padding-top:5px">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="send"/></button></div></div></li>
						</ul>
					</div>
		</s:form>
</div>

