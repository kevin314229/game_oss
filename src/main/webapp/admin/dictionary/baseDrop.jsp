<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典：掉落系统管理</title>
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
            $("#queryDropForm").submit();
        };
        
        function statPayInfo(){
        	$("#currPageNO").val(1);
            $("#queryDropForm").submit();
        }
        
        function deleteMsg(msgID) {
			if (confirm("你确定要删除该项吗？")){
				deleteDropForm.dropId.value = msgID;
				deleteDropForm.submit();
			}
		}
		function show(id){
		 var str=document.getElementById(id).value;
		 var json = eval(str);
		 var html;
		 var obj=document.getElementById('tb'+id);
		 if(document.getElementById('bt'+id).value=='详细'){
		 if(obj.rows.length<=0){
		  for(i=0;i<json.length;i++){
		   obj.insertRow(i);
		   obj.rows.item(i).insertCell(0);
		   if(str.indexOf("equipId")>0){
		   html=json[i].equipId+'/'+json[i].rate;
		   }else if(str.indexOf("propId")>0){
		   html=json[i].propId+'/'+json[i].number+'/'+json[i].rate;
		   }
		   obj.rows.item(i).cells.item(0).innerHTML=html;
		  }
		  }else {
		   $('#tb'+id).show();
		  }
		  document.getElementById('bt'+id).value='隐藏';
		  flag=false;
		 }else if(document.getElementById('bt'+id).value=='隐藏'){
		  $('#tb'+id).hide();
		  document.getElementById('bt'+id).value='详细';
		  flag=true;
		 }
		}
		$(document).ready(function(){
			$('label').each(function(i){
				var id = $(this).attr('id');
				id = id.substring(1,id.length);
				var str = $('#'+id).attr('value');
				if(str==null || ""==str){
				 $(this).append('不掉落');
				 $('#bt'+id).hide();
				}else{
					var json = eval(str);
					$(this).append('掉落1-'+json.length+'件');
				}
			});
		});
	</script>
  </head>
  
  <body>
  <div id="container">
    <div id="divLeft">
		<b>数据字典：掉落系统管理</b>
		<span class="color-red"><s:property value="errorInfo"/></span>
		<span class="color-gr"><s:property value="successInfo"/></span>
	</div>
    <div id="divQuery">
	    <s:form action="queryBaseDrop" method="post" theme="simple" id="queryDropForm">
	    <s:hidden name="currPageNO" id="currPageNO"></s:hidden>
	    	    	掉落ID：<s:textfield name="dropId" size="16"></s:textfield>
	    	    	<input type="button" style="cursor:hand" name="stat" value="查询" onclick="statPayInfo();"/>
	    	    	<input name="add" type="button" class="button_02" value="新 增" onclick="window.open('<%=basePath%>/admin/dictionary/addBaseDrop.jsp','_self');" >
	    </s:form>
	</div>
	<div id="divLeft">
		<s:form action="deleteBaseDrop" method="post" theme="simple" id="deleteDropForm">
		<s:hidden name="dropId" id="dropId"></s:hidden>
		</s:form>
	</div>
	<div id="topPager"></div>
	<div id="divList">
	    <table id="row">
	    	<tr>
	    	    <th>掉落ID</th>
	    	    <th>掉落描述</th>
	    	 	<th>银两</th>
	    	    <th>金票/几率</th>
	    	    <th>装备(装备ID/几率)</th>
	    	    <th>道具(道具ID/数量/几率)</th>
	    	    <th>操作</th>
	    	</tr>
	    	<s:iterator value="baseDropList" var="onePay" status="offset">
	    		<tr >
		    	    <td>
						<s:property value="#onePay.dropId"/>
		    	    </td>
		    	    <td>
						<s:property value="#onePay.description"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.silver"/>
		    	    </td>
		    	    <td>
		    	    	<s:property value="#onePay.goldTicket"/>/<s:property value="#onePay.goldTicketRate"/>
		    	    </td>
		    	    <td>
		    	    	<label id='le<s:property value="#onePay.dropId"/>'></label><input type="button" value="详细" onClick='show("e<s:property value="#onePay.dropId"/>");' id='bte<s:property value="#onePay.dropId"/>'>
		    	    	<input type="hidden" id='e<s:property value="#onePay.dropId"/>' value='<s:property value="#onePay.equipJson"/>'>
		    	    	<table id='tbe<s:property value="#onePay.dropId"/>'>
		    	    	 
		    	    	</table>
		    	    </td>
		    	    <td>
		    	    	<label id='lp<s:property value="#onePay.dropId"/>'></label><input type="button" value='详细' onClick='show("p<s:property value="#onePay.dropId"/>");' id='btp<s:property value="#onePay.dropId"/>'>
		    	    	<input type="hidden" id='p<s:property value="#onePay.dropId"/>' value='<s:property value="#onePay.propJson"/>'>
		    	    	<table id='tbp<s:property value="#onePay.dropId"/>'>
		    	    	 
		    	    	</table>
		    	    </td>
		    	    <td>
						 <A href='javascript:deleteMsg("<s:property value="#onePay.dropId"/>")' style="color:green">删除</A>
						 <a href="<%=basePath%>/admin/skill/editBaseDrop.action?dropId=${onePay.dropId }" style="color:green">修改</a>
		    	    </td>
		    	</tr>
	    	</s:iterator>
	    </table>
	 </div>
	 <div id="bottomPager"></div>
    
  </div>
  </body>
</html>
