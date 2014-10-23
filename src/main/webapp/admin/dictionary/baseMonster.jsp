<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典：怪物管理</title>
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
            $("#queryMonsterForm").submit();
        };
        
        function statPayInfo(){
        	$("#currPageNO").val(1);
            $("#queryMonsterForm").submit();
        }
        
        function deleteMsg(msgID) {
			if (confirm("你确定要删除该项吗？")){
				deleteMonsterForm.monsterId.value = msgID;
				deleteMonsterForm.submit();
			}
		}
	</script>
  </head>
  
  <body>
  <div id="container">
    <div id="divLeft">
		<b>数据字典：怪物管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
    <div id="divQuery">
	    <s:form action="queryBaseMonster" method="post" theme="simple" id="queryMonsterForm">
	    <s:hidden name="currPageNO" id="currPageNO"></s:hidden>
	    	    	怪物ID：<s:textfield name="monsterId" size="16"></s:textfield>
	    	    	或怪物名称：<s:textfield name="name" size="12"></s:textfield>
	    	    	<input type="button" style="cursor:hand" name="stat" value="查询" onclick="statPayInfo();"/>
	    	    	<input name="add" type="button" class="button_02" value="新 增" onclick="window.open('<%=basePath%>/admin/dictionary/addBaseMonster.jsp','_self');" >
	    </s:form>
	</div>
	<div id="divLeft">
		<s:form action="deleteBaseMonster" method="post" theme="simple" id="deleteMonsterForm">
		<s:hidden name="monsterId" id="monsterId"></s:hidden>
		</s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th>怪物ID</th>
	    	    <th>怪物名称</th>
	    	 	<th>怪物称号</th>
	    	    <th>模型路径</th>
	    	    <th>武学境界</th>
	    	    <th>怪物类型</th>
	    	    <th>技能ID</th>
	    	    <th>品阶</th>
	    	    <th>攻击</th>
	    	    <th>防御</th>
	    	    <th>暴击率</th>
	    	    <th>闪避率</th>
	    	    <th>格挡率</th>
	    	    <th>生命血量</th>
	    	    <th>怪物内力</th>
	    	    <th>AI</th>
	    	    <th>掉落ID</th>
	    	    <th>经验奖励</th>
	    	    <th>潜能奖励</th>
	    	    <th>操作</th>
	    	</tr>
	    	<s:iterator value="baseMonsterList" var="onePay" status="offset">
	    		<tr >
		    	    <td>
						<s:property value="#onePay.monsterId"/>
		    	    </td>
		    	    <td>
						<s:property value="#onePay.name"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.title"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.module"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.level"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.monsterType"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.skillId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.monsterRank"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.acttack"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.defend"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.strikeRate"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.dodgeRate"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.blockRate"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.blood"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.innerForce"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.ai"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.dropId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.experience"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.potential"/>
		    	    </td>
		    	    <td>
						 <A href='javascript:deleteMsg("<s:property value="#onePay.monsterId"/>")' style="color:green">删除</A>
						 <a href="<%=basePath%>/admin/skill/editBaseMonster.action?monsterId=${onePay.monsterId }" style="color:green">修改</a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
	 <div id="bottomPager"></div>
    
  </div>
  </body>
</html>
