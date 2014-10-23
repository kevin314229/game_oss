package com.jcwx.game.admin.initGame;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.admin.vo.PageMessage;
import com.jcwx.game.common.code.util.CodeUtil;


@ParentPackage("base")
@Namespace("/admin/initGame")
@ResultPath("/")
public class InitGameAction extends BaseInfoAction {

    private static final long serialVersionUID = 1L;
    private String result;
    private PageMessage pageMessage = PageMessage.getOkMessage();

    public String getResult() {
	return result;
    }

    @Action(value = "initGame_initArmy", results = { @Result(name = "success", location = "../../admin/initGame/initGame.jsp") })
    public String initArmy() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "InitGameHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		result = CodeUtil.getActionMsgByMap(object);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "initGame_index", results = { @Result(name = "success", location = "../../admin/initGame/initGame.jsp") })
    public String query() throws Exception {
	return SUCCESS;
    }

    public void setResult(String result) {
	this.result = result;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
