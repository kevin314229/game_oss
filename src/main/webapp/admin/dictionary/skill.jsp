<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <title>数据字典：玩家技能管理</title>
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
            $("#querySkillForm").submit();
        };
		function deleteMsg(msgID) {
			if (confirm("你确定要删除该技能吗？")){
				deleteSkillForm.skillId.value = msgID;
				deleteSkillForm.submit();
			}
		}
		
		function statPayInfo(){
        	$("#currPageNO").val(1);
            $("#querySkillForm").submit();
        }
	</script>
  </head>
  
  <body>

  	<div id="divLeft">
		<b>数据字典：玩家技能管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
	 <div id="divQuery">
	    <s:form action="queryBaseSkill" method="post" theme="simple" id="querySkillForm">
	    <s:hidden name="currPageNO" id="currPageNO"></s:hidden>
	    	    	技能ID：<s:textfield name="skillId" size="16"></s:textfield>
	    	    	或技能名称：<s:textfield name="name" size="12"></s:textfield>
	    	    	<input type="button" style="cursor:hand" name="stat" value="查询" onclick="statPayInfo();"/>
	    	    	<input name="add" type="button" class="button_02" value="新 增" onclick="window.open('<%=basePath%>/admin/dictionary/addBaseSkill.jsp','_self');" >
	    </s:form>
	</div>
	<div id="divLeft">
		<s:form action="deleteBaseSkill" method="post" theme="simple" id="deleteSkillForm">
		<s:hidden name="skillId" id="skillId"></s:hidden>
		</s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th>技能ID</th>
	    	    <th>名称</th>
	    	    <th>技能类型</th>
	    	    <th>武学类型</th>
	    	    <th>所属门派</th>
	    	    <th>释放速度</th>
	    	    <th>成长值</th>
	    	    <th>描述</th>
	    	    <th>操作</th>
	    	</tr>
	    	<s:iterator value="baseSkillList" var="oneRow"  status="offset">
	    		<tr  >
		    	    <td>
						 <s:property value="#oneRow.skillId"/>
		    	    </td>
		    	    <td>
						 <s:property value="#oneRow.name"/>
		    	    </td>
		    	    <td>
		    	         <s:property value="#oneRow.skillType"/>
		    	    </td>
		    	    <td>
		    	    	 <s:property value="#oneRow.martialType" />
		    	    </td>
		    	    <td>
		    	    	 <s:property value="#oneRow.school" />
		    	    </td>
		    	    <td>
		    	         <s:property value="#oneRow.speed" />
		    	    </td>
		    	    <td>
		    	         <s:property value="#oneRow.growup" />
		    	    </td>
		    	     <td>
		    	         <s:property value="#oneRow.description" />
		    	    </td>
		    	    <td>
						 <A href='javascript:deleteMsg("<s:property value="#oneRow.skillId"/>")' style="color:green">删除</A>
						 <a href="<%=basePath%>/admin/skill/editBaseSkill.action?skillId=${oneRow.skillId }" style="color:green">修改</a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
	 <div id="bottomPager"></div>
  </body>
</html>
