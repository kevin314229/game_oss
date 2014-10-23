<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp" />
<div class="pageContent">

	<s:form action="systemGift_doUpdate" namespace="/cjwz/notice"
		method="post" theme="simple" cssClass="pageForm required-validate"
		onsubmit="return $(this).valid()&&navTabSearch(this)">
		<s:hidden name="systemGift.id"></s:hidden>
		<s:hidden name="systemGift.createTime"></s:hidden>
		<div class="pageFormContent nowrap" layoutH="60">

			<dl>
				<dt width="30%">标题：</dt>
				<dd>
					<s:textfield id="title" size="83" name="systemGift.title"
						cssClass="required">
					</s:textfield>
				</dd>
			</dl>
			<dl>
				<dt width="30%">
					<s:text name="activity_description" />
					：
				</dt>
				<dd>
					<s:textarea cols="80" rows="8" id="content"
						name="systemGift.content" cssClass="required" maxlength="500"></s:textarea>
				</dd>
			</dl>
			<dl>
				<dt width="30%">奖励ID：</dt>
				<dd>
					<s:textarea cols="80" rows="2" id="gift" name="systemGift.gift"
						cssClass="required" maxlength="255"></s:textarea>
				</dd>
			</dl>
			<dl>
				<dt width="30%">领取人ID：</dt>
				<dd>
					<s:textarea cols="80" rows="6" id="playerIdList"
						name="systemGift.playerIdList" maxlength="300"></s:textarea>
				</dd>
			</dl>
			<dl>
				<dt width="30%">
					<s:text name="activity_over_time" />
					：
				</dt>
				<dd>
					<s:textfield id="endTime" name="systemGift.endTime"
						datefmt="yyyy-MM-dd HH:mm:ss" cssClass="required date">
					</s:textfield>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								<s:text name="confirm_to_add" />
							</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
</div>
