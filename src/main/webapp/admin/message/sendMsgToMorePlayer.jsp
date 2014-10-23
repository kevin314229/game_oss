<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<s:include value="/admin/template/scriptMessage.jsp"/>

<div class="pageContent">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span><s:text name="multiplayer"/></span></a></li>
					<li><a href="javascript:;"><span><s:text name="all_the_players"/></span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" layoutH="32">
			<div>	
					<s:form action="sendMsgToMorePlayer_sendMsgToMorePlayer" method="post" theme="simple" 
					cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
					<div class="pageFormContent nowrap" layoutH="100">
						<input name="allFlag" value="0" type="hidden"/>
						<dl>
							<dt>
								<s:text name="the_player_name" />(<s:text name="in_the_middle_to" />,
								<s:text name="separated" />,<s:text name="no_more_than" />256<s:text name="a_character" />)：
							</dt>
							<dd>
								<s:textarea name="playerNames" cols="50" rows="3"></s:textarea>
							</dd>
						<dl>
						<dl>
							<dt>
								<s:text name="email_title" />：
							</dt>
							<dd>
								<s:textfield name="msgTitle" maxlength="32"></s:textfield>
							</dd>
						<dl>
						<dl>
							<dt><s:text name="the_message" />(<s:text name="mail" />)<s:text name="content" />(<s:text name="no_more_than" />256<s:text name="a_character" />)<br/></dt>
							<dd><s:textarea name="msgContent" cols="50" rows="5"></s:textarea></dd>
						<dl>
						<dl>
					<dt> &nbsp;</dt>
					<dd>
					<div class="buttonActive" >
					<div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div>
					</div>
					</dd>
				<dl>
					</div>
					</s:form>
			</div>
			<div>	
					<s:form action="sendMsgToMorePlayer_sendMsgToMorePlayer" method="post" theme="simple"
					cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
					<input name="allFlag" value="1" type="hidden"/>
					<div class="pageFormContent nowrap" layoutH="100">
						<dl>
							<dt><s:text name="email_title" />：</dt>
							<dd><s:textfield name="msgTitle" maxlength="32"></s:textfield></dd>
						<dl>
						<dl>
							<dt>
								<s:text name="the_message" />(<s:text name="mail" />)<s:text name="content" />(<s:text name="no_more_than" />256<s:text name="a_character" />)<br/>
							</dt>
							<dd>
								<s:textarea name="msgContent" cols="50" rows="5"></s:textarea>
							</dd>
						<dl>
					<dl>
					<dt> &nbsp;</dt>
					<dd>
					<div class="buttonActive" >
					<div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div>
					</div>
					</dd>
				<dl>
					</div>
					</s:form>
			</div>
		</div>
	</div>
</div>
			
<!--

<s:submit value="%{getText('send')}"></s:submit>
-->