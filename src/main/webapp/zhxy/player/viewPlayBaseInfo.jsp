<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

	<div class="pageContent" layoutH="40">
	<a target="dialog" href="<%=basePath%>/zhxy/player/zhplayerInfo_viewPlayInfo.action?playerId=${ossPlayerBase.playerId}" 
	style="color:green"><s:text name="account" /></a> 
	    <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	   <th><s:text name="role_attribute" /></th>
	    	   <th><s:text name="power" /></th>
	    	  <%--  <th><s:text name="agile" /></th> --%>
	    	   <th><s:text name="wisdom" /></th>
	    	   <th><s:text name="physical" /></th>
	    	   <th><s:text name="hp" /></th>
	    	   <%-- <th><s:text name="magic" /></th> --%>
	    	   <th><s:text name="physical_attacks" /></th>
	    	   <th><s:text name="magic_attack" /> </th>
	    	   <th><s:text name="physical_defense" /></th>
	    	   <th><s:text name="magic_defense" /></th>
	    	   <th><s:text name="crit_value" /></th>
	    	   <th><s:text name="evasion_value" /></th>
	    	   <th><s:text name="a_pitch" /></th>
	    	   <%-- <th><s:text name="kill" /></th>
	    	   <th><s:text name="the_strength" /></th> --%>
	    	   <th><s:text name="hit" /></th>
	    	   <th><s:text name="combat_effectiveness" /></th>
	    	   <th><s:text name="the_current_level_experience" /></th>
	    	   <th><s:text name="the_current_level_has_reached_its_experience" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	<tr>
	    	   <td></td>
	    	   <td><s:property value="ossPlayerAttribute.strength"/></td>
	    	  <%--  <td><s:property value="ossPlayerAttribute.sharp"/></td> --%>
	    	   <td><s:property value="ossPlayerAttribute.clever"/></td>
	    	   <td><s:property value="ossPlayerAttribute.body"/></td>
	    	   <td><s:property value="ossPlayerAttribute.hp"/></td>
	    	   <%-- <td><s:property value="ossPlayerAttribute.mp"/></td> --%>
	    	   <td><s:property value="ossPlayerAttribute.physicalAttack"/></td>
	    	   <td><s:property value="ossPlayerAttribute.magicAttack"/></td>
	    	   <td><s:property value="ossPlayerAttribute.physicalDefend"/></td>
	    	   <td><s:property value="ossPlayerAttribute.magicDefend"/></td>
	    	   <td><s:property value="ossPlayerAttribute.critical"/></td>
	    	   <td><s:property value="ossPlayerAttribute.avoid"/></td>
	    	   <td><s:property value="ossPlayerAttribute.tenacity"/></td>
	    	   <%-- <td><s:property value="ossPlayerAttribute.penetrate"/></td>
	    	   <td><s:property value="ossPlayerAttribute.stem"/></td> --%>
	    	   <td><s:property value="ossPlayerAttribute.hit"/></td>
	    	   <td><s:property value="ossPlayerAttribute.fight"/></td>
	    	   <td><s:property value="ossPlayerAttribute.levelExp"/></td>
	    	   <td><s:property value="ossPlayerAttribute.currentLevelExp"/></td>
	    	</tr>
	    	</tbody>
	  </table>
	  
	     <s:text name="backpack_warehouse_goods" />
	     
	 <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	   <th width="30%"><s:text name="the_name_of_the" /></th>
	    	   <th><s:text name="type" /></th>
	    	   <th><s:text name="location" /></th>
	    	   <th><s:text name="the_number_of" /></th>
	    	   <th><s:text name="item_level" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	 <s:iterator value="ossPlayeBagItemList" var="oneRow"  status="offset">
	    	<tr>
	    	   <td><s:property value="#oneRow.name"/></td>
	    	   <td><s:if test="#oneRow.itemType==1"><s:text name="equipment" /></s:if><s:else><s:text name="the_props" /></s:else> </td>
	    	   <td><s:if test="#oneRow.itemPlace==1"><s:text name="backpack" /></s:if><s:else><s:text name="warehouse" /></s:else> </td>
	    	   <td><s:property value="#oneRow.itemNumber"/></td>
	    	   <td><s:property value="#oneRow.itemLevel"/></td>
	    	</tr>
	    	  </s:iterator>
	    	  </tbody>
	  </table>
	  <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	    <th><s:text name="the_name_of_the" />(<s:text name="role_of_equipment_information" />)</th>
	    	    
	    	    <th><s:text name="enhanced_level" /></th>
	    	    <th><s:text name="embedded_position" />1</th>
	    	    <th><s:text name="embedded_position" />2</th>
	    	    <th><s:text name="embedded_position" />3</th>
	    	    <th><s:text name="embedded_position" />4</th>
	    	    <th><s:text name="power" /></th>
	    	   <th><s:text name="wisdom" /></th>
	    	   <th><s:text name="physical" /></th>
	    	   <th><s:text name="hp" /></th>
	    	   <th><s:text name="physical_attacks" /></th>
	    	   <th><s:text name="magic_attack" /> </th>
	    	   <th><s:text name="physical_defense" /></th>
	    	   <th><s:text name="magic_defense" /></th>
	    	</tr>
	    	 </thead>
	    	<tbody>
	    	 <s:iterator value="ossPlayerEquipList" var="oneRow"  status="offset">
	    	<tr>
	    	   <td><s:property value="#oneRow.name"/></td>
	    	   <td><s:property value="#oneRow.strongLevel"/></td>
	    	   <td><s:property value="#oneRow.position1"/></td>
	    	   <td><s:property value="#oneRow.position2"/></td>
	    	   <td><s:property value="#oneRow.position3"/></td>
	    	   <td><s:property value="#oneRow.position4"/></td>
	    	   <td><s:property value="#oneRow.strength"/></td>
	    	   <td><s:property value="#oneRow.clever"/></td>
	    	    <td><s:property value="#oneRow.body"/></td>
	    	   <td><s:property value="#oneRow.hp"/></td>
	    	    <td><s:property value="#oneRow.physicalAttack"/></td>
	    	   <td><s:property value="#oneRow.magicAttack"/></td>
	    	   <td><s:property value="#oneRow.physicalDefend"/></td>
	    	    <td><s:property value="#oneRow.magicDefend"/></td>
	    	</tr>
	    	  </s:iterator>
	    	   </tbody>
	  </table>
	  
	    <table class="list" width="100%" >
	    	<thead>
	    	<tr>
	    	   
	    	   <th width="230"><s:text name="operating_functions" /> - <s:text name="today's_operating_record" /></th>
	    	   <th width="159"><s:text name="operating_frequency" /></th>
	    	   <th width="230"><s:text name="operating_functions" /></th>
	    	   <th width="159"><s:text name="operating_frequency" /></th>
    	    </tr>
    	     </thead> 
	    	<tbody>
            <tr>
            
   	         <td><s:text name="hang_up_the_total_experience_yesterday" /></td>
   	         <td>${ossPlayerData.yesterdayExp}</td>
   	         <td><s:text name="players_on_the_day_of_the_activity" /></td>
   	         <td>${ossPlayerData.vitality}</td>
    	    </tr>
    	    <tr>
   	         <td><s:text name="hang_up_the_total_experience_today" /></td>
   	         <td>${ossPlayerData.todayExp }</td>
   	          <td><s:text name="the_online_number_of_bonus_award" /></td>
   	         <td>${ossPlayerData.onlineStep}</td>
    	    </tr>
            
            <tr>
   	         <td><s:text name="group_members_fertilizing_times_a_day" /></td>
   	         <td>${ossPlayerData.godtreeFertilizeNum}</td>
   	         <td><s:text name="many_people_copy_number" /></td>
   	         <td>${ossPlayerData.manyCopyNum}</td>
    	    </tr>
           
    	    
    	    <tr>
   	         <td><s:text name="expedition_deep_activities_break_times" /></td>
   	         <td>${ossPlayerData.refreshExpeditionTimes}</td>
   	         <td><s:text name="copy_number_of_resurrection" /></td>
   	         <td>${ossPlayerData.copyRecoverTime}</td>
    	    </tr>
    	    <tr>
   	         <td><s:text name="training_experience_value" /></td>
   	         <td>${ossPlayerData.trainExp}</td>
   	         <td><s:text name="the_arena_rewards_every_day" /></td>
   	         <td>${ossPlayerData.arenaTimes}</td>
    	    </tr>
    	    <tr>
   	         <td>36<s:text name="reset_the_number_of_days_of_heavy_daily" /> </td>
   	         <td>${ossPlayerData.trialsLandTimes}</td>
   	         <td><s:text name="achievement_points" /> </td>
   	         <td>${ossPlayerData.achieveNum}</td>
    	    </tr>
    	    <tr>
   	         <td><s:text name="climb_the_road_to_buy_the_remaining_number" /> </td>
   	         <td>${ossPlayerData.ringBuy}</td>
   	         <td><s:text name="climb_the_road_has_been_free_number" /> </td>
   	         <td>${ossPlayerData.ringFree}</td>
    	    </tr>
    	    
    	    
    	    
    	    <tr>
    	     <td><s:text name="daily_task_completion_times" /> </td>
   	         <td>${ossPlayerData.dailyTaskTime}</td>
   	         <td><s:text name="filling_the_role_of_the_first_to_receive_a_daily_award_logo" />  </td>
   	         <td><s:if test="ossPlayerData.firstChargeRewardPreDay==1"><s:text name="have_received" /></s:if><s:else><s:text name="unclaimed" /></s:else>  </td>
    	    </tr>
    	    </tbody>
	  </table>	  
	  <table class="list" width="100%" >
	    	<thead>
	 		 
	    	<tr>
	    	   <%--  <th><s:text name="the_player_task_information" />-<s:text name="task" />ID</th> --%>
	    	    <th><s:text name="task_name" /> </th>
	    	    <th><s:text name="task_status" /></th>
	    	   <th><s:text name="meet_minimum_level" /></th>
	    	   <th><s:text name="rising_star_success_number" /></th>
	    	   <th><s:text name="rising_star_failure_identification" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
		    	<s:iterator value="ossPlayerTaskList" var="oneRow"  status="offset">
			    	<tr>
			    	   <%-- <td><s:property value="#oneRow.taskId"/></td> --%>
			    	   <td><s:property value="#oneRow.taskName"/></td>
			    	    <td>
								 <s:if test="#oneRow.status==0"><s:text name="do_not_pick_up" /></s:if>
								 <s:elseif test="#oneRow.status==1"><s:text name="don't_answer" /></s:elseif>
								 <s:elseif test="#oneRow.status==2"><s:text name="received" /></s:elseif>
								 <s:elseif test="#oneRow.status==3"><s:text name="has_been_completed" /></s:elseif>
								 <s:else><s:text name="has_been_submitted" /></s:else>
				    	</td>
			    	   <td><s:property value="#oneRow.lowestLevel"/></td>
			    	   <td><s:property value="#oneRow.upgradeTimes"/></td>
			    	    <td>
								 <s:if test="#oneRow.failFlag==0"><s:text name="never_fail" /></s:if>
								 <s:else><s:text name="failed" /></s:else>
				    	</td>
			    	</tr>
		    	</s:iterator>
	    	</tbody>
	  </table>
	 <table class="list" width="100%" >
	    	<thead>
	 		 
	    	<tr>
	    	    <th width="50%"><s:text name="a_copy_of_the_elite_information" /></th>
	    	    <th width="50%"><s:text name="how_many" /></th>
	    	</tr>
	    	</thead>
	    	<tbody>
	    	 <s:iterator value="ossPlayerCopyTimesList" var="oneRow"  status="offset">
	    	<tr>
	    	   <td><s:property value="copyName"/> </td>
	    	    <td><s:property value="times"/></td>
	    	</tr>
	    	  </s:iterator>
	    	  </tbody>
	  </table>
	  
	 </div>
	 
