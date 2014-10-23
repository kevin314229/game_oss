
//点击改变和TR的颜色
function changeTRBgColor(obj) {
 	if(obj.tagName!="TR") return;
	var tableObj = obj.parentNode.parentNode;
	for(var i = 1; i < tableObj.rows.length; i++) {
		tableObj.rows[i].style.backgroundColor = (tableObj.rows[i] == obj ? "#FFCB5B" : "");
	}
		
}