<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

	
	<script type="text/javascript" language="javascript">
		 function queryMoneyConsume(){
            $("#queryMoneyConsume1").submit();
        }
	</script>
  <s:include value="/admin/template/scriptMessage.jsp"/>
	<div class="pageContent">
	    <table class="list" width="100%">
	    	<thead>
	    	<tr>
	    	   <th><s:text name="the_player" />ID</th>
	    	   <th><s:text name="account_name" /></th>
	    	 <!--  <th><s:text name="the_player_name" /></th> 
	    	   <th>VIP<s:text name="level" /></th>
	    	   <th><s:text name="level" /></th> -->
	    	   <th><s:text name="registration_time" /></th>
	    	   <th><s:text name="recently_the_login" /> (<s:text name="from_today" />)</th>
	    	   <th><s:text name="the_last_login_role" />ID</th>
	    	   <th><s:text name="monster_crystal_quantity" /></th>
	    	   <th><s:text name="whether_or_not" />VIP</th>
	    	   <th>VIP<s:text name="level" /></th>
	    	   <th><s:text name="state" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<tr>
	    	   <td><s:property value="playerClass.playerId"/></td>
	    	   <td><s:property value="playerClass.loginName"/></td>
	    	   <td><s:date format="yyyy-MM-dd HH:mm:ss" name="playerClass.playerCreateTime"/></td>
	    	   <td>
		    	    	<s:date format="yyyy-MM-dd HH:mm:ss" name="playerClass.lastLoginTime"/>
		    	    	<s:if test="@com.jcwx.game.common.DateService@distanceNowDay(playerClass.lastLoginTime)==0">
		    	    		[<span class="color-gr">24<s:text name="hours" /></span>]
		    	    	</s:if>
		    	    	<s:elseif test="@com.jcwx.game.common.DateService@distanceNowDay(playerClass.lastLoginTime)<7">
		    	    		[<span class="color-gr"><s:property value="@com.jcwx.game.common.DateService@distanceNowDay(playerClass.lastLoginTime)"/></span>]
		    	    	</s:elseif>
		    	    	<s:else>
		    	    		[<span class="color-red"><s:property value="@com.jcwx.game.common.DateService@distanceNowDay(playerClass.lastLoginTime)"/></span>]
		    	    	</s:else>
		    	    	<s:if test="@com.jcwx.game.common.DateService@distanceNowDay(playerClass.lastLoginTime)>=30"><span class="color-red"><s:text name="more_than_a_month" /></span></s:if>
		    	    </td>
		       <td><s:property value="playerClass.lastLoginId"/></td>
	    	   <td><s:property value="playerClass.gold"/></td>
	    	    <td>
						 <s:if test="playerVipInfor.vip==0"><span class="color-gr"><s:text name="no" /></span></s:if>
						 <s:else><span class="color-red"><s:text name="is" /></span></s:else>
		    	    </td>
	    	   <td><s:property value="playerVipInfor.vipLevel"/></td>
	    	   <td>
		    	    <s:if test="playerClass.onlineStatus == 1"><s:text name="online" /></s:if>
					<s:else><s:text name="offline" /></s:else>
		    	</td>
	    	   
	    	</tr>
	    	</tbody>
	  </table>
	  
	  
	  <s:iterator value="playerBaseInfors" var="oneRow"  status="offset">
	   <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	   <th><s:text name="the_role_of" /></th>
	    	   <th><s:text name="professional" /></th>
	    	   <th><s:text name="level" /></th>
	    	   <th><s:text name="gift_certificates" /></th>
	    	   <th><s:text name="gold_coins" /></th>
	    	   <th><s:text name="backpack_grid" />(<s:text name="a" />)</th>
	    	   <th><s:text name="warehouse_check" />(<s:text name="a" />)</th>
	    	 <%--   <th><s:text name="hp" /></th>
	    	   <th><s:text name="magic" /></th> --%>
	    	   <th><s:text name="skill_points" /></th>
	    	   <th><s:text name="rune_essence" /></th>
	    	   <th><s:text name="rune_grid_number" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<tr>
	    	  <td>
	    		<a target="dialog" width="1080" height="540" href="<%=basePath%>/admin/base/playerInfo_viewPlayBaseInfo.action?playerBaseId=${oneRow.playerBaseId }" style="color:green"> <s:property value="#oneRow.nickName"/></a>
	    	  </td>
	    	   <td>
		    	    	<s:if test="#oneRow.occupation == 1"><s:text name="a_warrior" /></s:if>
		    	    	<s:if test="#oneRow.occupation == 2"><s:text name="the_mage" /></s:if>
		    	    	<s:if test="#oneRow.occupation == 3"><s:text name="the_archer" /></s:if>
		       </td>
	    	   <td><s:property value="#oneRow.level"/></td>
	    	   <td><s:property value="#oneRow.bindGold"/></td>
	    	   <td><s:property value="#oneRow.silver"/></td>
	    	   <td><s:property value="#oneRow.bagNumber"/></td>
	    	   <td><s:property value="#oneRow.wareNumber"/></td>
	    	 <%--   <td><s:property value="#oneRow.hp"/> </td>
	    	   <td><s:property value="#oneRow.mp"/></td> --%>
	    	   <td><s:property value="#oneRow.techPoint"/></td>
	    	   <td><s:property value="#oneRow.amulet"/></td>
	    	   <td><s:property value="#oneRow.amuletNumber"/></td>
	    	</tr>
	    	</tbody>
	  </table>
	 	</s:iterator>
	 	<s:iterator value="playerArmys" var="oneRow"  status="offset">
	  	 	<table class="list" width="100%">
	  	 	<thead>
	    	<tr>
	    	   <th><s:text name="the_name_of_the" /></th>
	    	   <th><s:text name="level" /></th>
	    	   <th><s:text name="this" /></th>
	    	   <th><s:text name="the_number_of_army" /></th>
	    	   <th><s:text name="ranking" /></th>
	    	   <th><s:text name="bianconeri_construction_degree" /></th>
	    	 <!--    <th><s:text name="resources_occupation_time_bonus" /></th>
	    	   <th><s:text name="prestige_addition" /></th>
	    	   <th><s:text name="addition_of_honor" /></th>--> 
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<tr> 
	    	   <td><s:if test="#oneRow.name!=null"><s:property value="#oneRow.name"/></s:if><s:else><span class="color-red"><s:text name="currently_there_is_no_join" /></span></s:else> </td>
	    	   <td><s:if test="#oneRow.level!=null"><s:property value="#oneRow.level"/></s:if></td>
	    	   <td><s:if test="#oneRow.owner!=null"><s:property value="#oneRow.owner"/></s:if></td>
	    	   <td><s:if test="#oneRow.armyNumber!=null"><s:property value="#oneRow.armyNumber"/></s:if></td>
	    	   <td><s:if test="#oneRow.armyRank!=null"><s:property value="#oneRow.armyRank"/></s:if></td>
	    	   <td><s:if test="#oneRow.develop!=null"><s:property value="#oneRow.develop"/></s:if></td>
	    	</tr>
	    	</tbody>
	  </table>
	  </s:iterator>

	 </div>
<s:if test='#request.actionMsg !=null && #request.actionMsg !="" '>
	<script language="javascript">
		alert("<s:property value="actionMsg" escape="false"/>");
	</script>
</s:if>	

<!-- <s:text name="this" />div<s:text name="don't_show_query_for_button" /> form -->
<div style="display: none;">
	 <form action="<%=basePath %>/admin/pay/queryMoneyConsume.action" method="post" id="queryMoneyConsume1">
	    <input type="hidden" name="userName" value="${player.userName}">
	</form>
</div>
	
