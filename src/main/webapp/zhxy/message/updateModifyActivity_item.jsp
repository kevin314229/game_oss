<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script>
function modifyItem(index){
    $.pdialog.open("<%=basePath%>/zhxy/message/modifyActivity_searchPropertyBaseList.action?index="+index, "99999", "<s:text name="synchronous" />", {
		width : 760,
		height : 720
	});
}

function sendForm(form){
	var arr =<s:property value="ossActivityDetails.size"/>;
	for(var i = 0; i < arr; i++){
		var textArray3_1 = $.pdialog.getCurrent().find($("input[name*=btn_itemId"+i+"]"));
		var textArray3_2 = $.pdialog.getCurrent().find($("input[name*=itemNum"+i+"]"));
	//	alert(textArray3_1.length+"dd")
		var item="";
		for(var j=0;j<textArray3_1.length;j++){
			var itemValue=textArray3_1[j].value;
		//	alert(itemValue)
			if(j==0){
				if(itemValue!=null&&itemValue!=''&&itemValue!=undefined){
				//alert("0"+j)
					item += textArray3_1[j].value+","+textArray3_2[j].value;
				}
			}else{
				if(itemValue!=null&&itemValue!=''&&itemValue!=undefined){
				 //	alert("01"+j)
					item += "#"+textArray3_1[j].value+","+textArray3_2[j].value;
				}
			}
		}
		$("input[name='ossActivityDetails["+i+"].item']").attr("value",item);
		 
	}
		//$("input[name='activityStr']").attr("value",JSON.stringify(sendArray));
		return true;
}
</script>
<s:include value="/admin/template/scriptMessage.jsp"/>
  
  	<div class="pageContent">
	   <s:form action="modifyActivity_updateModifyActivity" namespace="/zhxy/message" method="post" theme="simple"  
	     cssClass="pageForm required-validate" onsubmit="return sendForm(this)&&$(this).valid()&&navTabSearch(this)">
	     		<div class="pageFormContent nowrap" layoutH="60">
		      <dl>
		    	    <dt>平台标志：</dt>
		    	    <dd>
						<select id="type" name="ossActivity.carrierOperator"  >
						<option value="">--全部--</option>	 
		    	    	<s:iterator value="ptList" var="oneRow">
		    	    	<option value="<s:property value='#oneRow.ptCode'/>"
		    	    	<s:if test="#oneRow.ptCode==ossActivity.carrierOperator"> selected</s:if>
		    	    	><s:property value="#oneRow.ptName"/> </option>
		    	    	</s:iterator>
		    	    	</select>
		    	    </dd>
		    	</dl>
		        <dl>
		    	    <dt><s:text name="the_activity_type" />：</dt>
		    	    <dd>
		    	    	<%-- <s:textfield name="ossActivity.type" size="120" cssClass="required"/>
		    	    	 --%>
		    	    	<%--  <s:select name="ossActivity.type" cssClass="required" headerKey="1" headerValue="-- Please Select --" 
			    	    	list="#{'1':'1-'+getText('said_the_player_rating'),
			    	    	'2':'2-'+getText('rating'),
			    	    	'3':'3-'+getText('combat_power_rankings'),
			    	    	'6':'6-'+getText('top-up_amount'),
			    	    	'9':'9-'+getText('strengthen_level'),
						'11':'11-'+getText('the_cumulative_amount'),
						'13':'13-'+getText('synthetic_gem_grade'),
						'14':'14-'+getText('the_cumulative_gain_gold_runes'),
						'15':'15-'+getText('corps_level'),
						'16':'16-'+getText('the_number_of_army'),
						'17':'17-'+'VIP'+getText('level'),
						'18':'18-'+getText('constellation_level'),
						'19':'19-'+getText('active_value'),
						'20':'20-'+getText('on_behalf_of_the_number_of_kill_the_monster'),
						'22':'22-'+getText('kill_monster_number_specified'),
						'23':'23-'+getText('the_old_player_feedback'),
						'24':'24-'+getText('accumulated_top-up_every_day'),
						'30':'30-'+getText('long-term_accumulated_top-up'),
						'32':'32-'+'<s:text name="climb_road_ranking" />',
						'33':'33-'+'<s:text name="log_reward_every_day" />',
						'34':'34-'+'<s:text name="cumulative_landing_award" />'}"/> --%>
						<select id="type" name="ossActivity.type"  >
							<option value="1">-- Please Select -- </option>
		    	    	<s:iterator value="reustList" var="oneRow">
		    	    	<option value="<s:property value='#oneRow.type'/>"
		    	    	<s:if test="#oneRow.type==ossActivity.type"> selected</s:if>
		    	    	><s:property value='#oneRow.type'/>-<s:property value="#oneRow.name"/> </option>
		    	    	</s:iterator>
		    	    	</select>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="get_the_way" />：</dt>
		    	    <dd>
		    	    	<%-- <s:textfield name="ossActivity.characteristic" cssClass="required" size="120"/>
		    	    	 --%>
		    	    	 	<%-- 		    	    	,'2':'2-'+getText('by_the_server_through_system_mail_sent') --%>
			    	     <s:select name = "ossActivity.characteristic" cssClass="required" headerKey="1" list="#{ '1':'1-'+getText('click_and_receive_button_to_receive_awards_by_the_player')}" />
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
		    	    	${activityDesc}<s:textfield name="ossActivity.activityDesc"  size="120" cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="activity_rules" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="ossActivity.rule" size="120"  cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt>排序：</dt>
		    	    <dd>
		    	   <s:textfield name="ossActivity.rank" style="width:160px"  cssClass="required"/> 
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
		    	    <dt><s:text name="end_time_award" />：</dt>
		    	    <dd>
		    	    <s:textfield id="rewardOverDate" name="rewardOverDate" datefmt="yyyy-MM-dd HH:mm:ss" style="width:160px" 
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
					    	<option value="1" ${oneRow.occupation==1?'selected':''}><s:text name="monkey" /></option>
					    	<option value="2" ${oneRow.occupation==2?'selected':''}><s:text name="gods" /></option>
					    	<option value="3" ${oneRow.occupation==3?'selected':''}><s:text name="xuannv" /></option>
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
		    	<%-- <dl>
		    	   		  <dt><s:text name="item_number" />: </dt>
			    	      <dd>
			    	    	  <input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].number" value="${oneRow.number }" size="120" >
			    	   	</dd>
		    	</dl> --%>
		    <%-- 	<dl>
			    	 	<dt><s:text name="reward_items" />: </dt>
			    	 	 <dd>
			    	 		 <input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].item" value="${oneRow.item }" size="120" >
		    	   		 </dd>
		    	 </dl> --%>
		    	 <input type="hidden" name="ossActivityDetails[<s:property value="#offset.index"/>].item" value="${oneRow.item }">
		    	 <s:iterator value="#oneRow.itemList" id="oneRow2"  status="offset2">
		    	 <dl id="detailNum<s:property value='#offset.index'/>">
			    	 	<dt><s:if test="#offset2.index==0"><s:text name="reward_items" />: </s:if>
			    	 	<s:else>&nbsp;</s:else></dt>
			    	 	 <dd style="width:700px">
			    	 	 <input type="hidden" name="btn_itemId<s:property value='#offset.index'/><s:property value='#offset2.index'/>" value="${oneRow2.itemId }">
			    	 	 	
			    	 	    <input type="text" name="btn_itemName<s:property value='#offset.index'/><s:property value='#offset2.index'/>" value="<s:property value='sendBaseMap[#oneRow2.itemId]'/>" size="120" >
			    	 	   <a class="btnLook" id="searchId00" href="javascript:modifyItem('<s:property value="#offset.index"/><s:property value="#offset2.index"/>')"><span>查找带回</span></a>
			    	 	   <input type="text" name="itemNum<s:property value='#offset.index'/><s:property value='#offset2.index'/>" value="${oneRow2.num }" size="120" >
		    	   		 </dd>
		    	 </dl>
		    	 </s:iterator>
		    	<dl>
			    	 	<dt><s:text name="prompt" />: </dt>
			    	 	 <dd>
			    	 	  <input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].point" value="${oneRow.point }" size="120" >
		    	   		 </dd>
		    	 </dl>
		    	<%-- <input type="text" name="ossActivityDetails[<s:property value="#offset.index"/>].itemList.size" value="${oneRow.itemList }" size="120" > --%>

		    	<%-- 11 ${ossActivityDetails } --%>
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

    
