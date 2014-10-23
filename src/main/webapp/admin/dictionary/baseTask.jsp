<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典：任务管理</title>
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
            $("#queryTaskForm").submit();
        };
        
        function statPayInfo(){
        	$("#currPageNO").val(1);
            $("#queryTaskForm").submit();
        }
        
        function deleteMsg(msgID) {
			if (confirm("你确定要删除该项吗？")){
				deleteTaskForm.taskId.value = msgID;
				deleteTaskForm.submit();
			}
		}
	</script>
  </head>
  
  <body>
  <div id="container">
    <div id="divLeft">
		<b>数据字典：任务管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
    <div id="divQuery">
	    <s:form action="queryBaseTask" method="post" theme="simple" id="queryTaskForm">
	    <s:hidden name="currPageNO" id="currPageNO"></s:hidden>
	    	    	任务ID：<s:textfield name="taskId" size="16"></s:textfield>
	    	    	或任务名称：<s:textfield name="name" size="12"></s:textfield>
	    	    	<input type="button" style="cursor:hand" name="stat" value="查询" onclick="statPayInfo();"/>
	    	    	<input name="add" type="button" class="button_02" value="新 增" onclick="window.open('<%=basePath%>/admin/dictionary/addBaseTask.jsp','_self');" >
	    </s:form>
	</div>
	<div id="divLeft">
		<s:form action="deleteBaseTask" method="post" theme="simple" id="deleteTaskForm">
		<s:hidden name="taskId" id="taskId"></s:hidden>
		</s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList">
	    <table id="row" style="table-layout:fixed;">
	    	<tr>
	    	    <th>任务ID</th>
	    	    <th>任务名称</th>
	    	    <th>任务类型</th>
	    	    <th>是否可放弃</th> 
	    	    <!--
	    	    <th>任务环编号</th>
	    	    <th>接任务条件</th>
	    	    <th>接任务时间限制</th>
	    	    <th>前置任务ID</th>
	    	    <th>接任务关联ID</th>
	    	    <th>任务消耗</th>
	    	    <th>任务步骤</th>
	    	    <th>任务步骤描述</th>
	    	    <th>交任务关联ID</th>
	    	    <th>任务目标</th>
	    	    <th>目标数量</th>
	    	    <th>接任务对白</th>
	    	    <th>交任务对白</th>
	    	    <th>任务奖励</th>
	    	    <th>任务回收物品</th>
	    	    <th>接任务获得物品</th>
	    	    <th>道具奖励</th>
	    	    <th>完成方式</th>
	    	    <th>完成类型</th>
	    	    <th>任务描述</th> -->
	    	    <th colspan="2">操作</th>
	    	</tr>
	    	<s:iterator value="baseTaskList" var="onePay" status="offset">
	    		<tr  height="15">
		    	    <td>
						<s:property value="#onePay.taskId"/>
		    	    </td>
		    	    <td style="overflow:hidden;text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;
		">
						<s:property value="#onePay.name"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.type"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.abdicable"/>
		    	    </td>
		    	    <!--
		    	    <td>
		    	    	<s:property value="#onePay.loopId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.taskRequire"/>
		    	    </td>
		    	    <td><s:property value="#onePay.recvTime"/></td>
		    	    <td>
		    	    	<s:property value="#onePay.triggerId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.recvId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.recvCost"/>
		    	    </td>
		    	    <td style="overflow:hidden;text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;
		">
		    	    	<s:property value="#onePay.step"/>
		    	    </td>
		    	    <td style="overflow:hidden;text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;
		">
		    	    	<s:property value="#onePay.stepDesc"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.deliverId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.objectId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.objectNum"/>
		    	    </td>
		    	    <td style="overflow:hidden;text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;
		">
		    	    	<s:property value="#onePay.recvDialog"/>
		    	    </td>
		    	    <td style="overflow:hidden;text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;
		">
		    	    	<s:property value="#onePay.deliverDialog"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.reward"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.recycle"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.receive"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.propReward"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.finishType"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.taskType"/>
		    	    </td>
		    	    <td style="overflow:hidden;text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;
		">
		    	    	<s:property value="#onePay.description"/>
		    	    </td> -->
		    	    <td colspan="2">
						 <A href='javascript:deleteMsg("<s:property value="#onePay.taskId"/>")' style="color:green">删除</A>
						 <a href="<%=basePath%>/admin/skill/editBaseTask.action?taskId=${onePay.taskId }" style="color:green">修改</a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
	 <div id="bottomPager"></div>
    
  </div>
  </body>
</html>
