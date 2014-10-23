<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title><s:text name="batch_send_resources" /></title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>/media/default/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>
	
	<script src="<%=basePath%>/media/default/js/jquery.pager.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/public.js"  type="text/javascript"></script>
	
  </head>
  
  <body>

  	<div id="divLeft">
		<b><s:text name="batch_send_resources" /></b>
		<span class="color-red"><s:property value="msgNotice"/> <s:fielderror/></span>
		<span class="color-gr"><s:if test="successMsg!=''"><s:text name="successful_distributed_resources" />：</s:if> ${successMsg} </span>
	</div>
	<div id="divLeft">
	
	</div>


	<div id="divList">
	<s:form action="addResources_addResources" namespace="/admin/base" method="post" theme="simple">
	      <table width="855" id="row">
	    	<tr>
	    	    <th width="233"><s:text name="conditioned" />(<s:text name="the_radio" />)</th>
	    	    <th ><s:text name="secondary_conditions" />(<s:text name="multi-select" />)</th>
	    	    <th ><s:text name="level_3_conditions" />(<s:text name="the_specific_parameters" />)</th>
	    	</tr>
            <tr>
            	<td><s:text name="all_users" />           	    
           	    <input type="radio" name="userScope" id="radio0" value="0" <s:if test="userScope==0">checked="checked"</s:if> ></td>
                <td width="206">X<s:text name="days_have_login_user_records" />
                <s:checkbox name="second1"/> </td>
                <td width="400"><s:textfield name="scopeDay" size="5"/> </td>
            </tr>
            <tr>
            	<td><s:text name="the_current_online_users" /> <input type="radio" name="userScope" id="radio1" value="1" <s:if test="userScope==1">checked="checked"</s:if> ></td>
                <td><s:text name="bracket_user_selection" />
                <s:checkbox name="second2"/></td>
                <td><s:textfield name="gradeScope1" size="5"/>--<s:textfield name="gradeScope2" size="5"/> </td>
            </tr>
            <tr >
            	<td>&nbsp;</td>
                <td colspan="2">&nbsp;</td>
            </tr>

         </table>
         
       
         <table width="858" id="row">   
            <tr> 
            	<td width="94" rowspan="2" ><s:text name="distribution_of_resources" /></td>
                <td width="61"><s:text name="gold_coins" /></td>
                <td width="64"><s:text name="honor" /></td>
                <td width="68"><s:text name="prestige" /></td>
                <td width="73"><s:text name="silver" /></td>
                <td width="78"><s:text name="food" /></td>
                <td width="388"><s:text name="wood" /></td>
                
            </tr>
            <tr>
              <td><s:textfield name="lmoneys" size="5"/></td>
              <td><s:textfield name="glorys" size="5"/></td>
              <td><s:textfield name="renowns" size="5"/></td>
              <td><s:textfield name="moneys" size="5"/></td>
              <td><s:textfield name="foods" size="5"/></td>
              <td><s:textfield name="woods" size="5"/></td>
              
              
              
            </tr>

	    	
	      </table>
	     <s:submit value=" %{getText('confirm_the_change')}"></s:submit>
	    </s:form>
	 </div>
   <div id="bottomPager"></div>
<s:if test='#request.successMsg !=null && #request.successMsg !="" '>
	<script language="javascript">
		alert("<s:text name="resources_successfully_sent" />：<s:property value="successMsg" escape="false"/>");
	</script>
</s:if>	

<s:if test='#request.msgNotice !=null && #request.msgNotice !="" '>
	<script language="javascript">
		alert("<s:text name="the_error_message" />：<s:property value="msgNotice" escape="false"/>");
	</script>
</s:if>	

<s:if test="hasFieldErrors()">
	<script language="javascript">
		alert("<s:text name="data_type_conversion_error" />，<s:text name="please_enter_the_correct_values" />");
	</script>
</s:if>	
  </body>
</html>
