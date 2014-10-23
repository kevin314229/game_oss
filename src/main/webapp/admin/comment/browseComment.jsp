<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<form id="pagerForm" method="post" action="#rel#">
	<input type="hidden" name="currPageNO" value="${currPageNO}" />
	<input type="hidden" name="onePageNum" value="${onePageNum}" />
</form>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageHeader">
	<s:form rel="pagerForm" action="comment_browseComment" id="searchForm" name="searchForm" method="post" theme="simple" cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		  		<div class="searchDiv_left">
					<s:text name="the_sorting" />：<s:select list="#{'DESC': getText('creation_time')+'↓','ASC': getText('creation_time')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
				 
					<s:text name="type" />：<s:select list="#{'': getText('all'),'1':'BUG','2': getText('complaints'),'3': getText('advice'),'4': getText('other')}" label="" listKey="key" listValue="value" name="questionType"></s:select>
			 
					<s:text name="state" />：<s:select list="#{'': getText('all'),'0': getText('did_not_return'),'1': getText('reply')}" label="" listKey="key" listValue="value" name="questionStatus"></s:select>
			 
					<s:text name="statistical_time" />：<s:textfield id="beginDate" name="beginDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
				 
					<s:text name="to" /> <s:textfield id="endDate" name="endDate" maxlength="10" size="12" cssClass="required date"></s:textfield>
				 
					<s:text name="role_name"/>  <s:textfield id="nickName" name="nickName" maxlength="10" size="12" />
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
	    <table class="list" width="100%" layoutH="64" >
	    	<thead>
		    	<tr>
		    	    <th width="59"><s:text name="data" />ID</th>
		    	    <th width="46"><s:text name="the_player_name" /></th>
		    	    <th width="46"><s:text name="type_of_problem" /></th>
		    	    <th width="48"><s:text name="state_of_the_problem" /></th>
		    	    <th  width="400"><s:text name="problem_description" /></th>
		    	    <th width="166"><s:text name="question_reply" /></th>
		    	    <th width="93"> <s:text name="creation_time" /></th>
		      	</tr>
	    	</thead>
	    <tbody>
	    <s:iterator value="playerBaseQuestionList" var="oneRow"  status="offset">
	    <tr >
		    	    <td>
						 <s:property value="#oneRow.id"/>
		    	    </td>
		    	     <td>
						 <s:property value="#oneRow.nickName"/>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#oneRow.questionType==1">BUG</s:if>
		    	    	<s:if test="#oneRow.questionType==2"><s:text name="complaints" /></s:if>
		    	    	<s:if test="#oneRow.questionType==3"><s:text name="advice" /></s:if>
		    	    	<s:if test="#oneRow.questionType==4"><s:text name="other" /></s:if>
		    	    </td>
		    	    <td>
		    	    	<s:if test="#oneRow.questionStatus==0"><span style="color:red"><s:text name="did_not_return" /></span></s:if>
		    	    	<s:if test="#oneRow.questionStatus==1"><span style="color:green"><s:text name="reply" /></span></s:if>
		    	    </td>
		    	    <td>
						<div style="word-wrap:break-word; word-break:break-all;display:block;width:400px;">
							<s:property value="#oneRow.questionComment" escape="false"/>
						</div>
		    	    </td>
		    	    <td > 
		    	    <div style="word-wrap:break-word; word-break:break-all;display:block;width:166px;">
		    	    
		    	    		<a  rel="dlg_page2" target="dialog" href="<%=path %>/admin/base/comment_toaddAnswer.action?cid=${oneRow.id }&&question=${oneRow.questionComment}" style="color:#009900;">[<s:text name="respond_to_the_player" />]</a>
		    	    		<s:property value="#oneRow.questionReplay" escape="false"/>
		    	    </div>
		    	    </td>
		    	    <td>
		    	  		 <s:date name="#oneRow.createTime"/>
		    	    </td>
		    	  
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <s:include value="/admin/template/paging.jsp"/>
  </div>
