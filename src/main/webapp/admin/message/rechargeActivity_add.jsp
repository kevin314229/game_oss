<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	
	<script src="<%=basePath%>/media/default/js/json2.js" type="text/javascript"></script>
	
	<script type="text/javascript" language="javascript">
    	
		/* var textArray2 = $.pdialog.getCurrent().find($("input[name*=number]")); */
		
		function send(form){
			try{
			var array = $(form).find($("select[name*=value]")).length;
			var textArray1 = $("select[name*=value]",$.pdialog.getCurrent());
			var textArray2 =$("input[name*=item]",$.pdialog.getCurrent());
			var sendArray = [];
			for(var i = 0; i < array; i++){
				var sendObj = new Object();
				sendObj.occupation = textArray1[i].value;
				sendObj.item = textArray2[i].value;
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
	     <s:form action="rechargeActivity_doAdd" namespace="/admin/message" method="post" theme="simple"
	     cssClass="pageForm required-validate"  onsubmit="return send(this)&&$(this).valid()&&navTabSearch(this);" >
	      
		 	  <s:hidden  name="activityStr"></s:hidden>
		 	   <div class="pageFormContent nowrap"  layoutH="60">
			        <dl>
			    	    <dt><s:text name="the_activity_type" />：</dt>
			    	    <dd>
			    	    	<s:select name="ossRechargeActivity.type" cssClass="required" headerKey="1" headerValue="-- Please Select --" 
			    	    	list="#{'1':getText('once_a_day'),
			    	    	'2':getText('activity_cycle')}"/>  
			    	    </dd>
			    	</dl>
			    	
			    	<dl>
			    	    <dt><s:text name="activity_description" />1 ：</dt>
			    	    <dd>
			    	    <s:textarea cols="75" rows="5" id="content" name="ossRechargeActivity.activityDesc" cssClass="required" maxlength="1500"></s:textarea>
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="activity_description" />2：</dt>
			    	    <dd>
			    	    <s:textarea cols="75" rows="5" id="content" name="ossRechargeActivity.activityDescSecond" cssClass="required" maxlength="1500"></s:textarea>
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="activities_start_time" />：</dt>
			    	    <dd>
			    	    	<s:textfield id="openDate" name="ossRechargeActivity.openTime"  style="width:160px" 
				dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" /> 
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="activity_over_time" />：</dt>
			    	    <dd>
			    	    	<s:textfield id="endDate" name="ossRechargeActivity.overTime"  style="width:160px" 
				dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="required date" readonly="true" /> 
			    	    	
			    	    </dd>
			    	</dl>
			    	<div class="panel">
				    		<h1></h1>
					    	<div>
					    	 <s:text name="reward_items_according_to_the_format_please_fill_in" />:2010101,10#2010201,10#2020101,10  
					    	 (<s:text name="be_careful_not_to_take_space" />)
					    	
					    	</div>
		    	</div>
			    	<div class="panel">
				    		<h1></h1>
					    	<div>
						    	<table id="table1" class="list itemDetail" addButton="<s:text name="new_items" />" width="100%">
						    	<thead>
						    	<tr >
							    	<th width="70" type="enum" enumUrl="<%=basePath%>/admin/message/modifyActivity_select.action" size="12" name="value#index#" >
							    		<s:text name="professional" />
							    	</th>
							    	<th type="text" name="item#index#" size="88"  >
							    		<s:text name="reward_items" />
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
