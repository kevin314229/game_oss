<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
		<s:form  action="/admin/assay/onlineTimeAssay.action" namespace="/admin/assay" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
			   <div class="searchBar">
			      <div class="searchDiv_left">
				     
							<s:text name="the_login_time" />：
							<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
						 
							<s:text name="to" /> 
							<s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
						 
							 <s:text name="the_length" />:
	            			<s:select 
	            			list="#{'60':'1 '+getText('minutes'),'300':'5 '+getText('minutes'),'600':'10 '+ getText('minutes'),'1800':'30 '+ getText('minutes'),'3600':'60 '+ getText('minutes'),'7200':'120 '+ getText('minutes'),'10800':'180 '+ getText('minutes')}" 
	            			lable="" listKey="key" listValue="value" name="cutNum"> </s:select>
						 
							 <s:text name="the_logo"/>:
							 <s:select list="#{'-1':'--'+getText('all')+'--','1': getText('page_to_swim'),'2': getText('hand_swim')}"
							  lable="" listKey="key" listValue="value" name="loginFrom"> </s:select>
				</div>						 
			    	<div class="searchButton2">
						<ul>
							<li> <div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="the_query"/></button></div></div></li>
						</ul>
					</div>
				</div>
		</s:form>
</div>

<div class="pageContent">
	    <table class="list" width="100%" layoutH="50" >
	    	<thead>
	    	<tr>
				<th><s:text name="the_length" />(min)</th>
				<th><s:text name="the_number_of" /></th>
			</tr>
			</thead>
			<tbody>
	    	<s:iterator value="onlineList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
		    	    	<s:if test="#oneRow.temp1!=0">
		    	    		<s:property value="(#oneRow.temp1-1)*cutNum/60"/> - <s:property value="(#oneRow.temp1) *cutNum/60"/>
		    	    	</s:if>
		    	    	<s:else>
		    	    		0
		    	    	</s:else>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#oneRow.c"/>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
</div>
