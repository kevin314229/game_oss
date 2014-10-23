<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
  
  	<div class="pageContent">
	   <s:form action="activity_updateActivity" namespace="/cjwz/message" method="post" theme="simple"  
	     cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)">
	     		<div class="pageFormContent nowrap" layoutH="60">
		        <dl>
		    	    <dt><s:text name="the_activity_type" />：</dt>
		    	    <dd>
		    	    <s:hidden name="activity.activityId" />
		    	    	<%-- <s:textfield name="activity.type" size="120" cssClass="required"/>
		    	    	 --%>
		    	    	  <s:select name="activity.type" cssClass="required" headerKey="1" headerValue="-- Please Select --" 
			    	    	list="#{'0':'0-奇闻',
			    	    	'1':'1-赏金',
						'2':'2-日常',
						'3':'3-商城','4':'4-装备','5':'5-守护灵','6':'6-其他'}"/>
				<%-- 		<select id="type" name="activity.type"  >
							<option value="1">-- Please Select -- </option>
		    	    	<s:iterator value="reustList" var="oneRow">
		    	    	<option value="<s:property value='#oneRow.type'/>"
		    	    	<s:if test="#oneRow.type==activity.type"> selected</s:if>
		    	    	><s:property value='#oneRow.type'/>-<s:property value="#oneRow.name"/> </option>
		    	    	</s:iterator>
		    	    	</select> --%>
		    	    </dd>
		    	</dl>
		    <%-- 	<dl>
		    	    <dt><s:text name="get_the_way" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="activity.characteristic" cssClass="required" size="120"/>
		    	    	
		    	    	 			    	    	,'2':'2-'+getText('by_the_server_through_system_mail_sent')
			    	     <s:select name = "activity.characteristic" cssClass="required" headerKey="1" list="#{ '1':'1-'+getText('click_and_receive_button_to_receive_awards_by_the_player')}" />
		    	    </dd>
		    	</dl> --%>
		    	<%-- <dl>
		    	    <dt><s:text name="get_the_type" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="activity.times" size="120" cssClass="required"/>
		    	    	
			    	    <s:select name = "activity.times" cssClass="required" headerKey="1" list="#{ '1':'1-'+getText('you_can_only_receive_a_reward_activity'),'2':'2-'+getText('every_reward_can_be_obtained'),'3':'3-'+getText('repeated_every_day_for_a_reward'),'4':'4-'+getText('repeatable'),'5':'5-'+getText('top-up_amount_can_receive')}" />
		    	    </dd>
		    	</dl> --%>
		    	<dl>
		    	    <dt><s:text name="the_name_of_the_event" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="activity.name" size="120" cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt><s:text name="activity_description" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="activity.activityDesc"  size="120" cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="activity_rules" />：</dt>
		    	    <dd>
		    	    	<s:textfield name="activity.rule" size="120"  />
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt>活动条件：</dt>
		    	    <dd>
		    	    	<s:textfield name="activity.activityCondition" size="120"  cssClass="required"/>
		    	    </dd>
		    	</dl>
		    	
		    	<dl>
		    	    <dt>活动奖励：</dt>
		    	    <dd>
		    	    	<s:textfield name="activity.reward"  size="120"  />
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt>按钮名称：</dt>
		    	    <dd>
		    	    	<s:textfield name="activity.btnName" size="120"  />
		    	    </dd>
		    	</dl>
		    		<dl>
		    	    <dt>活动iconID：</dt>
		    	    <dd>
		    	    	<s:textfield name="activity.icon" size="120"   />
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt>活动显示时间：</dt>
		    	    <dd>
		    	    	<s:textfield id="openTime" name="activity.openTime" datefmt="yyyy-MM-dd HH:00:00" style="width:160px" 
			cssClass="required date" readonly="true" /> 
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="activities_start_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="startTime" name="activity.startTime" datefmt="yyyy-MM-dd HH:00:00" style="width:160px" 
			cssClass="required date" readonly="true" /> 
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt><s:text name="activity_over_time" />：</dt>
		    	    <dd>
		    	    <s:textfield id="endTime" name="activity.endTime" datefmt="yyyy-MM-dd HH:00:00" style="width:160px" 
			cssClass="required date" readonly="true"/> 
			
		    	    </dd>
		    	</dl>
		    <%-- 	<dl>
		    	    <dt>领奖结束时间：</dt>
		    	    <dd>
		    	    <s:textfield id="rewardOverDate" name="rewardOverDate" datefmt="yyyy-MM-dd HH:mm:ss" style="width:160px" 
			cssClass="required date" readonly="true"/> 
			
		    	    </dd>
		    	</dl> --%>
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

    
