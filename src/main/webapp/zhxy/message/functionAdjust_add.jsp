<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script>
		$(function(){
			/**
			* json<s:text name="the_default_editor_is_called" />
			*/
			$("#jsonLook").click(function(){
				$("#jsonLook").data("param"
					,
					$("textarea[name='ossFunctionAdjust.functionNub']").val()
				);
			
			
				$("#jsonLook").attr("href",
					"<%=basePath%>/admin/base/jsoneditor.action?callBackObjName="+$("#jsonLook").attr("callBackObjName")
				);
			});
			
		});
		
		function changeDlBlock(value){
			var dl =$("#desc_dl").children("dd");
			for(var i=0;i<dl.length;i++){
				if(dl[i].id=="desc"+value){
					dl[i].style.display="block";
				}else{
					dl[i].style.display="none";
				}
			}
		}
</script>

<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent">
	     <s:form action="zhfunctionAdjust_doAdd" namespace="/admin/message" method="post" theme="simple" 
 			cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
 			 <div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="the_function_point" />：</dt>
		    	    <dd>
		    	    	<select id="functionType" name="ossFunctionAdjust.functionType" onchange="changeDlBlock(this.value)" >
		    	    	<s:iterator value="reustList" var="oneRow">
		    	    	<option value="<s:property value='#oneRow.type'/>"><s:property value='#oneRow.type'/>-<s:property value="#oneRow.name"/> </option>
		    	    	</s:iterator>
		    	    		<%-- <option value="1">1-<s:text name="refresh_the_mystery_shop" /> </option>
		    	    		<option value="2">2-<s:text name="rune_master_invited" /> </option>
		    	    		<option value="3">3-<s:text name="gold_multiples" /> </option>
		    	    		<option value="4">4-<s:text name="runic_writing_discount" /></option>
		    	    		<option value="5">5-<s:text name="main_invite" /> </option>
							<option value="6">6-<s:text name="are_invited_to_join" /></option>
							<option value="7">7-<s:text name="invited_rating" /> </option>
							<option value="8">8-<s:text name="number_of_invited" /> </option>
							<option value="9">9-<s:text name="probability_of_cure_park_seed" /> </option>
							<option value="10">10-<s:text name="elixir_garden_seed_prices" /> </option>
							<option value="11">11-<s:text name="refresh_consumption_elixir_park" /> </option>
							<option value="12">12-<s:text name="online_awards" /> </option>
							<option value="13">13-<s:text name="the_first_charge_active_configuration" /> </option>
							<option value="14">14-<s:text name="the_first_charge_reward" /> </option>
							<option value="15">15-<s:text name="daily_recharge_awards" /> </option>
							<option value="16">16-vip<s:text name="packs" /></option>
							<option value="17">17-<s:text name="integral_wall_configuration" /> (<s:text name="hong_kong,_macao_and_taiwan" />)</option> --%>
		    	    	</select>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="function_point_name" />：</dt>
		    	    <dd>
		    	    	<s:textfield size="40" name="ossFunctionAdjust.functionName"  cssClass="required"></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_function_point_change_value" />：</dt>
		    	    <dd>
		    	    	<!-- class jsonValid -->
		    	    	<s:textarea cols="40" rows="6"  name="ossFunctionAdjust.functionNub"  cssClass="required" ></s:textarea>
		    	    	<a class="btnLook" id="jsonLook" href="javascript:void(0)" width="830" height="510" callbackObjName="functionNub"
		    	    	lookupgroup="ossFunctionAdjust" ref="JSONeditor">JSONeditor</a>
		    	    	<span class="info"><s:text name="you_can_enter_income" />,<s:text name="you_can_also_control_the_input" />。<s:text name="when_parsing" />JSON<s:text name="incorrect_format" />，<s:text name="or_when_you_do_not_know_how_to_write" />。
		    	    	<s:text name="through_the_control_input" />。</span>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="enabled_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="startTime" name="ossFunctionAdjust.startTime"  style="width:160px" 
								dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" >
							<s:param name="value"><s:date name="ossFunctionAdjust.startTime" format="yyyy-MM-dd HH:mm:ss" /></s:param>
						</s:textfield> 
			
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="function_over_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="endTime" name="ossFunctionAdjust.endTime"  style="width:160px" 
			dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" /> 
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="describe" />：</dt>
		    	    <dd>
		    	    	<s:textarea id="describe" cols="40" rows="6"  name="ossFunctionAdjust.describe" >
		    	    	</s:textarea>
		    	    </dd>
		    	</dl>
		    	<%-- <dl>
		    	    <dt></dt>
		    	    <dd>
		    	    	<span class="info">
		    	    	<s:text name="prompt" />：<s:text name="when_the_function_point_adjustment" /> ：<s:text name="main_invite" /> 、<s:text name="are_invited_to_join" />、 <s:text name="invited_rating" />、<s:text name="number_of_invited" />
						<s:text name="change_the_value_of_function_points" /> ：<s:text name="use" />json<s:text name="storage_format" />，<s:text name="attribute_the_following_properties_are" />condition 
						 <s:text name="required_conditions" />,<s:text name="optional" /> award <s:text name="reward_after_reaching_conditions" />，<s:text name="format" />：<s:text name="goods" />id,<s:text name="quantity" />#<s:text name="goods" />id,<s:text name="quantity" /> 
						  <s:text name="as" />： {
							"condition1":"5",
							"award1":"20013,10#20014,20",
							"condition2":"10",
							"award2":"20015,10#20016,20"
							} 
							</span>
		    	    </dd>
		    	</dl>
		    	
		    	<dl> --%>
		    	<dl id="desc_dl">
		    	    <dt><s:text name="fill_format" />：</dt>
		    	    <s:iterator value="reustList" var="oneRow" status="offset">
		    	    <s:if test="#offset.index==0">
		    	      <dd style="" id="desc<s:property value='#oneRow.type'/>">
		    	    	 	<span style="color:red"> <s:property value='#oneRow.desc'/></span>
		    	   		 </dd>
		    	    </s:if>
		    	    <s:else>
		    	    <dd style="display:none" id="desc<s:property value='#oneRow.type'/>">
		    	    	 	 	<span style="color:red"><s:property value='#oneRow.desc'/></span>
		    	   		 </dd>
		    	    </s:else>
		    	    	
		    	    	</s:iterator>
		    	  
		    	</dl>
		    	</div>
		    	
		    	
		    	<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_to_add"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
					</ul>
				</div>
	    </s:form>
</div>
