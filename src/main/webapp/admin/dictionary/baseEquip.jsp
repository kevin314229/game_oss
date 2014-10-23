<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典：装备管理</title>
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
            $("#queryEquipForm").submit();
        };
        
        function statPayInfo(){
        	$("#currPageNO").val(1);
            $("#queryEquipForm").submit();
        }
        
        function deleteMsg(msgID) {
			if (confirm("你确定要删除该项吗？")){
				deleteEquipForm.baseEquipId.value = msgID;
				deleteEquipForm.submit();
			}
		}
	</script>
  </head>
  
  <body>
  <div id="container">
    <div id="divLeft">
		<b>数据字典：装备管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
    <div id="divQuery">
	    <s:form action="queryBaseEquip" method="post" theme="simple" id="queryEquipForm">
	    <s:hidden name="currPageNO" id="currPageNO"></s:hidden>
	    	    	装备ID：<s:textfield name="baseEquipId" size="16"></s:textfield>
	    	    	或装备名称：<s:textfield name="name" size="12"></s:textfield>
	    	    	<input type="button" style="cursor:hand" name="stat" value="查询" onclick="statPayInfo();"/>
	    	    	<input name="add" type="button" class="button_02" value="新 增" onclick="window.open('<%=basePath%>/admin/dictionary/addBaseEquip.jsp','_self');" >
	    </s:form>
	</div>
	<div id="divLeft">
		<s:form action="deleteBaseEquip" method="post" theme="simple" id="deleteEquipForm">
		<s:hidden name="baseEquipId" id="baseEquipId"></s:hidden>
		</s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th>装备ID</th>
	    	    <th>装备名称</th>
	    	    <th>模型路径</th>
	    	    <th>装备类型</th>
	    	    <th>是否可交易</th>
	    	    <th>是否可出售</th>
	    	    <th>出售价格</th>
	    	    <th>是否可丢弃</th>
	    	    <th>装备部位</th>
	    	    <th>男路径</th>
	    	    <th>女路径</th>
	    	    <th>图片路径</th>
	    	    <th>装备品质</th>
	    	    <th>门派限制</th>
	    	    <th>性别限制</th>
	    	    <th>等级限制</th>
	    	    <th>装备描述</th>
	    	    <th>操作</th>
	    	</tr>
	    	<s:iterator value="baseEquipList" var="onePay" status="offset">
	    		<tr >
		    	    <td>
						<s:property value="#onePay.baseEquipId"/>
		    	    </td>
		    	    <td>
						<s:property value="#onePay.name"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.moduleName"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.equipType"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.tradeFlag"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.sellFlag"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.sellPrice"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.discardFlag"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.equipPos"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.malePath"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.femalePath"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.imgPath"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.quality"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.schoolFlag"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.sexFlag"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.levelFlag"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.description"/>
		    	    </td>
		    	    <td>
						 <A href='javascript:deleteMsg("<s:property value="#onePay.baseEquipId"/>")' style="color:green">删除</A>
						 <a href="<%=basePath%>/admin/skill/editBaseEquip.action?baseEquipId=${onePay.baseEquipId }" style="color:green">修改</a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
	 <div id="bottomPager"></div>
    
  </div>
  </body>
</html>
