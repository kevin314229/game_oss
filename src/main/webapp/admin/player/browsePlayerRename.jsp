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
	<s:form rel="pagerForm" action="/admin/base/playerRename_browsePlayerRename.action" namespace="/admin/base" method="post" theme="simple"  cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
		   <div class="searchBar">
		   		<div class="">
					<s:text name="the_sorting" />：<s:select list="#{'DESC': getText('registration_time')+'↓','ASC': getText('registration_time')+'↑'}" label="" listKey="key" listValue="value" name="orderFlag"></s:select>
					<s:text name="the_keyword" />：<s:textfield name="keyword"/>(<s:text name="the_user_account" />、<s:text name="the_player_name" />)
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
	    <table class="list" width="100%" layoutH="90" >
	    	<thead>
	    	<tr>
	    	    <th><s:text name="the_player" />ID</th>
	    	    <th><s:text name="the_user_account" /></th>
	    	    <th><s:text name="the_player_name" />(<s:text name="directly_modifying" />)</th>
	    	    <th><s:text name="registration_time" /></th>
	    	    <th><s:text name="the_last_login_time" /></th>
	    	    <th><s:text name="the_login" />IP</th>
	    	    <th><s:text name="players_level" /></th>
	    	    <th><s:text name="state" /></th>
	    	   
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<s:iterator value="playerList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
						 <s:property value="#oneRow.playerID"/>
		    	    </td>
		    	    <td>
						 <s:property value="#oneRow.userName"/>
		    	    </td>
		    	    <td>
						 <input style="border: none;background-color: #7DC4E6;color:white" id="playerNameInput" type="text" size="15" name="s" value="${oneRow.playerName }" value2="${oneRow.playerName}" value3="${oneRow.playerID}" onchange="changeUserName(this)"/>
						 <img id="img_${oneRow.playerID}" src="<%=basePath%>/media/default/images/titlebg1.gif"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.createTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="#oneRow.lastLoginTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.lastLoginIP"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#oneRow.level"/>
		    	    </td>
		    	    <td> 
		    	    	<s:if test="#oneRow.state==@com.jjl.rzjh.constant.PlayerStateConstant@FRESHMAN"><s:text name="a_novice" /></s:if>
		    	    	<s:if test="#oneRow.state==@com.jjl.rzjh.constant.PlayerStateConstant@NORMAL"><span class="color-gr"><s:text name="normal" /></span></s:if>
		    	    	<s:if test="#oneRow.state==@com.jjl.rzjh.constant.PlayerStateConstant@FREEWAR"><s:text name="avoid_war" /></s:if>
		    	    	<s:if test="#oneRow.state==@com.jjl.rzjh.constant.PlayerStateConstant@STOPUSE"><span class="color-red"><s:text name="closure" /></span></s:if>
		    	    </td>
		    	   
		    	</tr>
	    	</s:iterator>
	    	</tbody>
	    </table>
	    <s:include value="/admin/template/paging.jsp"/>
	 </div>
   <div id="bottomPager"></div>
<s:if test='#request.actionMsg !=null && #request.actionMsg !="" '>
	<script language="javascript">
		alert("<s:property value="actionMsg" escape="false"/>");
	</script>
</s:if>	

	<SCRIPT type="text/javascript">
		//<s:text name="modify_user_name" />
 function changeUserName(text){
 	var id = text.getAttribute("value3")
	var name=text.value;
	if(name==""){
		alert("<s:text name="please_enter_the_correct_name" />!");
    	text.value=text.getAttribute("value2");
		return;
	}else{
		   if(confirm("<s:text name="sure_to_modify?" />？ "+text.getAttribute("value2")+" <s:text name="modified_to" />： "+text.value )){
		   		var image=document.getElementById("img_"+id); 
				image.src = "<%=basePath%>/media/default/images/05043139.gif";
				   $.ajax({
					        type: "POST",
					        url: "<%=basePath%>/admin/base/playerRename_updatePlayerRename.action",
					        cache: false,
					        data:"playerID="+id+"&playerName="+name,
					        success: function(html){
					        	if(html=="success"){
					        		image.src = "<%=basePath%>/media/default/images/success_icon.gif";
					        	}else if(html=="samename"){
					        		image.src = "<%=basePath%>/media/default/images/standard_msg_error.gif";
					        		alert("<s:text name="find_the_nuptial" />！");
					        	}else{
					        		image.src = "<%=basePath%>/media/default/images/standard_msg_error.gif";
					        	}
								
					        }
					        
					        
			  		  });  
				  
		   }else{
		   	  text.value = text.getAttribute("value2");
		   }
	
  }

}	
	</SCRIPT>
