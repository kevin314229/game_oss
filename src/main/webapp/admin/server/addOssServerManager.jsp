<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:head/>
<s:include value="/admin/template/scriptMessage.jsp"/>
<script type="text/javascript">
 function initAccountSelect(){
	
	 var obj= $("#ossServer_updateAddOssServerManagerr_rightSideCartoonCharacters")[0].options;
	 for(var i=0;i<obj.length;i++){
		 obj[i].selected="selected";
	 }
	 
	 return true;
	 
 } 
</script>
<div class="accountInfo">
	<div >
		<p><s:text name="the_administrator_is_the_current_game_service_area_distribution" />：<s:property value="model.name"/></p>
		<p><s:text name="explanation" />：<s:text name="move_to_the_right_of_the_menu" />，<s:text name="and_select" />，<s:text name="can_be_considered_formally_submitted" />。
		<s:text name="just_move_content_to_the_right" />，<s:text name="select_instead" />，<s:text name="remove_all" />。
		</p>
	</div>
</div>
  	<div class="pageContent">
	    <s:form action="ossServer_updateAddOssServerManagerr" namespace="/admin/base" method="post" theme="simple"  
	    cssClass="pageForm required-validate" 
	    onsubmit="return $(this).valid()&&initAccountSelect()&&dialogSearch(this)">
	    <div layoutH="100">
		    <s:optiontransferselect
			     label="Favourite Cartoons Characters"
			     name="leftSideCartoonCharacters"
			     leftTitle="%{getText('can_be_assigned_personnel')}"
			     rightTitle="%{getText('assigned_personnel')}"
			     list="ossUserServerOffList"
			     listKey="username"
			     listValue="username"
			     multiple="true"
			     headerKey="headerKey"
			     headerValue="--- %{getText('please_select_a')} ---"
			     emptyOption="true"
			     doubleList="ossUserServerOnList"
			     doubleListKey="username"
			     doubleListValue="username"
			     doubleName="rightSideCartoonCharacters"
			     doubleHeaderKey="doubleHeaderKey"
			     doubleHeaderValue="--- %{getText('please_select_a')} ---"
			     doubleEmptyOption="true"
			     doubleMultiple="true"
			 />
		</div>
		<s:hidden name="id"/>
		<s:hidden name="ossRoleID"/>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div></div></li>
			</ul>
		</div>
		</s:form>
    </div>
    
