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
		
</script>

<s:include value="/admin/template/scriptMessage.jsp"/>
    	
		<div class="pageContent">
	     <s:form action="zhfunctionAdjust_update" namespace="/admin/message" method="post" theme="simple"
	    	 cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
	    	 <div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="the_function_point" />：</dt>
		    	    <dd>
		    	        <s:textfield size="40" id="functionType" style="display:none" name="ossFunctionAdjust.functionType" ></s:textfield>
		    	      <s:property value="typeMap[ossFunctionAdjust.functionType]"/>
		    	        <%-- <s:if test="ossFunctionAdjust.functionType==1">
		    	        	1-<s:text name="refresh_the_mystery_shop" />
		    	        </s:if>
		    	        <s:elseif test="ossFunctionAdjust.functionType==2">
		    	        	2-<s:text name="rune_master_invited" />
		    	        </s:elseif>
		    	        <s:elseif test="ossFunctionAdjust.functionType==3">
		    	        	3-<s:text name="gold_multiples" />
		    	        </s:elseif>
		    	        <s:elseif test="ossFunctionAdjust.functionType==4">
		    	    		4-<s:text name="runic_writing_discount" />
		    	    	</s:elseif>
		    	        <s:elseif test="ossFunctionAdjust.functionType==5">
		    	    		5-<s:text name="main_invite" />
		    	    	</s:elseif>
		    	        <s:elseif test="ossFunctionAdjust.functionType==6">
		    	    		6-<s:text name="are_invited_to_join" />
		    	    	</s:elseif>
		    	        <s:elseif test="ossFunctionAdjust.functionType==7">
		    	    		7-<s:text name="invited_rating" />
		    	    	</s:elseif>
		    	        <s:elseif test="ossFunctionAdjust.functionType==8">
		    	    		8-<s:text name="number_of_invited" />
		    	    	</s:elseif>
		    	        <s:elseif test="ossFunctionAdjust.functionType==9">
		    	    		9-<s:text name="probability_of_cure_park_seed" /> 
		    	    	</s:elseif>
		    	        <s:elseif test="ossFunctionAdjust.functionType==10">
		    	    		10-<s:text name="elixir_garden_seed_prices" />
		    	    	</s:elseif>
		    	        <s:elseif test="ossFunctionAdjust.functionType==11">
		    	    		11-<s:text name="refresh_consumption_elixir_park" />
		    	    	</s:elseif>
		    	    	<s:elseif test="ossFunctionAdjust.functionType==12">
		    	    		12-<s:text name="online_awards" />
		    	    	</s:elseif>
		    	    	<s:elseif test="ossFunctionAdjust.functionType==13">
		    	    		13-<s:text name="the_first_charge_active_configuration" />
		    	    	</s:elseif>
		    	    	<s:elseif test="ossFunctionAdjust.functionType==14">
		    	    	14-<s:text name="the_first_charge_reward" />
		    	    	</s:elseif>
		    	    	<s:elseif test="ossFunctionAdjust.functionType==15">
		    	    	15-<s:text name="daily_recharge_awards" />
		    	    	</s:elseif>
		    	    	<s:elseif test="ossFunctionAdjust.functionType==16">
		    	    	16-vip<s:text name="packs" />
		    	    	</s:elseif>
		    	    	<s:elseif test="ossFunctionAdjust.functionType==17">
		    	    	17-<s:text name="integral_wall_configuration" /> (<s:text name="hong_kong,_macao_and_taiwan" />)
		    	    	</s:elseif>
		    	        <s:else>
		    	        	<s:text name="other" />
		    	        </s:else> --%>
		    	        
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
		    	    	<s:textarea cols="40" rows="6" name="ossFunctionAdjust.functionNub" cssClass="required"></s:textarea>
		    	    	<a class="btnLook" id="jsonLook" href="javascript:void(0)" width="830" height="510"  callbackObjName="functionNub"
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
		    	   		<s:textfield id="startTime" name="ossFunctionAdjust.endTime"  style="width:160px" 
								dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" >
							<s:param name="value"><s:date name="ossFunctionAdjust.endTime" format="yyyy-MM-dd HH:mm:ss" /></s:param>
						</s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="describe" />：</dt>
		    	    <dd>
		    	    	<s:textarea id="describe" cols="40" rows="6" name="ossFunctionAdjust.describe" >
		    	    	</s:textarea>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="fill_format" />：</dt>
		    	    <dd>
		    	    	<span style="color:red" > <s:property value="typeMap[9999]"/></span>
		    	    </dd>
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
