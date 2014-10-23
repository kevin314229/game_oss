<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageFormContent">
    <s:form action="modifyMutiLanguage_list" method="post" theme="simple" onsubmit="return $(this).valid()&&navTabSearch(this)">
	    <dl>
	    	<dt><s:text name="bundleNameX" /></dt>
	    	    <dd>
	    	    	<s:textfield name="bundleName"></s:textfield>
	    	    </dd>
	   	</dl>
	   	 <dl>
	    	<dt><s:text name="srcLocaleX" /></dt>
	    	     <dd>
	    	    	<s:textfield name="srcLocale"></s:textfield>
	    	    </dd>
	   	</dl>
	   	 <dl>
	    	<dt><s:text name="descLocaleX" /></dt>
	    	     <dd>
	    	    	<s:textfield name="descLocale"></s:textfield>
	    	    </dd>
	   	</dl>
	   	<dl>
	    		<dd>
	    		 	<div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div>
	    		</dd>
	    </dl>
    </s:form>
</div>

<div class="pageFormContent">
    <s:form action="modifyMutiLanguage_change" method="post" theme="simple" onsubmit="return $(this).valid()&&navTabSearch(this)">
	    <dl>
	    	<dt><s:text name="keyX" /></dt>
	    	    <dd>
	    	    	<s:textfield name="key"></s:textfield>
	    	    </dd>
	   	</dl>
	   	 <dl>
	    	<dt><s:text name="valueX" /></dt>
	    	     <dd>
	    	    	<s:textfield name="value"></s:textfield>
	    	    </dd>
	   	</dl>
	   	 <dl>
	    	<dt><s:text name="localeX" /></dt>
	    	     <dd>
	    	    	<s:textfield name="descLocale"></s:textfield>
	    	    </dd>
	   	</dl>
	   	<dl>
	    		<dd>
	    		 	<div class="buttonActive"><div class="buttonContent"><button type="submit">修改</button></div></div>
	    		</dd>
	    </dl>
    </s:form>
</div>
<div class="pageContent">
			<h1>笑嘻嘻</h1>
			<div>
				 <table class="list" width="100%">
			    	<thead>
					<tr>
						<th width="300">文件Keys</th>
						<th>源语言</th>
						<th>目标语言</th>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="languageArray" var="oneRow" status="offset">
						
							<s:iterator value="#oneRow.keys" var="oneRowKey" status="offset">
							<tr >
							<td><s:property value="#oneRowKey" /></td>
							<td><s:property value="#oneRow[#oneRowKey].src" /></td>
							<td><s:property value="#oneRow[#oneRowKey].desc" /></td>
							</tr>
							</s:iterator>
						
						
					</s:iterator>
					</tbody>
				</table>
				<div id="container2" style="min-width: 400px; height: 400px"></div>
			</div>
	</div>