<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script type="text/javascript" language="javascript">
		function onchangeItmeId(obj){
			if(obj.value==0){
				$("#itemId").val("");
				$("#initPrice").val("");
				$("#vipPrice").val("");
			}else{
				var strs = obj.value.split("-");
				$("#itemId").val(strs[0]);
				$("#initPrice").val(strs[1]);
				$("#vipPrice").val(strs[2]);
			}
		}
	
	
   		function checkForm(o) {
			if($("#maxNubDay").val() == 0||$("#maxNubPlayer").val()==0){
				alert("<s:text name="maximum_number_of_purchase" />，<s:text name="can't_fill_in" /> 0 "); return false;
			}
			return true;
		}
		
	</script>
	<s:include value="/admin/template/scriptMessage.jsp"/>
  <div class="pageContent">
	     <s:form action="mallActivity_doAdd" namespace="/admin/message" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return checkForm(this)&&$(this).valid()&&navTabSearch(this)" >
		  	 <div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="name_of_commodity" />：</dt>
		    	    <dd>
		    	    	<select  onchange="onchangeItmeId(this)">
		    	    		<option value=''>-<s:text name="please_select_from_the_city" />-</option>
		    	    		<s:iterator value="mallActivityList" var="oneRow"  status="offset">
		    	    		 	<option value="${itemId}-${initPrice}-${vipPrice}"><s:property value="mallName"/></option>
		    	    		</s:iterator>
		    	    	</select>
		    	    	<span class="info">( <s:text name="activities_commodities_can_choose_directly_from_the_mall" />，<s:text name="can_also_be_manually_fill_out" />！) </span>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt> <s:text name="items" />ID：</dt>
		    	    <dd>
		    	     <s:textfield  id="itemId" name="ossMallActivity.itemId" cssClass="required"></s:textfield>
		    	     <span class="info">
			    	     	ID<s:text name="generally_do_not_modify" />，<s:text name="can_be_automatically_obtained_from_the_above_list" />，
			    	     	<s:text name="only_when_there_is_no_need_to_store_the_items_sale" />，<s:text name="before_completing_items" />ID，<s:text name="such_as_equipment_or_props" />ID
		    	     	</span>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt> <s:text name="store_the_original_price" />：</dt>
		    	    <dd>
		    	     <s:textfield  id="initPrice" name="ossMallActivity.initPrice" max="99999999" min="1" cssClass="required"></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="mall" />VIP<s:text name="price" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="vipPrice" name="ossMallActivity.vipPrice" max="99999999" min="1" cssClass="required"></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="this_activity_price" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="rebatePrice" name="ossMallActivity.rebatePrice" max="99999999" min="1" cssClass="required"></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="goods_online_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="startTime" name="ossMallActivity.startTime"  style="width:160px" 
							dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" >
							<s:param name="value"><s:date name="ossMallActivity.startTime" format="yyyy-MM-dd HH:mm:ss" /></s:param>
						</s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="labels" />：</dt>
		    	    <dd>
		    	    	<s:select name = "ossMallActivity.state" headerKey="0" list="#{ '0':getText('there_is_no'),'1':getText('new'),'2':getText('hot')}" />
		    	    </dd>
		    	</dl>
		    	<dl>
		    		<dt><s:text name="goods_rolled_off_the_production_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="endTime" name="ossMallActivity.endTime"  style="width:160px" 
									dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" > 
								<s:param name="value"><s:date name="ossMallActivity.startTime" format="yyyy-MM-dd HH:mm:ss" /></s:param>
						</s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="conditions_of_purchase" />：</dt>
		    	    <dd>
		    	    	<s:select name = "ossMallActivity.vipGrade" headerKey="0" list="#{ '0':getText('there_is_no'),'1':'VIP1','2':'VIP2','3':'VIP3','4':'VIP4','5':'VIP5','6':'VIP6','7':'VIP7','8':'VIP8','9':'VIP9','10':'VIP10','11':'VIP11'}" />
		    	    </dd>
		    	    
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="players_on_the_day_of_the_shop_to_buy" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="maxNubPlayer" name="ossMallActivity.maxNubPlayer" max="99999999" min="1" cssClass="required"></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="mall_shop_on_the_day_of_the_sale" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="maxNubDay" name="ossMallActivity.maxNubDay" max="99999999" min="1" cssClass="required"></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl style="display: none">
		    	    <dt><s:text name="additional_types_of_consumption_goods" />：</dt>
		    	    <dd>
		    	    	<s:select name = "ossMallActivity.costType" headerKey="0" list="#{ '0':getText('there_is_no'),'1':getText('gold_coins'),'2':getText('gift_certificates'),'3':getText('integral')}" />
		    	    </dd>
		    	</dl>
		    	<dl style="display: none">
		    	    <dt><s:text name="additional_consumption_quantity" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="costPrice" name="ossMallActivity.costPrice" value="0"></s:textfield>
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="describe" />：</dt>
		    	    <dd>
		    	    	<s:textarea id="describe" cols="40" rows="4" maxlength="12" name="ossMallActivity.describe" placeholder="%{getText('description_up')+'12'+getText('words')}">
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
