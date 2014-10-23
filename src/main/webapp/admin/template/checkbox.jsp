<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">



function isCheckedAll(obj,checkName,divId){
	if(obj.checked){
		checkAllById(checkName,divId);
	}else{
		uncheckAllById(checkName,divId)
	}
}
function checkAllById(checkName,divId) { 

	var code_Values = $("#"+divId+" input[name='"+checkName+"']"); 
	if(code_Values.length){ 
		for(var i=0;i<code_Values.length;i++) { 
			code_Values[i].checked = true; 
		} 
	}else{ 
		code_Values.checked = true; 
	} 
} 
function uncheckAllById(checkName ,divId) { 
	var code_Values = $("#"+divId+" input[name='"+checkName+"']"); 
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
	var code_Values = $("#"+divId+" input[name='"+checkName+"']"); 
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