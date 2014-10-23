<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典：招式管理</title>
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
            $("#queryActionForm").submit();
        };
        
        function statPayInfo(){
        	$("#currPageNO").val(1);
            $("#queryActionForm").submit();
        }
        
        function deleteMsg(msgID) {
			if (confirm("你确定要删除该技能吗？")){
				deleteActionForm.baseActionId.value = msgID;
				deleteActionForm.submit();
			}
		}
	</script>
  </head>
  
  <body>
  <div id="container">
    <div id="divLeft">
		<b>数据字典：招式管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
    <div id="divQuery">
	    <s:form action="queryBaseAction" method="post" theme="simple" id="queryActionForm">
	    <s:hidden name="currPageNO" id="currPageNO"></s:hidden>
	    	    	招式ID：<s:textfield name="baseActionId" size="16"></s:textfield>
	    	    	或招式名称：<s:textfield name="name" size="12"></s:textfield>
	    	    	<input type="button" style="cursor:hand" name="stat" value="查询" onclick="statPayInfo();"/>
	    	    	<input name="add" type="button" class="button_02" value="新 增" onclick="window.open('<%=basePath%>/admin/dictionary/addBaseAction.jsp','_self');" >
	    </s:form>
	</div>
	<div id="divLeft">
		<s:form action="deleteBaseAction" method="post" theme="simple" id="deleteActionForm">
		<s:hidden name="baseActionId" id="baseActionId"></s:hidden>
		</s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th>招式ID</th>
	    	    <th>招式来源ID</th>
	    	    <th>招式名称</th>
	    	    <th>技能等级</th>
	    	    <th>招式类型</th>
	    	    <th>释放方式</th>
	    	    <th>招式目标</th>
	    	    <th>目标数量</th>
	    	    <th>基础伤害</th>
	    	    <th>内力消耗</th>
	    	    <th>内力加成消耗</th>
	    	    <th>活力消耗</th>
	    	    <th>释放频率</th>
	    	    <th>招式效果</th>
	    	    <th>招式描述</th>
	    	    <th>操作</th>
	    	</tr>
	    	<s:iterator value="baseActionList" var="onePay" status="offset">
	    		<tr >
		    	    <td>
						<s:property value="#onePay.baseActionId"/>
		    	    </td>
		    	    <td>
						<s:property value="#onePay.skillId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.name"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.skillLevel"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.actionType"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.freeType"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.goalType"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.goalNum"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.baseHurt"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.forceConsume"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.forceAddConsume"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.vigourConsume"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.freeTime"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.effect"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.description"/>
		    	    </td>
		    	    <td>
						 <A href='javascript:deleteMsg("<s:property value="#onePay.baseActionId"/>")' style="color:green">删除</A>
						 <a href="<%=basePath%>/admin/skill/editBaseAction.action?baseActionId=${onePay.baseActionId }" style="color:green">修改</a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
	 <div id="bottomPager"></div>
    
  </div>
  </body>
</html>
