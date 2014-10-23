<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:head/>

<script type="text/javascript">
 function initSelect(){
	
	 var obj= $("#ossRole_updateAddOssUser_rightSideCartoonCharacters")[0].options;
	 for(var i=0;i<obj.length;i++){
		 obj[i].selected="selected";
	 }
	 
	 return true;
	 
 } 
</script>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="accountInfo">
	<div >
		
		<p><s:text name="the_current_distribution_is_the_role_of_the_user" />：<s:property value="roleName"/></p> 
		<p><s:text name="explanation" />：<s:text name="move_to_the_right_of_the_menu" />，<s:text name="and_select" />，<s:text name="can_be_considered_formally_submitted" />。
		<s:text name="just_move_content_to_the_right" />，<s:text name="select_instead" />，<s:text name="remove_all" />。
		</p>
	</div>
</div>

<div class="pageContent">
	 <s:form action="ossRole_updateAddOssUser" namespace="/admin/base" method="post" theme="simple"  cssClass="pageForm required-validate" 
	 onsubmit="return ($(this).valid()&&initSelect()&&dialogSearch(this));">
	 <div layoutH="100">
		    <s:optiontransferselect
			     label="Favourite Cartoons Characters"
			     name="leftSideCartoonCharacters"
			     leftTitle="%{getText('can_be_assigned_personnel')}"
			     rightTitle="%{getText('assigned_personnel')}"
			     list="ossUserOffList"
			     listKey="username"
			     listValue="username"
			     multiple="true"
			     headerKey="headerKey"
			     headerValue="--- %{getText('please_select_a')} ---"
			     emptyOption="true"
			     doubleList="ossUserOnList"
			     doubleListKey="username"
			     doubleListValue="username"
			     doubleName="rightSideCartoonCharacters"
			     doubleHeaderKey="doubleHeaderKey"
			     doubleHeaderValue="--- %{getText('please_select_a')} ---"
			     doubleEmptyOption="true"
			     doubleMultiple="true"
			 />
	<s:hidden name="ossRoleID"/>
	</div>
		  	<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div></div></li>
		    	</ul>
		    </div>
	</s:form>
	
	
</div> 
