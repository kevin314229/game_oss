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
			//var textArray2 = $.pdialog.getCurrent().find($("input[name*=number]"));
			//var textArray3 = $.pdialog.getCurrent().find($("input[name*=item]"));
			var textArray4 = $.pdialog.getCurrent().find($("input[name*=point]"));
			var textArray5 = $.pdialog.getCurrent().find($("select[name*=occupation]"));
			var sendArray = [];
			var num=0;
			for(var i = 0; i < array; i++){
				var sendObj = new Object();
				sendObj.value = textArray1[i].value;
			//	sendObj.number = textArray2[i].value;
				var textArray3_1=$.pdialog.getCurrent().find($("input[name*=btn_itemId"+num+"]"));
				var textArray3_2 = $.pdialog.getCurrent().find($("input[name*=btn_itemNum"+num+"]"));
				 
				while(textArray3_1.length==0){
					num++;
					textArray3_1=$.pdialog.getCurrent().find($("input[name*=btn_itemId"+num+"]"));
					textArray3_2 = $.pdialog.getCurrent().find($("input[name*=btn_itemNum"+num+"]"));
					
				}
				num++;
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
				sendObj.item=item;
				sendObj.number=0;
				sendObj.point = textArray4[i].value;
				sendObj.occupation = textArray5[i].value;
				sendArray.push(sendObj);
			}
				$("input[name='activityStr']").attr("value",JSON.stringify(sendArray));
				alert(JSON.stringify(sendArray));
			//	return false;
			}catch(e){
				alertMsg.error(e.message);
				return false;
			}
			return true;
		}
		
		function sendItemList(form){

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
			var textArray2 = $.pdialog.getCurrent().find($("input[name*=btn_number]"));
			var textArray3 = $.pdialog.getCurrent().find($("input[name*=btm_item]"));
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
		function addItem(index){
		    $.pdialog.open("<%=basePath%>/zhxy/message/modifyActivity_searchPropertyBaseList.action?index="+index, "99999", "<s:text name="synchronous" />", {
				width : 760,
				height : 720
			});
		}

		var indexNum = 1;
		function addLine(id,tdId,index){
		 //	var name =	document.getElementsByName("itemName00")[0].value;
			var td = document.getElementById(tdId);
			var input = document.createElement("input");
			input.name='btn_itemId'+index+indexNum;
			input.type="hidden";
			
			var input2 = document.createElement("input");
			input2.name='btn_itemName'+index+indexNum;
			input2.type="text";
			
			input2.readOnly=true;
			//input2.cssClass="required";
			input2.className="textInput required";
			var input3 = document.createElement("input");
			input3.name='btn_itemNum'+index+indexNum;
			input3.type="text";
			input3.className="textInput";
			input3.style.width="80px";
			var html ="<input type='hidden' name='itemId"+index+indexNum+"'><input type='text' value='' name='itemName"+index+indexNum+"' class='textInput'>"+
			" <input type='text' class='textInput' style='width:80px' name='itemNum"+index+indexNum+"' />"+
			"	<a class='btnLook' id='itemId"+index+indexNum+"' href='javascript:addItem(&quot;"+index+indexNum+"&quot;)'; ><span>查找带回</span></a> "+
			"<a id='addDel"+index+indexNum+"' href='javascript:void(0)' class='btnDel2' onclick='delLine('item"+index+indexNum+"','td_btn"+index+indexNum+"')'>删除</a><br>";
			//alert(td.innerHTML)
			//td.innerHTML+=html;
		 	td.appendChild(input);
			td.appendChild(input2);
			td.appendChild(input3);
			var a1 = document.createElement("a");
			a1.name='searchId'+index+indexNum;
			a1.className="btnLook";
			a1.id="searchId"+index+indexNum;
			a1.href='javascript:addItem("'+index+indexNum+'");';
			var span = document.createElement("span");
			span.innerHTML="查找带回";
			a1.appendChild(span);
			var a2 = document.createElement("a");
			a2.name='delId'+index+indexNum;
			a2.className="btnDel2";
			a2.id='delId'+index+indexNum;
			a2.onclick="delLine('item"+index+indexNum+"','td_btn"+index+indexNum+"')";
			a2.href='javascript:delLine("'+index+indexNum+'");';
			a2.innerHTML="删除";
			
			var br = document.createElement("br");
			br.id="br"+index+indexNum;
			td.appendChild(a1);
			td.appendChild(a2);
			td.appendChild(br);  
			indexNum++;
		}
		function delLine(index){
			 var input1=$("input[name='btn_itemId"+index+"']");
			 var input2=$("input[name='btn_itemName"+index+"']");
			 var input3=$("input[name='btn_itemNum"+index+"']");
			 var a1=$("a[name='searchId"+index+"']");
			 var a2=$("a[name='delId"+index+"']");
			 input1.remove();
			 input2.remove();
			 input3.remove();
			 $("#searchId"+index).remove();
			 $("#delId"+index).remove();
			// a1.remove();
			 //a2.remove();
			 $("#br"+index).remove();
		}
	</script>
	<s:include value="/admin/template/scriptMessage.jsp"/>
  	<div class="pageContent">
	     <s:form action="modifyActivity_addModifyActivity" namespace="/zhxy/message" method="post" theme="simple" onsubmit="return send(this)&&$(this).valid()&&navTabSearch(this);" >
		 	  <s:hidden  name="activityStr"></s:hidden>
		 	   <div class="pageFormContent"  layoutH="60">
			        
			        <dl>
			    	    <dt>平台标志：</dt>
			    	    <dd>
						
						<select id="carrierOperator" name="carrierOperator" " >
						<option value="">--全部--</option>	 
		    	    	<s:iterator value="ptList" var="oneRow">
		    	    	<option value="<s:property value='ptCode'/>"><s:property value='#oneRow.ptName'/> </option>
		    	    	</s:iterator>
		    	    	</select>
			    	    </dd>
			    	</dl>
			        <dl>
			    	    <dt><s:text name="the_activity_type" />：</dt>
			    	    <dd>
			    	    	<%-- <s:select id="type" name="type" cssClass="required" headerKey="1" headerValue="-- Please Select --" 
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
						'34':'34-'+'<s:text name="cumulative_landing_award" />'}"/>  --%> 
						
						<select id="type" name="type" " >
							<option value="1">-- Please Select -- </option>
		    	    	<s:iterator value="reustList" var="oneRow">
		    	    	<option value="<s:property value='#oneRow.type'/>"><s:property value='#oneRow.type'/>-<s:property value="#oneRow.name"/> </option>
		    	    	</s:iterator>
		    	    	</select>
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="get_the_way" />：</dt>
			    	    <dd>	<%-- 		    	    	,'2':'2-'+getText('by_the_server_through_system_mail_sent') --%>
			    	    	<s:select id="characteristic" name = "characteristic" cssClass="required" headerKey="1" list="#{ '1':'1-'+getText('click_and_receive_button_to_receive_awards_by_the_player')

			    	    	}" />
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
		    	    <dt>排序：</dt>
		    	    <dd>
		    	   		<s:textfield name="rank" style="width:160px"  cssClass="required"/> 
		    	    </dd>
		    		</dl>
			    	<dl>
			    	    <dt><s:text name="activities_start_time" />：</dt>
			    	    <dd>
			    	    	<s:textfield id="openDate" name="openDate"  style="width:160px" datefmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" /> 
			    	    	<!--  <input class="required date textInput readonly" type="text" readonly="true" datefmt="yyyy-MM-dd HH:mm:ss" name="date10"></input> --> 
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="activity_over_time" />：</dt>
			    	    <dd>
			    	    	<%-- <s:textfield id="endDate"  name="endDate" >
			    	    	</s:textfield> --%>
			    	    	<s:textfield id="endDate" name="endDate" datefmt="yyyy-MM-dd HH:mm:ss" style="width:160px" 
				cssClass="required date" readonly="true" /> 
			    	    	
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="end_time_award" />：</dt>
			    	    <dd>
			    	    	<%-- <s:textfield id="endDate"  name="endDate" >
			    	    	</s:textfield> --%>
			    	    	<s:textfield id="rewardOverDate" name="rewardOverDate" datefmt="yyyy-MM-dd HH:mm:ss" style="width:160px" 
				cssClass="required date" readonly="true" /> 
			    	    	
			    	    </dd>
			    	</dl>
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
						    	<th type="enum" enumUrl="<%=basePath%>/zhxy/message/modifyActivity_select.action" size="12" name="occupation#index#" >
						    		<s:text name="professional" />
						    	</th>
						    	<th type="text"  fieldClass="required" name="value#index#">
						    		<s:text name="activity_conditions" />
						    	</th>
						    	<%-- <th type="text" name="number#index#" >
						    		<s:text name="item_number" />
						    	</th> --%>
						    	
						    	<th type="btn" lookupGroup="btn_itemId#index#0" searchId="searchId#index#" name="btn_itemName#index#" addId="addId#index#" delId="delId#index#" numId="btn_itemNum#index#"
						    	 size="38" btnAdd="addLine('item#index#0','td_btn#index#','#index#')" btnDel="delLine('#index#0')" btnSearch="javascript:addItem('#index#0')" >
						    	 
						    	<%--   btnSearch="<%=basePath%>/admin/message/sendStage_searchIndex.action?optType=2&type=optV#index#" --%>
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
