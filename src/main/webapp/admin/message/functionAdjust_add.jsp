<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent">
	     <s:form action="functionAdjust_doAdd" namespace="/admin/message" method="post" theme="simple" 
 			cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
 			 <div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="the_function_point" />：</dt>
		    	    <dd>
		    	    	<select id="functionType" name="ossFunctionAdjust.functionType" >
		    	    		<option value="1">1-<s:text name="refresh_the_mystery_shop" /> </option>
		    	    		<option value="2">2-<s:text name="rune_master_invited" /> </option>
		    	    		<option value="3">3-<s:text name="gold_multiples" /> </option>
		    	    		<option value="4">4-<s:text name="runic_writing_discount" /></option>
		    	    		<option value="5">5-<s:text name="common_purification_of_gold_discounts" /></option>
		    	    		<option value="6">6-<s:text name="senior_discounts_purification_of_gold" /></option>
		    	    		<option value="7">7-<s:text name="super_purification_of_gold_discounts" /></option>
		    	    		<option value="8">8-<s:text name="perfect_purification_of_gold_discount" /></option>
		    	    		<option value="9">9-<s:text name="ordinary_magic_crystal_purification_of_discounted_gift_certificates" /></option>
		    	    		<option value="10">10-<s:text name="senior_sanctity_magic_crystal_discounted_gift_certificates" /></option>
		    	    		<option value="11">11-<s:text name="super_magic_crystal_purification_of_discounted_gift_certificates" /></option>
		    	    		<option value="12">12-<s:text name="perfect_magic_crystal_purification_of_discounted_gift_certificates" /></option>
		    	    		<option value="13">13-<s:text name="gold_coins_discount_culture" /></option>
		    	    		<option value="14">14-<s:text name="advanced_training_gold_discount" /></option>
		    	    		<option value="15">15-<s:text name="extreme_training_coins_discounts" /></option>
		    	    		<option value="16">16-<s:text name="god_gold_discount_culture" /></option>
		    	    		<option value="17">17-<s:text name="training_magic_crystal_gold_discounted_gift_certificates" /></option>
		    	    		<option value="18">18-<s:text name="advanced_training_magic_crystal_discounted_gift_certificates" /></option>
		    	    		<option value="19">19-<s:text name="extreme_training_magic_crystal_discounted_gift_certificates" /></option>
		    	    		<option value="20">20-<s:text name="god_culture_magic_crystal_gift_certificate_discounts" /></option>
		    	    		<option value="21">21-<s:text name="chamber_coins_discounts" /></option>
		    	    		<option value="22">22-<s:text name="library_gold_discount" /></option>
		    	    		<option value="23">23-<s:text name="legion_donate_gold_discount" /></option>
		    	    		<option value="24">24-<s:text name="chamber_magic_crystal_discounted_gift_certificates" /></option>
		    	    		<option value="25">25-<s:text name="library_magic_crystal_discounted_gift_certificates" /></option>
		    	    		<option value="26">26-<s:text name="legion_discounted_gift_certificates_donated_magic_crystal" /></option>
		    	    	</select>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="the_function_point_change_value" />：</dt>
		    	    <dd>
		    	    	<s:textfield size="40" id="functionNub" name="ossFunctionAdjust.functionNub"  cssClass="required"></s:textfield>
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
		    	    	<s:textarea id="describe" cols="32" rows="6"  name="ossFunctionAdjust.describe" >
		    	    	</s:textarea>
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
