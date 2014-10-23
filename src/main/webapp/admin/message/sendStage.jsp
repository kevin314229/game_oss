<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script type="text/javascript" language="javascript">
    	
	function addButEvent($addBut){
			var currForm = navTab.getCurrentPanel().find(".tabs>.tabsContent>div:visible>form");
			
			currForm.find($("select[name*=optV]")).unbind("change").bind("change",function(){
						currForm.find($("a[lookupgroup="+$(this).context.name+"]")).attr("href",
						"<%=basePath%>/admin/message/sendStage_searchIndex.action?optType="+$(this).children('option:selected').val()+"&type=optV#index#");
			});
	}
	
	
	function send(form,okCallFunc){
		
		var array = $(form).find($("select[name*='optV']"));
		var miss="<s:text name="sure_to_send_the_following_items" /> : \n";
		var sendObj = [];
		for(var i=0,obj=array[i];i<array.length;i++){
			
			var lvalue=$(form).find($("input[name='optV"+i+".value']")).val();
			
			var lname=$(form).find($("input[name='optV"+i+".name']")).val();
			
			var lnumber = $(form).find($("a[lookupgroup='optV"+i+"']")).parent().next().find("input").val();
			
			if(!lvalue||!lnumber){
				continue;
			}
			miss+="<s:text name="goods" />："+lname+",<s:text name="quantity" />"+lnumber+";";
			sendObj.push({"value":lvalue,"num":lnumber});
		}
		
		$(form).find("input[name='equipStr']").val(
			JSON.stringify(sendObj)
		);
		
		if(sendObj.length==0){
			alertMsg.warn("<s:text name="please_select_the_items_you_want_to_send" />!");
			return false;
		}
		
		alertMsg.confirm(miss,{okCall:function(){ 
				okCallFunc(form);
			}
		});
		return false;
	}
	
	
</script>
<s:include value="/admin/template/scriptMessage.jsp"/>

