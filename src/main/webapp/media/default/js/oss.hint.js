$(document).ready(function() {
	if(!tipMode){
		return;
	}
				 	$("th,input:visible").jqueryContextMenu({
				 		menuId: 'contextMenu',
				 		onContextMenuItemSelected:function(menuItemId, $triggerElement){
							alert(menuItemId);

						},
						onContextMenuShow:function($triggerElement){
						var nodeSelected = "";
						
						var isStart = false;
						for(var i=$triggerElement.parents().size()-1;o=$triggerElement.parents()[i];i--){
							if(isStart){
								nodeSelected +=getNodeByJquery($(o));
							}
							
							if($(o).attr("class")=="page unitBox"){
								isStart = true;
								continue;
							}
						}
	
						nodeSelected += getNodeByJqueryCurrent($triggerElement);
						
						$("#hintKey").val(nodeSelected);

						},
						showShadow:false
				    })
})

					var nodeNames = ["INPUT","TH","TR","SELECT","BUTTON"];
			
			 function getNodeByJquery(jqueryObject){
			 	if(jqueryObject.prevAll(jqueryObject[0].nodeName).size()>0){
			 		return ">"+jqueryObject[0].nodeName+":eq("+jqueryObject.prevAll(jqueryObject[0].nodeName).size()+")";
			 	}
				return ">"+jqueryObject[0].nodeName;
			 }
			 
			  function getNodeByJqueryCurrent(jqueryObject){
				return ">"+jqueryObject[0].nodeName+":eq("+jqueryObject.prevAll(jqueryObject[0].nodeName).size()+")";
			 }

			 function uniqueNode(jqueryObject){
				return	$(jqueryObject[0].nodeName,jqueryObject.parent()).size()==1;
				
			 }



			function addHint(){
				var pageUrl=document.getElementsByName("pageUrl")[navTab._currentIndex-1].value;
				$.pdialog.open(basePath+"/admin/base/hint_toAdd.action?hint.hintKey="+$("#hintKey").val()+"&pageUrl="+ pageUrl, "1", "提示内容", {
					width : 800,
					height : 400
				});
			}