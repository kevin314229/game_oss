package com.jcwx.game.admin.system;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.Hint;
import com.jcwx.game.domain.OssMenu;
import com.jcwx.game.service.oss.IHintService;
import com.jcwx.game.service.oss.IOssMenuService;

/** Oss玩家缓存管理 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class HintAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 添加的 url
    private String pageUrl;
    //接收删除的ids
    private String hintStr;
    
    private Hint hint;
    
    private List<Hint> hintList;
    
    /** 系统菜单列表(树) */
    private List<OssMenu> menuAllListTree;
    
    @Autowired
    private IHintService hintService;

    @Autowired
    private IOssMenuService ossMenuService;

    @Action(value = "hint_index", results = { @Result(name = "success", location = "../../admin/system/hint/hint.jsp") })
    public String query() throws Exception {
	Integer ProjectId = getBaseAdminContext().getProject().getProjectId();
	menuAllListTree = ossMenuService
		.getOssParentMenuListByProjectId(ProjectId);
	if (menuAllListTree != null && !menuAllListTree.isEmpty()) {
	    int n = menuAllListTree.size();
	    // String lastParentId = null;
	    OssMenu ossMenu = null;
	    for (int i = 0; i < n; i++) {
		ossMenu = menuAllListTree.get(i);
		List<OssMenu> temp = ossMenuService
			.getOssMenuListByProjectIdAndParentMenuID(ProjectId,
				ossMenu.getOssMenuID());

		if (temp != null && temp.size() > 0) {
		    menuAllListTree.addAll(i + 1, temp);
		    n += temp.size();
		    i += temp.size();
		}
	    }
	}
	return SUCCESS;
    }

    
    @Action(value = "hint_list", results = { @Result(name = "success", location = "../../admin/system/hint/hint_list.jsp") })
    public String toHint_list() throws Exception {
	hintList=hintService.getHintListByMenuId(hint.getMenuId());
	return SUCCESS;
    }
    
    
    @Action(value = "hint_toAdd", results = { @Result(name = "success", location = "../../admin/system/hint/hint_add.jsp") })
    public String toAddHint() throws Exception {
	return SUCCESS;
    }
    
    protected String getBasePath(){
        String path = httpServletRequest.getContextPath();
        String basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName();
       if(httpServletRequest.getServerPort()!=80){
           basePath += ":"+httpServletRequest.getServerPort()+path;
       }
       return basePath;
        
    }
    
    @Action(value = "hint_doAdd")
    public void doAddHint() throws Exception {
	String code = "0";
	try {
	    String url=pageUrl.replace(".action", "").replace(getBasePath(), "");
	    OssMenu ossMenu = ossMenuService.getOssMenuByPageUrl(
		    getBaseAdminContext().getProject().getProjectId(), url);
	    if (ossMenu != null) {
		hint.setMenuId(ossMenu.getOssMenuID());
		hintService.createHint(hint);
		
		code = "1";
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	getJSONResponse().responseJson(code);
    }

    
    @Action(value = "hint_toUpdate", results = { @Result(name = "success", location = "../../admin/system/hint/hint_update.jsp") })
    public String toUpdateHint() throws Exception {
	
	hint=hintService.getHintByID(hint.getHintId().toString());
	return SUCCESS;
    }
    
    
    @Action(value = "hint_doUpdate")
    public void doUpdateHint() throws Exception {
	String code = "0";
	try {
	    hintService.updateHint(hint);
	    code="1";
	} catch (Exception e) {
	    e.printStackTrace();
	}
	getJSONResponse().responseJson(code);
    }
    
    
    @Action(value = "hint_doDel", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "playerCacheManage_index", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}" }) })
    public void doDelHint() throws Exception {
	String code = "0";
	try {
	    String hintIds[]=hintStr.split(",");
	    for (String hintId : hintIds) {
		hintService.deleteHintByID(hintId);
	    }
	    code = "1";
	} catch (Exception e) {
	    e.printStackTrace();
	}
	getJSONResponse().responseJson(code);
    }

    public Hint getHint() {
	return hint;
    }

    public void setHint(Hint hint) {
	this.hint = hint;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public List<OssMenu> getMenuAllListTree() {
        return menuAllListTree;
    }

    public void setMenuAllListTree(List<OssMenu> menuAllListTree) {
        this.menuAllListTree = menuAllListTree;
    }


    public List<Hint> getHintList() {
        return hintList;
    }


    public void setHintList(List<Hint> hintList) {
        this.hintList = hintList;
    }


    public String getHintStr() {
        return hintStr;
    }


    public void setHintStr(String hintStr) {
        this.hintStr = hintStr;
    }

    
    
    
}
