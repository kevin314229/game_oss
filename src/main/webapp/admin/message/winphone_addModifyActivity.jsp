<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	
	<script src="<%=basePath%>/media/default/js/json2.js" type="text/javascript"></script>
	
	<script type="text/javascript" language="javascript">
		function send(form){

			if($("#characteristic").val()==2){
				if($("#type").val()==2||$("#type").val()==3){
					 
				}else{
					alertMsg.warn("<s:text name="get_the_way" />"+" error");
					return false;
				}
			}
			try{
			var array = $(form).find($("input[name*=value]")).length;
			var textArray1 = $.pdialog.getCurrent().find($("input[name*=value]"));
			var textArray2 = $.pdialog.getCurrent().find($("input[name*=number]"));
			var textArray3 = $.pdialog.getCurrent().find($("input[name*=item]"));
			var textArray4 = $.pdialog.getCurrent().find($("input[name*=point]"));
			var textArray5 = $.pdialog.getCurrent().find($("select[name*=occupation]"));
			var sendArray = [];
			for(var i = 0; i < array; i++){
				var sendObj = new Object();
				sendObj.value = textArray1[i].value;
				sendObj.number = textArray2[i].value;
				sendObj.item = textArray3[i].value;
				sendObj.point = textArray4[i].value;
				sendObj.occupation = textArray5[i].value;
				sendArray.push(sendObj);
			}
				$("input[name='activityStr']").attr("value",JSON.stringify(sendArray));
			}catch(e){
				alertMsg.error(e.message);
				return false;
			}
			return true;
		}
		
	</script>
	<s:include value="/admin/template/scriptMessage.jsp"/>
  	<div class="pageContent">
	     <s:form action="modifyActivity_winphone_addModifyActivity" namespace="/admin/message" method="post" theme="simple" onsubmit="return send(this)&&$(this).valid()&&navTabSearch(this);" >
		 	  <s:hidden  name="activityStr"></s:hidden>
		 	   <div class="pageFormContent"  layoutH="60">
		 	   	<div class="panel">
				    		<h1></h1>
					    	<div>
			        <dl>
			    	    <dt><s:text name="the_activity_type" />：</dt>
			    	    <dd>
			    	    	<s:select id="type" name="type" cssClass="required" headerKey="1" headerValue="-- Please Select --" 
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
						'31':'31-'+getText('the_first_charge_daily_activities')}"/>  
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="get_the_way" />：</dt>
			    	    <dd>
			    	    	<s:select id="characteristic" name = "characteristic" cssClass="required" headerKey="1" list="#{ '1':'1-'+getText('click_and_receive_button_to_receive_awards_by_the_player'),'2':'2-'+getText('by_the_server_through_system_mail_sent')}" />
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="get_the_type" />：</dt>
			    	    <dd>
			    	    	<s:select name = "times" cssClass="required" headerKey="1" list="#{ '1':'1-'+getText('you_can_only_receive_a_reward_activity'),'2':'2-'+getText('every_reward_can_be_obtained'),'3':'3-'+getText('repeated_every_day_for_a_reward'),'4':'4-'+getText('repeatable'),'5':'5-'+getText('top-up_amount_can_receive')}" />
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="the_name_of_the_event" />：</dt>
			    	    <dd>
			    	    	<s:textfield name="name" cssClass="required"/>
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="activity_description" />：</dt>
			    	    <dd>
			    	    	<s:textfield name="activityDesc" cssClass="required" />
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="activity_rules" />：</dt>
			    	    <dd>
			    	    	<s:textfield name="rule" cssClass="required" />
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="activities_start_time" />：</dt>
			    	    <dd>
			    	    	<s:textfield id="openDate" name="openDate"  style="width:160px" datefmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" /> 
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="activity_over_time" />：</dt>
			    	    <dd>
			    	    	<s:textfield id="endDate" name="endDate" datefmt="yyyy-MM-dd HH:mm:ss" style="width:160px" 
				cssClass="required date" readonly="true" /> 
			    	    	
			    	    </dd>
			    	</dl>
			    	
			    	<dl>
			    	    <dt>(winphone)指引页面：</dt>
			    	    <dd>
			    	    	<s:select name="winphoneType" cssClass="required" list="#{ 
			    	    	'101':'101-'+getText('个人等级排行'),
			    	    	'102':'102-'+getText('个人战力排行'),
			    	    	'103':'103-'+getText('军团经验排行'),
			    	    	'104':'104-'+getText('巅峰对决')}" /> 
			    	    	 
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt>(winphone)说明：</dt>
			    	    <dd>
			    	    	<s:textarea name="winphoneContent" cssClass="required" /> 
			    	    	
			    	    </dd>
			    	</dl>
			    	</div>
		    	</div>
			    	
			    	<div class="panel">
				    		<h1></h1>
					    	<div>
					    	 <s:text name="reward_items_according_to_the_format_please_fill_in" />:2010101,10#2010201,10#2020101,10  (<s:text name="be_careful_not_to_take_space" />)
					    		type<s:text name="on_behalf_of_the_activity_type" />，1<s:text name="said_the_player_rating" />，2<s:text name="rating" />，3<s:text name="combat_power_rankings" />，4<s:text name="accumulated_top-up_rankings" />，5<s:text name="the_layer_number" />id，6<s:text name="top-up_amount" />，7<s:text name="for_the_first_time_open" />VIP<s:text name="months" />，8<s:text name="open" />/<s:text name="renewal" />VIP<s:text name="months" />，9<s:text name="strengthen_level" />
								10<s:text name="on_behalf_of_the_one-time_top-up_amount" />，11<s:text name="the_cumulative_amount" />，12<s:text name="collect_items_number" />，13<s:text name="synthetic_gem_grade" />，14<s:text name="the_cumulative_gain_gold_runes" />，15<s:text name="corps_level" />，16<s:text name="the_number_of_army" />，17VIP<s:text name="level" />，18<s:text name="constellation_level" />
								19<s:text name="active_value" />，20<s:text name="on_behalf_of_the_number_of_kill_the_monster" />，21<s:text name="hit_a_number_of_eggs" />，22<s:text name="kill_monster_number_specified" />
								characteristic<s:text name="on_behalf_of_the_receiving_way" />,1<s:text name="on_behalf_of_the_players_click_the_receive_button" />,2<s:text name="on_behalf_of_the_server_through_system_mail_sent" />
								times<s:text name="on_behalf_of_the_receiving_type" />,1<s:text name="on_behalf_of_the_activity_can_only_get_a_reward" />；2<s:text name="on_behalf_of_each_reward_can_be_obtained" />；3<s:text name="on_behalf_of_repeatable_every_day_for_a_reward" /> 4<s:text name="on_behalf_of_repeatable" />
		    					<br>
		    					<p style="color:red;"> <s:text name="note" />：<s:text name="only" /> <s:text name="top_level" />， <s:text name="combat_ranking" /> <s:text name="send_by_mail" /> 。</p>
		    				</div>
		    	</div>
		    	<!-- <s:text name="add_form" /> -->
		    	
			    	<div class="panel">
				    		<h1></h1>
					    	<div>
						    	<table id="table1" class="list itemDetail" addButton="<s:text name="new_items" />" width="100%">
						    	<thead>
						    	<tr >
						    	<th type="enum" enumUrl="<%=basePath%>/admin/message/modifyActivity_select.action" size="12" name="occupation#index#" >
						    		<s:text name="professional" />
						    	</th>
						    	<th type="text"  fieldClass="required" name="value#index#">
						    		<s:text name="activity_conditions" />
						    	</th>
						    	<th type="text" name="number#index#" >
						    		<s:text name="item_number" />
						    	</th>
						    	<th type="text" name="item#index#" size="38"  >
						    		<s:text name="reward_items" />
						    	</th>
						    	<th type="text" name="point#index#" size="38" >
						    		<s:text name="prompt" />
						    	</th>
						    	<th type="del" width="60"><s:text name="operating" /></th>
						    	</tr>
						    	
						    	</thead>
						    	<tbody>
						    	</tbody>
						    	</table>
					    	</div>
			    	</div>
		    	</div>
		    	<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_to_add"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
					</ul>
				</div>
	    </s:form>
	    </div>
