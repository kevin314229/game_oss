<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	
	<script src="<%=basePath%>/media/default/js/json2.js" type="text/javascript"></script>
	
	<script type="text/javascript" language="javascript">
		
		function send(form){
			try{
			var array = $(form).find($("select[name*=value]")).length;
			var textArray1 = $("select[name*=value]",$.pdialog.getCurrent());
			var textArray2 =$("input[name*=item]",$.pdialog.getCurrent());
			var textArray3 =$("input[name*=detailId]",$.pdialog.getCurrent());
			var textArray4 =$("input[name*=activityId]",$.pdialog.getCurrent());
			var sendArray = [];
			for(var i = 0; i < array; i++){
				var sendObj = new Object();
				sendObj.occupation = textArray1[i].value;
				sendObj.item = textArray2[i].value;
				sendObj.rechargeActivityDetailId = textArray3[i].value;
				sendObj.recharegeActivityId = textArray4[i].value ;
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
	     <s:form action="rechargeActivity_doUpdate" namespace="/admin/message" method="post" theme="simple" 
	     cssClass="pageForm required-validate" onsubmit="return send(this)&&$(this).valid()&&navTabSearch(this);" >
		 	  <s:hidden  name="activityStr"></s:hidden>
		 	  <s:hidden  name="ossRechargeActivity.recharegeActivityId"></s:hidden>
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
					<s:textfield id="openDate" name="ossRechargeActivity.openTime" style="width:160px" 
						dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="date" readonly="true" value="">
					 	<s:param name="value"><s:date name="ossRechargeActivity.openTime"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
					</s:textfield>  
				
			    	    </dd>
			    	</dl>
			    	<dl>
			    	    <dt><s:text name="activity_over_time" />：</dt>
			    	    <dd>
					<s:textfield id="endDate" name="ossRechargeActivity.overTime" style="width:160px" 
						dateFmt="yyyy-MM-dd HH:mm:ss" cssClass="date" readonly="true" value="">
					 	<s:param name="value"><s:date name="ossRechargeActivity.overTime"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
					</s:textfield>  
					
				
			    	    	
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
						    	<table id="table1" class="list"  width="100%">
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
						    	
						    <s:iterator value="ossRechargeActivity.ossRechargeActivityDetailList" var="oneRow" status="offset">
								<tr class="unitBox">
									<td>
										<div ><!-- class="combox" -->
										<input type="hidden" class="textInput" size="88" value="${rechargeActivityDetailId}" name="detailId<s:property value='#offset.index'/>">
										<input type="hidden" class="textInput" size="88" value="${recharegeActivityId}" name="activityId<s:property value='#offset.index'/>">
											<div class="select" id="combox_4574097">
												<!-- <a value="0" name="value#index#" class="" href="javascript:"><s:text name="whole" /></a> -->
												<select name="value0"  class="">
													<option value="0" ${occupation==0?'selected':''}><s:text name="whole" /></option>
													<option value="1" ${occupation==1?'selected':''}><s:text name="warrior" /></option>
													<option value="2" ${occupation==2?'selected':''}><s:text name="master" /></option>
													<option value="3" ${occupation==3?'selected':''}><s:text name="archer" /></option>
												</select>
											</div>
										</div>
									</td>
									<td><input type="text" class="textInput" size="88" value="${item}" name="item0"></td>
									<td><a class="btnDel " href="javascript:void(0)"><s:text name="delete" /></a></td>
								</tr>
							</s:iterator>
						    	
						    	
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
