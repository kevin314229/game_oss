package com.jcwx.game.admin.oss.admin.menu;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.oss.admin.menu.service.impl.TestMenuService;
import com.jcwx.game.admin.oss.domain.OssMenu;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.service.impl.IMenuService;

@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin")
@ResultPath("/")
public class OssMenuAction  extends BasalAction{
    
    @Action("test")
    public void action(){
	try {
	    IMenuService service = SpringService.getBean(IMenuService.class);
	    for(OssMenu menu:service.findAll()){
	        System.out.println(menu);
	    }
	    System.out.println("OK");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