<div class="pageContent">
			<div class="tabs" currentIndex="0" eventType="click">
					<div class="tabsHeader">
						<div class="tabsHeaderContent">
							<ul>
								<li><a href="javascript:;"><span><s:text name="multiplayer"/></span></a></li>
								<li><a href="javascript:;"><span><s:text name="all_the_players"/></span></a></li>
								<li><a href="javascript:;"><span><s:text name="points_platform"/></span></a></li>
								<li><a href="javascript:;"><span><s:text name="by_rating" /></span></a></li>
							</ul>
						</div>
					</div>
					<div class="tabsContent" layoutH="40">
							<div>
								<s:form action="sendStage_sendStage" method="post" theme="simple"
								id="sendStageForm" onsubmit="return $(this).valid()&&send(this,navTabSearch)">
								<s:hidden name="equipStr"/>
								<s:hidden name="allFlag" value="0"/>
								<div class="pageFormContent nowrap" layoutH="100">
											<dl>
													<dt>
														<s:text name="the_player_name" />
													</dt>
													<dd>
														<s:textarea id="nickName" name="nickName" cols="150" rows="4" cssClass="required" maxLength="3000"></s:textarea>
														<span class="info">(<s:text name="in_the_middle_to" />,<s:text name="separated" />，<s:text name="no_more_than" />3000<s:text name="a_character" />)</span>
													</dd>
											</dl>
											<dl>
													<dt>
															<s:text name="email_title" />：
													</dt>
													<dd>
													<s:textfield name="msgTitle" maxlength="32" cssClass="required" ></s:textfield>
													</dd>
											</dl>
											<dl>
													<dt>
														 <s:text name="email_content" />
													</dt>
													<dd>
														<s:textarea name="content" id="cid" cols="50" rows="5" cssClass="required" maxLength="200"/>
														<span class="info">(<s:text name="no_more_than" />200<s:text name="a_character" />)</span>
													</dd>
											</dl>
											
											<div class="divider"></div>
											<dl>
												<dt></dt>
												<dd>
													<div>
													<div class="button" style="float:right;"><div class="buttonContent"><button type="submit"><s:text name="grant" /></button></div></div>
														<table class="list itemDetail" addButton="<s:text name="new_items" />"   width="100%" id="optTab">
															<thead>
																<tr>
																	<th type="enum" name="optV#index#" enumUrl="<%=basePath%>/admin/message/sendStage_select.action" size="12">
																		<s:text name="items" /><s:text name="type" />
																	</th>
																	
																	<th type="lookup" name="optV#index#.name"  lookupPk="value"
																	lookupGroup="optV#index#" 
																	rel="sendStageLookup"
																	
																	lookupUrl="<%=basePath%>/admin/message/sendStage_searchIndex.action?optType=2&type=optV#index#" 
																	size="12" fieldClass="readonly">
																		<s:text name="goods" />
																	</th>
																	<th type="text" name="equipNum" fieldClass="required" size="12"><s:text name="quantity" /></th>
																	<th type="del" width="60"><s:text name="operating" /></th>
																</tr>
															</thead>
															<tbody></tbody>
														</table>
													</div>
												</dd>
											</dl>
											
								</div>
							<!-- 	<div class="formBar">
									<ul>
										<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div></div></li>
									</ul>
								</div> -->
								</s:form>
							</div>
							<div>
								<s:form action="sendStage_sendStage" method="post" theme="simple"
								id="sendStageForm" onsubmit="return $(this).valid()&&send(this,navTabSearch);">
								<s:hidden name="equipStr"/>
								<s:hidden name="allFlag" value="1"/>
								<div class="pageFormContent nowrap" layoutH="100">
											<dl>
													<dt>
															<s:text name="email_title" />：
													</dt>
													<dd>
													<s:textfield name="msgTitle" maxlength="32" cssClass="required" ></s:textfield>
													</dd>
											</dl>
											<dl>
													<dt>
														 <s:text name="email_content" />
													</dt>
													<dd>
														<s:textarea name="content" id="cid" cols="50" rows="5" cssClass="required" maxLength="200"/>
														<span class="info">(<s:text name="no_more_than" />200<s:text name="a_character" />)</span>
													</dd>
											</dl>
											
											<div class="divider"></div>
											<dl>
												<dt></dt>
												<dd>
													<div>
														<div class="button" style="float:right;"><div class="buttonContent"><button type="submit"><s:text name="grant" /></button></div></div>
														<table class="list itemDetail" addButton="<s:text name="new_items" />" width="100%" id="optTab">
															<thead>
																<tr>
																	<th type="enum" name="optV#index#" enumUrl="<%=basePath%>/admin/message/sendStage_select.action" size="12">
																		<s:text name="items" /><s:text name="type" />
																	</th>
																	
																	<th type="lookup" name="optV#index#.name"  postField="optV#index#.value" lookupPk="value"
																	lookupGroup="optV#index#" lookupUrl="<%=basePath%>/admin/message/sendStage_searchIndex.action?optType=2&type=optV#index#" 
																	 suggestFields="value" size="12" fieldClass="readonly">
																		<s:text name="goods" />
																	</th>
																	<th type="text" name="equipNum" fieldClass="required" size="12"><s:text name="quantity" /></th>
																	<th type="del" width="60"><s:text name="operating" /></th>
																</tr>
															</thead>
															<tbody></tbody>
														</table>
													</div>
												</dd>
											</dl>
								</div>
								<!-- <div class="formBar">
									<ul>
										<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div></div></li>
									</ul>
								</div> -->
								</s:form>
							</div>
							<!-- <s:text name="of_platform" />-->
							<div>
								<s:form action="sendStage_sendStage" method="post" theme="simple"
								id="sendStageForm" onsubmit="return $(this).valid()&&send(this,navTabSearch);">
								<s:hidden name="equipStr"/>
								<s:hidden name="allFlag" value="2"/>
								<div class="pageFormContent nowrap" layoutH="100">
											<dl>
												<dt>
													<s:text name="platform" />
												</dt>
												<dd>
													<select id="serverCode" name="serverCode">
														<s:iterator value="#session.ADMIN_SYSTEM_USER_NAME.ossOperationList" var="oneRow"  status="offset">
															
															<s:if test="serverValue == #oneRow.carrierOperator">
															<option value="<s:property value="#oneRow.carrierOperator" />" selected> 	
											    				<s:property value="#oneRow.operationName"/>
											    			</option>
															</s:if> 
															<s:else> 
															<option value="<s:property value="#oneRow.carrierOperator" />"  > 	
											    				<s:property value="#oneRow.operationName"/> 
											    			</option>
											    			</s:else>
														
											    		</s:iterator>
													</select>
												</dd>
											</dl>
											
											<dl>
													<dt>
															<s:text name="email_title" />：
													</dt>
													<dd>
													<s:textfield name="msgTitle" maxlength="32" cssClass="required" ></s:textfield>
													</dd>
											</dl>
											<dl>
													<dt>
														 <s:text name="email_content" />
													</dt>
													<dd>
														<s:textarea name="content" id="cid" cols="50" rows="5" cssClass="required" maxLength="200"/>
														<span class="info">(<s:text name="no_more_than" />200<s:text name="a_character" />)</span>
													</dd>
											</dl>
											
											<div class="divider"></div>
											<dl>
												<dt></dt>
												<dd>
													<div>
														<div class="button" style="float:right;"><div class="buttonContent"><button type="submit"><s:text name="grant" /></button></div></div>
														<table class="list itemDetail" addButton="<s:text name="new_items" />" width="100%" id="optTab">
															<thead>
																<tr>
																	<th type="enum" name="optV#index#" enumUrl="<%=basePath%>/admin/message/sendStage_select.action" size="12">
																		<s:text name="items" /><s:text name="type" />
																	</th>
																	
																	<th type="lookup" name="optV#index#.name"  postField="optV#index#.value" lookupPk="value"
																	lookupGroup="optV#index#" lookupUrl="<%=basePath%>/admin/message/sendStage_searchIndex.action?optType=1&type=optV#index#" 
																	 suggestFields="value" size="12" fieldClass="readonly">
																		<s:text name="goods" />
																	</th>
																	<th type="text" name="equipNum" fieldClass="required" size="12"><s:text name="quantity" /></th>
																	<th type="del" width="60"><s:text name="operating" /></th>
																</tr>
															</thead>
															<tbody></tbody>
														</table>
													</div>
												</dd>
											</dl>
								</div>
								<!-- <div class="formBar">
									<ul>
										<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div></div></li>
									</ul>
								</div> -->
								</s:form>
							</div>
							
							<!-- <s:text name="by_rating" />-->
							<div>
								<s:form action="sendStage_sendStage" method="post" theme="simple"
								id="sendStageForm" onsubmit="return $(this).valid()&&send(this,navTabSearch);">
								<s:hidden name="equipStr"/>
								<s:hidden name="allFlag" value="3"/>
								<div class="pageFormContent nowrap" layoutH="100">
											<dl>
												<dt>
													<s:text name="starting_level" />
												</dt>
												<dd>
													<s:textfield name="minGrade" maxlength="32" size="2" cssClass="required" ></s:textfield>
													<span class="info"><s:text name="the_minimum_is_the_minimum_level_of_the_game" />,<s:text name="the_maximum_value_is_the_maximum_level_of_the_game" /></span>
												</dd>
												
											</dl>
											
											<dl>
												<dt>
													<s:text name="end_level" />
												</dt>
												<dd>
													<s:textfield name="maxGrade" maxlength="32" size="2" cssClass="required" ></s:textfield>
													<span class="info"><s:text name="a_minimum_of_tour" />f<s:text name="the_minimum_level_of_play" />,<s:text name="the_maximum_value_is_the_maximum_level_of_the_game" /></span>
												</dd>
											</dl>
											
											<dl>
													<dt>
															<s:text name="email_title" />：
													</dt>
													<dd>
													<s:textfield name="msgTitle" maxlength="32" cssClass="required" ></s:textfield>
													</dd>
											</dl>
											<dl>
													<dt>
														 <s:text name="email_content" />
													</dt>
													<dd>
														<s:textarea name="content" id="cid" cols="50" rows="5" cssClass="required" maxLength="200"/>
														<span class="info">(<s:text name="no_more_than" />200<s:text name="a_character" />)</span>
													</dd>
											</dl>
											
											<div class="divider"></div>
											<dl>
												<dt></dt>
												<dd>
													<div>
														<div class="button" style="float:right;"><div class="buttonContent"><button type="submit"><s:text name="grant" /></button></div></div>
														<table class="list itemDetail" addButton="<s:text name="new_items" />" width="100%" id="optTab">
															<thead>
																<tr>
																	<th type="enum" name="optV#index#" enumUrl="<%=basePath%>/admin/message/sendStage_select.action" size="12">
																		<s:text name="items" /><s:text name="type" />
																	</th>
																	
																	<th type="lookup" name="optV#index#.name"  postField="optV#index#.value" lookupPk="value"
																	lookupGroup="optV#index#" lookupUrl="<%=basePath%>/admin/message/sendStage_searchIndex.action?optType=1&type=optV#index#" 
																	 suggestFields="value" size="12" fieldClass="readonly">
																		<s:text name="goods" />
																	</th>
																	<th type="text" name="equipNum" fieldClass="required" size="12"><s:text name="quantity" /></th>
																	<th type="del" width="60"><s:text name="operating" /></th>
																</tr>
															</thead>
															<tbody></tbody>
														</table>
													</div>
												</dd>
												 
											</dl>
								</div>
								<!-- <div class="formBar">
									<ul>
										<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="submit" /></button></div></div></li>
									</ul>
								</div> -->
								</s:form>
							</div>
							
					</div>
					<div class="tabsFooter">
						<div class="tabsFooterContent">
						</div>
					</div>
				</div>
			</div>
		
	</div>
