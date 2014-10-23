<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典：游戏副本系统管理</title>
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
            $("#queryCopyForm").submit();
        };
        
        function statPayInfo(){
        	$("#currPageNO").val(1);
            $("#queryCopyForm").submit();
        }
        
        function deleteMsg(msgID) {
			if (confirm("你确定要删除该项吗？")){
				deleteCopyForm.copyId.value = msgID;
				deleteCopyForm.submit();
			}
		}
		
	</script>
  </head>
  
  <body>
  <div id="container">
    <div id="divLeft">
		<b>数据字典：游戏副本系统管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
    <div id="divQuery">
	    <s:form action="queryGameCopy" method="post" theme="simple" id="queryCopyForm">
	    <s:hidden name="currPageNO" id="currPageNO"></s:hidden>
	    	    	副本ID：<s:textfield name="copyId" size="16"></s:textfield>
	    	    	<input type="button" style="cursor:hand" name="stat" value="查询" onclick="statPayInfo();"/>
	    	    	<input name="add" type="button" class="button_02" value="新 增" onclick="window.open('<%=basePath%>/admin/dictionary/addGameCopy.jsp','_self');" >
	    </s:form>
	</div>
	<div id="divLeft">
		<s:form action="deleteGameCopy" method="post" theme="simple" id="deleteCopyForm">
		<s:hidden name="copyId" id="copyId"></s:hidden>
		</s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th>副本ID</th>
	    	    <th>副本地图</th>
	    	    <th>副本最大次数</th>
	    	 	<th>开启条件</th>
	    	    <th>消耗</th>
	    	    <th>事件</th>
	    	    <th>副本完成条件</th>
	      	    <th>后置剧情ID</th>
	    	    <th>前置剧情ID</th>
	    	    <th>操作</th>
	    	</tr>
	    	<s:iterator value="gameCopyList" var="onePay" status="offset">
	    		<tr >
		    	    <td>
						<s:property value="#onePay.copyId"/>
		    	    </td>
		    	    <td>
						<s:property value="#onePay.mapId"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.maxNumber"/>
		    	    </td>
		    	    <td>
		    	    	<label id='lc<s:property value="#onePay.copyId"/>'></label><input type="button" value="详细" onClick='show("c<s:property value="#onePay.copyId"/>");' id='btc<s:property value="#onePay.copyId"/>'>
		    	    	<input type="hidden" id='c<s:property value="#onePay.copyId"/>' value='<s:property value="#onePay.conditionJson"/>'>
		    	    	<table id='tbc<s:property value="#onePay.copyId"/>'>
		    	    	 
		    	    	</table>
		    	    </td>
		    	     <td>
		    	    	<label id='ls<s:property value="#onePay.copyId"/>'></label><input type="button" value="详细" onClick='show("s<s:property value="#onePay.copyId"/>");' id='bts<s:property value="#onePay.copyId"/>'>
		    	    	<input type="hidden" id='s<s:property value="#onePay.copyId"/>' value='<s:property value="#onePay.consumeJson"/>'>
		    	    	<table id='tbs<s:property value="#onePay.copyId"/>'>
		    	    	 
		    	    	</table>
		    	    </td>
		    	    <td>
		    	    	<label id='le<s:property value="#onePay.copyId"/>'></label><input type="button" value="详细" onClick='show("e<s:property value="#onePay.copyId"/>");' id='bte<s:property value="#onePay.copyId"/>'>
		    	    	<input type="hidden" id='e<s:property value="#onePay.copyId"/>' value='<s:property value="#onePay.eventJson"/>'>
		    	    	<table id='tbe<s:property value="#onePay.copyId"/>'>
		    	    	 
		    	    	</table>
		    	    </td>
		    	    <td>
		    	    	<label id='lf<s:property value="#onePay.copyId"/>'></label><input type="button" value='详细' onClick='show("f<s:property value="#onePay.copyId"/>");' id='btf<s:property value="#onePay.copyId"/>'>
		    	    	<input type="hidden" id='f<s:property value="#onePay.copyId"/>' value='<s:property value="#onePay.finishJson"/>'>
		    	    	<table id='tbf<s:property value="#onePay.copyId"/>'>
		    	    	 
		    	    	</table>
		    	    </td>
		    	    <td>
						<s:property value="#onePay.suffixPlotId"/>
		    	    </td>
		    	    <td>
						<s:property value="#onePay.prefixPlotId"/>
		    	    </td>
		    	    <td>
						 <A href='javascript:deleteMsg("<s:property value="#onePay.copyId"/>")' style="color:green">删除</A>
						 <a href="<%=basePath%>/admin/skill/editeGameCopy.action?copyId=${onePay.copyId}" style="color:green">修改</a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
	 <div id="bottomPager"></div>
    
  </div>
  </body>
</html>
