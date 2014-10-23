<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典：跑环任务管理</title>
    <link href="<%=basePath%>/media/default/css/default.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/jquery.ui.all.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/media/default/css/Pager.css" type="text/css"/>
	<script src="<%=basePath%>/media/default/js/jquery-1.5.1.min.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/jquery.ui.core.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/jquery.ui.widget.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/jquery.ui.datepicker.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/jquery.pager.js"  type="text/javascript"></script>
	<script src="<%=basePath%>/media/default/js/public.js"  type="text/javascript"></script>
	<script type="text/javascript" language="javascript">
		$(document).ready(function() {
            $("#topPager").pager({ pagenumber: '<s:property value="currPageNO" default="0"/>', pagecount: '<s:property value="pages" default="0"/>', buttonClickCallback: PageClick });
        	$("#topPager").append(' 共 <s:property value="allNum" default="0"/> 条记录，共 <s:property value="pages" default="0"/> 页');
        	$("#bottomPager").pager({ pagenumber: '<s:property value="currPageNO" default="0"/>', pagecount: '<s:property value="pages" default="0"/>', buttonClickCallback: PageClick });
        	$("#bottomPager").append(' 共 <s:property value="allNum" default="0"/> 条记录，共 <s:property value="pages" default="0"/> 页');
        });

        PageClick = function(pageclickednumber) {
            $("#currPageNO").val(pageclickednumber);
            $("#queryTaskRepeatForm").submit();
        };
        
        function statPayInfo(){
        	$("#currPageNO").val(1);
            $("#queryTaskRepeatForm").submit();
        }
        
        function deleteMsg(msgID) {
			if (confirm("你确定要删除该项吗？")){
				deleteTaskRepeatForm.repeatId.value = msgID;
				deleteTaskRepeatForm.submit();
			}
		}
	</script>
  </head>
  
  <body>
  <div id="container">
    <div id="divLeft">
		<b>数据字典：跑环任务管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
    <div id="divQuery">
	    <s:form action="queryBaseTaskRepeat" method="post" theme="simple" id="queryTaskRepeatForm">
	    <s:hidden name="currPageNO" id="currPageNO"></s:hidden>
	    	    	任务ID：<s:textfield name="repeatId" size="16"></s:textfield>
	    	    	<input type="button" style="cursor:hand" name="stat" value="查询" onclick="statPayInfo();"/>
	    	    	<input name="add" type="button" class="button_02" value="新 增" onclick="window.open('<%=basePath%>/admin/dictionary/addBaseTaskRepeat.jsp','_self');" >
	    </s:form>
	</div>
	<div id="divLeft">
		<s:form action="deleteBaseTaskRepeat" method="post" theme="simple" id="deleteTaskRepeatForm">
		<s:hidden name="repeatId" id="repeatId"></s:hidden>
		</s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th>跑环任务ID</th>
	    	 	<th>每环任务数</th>
	    	    <th>清空时间</th>
	    	    <th>清空时间类型</th>
	    	    <th>任务等级限制</th>
	    	    <th>操作</th>
	    	</tr>
	    	<s:iterator value="baseTaskRepeatList" var="onePay" status="offset">
	    		<tr >
		    	    <td>
						<s:property value="#onePay.repeatId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.taskNumber"/>
		    	    </td>
		    	    <td>
		    	    	<s:date name="#onePay.clearTime" format="yyyy-mm-dd HH:mm:ss"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.clearType"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.level"/>
		    	    </td>
		    	    <td>
						 <A href='javascript:deleteMsg("<s:property value="#onePay.repeatId"/>")' style="color:green">删除</A>
						 <a href="<%=basePath%>/admin/skill/editBaseTaskRepeat.action?repeatId=${onePay.repeatId }" style="color:green">修改</a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
	 <div id="bottomPager"></div>
    
  </div>
  </body>
</html>
