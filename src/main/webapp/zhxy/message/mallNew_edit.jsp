<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script type="text/javascript" language="javascript">
		function onchangeItmeId(obj){
			if(obj.value==0){
				$("#itemId").val("");
				$("#itemName").val("");
			}else{
				var itme=obj.value.split("-");
				$("#itemId").val(itme[0]);
				$("#itemName").val(itme[2]);
				$("#"+itme[1]).val(0);
			}
		}

		function onchangeSellType(o){
			var obj =document.getElementById("sellType");
			if(obj.value==0){
				$("#dlstartTime").hide();
				$("#dlendTime").hide();
				$("#dlmaxNubDay").hide();
				$("#dlmaxNubPlayer").hide();
			}else if(obj.value==1){
				$("#dlstartTime").show();
				$("#dlendTime").show();
				$("#dlmaxNubDay").hide();
				$("#dlmaxNubPlayer").hide();
			}else if(obj.value==2){
				$("#dlstartTime").hide();
				$("#dlendTime").hide();
				$("#dlmaxNubDay").show();
				$("#dlmaxNubPlayer").show();
			}else if(obj.value==3){
				$("#dlstartTime").show();
				$("#dlendTime").show();
				$("#dlmaxNubDay").show();
				$("#dlmaxNubPlayer").show();
			}
		}

		function onchangestate(o){
			var obj =document.getElementById("state");
			if(obj.value==4||obj.value==5){
				$("#dlrebatePrice").show();
			}else{
				$("#dlrebatePrice").hide();
			}
		}
		
   		function checkForm(o) {
   	   		var type=$("#sellType").val();
			if(type==1){
				if($("#startTime").val()==""||$("#endTime").val()==""){
					alert("<s:text name="time_can_not_be_empty" />");
				}

			}else if(type==2) {
				if($("#maxNubDay").val()==""){
					alert("<s:text name="largest_single-day_number_can_not_be_empty" />。");
				}
				if($("#maxNubPlayer").val()==""){
					alert("<s:text name="players_single_day_purchases_can_not_be_empty" />");
				}

			}else if(type==3) {
				if($("#startTime").val()==""||$("#endTime").val()==""){
					alert("<s:text name="time_can_not_be_empty" />");
				}
				if($("#maxNubDay").val()==""){
					alert("<s:text name="largest_single-day_number_can_not_be_empty" />。");
				}
				if($("#maxNubPlayer").val()==""){
					alert("<s:text name="players_single_day_purchases_can_not_be_empty" />");
				}
			}
   	   		
			return true;
		}
		
	</script>
	<s:include value="/admin/template/scriptMessage.jsp"/>
  <div class="pageContent"> 
	     <s:form action="mallNew_update" namespace="/admin/message" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return checkForm(this)&&$(this).valid()&&navTabSearch(this)" >
		  	 <input type="hidden" name="ossMallNew.mallNewId" value='<s:property value="ossMallNew.mallNewId"/>'> 
		  	 <input type="hidden" name="ossMallNew.createTime" value='<s:property value="ossMallNew.createTime"/>'> 
		  	 
		  	 <div class="pageFormContent" layoutH="60">
		    	<dl>
		    	    <dt><s:text name="props" />：</dt>
		    	    <dd>
		    	    	<span class="info">
		    	    	<select id="property" onchange="onchangeItmeId(this)">
		    	    		<option value='0'>--<s:text name="goods_from_props" />--</option>
		    	    		<s:iterator value="propertyList" var="oneRow"  status="offset">
		    	    		 	<option value="${value}-equip-<s:property value="name"/>"><s:property value="value"/>-<s:property value="name"/></option>
		    	    		</s:iterator>
		    	    	</select>
		    	    	</span>
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="equipment" />：</dt>
		    	    <dd>
		    	     <select id="equip" onchange="onchangeItmeId(this)">
		    	    		<option value='0'>--<s:text name="goods_from_equipment" />--</option>
		    	    		<s:iterator value="quipList" var="oneRow"  status="offset">
		    	    		 	<option value="${value}-property-<s:property value="name"/>"><s:property value="value"/>-<s:property value="name"/></option>
		    	    		</s:iterator>
		    	    </select>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt> <s:text name="items" />ID：</dt>
		    	    <dd>
		    	     <s:textfield  id="itemId" name="ossMallNew.itemId" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt> <s:text name="items" /><s:text name="name" />：</dt>
		    	    <dd>
		    	     <s:textfield  id="itemName" name="ossMallNew.itemName" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt> <s:text name="store_the_original_price" />：</dt>
		    	    <dd>
		    	     <s:textfield  id="initPrice" name="ossMallNew.initPrice" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="mall" />VIP<s:text name="price" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="vipPrice" name="ossMallNew.vipPrice" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="consumption_type" />：</dt>
		    	    <dd>
		    	    	<s:select name = "ossMallNew.costType" headerKey="1" 
		    	    	list="#{'1':getText('ingot')}"/><%-- ,'2':getText('star_power'),'3':getText('coins')}"/> --%>
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="labels" />：</dt>
		    	    <dd>
		    	    	<s:select id="state" onchange="onchangestate(this)" name = "ossMallNew.state" headerKey="0" list="#{ '0':getText('there_is_no'),'1':getText('new'),'2':getText('hot'),'3':getText('recommend'),'4':getText('promotions'),'5':getText('promotional_recommended')}"  />
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="sell_​​type" />：</dt>
		    	    <dd>
		    	    	<s:select onchange="onchangeSellType(this)" id="sellType" name = "ossMallNew.sellType" headerKey="0" 
		    	    	list="#{'0':getText('no'), '1':getText('limited_time'),'2':getText('limit'),'3':getText('limited_limited')}" />
		    	    </dd>
		    	</dl>
		    	
		    	
		    	<dl style="display: none" id="dlrebatePrice">
		    	    <dt><s:text name="the_promotional_price" /> ：</dt>
		    	    <dd>
		    	    	<s:textfield  id="rebatePrice" name="ossMallNew.rebatePrice" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	
		    	<dl style="display: none" id="dlstartTime">
		    	    <dt><s:text name="goods_online_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="startTime" name="ossMallNew.startTime" style="width:160px" 
					dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="date" readonly="true" value="">
					 <s:param name="value"><s:date name="ossMallNew.startTime"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
					</s:textfield>  
					
					
					
		    	    </dd>
		    	</dl>
		    	
		    	<dl style="display: none" id="dlmaxNubPlayer">
		    	    <dt><s:text name="players_on_the_day_of_the_shop_to_buy" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="maxNubPlayer" name="ossMallNew.maxNubPlayer" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl style="display: none" id="dlendTime">
		    		<dt><s:text name="goods_rolled_off_the_production_time" />：</dt>
		    	    <dd>
		    	 <s:textfield id="endTime" name="ossMallNew.endTime"  style="width:160px" 
			dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="date" readonly="true" >
			   <s:param name="value"><s:date name="ossMallNew.endTime"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
			</s:textfield> 
		    	    </dd>
		    	</dl>
		    	
		    	
		    	<dl style="display: none" id="dlmaxNubDay">
		    	    <dt><s:text name="mall_shop_on_the_day_of_the_sale" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="maxNubDay" name="ossMallNew.maxNubDay" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="added_column" />：</dt>
		    	    <dd>
		    	    	<s:select name = "ossMallNew.mallType" headerKey="1" 
		    	    	list="#{ '1':getText('props'),'2':getText('gem'),'3':getText('gift_certificates'),'4':getText('fashion_clothes'),'5':getText('fashion_weapons')}" />
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="product_order" />：</dt>
		    	    <dd>
		    	    	<s:textfield  id="mallOrder" name="ossMallNew.mallOrder" ></s:textfield>
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="are_added" /> ：</dt>
		    	    <dd>
		    	    	<s:select name = "ossMallNew.isShow" headerKey="1" list="#{'1':getText('added'),'0':getText('off_the_shelf'),'2':getText('moratorium_on_the_sale')}" />
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt></dt>
		    	    <dd>
		    	    	&nbsp;
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="describe" />：</dt>
		    	    <dd>
		    	    	<s:textarea id="describe" cols="30" rows="4"  name="ossMallNew.describe" >
		    	    	</s:textarea>
		    	    </dd>
		    	</dl>
		    	</div>
		    	<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_to_add"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" class="close"><s:text name="close" /></button></div></div></li>
					</ul>
					
				</div>
	    </s:form>
	   </div>  