<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<s:include value="/admin/template/scriptMessage.jsp" />
<div class="pageHeader">
	<span class="color-red"></span>
	<div id="divLeft" style="height: 18px">
		<b>数据分析 ：渠道数据</b> <span class="color-red"><s:property
				value="errorInfo" /></span> <span class="color-gr"><s:property
				value="successInfo" /></span>
	</div>
	<s:form action="dudubear_oprator" method="post" theme="simple"
		id="queryRecordForm"
		onsubmit="return $(this).valid()&&navTabSearch(this);">
		<div class="searchBar">
			<div class="searchDiv_left">
			渠道ID:<s:select headerKey="0" headerValue="-请选择-" list="#{ '360':'360','dl':'当乐','xm':'小米'}"></s:select>
				<s:text name="statistical_time" />
				：
				<s:textfield id="beginTime" name="beginTime" maxlength="10"
					size="12" cssClass="required date">
					<s:param name="value">
						<s:date name="beginTime" format="yyyy-MM-dd" />
					</s:param>
				</s:textfield>

				<s:text name="to" />
				<s:textfield id="endTime" name="endTime" maxlength="10" size="12"
					cssClass="required date">
					<s:param name="value">
						<s:date name="endTime" format="yyyy-MM-dd" />
					</s:param>
				</s:textfield>
			</div>
			<div class="searchButton2">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									<s:text name="statistical" />
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</s:form>
</div>
<div class="pageContent">
	<table id="row" class="list" width="100%" layoutH="70">
		<thead>
		<tr class="even">
			<th>渠道ID </th>
			<th>注册用户数量 </th>
			<th>二次登录用户数量</th>
			<th>二次以上注册数量</th>
		</tr>
		</thead>
		<tbody>
		 <!-- t1.opratorID,t1.reg_num  ,t3.num1,t3.num2  -->
		<s:iterator value="jsonarrayArray" status="offsets" var="oneRow">
			<tr <s:if test="#offsets.even == true"> class="even" </s:if>
				onClick="changeTRBgColor(this)">
				<td><s:property  value="#oneRow.opratorID"/> </td>
				<td><s:property  value="#oneRow.reg_num"/></td>
				<td><s:property  value="#oneRow.num1"/></td>
				<td><s:property  value="#oneRow.num2"/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</div>


