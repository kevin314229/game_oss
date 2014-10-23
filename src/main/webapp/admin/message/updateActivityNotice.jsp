<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<s:include value="/admin/template/scriptMessage.jsp"/>
<div class="pageContent"  >
	     <s:form action="activityNotice_updateActivityNotice" namespace="/admin/message" method="post" theme="simple" 
	      cssClass="pageForm required-validate" onsubmit="return $(this).valid()&&navTabSearch(this)"  >
		 	  <s:hidden  name="ossActivityNotice.activityNoticeId" ></s:hidden>
		 	  <s:hidden  name="ossActivityNotice.createTime" ></s:hidden>
		  	<div class="pageFormContent nowrap" layoutH="60">
		    	<dl>
		    	    <dt width="30%"><s:text name="activity_description" />：</dt>
		    	    <dd>
		    	     (<s:text name="tips_should_not_exceed" />2048<s:text name="characters." />,1024<s:text name="characters" />!)
		    	    <br>
		    	    	<s:textarea cols="80" rows="22" id="content" name="ossActivityNotice.content" cssClass="required"  maxlength="2048" ></s:textarea>
		    	    </dd>
		    	</dl>
		    	 <dl id="desc_dl">
		    	    <dt>颜色设置说明：</dt>
		    	      <dd style="" id="desc5">
		    	    	 	<span style="color:red"> （格式为：#color[FF0000]，[ ]内代码为：FFFFFF是白色，000000是黑色，FF0000是红色，00FF00是绿色，0000FF是蓝色）</span>
		    	   		 </dd>
		    	</dl>
		    	<dl>
		    	    <dt width="30%"><s:text name="activities_start_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="startTime"  name="ossActivityNotice.startTime" cssClass="required date"  dateFmt="yyyy-MM-dd HH:mm:00" >
		    	    		<s:param name="value"><s:date name="ossActivityNotice.startTime" format="yyyy-MM-dd HH:mm:ss" /></s:param>
		    	    	</s:textfield>
		    	    </dd>
		    	</dl>
		    	<dl>
		    	    <dt width="30%"><s:text name="activity_over_time" />：</dt>
		    	    <dd>
		    	    	<s:textfield id="endTime"  name="ossActivityNotice.endTime" cssClass="required date" dateFmt="yyyy-MM-dd HH:mm:59" >
		    	    		<s:param name="value"><s:date name="ossActivityNotice.endTime" format="yyyy-MM-dd HH:mm:ss" /></s:param>
		    	    	</s:textfield>
		    	    </dd>
		    	</dl>
		    	</div>
		    	<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit"><s:text name="confirm_the_change"/></button></div></div></li>
						<li><div class="buttonActive"><div class="buttonContent"><button type="reset"><s:text name="reset"/></button></div></div></li>
					</ul>
				</div>
	    </s:form>
</div>
