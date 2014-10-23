package com.jcwx.game.admin.online;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.http.domain.PlayerClass;
import com.jcwx.game.util.Page;


/** 在线与注册：所有注册用户 */
@ParentPackage("base")
@Namespace("/admin/online")
@ResultPath("/")
@Action(value = "queryAllPlayer", results = { @Result(name = "success", location = "../../admin/online/queryAllPlayer.jsp") })
public class QueryAllPlayerAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Page page = Page.createDefaultPage();

    /** 玩家信息 */
    private List<PlayerClass> playerClassList;

    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("orderFlag", page.getOrderFlag());
	object.put("beginNum", page.getBeginNum());
	object.put("onePageNum", page.getOnePageNum());
	object.put("handlerName", "QueryAllPlayerHandler");
	object = CONNECTION.sendMsg(object);
	Integer allNum = (Integer) object.get("allNum");
	page.setAllNum(allNum);
	playerClassList = (List<PlayerClass>) object.get("playerClassList");
	return "success";
    }


    public List<PlayerClass> getPlayerClassList() {
	return playerClassList;
    }

    public void setPlayerClassList(List<PlayerClass> playerClassList) {
	this.playerClassList = playerClassList;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
    
    

}
