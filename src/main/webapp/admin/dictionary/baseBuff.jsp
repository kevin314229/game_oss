<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典：Buff系统管理</title>
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
            $("#queryBuffForm").submit();
        };
        
        function statPayInfo(){
        	$("#currPageNO").val(1);
            $("#queryBuffForm").submit();
        }
        
        function deleteMsg(msgID) {
			if (confirm("你确定要删除该项吗？")){
				deleteBuffForm.buffId.value = msgID;
				deleteBuffForm.submit();
			}
		}
	</script>
  </head>
  
  <body>
  <div id="container">
    <div id="divLeft">
		<b>数据字典：Buff系统管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
    <div id="divQuery">
	    <s:form action="queryBaseBuff" method="post" theme="simple" id="queryBuffForm">
	    <s:hidden name="currPageNO" id="currPageNO"></s:hidden>
	    	    	任务ID：<s:textfield name="buffId" size="16"></s:textfield>
	    	    	<input type="button" style="cursor:hand" name="stat" value="查询" onclick="statPayInfo();"/>
	    	    	<input name="add" type="button" class="button_02" value="新 增" onclick="window.open('<%=basePath%>/admin/dictionary/addBaseBuff.jsp','_self');" >
	    </s:form>
	</div>
	<div id="divLeft">
		<s:form action="deleteBaseBuff" method="post" theme="simple" id="deleteBuffForm">
		<s:hidden name="buffId" id="buffId"></s:hidden>
		</s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th>buffID</th>
	    	    <th>buff名称</th>
	    	 	<th>buff图标</th>
	    	    <th>buff提示信息</th>
	    	    <th>光效文件名</th>
	    	    <th>属性改变方式</th>
	    	    <th>是否可删除</th>
	    	    <th>buff效果</th>
	    	    <th>操作</th>
	    	</tr>
	    	<s:iterator value="baseBuffList" var="onePay" status="offset">
	    		<tr >
		    	    <td>
						<s:property value="#onePay.buffId"/>
		    	    </td>
		    	    <td>
						<s:property value="#onePay.name"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.icon"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.description"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.lightName"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.changeType"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#onePay.deleteFlag"/>
		    	    </td>
		    	     <td>
		    	    	<s:property value="#onePay.effect"/>
		    	    </td>
		    	    <td>
						 <A href='javascript:deleteMsg("<s:property value="#onePay.buffId"/>")' style="color:green">删除</A>
						 <a href="<%=basePath%>/admin/skill/editBaseBuff.action?buffId=${onePay.buffId }" style="color:green">修改</a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
	 <div id="bottomPager"></div>
    
  </div>
  </body>
</html>
