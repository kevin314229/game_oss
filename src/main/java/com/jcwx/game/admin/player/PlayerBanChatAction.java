package com.jcwx.game.admin.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.PlayerBanChat;


@ParentPackage("base")
@Namespace("/admin/player")
@ResultPath("/")
public class PlayerBanChatAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 禁言时间 */
    private Integer banChatMinus;

    /** 玩家帐号 */
    private String nickName;

    /** 禁言列表 */
    private List<PlayerBanChat> playerBanChatList;

    /** 角色ID */
    private Integer playerBaseId;

    /** 系统消息 */
    private String sysInfo;

    /**
     * 增加禁言
     * */

    @Action(value = "banChat_addPlayerBanChat", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "banChat_adminPlayerBanChat", "namespace",
	    "/admin/player", "actionMsg", "${actionMsg}" }) })
    public String addPlayerBanChat() throws Exception {
	/*
	 * if(actionMsg!=null && !"".equals(actionMsg)){ actionMsg = new
	 * String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	 * addActionMessage(actionMsg); }
	 */
	Map<String, Object> object = new HashMap<String, Object>();
	Integer code = 0;
	if (nickName != null && banChatMinus != null) {
	    object.put("nickName", nickName);
	    object.put("banChatMinus", banChatMinus);
	    object.put("handlerName", "PlayerBanChatHandler");
	    object.put("methodName", "addPlayerBanChat");
	    try {
		object = CONNECTION.sendMsg(object);
		code = (Integer) object.get("code");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	if (banChatMinus == null) {
	    this.setActionMsg("增加禁言失败,请输入禁言时间！");
	}
	if (code != null && code > 0) {
	    this.setActionMsg("增加禁言成功！");
	} else {
	    this.setActionMsg("增加禁言失败,请输入正确的角色名！");
	}
	return "success";
    }

    @Action(value = "banChat_adminPlayerBanChat", results = { @Result(name = "success", location = "../../admin/player/playerBanChat.jsp") })
    public String adminPlayerBanChat() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "PlayerBanChatHandler");
	try {
	    object = CONNECTION.sendMsg(object);
	    playerBanChatList = (List<PlayerBanChat>) object
		    .get("playerBanChatList");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return "success";
    }

    /**
     * 解除禁言
     * */
    @Action(value = "banChat_deletePlayerBanChat", results = { @Result(name = "success", location = "../../admin/player/playerBanChat.jsp") })
    public String deletePlayerBanChat() throws Exception {

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	Integer code = 0;
	if (playerBaseId != null && playerBaseId.intValue() != 0) {
	    object.put("playerBaseId", playerBaseId);
	    object.put("handlerName", "PlayerBanChatHandler");
	    object.put("methodName", "deletePlayerBanChat");
	    try {
		object = CONNECTION.sendMsg(object);
		code = (Integer) object.get("code");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	if (code != null && code > 0) {
	    this.setActionMsg("解除禁言成功！");
	} else {
	    this.setActionMsg("解除禁言失败！");
	}
	return "success";
    }

    public Integer getBanChatMinus() {
	return banChatMinus;
    }

    public String getNickName() {
	return nickName;
    }

    public List<PlayerBanChat> getPlayerBanChatList() {
	return playerBanChatList;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public String getSysInfo() {
	return sysInfo;
    }

    public void setBanChatMinus(Integer banChatMinus) {
	this.banChatMinus = banChatMinus;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPlayerBanChatList(List<PlayerBanChat> playerBanChatList) {
	this.playerBanChatList = playerBanChatList;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setSysInfo(String sysInfo) {
	this.sysInfo = sysInfo;
    }

}
