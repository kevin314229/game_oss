package com.jcwx.game.admin.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.web.InitHint;

@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin")
@ResultPath("/")
public class TipAction extends BasalAction{
    private String url;
    
    @Action("tip")
    public void tip() throws IOException{
	String path = httpServletRequest.getContextPath();
	String basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+path;
	
	url = url.replace(basePath, "");
	int actionIndex = url.indexOf(".action");
	if(actionIndex>-1){
	    url = url.substring(0,actionIndex);
	}
	PrintWriter writerx = httpServletResponse.getWriter();
	httpServletResponse.setContentType("application/javascript;charset=UTF-8");
	StringBuffer buffer = new StringBuffer();
	
	Map<String,String> tipList = InitHint.getHintCache(getBaseAdminContext().getProject().getProjectId(),url);
	buffer.append("$(function(){setTimeout(\"qtipAction()").append("\",1000);});");
	
	buffer.append("function qtipAction(){");
		for(String tip:tipList.keySet()){
		    buffer.append(
			String.format(
		    "$(\"%s\",navTab.getCurrentPanel()).qtip({content:'%s',style: {width:300,padding: 5,color: 'black',textAlign: 'center',border: {width: 5,radius: 5,},tip: 'topRight',name: 'blue'},show:'click',position:{type: 'fixed',corner: {target: 'leftTop',tooltip: 'topRight'} }});"
			    ,tip,tipList.get(tip).replace("\n", "<br/>").replace("\"", "\\\"")));
		}
		buffer.append("}");
	writerx.write(buffer.toString());
	
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
