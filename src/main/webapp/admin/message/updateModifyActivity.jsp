<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
  
  	<div class="pageContent">
	   <s:form action="modifyActivity_updateModifyActivity" namespace="/admin/message" method="post" theme="simple"  
	     cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
	     		<div class="pageFormContent nowrap" layoutH="60">
		        <dl>
		    	    <dt><s:text name="the_activity_type" />：</dt>
		    	    <dd>
		    	    	<%-- <s:textfield name="ossActivity.type" size="120" cssClass="required"/>
		    	    	 --%>
		    	    	 <s:select name="ossActivity.type" cssClass="required" headerKey="1" headerValue="-- Please Select --" 
			    	    	list="#{'1':'1-'+getText('said_the_player_rating'),
			    	    	'2':'2-'+getText('rating'),
			    	    	'3':'3-'+getText('combat_power_rankings'),
			    	    	'4':'4-'+getText('accumulated_top-up_rankings'),
			    	    	'5':'5-'+getText('the_layer_number')+'id',
			    	    	'6':'6-'+getText('top-up_amount'),
			    	    	'7':'7-'+getText('for_the_first_time_open')+'VIP'+getText('months'),	
			    	    	'8':'8-'+getText('open')+'/:'+getText('renewal')+'VIP:'+getText('months'),
			    	    	'9':'9-'+getText('strengthen_level'),
						'10':'10-'+getText('on_behalf_of_the_one-time_top-up_amount'),
						'11':'11-'+getText('the_cumulative_amount'),
						'12':'12-'+getText('collect_items_number'),
						'13':'13-'+getText('synthetic_gem_grade'),
						'14':'14-'+getText('the_cumulative_gain_gold_runes'),
						'15':'15-'+getText('corps_level'),
						'16':'16-'+getText('the_number_of_army'),
						'17':'17-'+'VIP'+getText('level'),
						'18':'18-'+getText('constellation_level'),
						'19':'19-'+getText('active_value'),
						'20':'20-'+getText('on_behalf_of_the_number_of_kill_the_monster'),
						'21':'21-'+getText('hit_a_number_of_eggs'),
						'22':'22-'+getText('kill_monster_number_specified'),
						'23':'23-'+getText('the_old_player_feedback'),
						'24':'24-'+getText('accumulated_top-up_every_day'),
						'25':'25-'+getText('the_arena_in_a_row'),
						'26':'26-'+getText('the_arena_games'),
						'27':'27-'+getText('rank'),
						'28':'28-'+getText('try_to_practice_struck')+'BOSS',
						'29':'29-'+getText('collect_items_for'),
						'30':'30-'+getText('long-term_accumulated_top-up'),
						'31':'31-'+getText('the_first_charge_daily_activities'),
						'32':'32-'+getText('climb_road_ranking'),
						'33':'33-'+getText('log_reward_every_day'),
						'34':'34-'+getText('cumulative_landing_award')}"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="get_the_way" />：</dt>
		    	    <dd>
		    	    	<%-- <s:textfield name="ossActivity.characteristic" cssClass="required" size="120"/>
		    	    	 --%>
			    	     <s:select name = "ossActivity.characteristic" cssClass="required" headerKey="1" list="#{ '1':'1-'+getText('click_and_receive_button_to_receive_awards_by_the_player'),'2':'2-'+getText('by_the_server_through_system_mail_sent')}" />
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="get_the_type" />：</dt>
		    	    <dd>
		    	    	<%-- <s:textfield name="ossActivity.times" size="120" cssClass="required"/>
		    	    	 --%>
			    	    <s:select name = "ossActivity.times" cssClass="required" headerKey="1" list="#{ '1':'1-'+getText('you_can_only_receive_a_reward_activity'),'2':'2-'+getText('every_reward_can_be_obtained'),'3':'3-'+getText('repeated_every_day_for_a_reward'),'4':'4-'+getText('repeatable'),'5':'5-'+getText('top-up_amount_can_receive')}" />
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_name_of_the_event" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="ossActivity.name" size="120" cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="activity_description" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="ossActivity.activityDesc"  size="120" cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="activity_rules" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="ossActivity.rule" size="120"  cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="activities_start_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="openDate" name="openDate" datefmt="yyyy-MM-dd HH:mm:ss" style="width:160px" 
			cssClass="required date" readonly="true" /> 
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="activity_over_time" />：</dt>
		    	    <dd>
		    	    <s:textfield id="endDate" name="endDate" datefmt="yyyy-MM-dd HH:mm:ss" style="width:160px" 
			cssClass="required date" readonly="true"/> 
			
		    	    </dd>
		    	</dl>
		    	<dl>
		    		<dt><font color="green"><s:text name="activities_related_to_conditions" /></font></dt>
		    		<dd><font color="green"> <s:text name="reward_items_according_to_the_format_please_fill_in" />:2010101,10#2010201,10#2020101,10
		    		<br />
		    		type<s:text name="on_behalf_of_the_activity_type" />，1<s:text name="said_the_player_rating" />，2<s:text name="rating" />，3<s:text name="combat_power_rankings" />，4<s:text name="accumulated_top-up_rankings" />，5<s:text name="the_layer_number" />id，6<s:text name="top-up_amount" />，7<s:text name="for_the_first_time_open" />VIP<s:text name="months" />，8<s:text name="open" />/<s:text name="renewal" />VIP<s:text name="months" />，9<s:text name="strengthen_level" />
					10<s:text name="on_behalf_of_the_one-time_top-up_amount" />，11<s:text name="the_cumulative_amount" />，12<s:text name="collect_items_number" />，13<s:text name="synthetic_gem_grade" />，14<s:text name="the_cumulative_gain_gold_runes" />，15<s:text name="corps_level" />，16<s:text name="the_number_of_army" />，17VIP<s:text name="level" />，18<s:text name="constellation_level" />
					19<s:text name="active_value" />，20<s:text name="on_behalf_of_the_number_of_kill_the_monster" />，21<s:text name="hit_a_number_of_eggs" />，22<s:text name="kill_monster_number_specified" />,23<s:text name="the_old_player_feedback" />,24<s:text name="accumulated_top-up_every_day" />'
					<br />
					characteristic<s:text name="on_behalf_of_the_receiving_way" />,1<s:text name="on_behalf_of_the_players_click_the_receive_button" />,2<s:text name="on_behalf_of_the_server_through_system_mail_sent" />
					<br />
					times<s:text name="on_behalf_of_the_receiving_type" />,1<s:text name="on_behalf_of_the_activity_can_only_get_a_reward" />；2<s:text name="on_behalf_of_each_reward_can_be_obtained" />；3<s:text name="on_behalf_of_repeatable_every_day_for_a_reward" /> 4<s:text name="on_behalf_of_repeatable" />:5<s:text name="top-up_amount_can_receive" /></font>
					<br>
		    		<p style="color:red;"> <s:text name="note" />：<s:text name="only" /> <s:text name="top_level" />， <s:text name="combat_ranking" /> <s:text name="send_by_mail" /> 。</p>
					</dd>
		    		
		    	</dl>
		    	<s:iterator value="ossActivityDetails" id="oneRow"  status="offset">
		    	<dl>
		    	  		  <dt><s:text name="conditions" />ID: </dt>
		    	  		  <dd>
		    	   			 <input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].id" value="${oneRow.id}" size="120"  readonly>
		    	   		  </dd>
		    	 </dl>
		    	<dl>
		    	  		  <dt><s:text name="activity" />ID: </dt>
		    	  		  <dd>
		    	  		  	<input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].activityId" value="${oneRow.activityId }" size="120"  readonly>
		    	   		  </dd>
		    	</dl>
		    	<dl>
		    	  		  <dt><s:text name="professional" />: </dt>
		    	  		  <dd>
		    	  		  <select id="occupation1" name="ossActivityDetails[<s:property value="#offset.index"/>].occupation" >
					    	<option value="0" ${oneRow.occupation==0?'selected':''} ><s:text name="all" /></option>
					    	<option value="1" ${oneRow.occupation==1?'selected':''}><s:text name="a_warrior" /></option>
					    	<option value="2" ${oneRow.occupation==2?'selected':''}><s:text name="the_mage" /></option>
					    	<option value="3" ${oneRow.occupation==3?'selected':''}><s:text name="striker" /></option>
					    </select>
		    	  		  
		    	   			 	<%-- <input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].occupation" value="${oneRow.occupation}" size="120" > --%>
		    	   		  </dd>
		    	</dl>
		    	<dl>
		    	  		  <dt><s:text name="activity_conditions" />: </dt>
		    	  		  <dd>
		    	   			 	<input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].value" value="${oneRow.value }" size="120" >
		    	   		  </dd>
		    	</dl>
		    	<dl>
		    	   		  <dt><s:text name="item_number" />: </dt>
			    	      <dd>
			    	    	  <input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].number" value="${oneRow.number }" size="120" >
			    	   	</dd>
		    	</dl>
		    	<dl>
			    	 	<dt><s:text name="reward_items" />: </dt>
			    	 	 <dd>
			    	 		 <input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].item" value="${oneRow.item }" size="120" >
		    	   		 </dd>
		    	 </dl>
		    	<dl>
			    	 	<dt><s:text name="prompt" />: </dt>
			    	 	 <dd>
			    	 	  <input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].point" value="${oneRow.point }" size="120" >
		    	   		 </dd>
		    	 </dl>
		    	<dl>
		    		<dt><font color="green"><s:text name="activities_related_to_conditions" /></font></dt>
		    		<dd><font color="green"><s:text name="pay_attention_to" />--- <s:text name="reward_items_according_to_the_format_please_fill_in" />:2010101,10#2010201,10#2020101,10</font></dd>
		    	</dl>
		    	</s:iterator>
		    	 </div>
				<div class="formBar">
					<ul>
						<s:hidden name="id"></s:hidden>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_the_change" /></button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="reset"><s:text name="reset" /></button></div></div></li>
					</ul>
				</div>
				
			
	    </s:form>
	
	    </div>

    
