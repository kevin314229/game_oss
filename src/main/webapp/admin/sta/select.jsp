<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">

/**
 * 选择项目事件改变平台ID
 * gameId 选中的项目ID值
 * ptId   需改变option的平台Id
 */ 
function changePtIdOption(gameId,ptId,areaId){

	$("#"+ptId).empty();
	if(gameId==-1){
		//$("#kpi_areaId").append("<option value='-1'>全部</option>");
		//checkStutKPI();
		//return;
	}
	$.ajax({
    type: "post",//使用post方法访问后台
    url: "${pageContext.request.contextPath}/admin/kpiReport/query_queryOssPtlist.action",
    cache: false,
    global:false,
    data: "gameId="+gameId,//要发送的数据
    success: function(msg){//msg为返回的数据，在这里做数据绑定
     	var myobj=eval(msg);
    	$("#"+ptId).append("<option value='-1'>全部平台</option>");
    	for(var i=0;i<myobj.length;i++){
    		 $("#"+ptId).append("<option value="+myobj[i].serverId+">"+myobj[i].serverProvider+"</option>"); 
    	} 
    },
    error:function(msg1,text){
    	alert(msg1+text);
    }
    
}); 
	optionChangeAreaById(areaId,gameId,"-1");
}
/**
 * 选择项目事件改变平台Code
 * gameId 选中的项目ID值
 * ptId   需改变option的平台Id
 */ 
function changePtCodeOption(gameId,ptId,areaId){
	//alert("${pageContext.request.contextPath}/admin/kpiReport/query_queryOssPtlist.action")
	$("#"+ptId).empty();
	if(gameId==-1){
		//$("#kpi_areaId").append("<option value='-1'>全部</option>");
		//checkStutKPI();
		//return;
	}
	$.ajax({
    type: "post",//使用post方法访问后台
    url: "${pageContext.request.contextPath}/admin/kpiReport/query_queryOssPtlist.action",
    cache: false,
    global:false,
    data: "gameId="+gameId,//要发送的数据
    success: function(msg){//msg为返回的数据，在这里做数据绑定
    	var myobj=eval(msg);
    	$("#"+ptId).append("<option value=''>全部平台</option>");
    	for(var i=0;i<myobj.length;i++){
    		$("#"+ptId).append("<option value="+myobj[i].serverCode+">"+myobj[i].serverProvider+"</option>");
    	}
    },
    error:function(msg1,text){
    	alert(msg1+text);
    }
    
});
	
	optionChangeAreaByCode(areaId,gameId,"");
}

/**
 * 改变服务器列表option
 * areaId 服务器select标签ID值
 * gameId   选择项目值
 * ptId     选择的游戏平台值 serverId
 */
function optionChangeAreaById(areaId,gameId,ptId){
	$("#"+areaId).empty();
	$.ajax({
    type: "post",//使用post方法访问后台
    url: "${pageContext.request.contextPath}/admin/kpiReport/query_queryOssServerByPtId.action",
    cache: false,
    global:false,
    data:  "gameId="+gameId+"&ptId="+ptId,//要发送的数据
    success: function(msg){//msg为返回的数据，在这里做数据绑定
    	var myobj=eval(msg);
    	$("#"+areaId).append("<option value='-1'>全部服务器</option>");
    	for(var i=0;i<myobj.length;i++){
    		$("#"+areaId).append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
    	}
    },
    error:function(msg1,text){
    	alert(msg1+text);
    }
    
	});
}
/**
 * 改变服务器列表option
 * areaId 服务器select标签ID值
 * gameId   选择项目值
 * ptCode     选择的游戏平台值 serverCode
 */
function optionChangeAreaByCode(areaId,gameId,ptCode){
	$("#"+areaId).empty();
	$.ajax({
    type: "post",//使用post方法访问后台
    url: "${pageContext.request.contextPath}/admin/kpiReport/query_queryOssServerByPtCode.action",
    cache: false,
    global:false,
    data: "gameId="+gameId+"&ptCode="+ptCode,//要发送的数据
    success: function(msg){//msg为返回的数据，在这里做数据绑定
    	var myobj=eval(msg);
    	$("#"+areaId).append("<option value='-1'>全部服务器</option>");
    	for(var i=0;i<myobj.length;i++){
    		$("#"+areaId).append("<option value="+myobj[i].id+">"+myobj[i].name+"</option>");
    	}
    },
    error:function(msg1,text){
    	alert(msg1+text);
    }
	});
}
/**
 * 点击平台初始化相关参数
 * areaId 服务器select标签ID值
 * gameId   项目select标签ID值
 * ptId     选择的游戏平台值 serverId
 */
function initPtIdParam(gameId,ptId,areaId){
	var gameValue=$("#"+gameId).val();
	optionChangeAreaById(areaId,gameValue,ptId);
}
/**
 * 点击平台初始化相关参数
 * areaId 服务器select标签ID值
 * gameId   项目select标签ID值
 * ptCode     选择的游戏平台值 serverCode
 */
function initPtCodeParam(gameId,ptId,areaId){
	var gameValue=$("#"+gameId).val();
	optionChangeAreaByCode(areaId,gameValue,ptId);
}

function isChecked(obj,checkName,divId){
	if(obj.checked){
		checkAll(checkName,divId);
	}else{
		uncheckAll(checkName,divId)
	}
}
function checkAll(checkName,divId) { 

	var code_Values = $("#"+divId+" input[type=checkbox]"); 
	if(code_Values.length){ 
		for(var i=0;i<code_Values.length;i++) { 
			code_Values[i].checked = true; 
		} 
	}else{ 
		code_Values.checked = true; 
	} 
} 
function uncheckAll(checkName ,divId) { 
	var code_Values = $("#"+divId+" input[type=checkbox]"); 
	if(code_Values.length){ 
		for(var i=0;i<code_Values.length;i++) { 
			code_Values[i].checked = false; 
		} 
	}else{ 
		code_Values.checked = false; 
	} 
} 
function multipleDelete(checkName,divId) { 
	var num = 0; 
	var code_Values = $("#"+divId+" input[type=checkbox]"); 
	if(code_Values.length){ 
		for(var i=0;i<code_Values.length;i++){ 
			if(code_Values[i].checked == true) { 
				num ++; 
			} 
		} 
	}else{ 
		if(code_Values.checked == true){ 
			num ++ ; 
		} 
	} 
	if(num == 0){ 
		alert('Please select delete item'); 
	} 
	if(num >0){ 
	} 
} 
</script>