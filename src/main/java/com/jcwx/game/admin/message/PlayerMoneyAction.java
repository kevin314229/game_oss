package com.jcwx.game.admin.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.AndPredicate;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.PlayerSdata;
import com.jcwx.game.domain.PlayerSdataVo;
import com.jcwx.game.domain.ZEquipDay;
import com.jcwx.game.domain.ZItemNumberDay;
import com.jcwx.game.domain.ZPlayerMoneyDay;
import com.jcwx.game.service.oss.IOssLogService;


@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class PlayerMoneyAction extends BasalAction {
    private static final Logger logger = Logger
	    .getLogger(PlayerMoneyAction.class);
    private boolean firstFlag;
    ZItemNumberDay itemNumber = new ZItemNumberDay();
    private String loginName;
    private String nickName;
    private Integer oldNumber;
    private List<ZEquipDay> ossEquips;
    private List<ZItemNumberDay> ossItemNumbers;
    @Autowired
    private IOssLogService ossLogService;
    private List<ZPlayerMoneyDay> ossPlayerMoneys;
    PlayerSdata ossPlayerSdata;

    PlayerSdataVo ossPlayerSdataVo;

    ZPlayerMoneyDay playerMoneyDay = new ZPlayerMoneyDay();

    ZEquipDay zequipDay = new ZEquipDay();

    @Action(value = "playermoney_equipmodify", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "playermoney_index", "namespace", "/admin/message",
	    "actionMsg", "${actionMsg}", "loginName", "${loginName}",
	    "nickName", "${nickName}" }) })
    public String equipmodify() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("zequipDay", zequipDay);
	object.put("handlerName", "ZPlayerMoneyHandler");
	object.put("methodName", "modifyEquip");
	try {
	    object = CONNECTION.sendMsg(object);

	    StringBuffer buffer = new StringBuffer();
	    buffer.append("playerItemId:").append(zequipDay.getPlayerItemId())
		    .append(",").append("number:").append(oldNumber.intValue())
		    .append(",").append("playerBaseId:")
		    .append(zequipDay.getPlayerBaseId());

	    ossLogService.createOssLog(ServletActionContext.getRequest(),
		    OssLogConstant.DELETE_GOODS, buffer.toString(), null);

	    setActionMsg(CodeUtil.getActionMsgByMap(object));

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public boolean getFirstFlag() {
	return firstFlag;
    }

    public ZItemNumberDay getItemNumber() {
	return this.itemNumber;
    }

    public String getLoginName() {
	return this.loginName;
    }

    public String getNickName() {
	return this.nickName;
    }

    public Integer getOldNumber() {
	return this.oldNumber;
    }

    public List<ZEquipDay> getOssEquips() {
	return this.ossEquips;
    }

    public List<ZItemNumberDay> getOssItemNumbers() {
	return this.ossItemNumbers;
    }

    public List<ZPlayerMoneyDay> getOssPlayerMoneys() {
	return this.ossPlayerMoneys;
    }

    public PlayerSdata getOssPlayerSdata() {
	return this.ossPlayerSdata;
    }

    public PlayerSdataVo getOssPlayerSdataVo() {
	return this.ossPlayerSdataVo;
    }

    public ZPlayerMoneyDay getPlayerMoneyDay() {
	return this.playerMoneyDay;
    }

    public ZEquipDay getZequipDay() {
	return this.zequipDay;
    }

    /**
     * 
     * @Title: itemModify
     * @Description: 修改物品数量
     * @throws
     */
    @Action(value = "playermoney_itemmodify", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "playermoney_index", "namespace", "/admin/message",
	    "actionMsg", "${actionMsg}", "loginName", "${loginName}",
	    "nickName", "${nickName}" }) })
    public String itemModify() throws Exception {
	int code = 0;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ZPlayerMoneyHandler");
	object.put("methodName", "modifyItemNumber");
	object.put("zItemNumberDay", itemNumber);
	try {
	    object = CONNECTION.sendMsg(object);
	    code = (Integer) object.get("code");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (code == 0) {
	    setActionMsg("OK");
	    StringBuffer buffer = new StringBuffer();
	    buffer.append("itemId:")
		    .append(itemNumber.getItemId())
		    .append(",")
		    .append("number:")
		    .append(oldNumber.intValue()
			    - itemNumber.getItemNumber().intValue())
		    .append(",").append("playerBaseId:")
		    .append(itemNumber.getPlayerBaseId());
	    ossLogService.createOssLog(OssLogConstant.DELETE_GOODS,
		    buffer.toString());
	} else {
	    setActionMsg("ERROR");
	}
	return SUCCESS;
    }

    /**
     * 
     * @Title: modify
     * @Description: 修改玩家金币，魔晶相关的属性 , 不加入到OSS管理员日志，在服务端做日志。
     * @throws
     */
    @Action(value = "playermoney_modify", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "playermoney_index", "namespace", "/admin/message",
	    "actionMsg", "${actionMsg}", "loginName", "${loginName}",
	    "nickName", "${nickName}" }) })
    public String modify() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("zPlayerMoneyDay", playerMoneyDay);
	object.put("createUserId", getBaseAdminContext().getOssUser()
		.getUsername());
	object.put("handlerName", "ZPlayerMoneyHandler");
	object.put("methodName", "modifyPlayerMoneyDay");
	try {
	    object = CONNECTION.sendMsg(object);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	return SUCCESS;
    }

    /**
     * 
     * @Title: query
     * @Description: 查询玩家金币相关属性 当查询到了玩家数量为一个，那么就显示物品修改，装备修改， 如果为多个，就不显示
     * @throws
     */
    @Action(value = "playermoney_index", results = { @Result(name = "success", location = "../../admin/message/playerMoney.jsp") })
    public String query() throws Exception {
	try {
	    if (!firstFlag) {
		return SUCCESS;
	    }
	    if (StringUtils.isEmpty(loginName) && StringUtils.isEmpty(nickName)) {
		setActionMsg(ERROR);
		return SUCCESS;
	    }

	    setZPlayerMoney(getBaseAdminContext());

	} catch (Exception e) {
	    logger.error("query error:", e);
	}
	return "success";
    }

    /**
     * 
     * @Title: query
     * @Description: 查询玩家金币相关属性 当查询到了玩家数量为一个，那么就显示物品修改，装备修改， 如果为多个，就不显示
     * @throws
     */
    @Action(value = "playermoney_detail", results = { @Result(name = "success", location = "../../admin/message/playerMoney.jsp") })
    public String queryDetail() throws Exception {
	try {
	    if (StringUtils.isEmpty(loginName) && StringUtils.isEmpty(nickName)) {
		setActionMsg(ERROR);
		return SUCCESS;
	    }

	    setDetailPlayerMoney(getBaseAdminContext());

	} catch (Exception e) {
	    logger.error("query error:", e);
	}
	return "success";
    }

    /**
     * 
     * @Title: sdataModify
     * @Description: 删除装备
     * @throws
     */
    @Action(value = "playermoney_sdatamodify", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "playermoney_index", "namespace", "/admin/message",
	    "actionMsg", "${actionMsg}", "loginName", "${loginName}",
	    "nickName", "${nickName}" }) })
    public String sdataModify() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ZPlayerMoneyHandler");
	object.put("methodName", "modifyPlayerSdata");
	object.put("playerSdata", ossPlayerSdata);
	try {
	    object = CONNECTION.sendMsg(object);

	    setActionMsg(CodeUtil.getActionMsgByMap(object));

	    ossLogService.createOssLog(ServletActionContext.getRequest(),
		    OssLogConstant.DELETE_GOODS,
		    String.valueOf(object.get("log")), null);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    private void setDetailPlayerMoney(BaseAdminContext baseAdminContext)
	    throws Exception {
	if (playerMoneyDay.getPlayerBaseId() == null) {
	    setActionMsg("详细需要playerBaseId");
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ZPlayerMoneyHandler");
	object.put("loginName", loginName);
	object.put("nickName", nickName);
	object = CONNECTION.sendMsg(object);

	ossPlayerMoneys = (List<ZPlayerMoneyDay>) object
		.get("ossZPlayerMoneyList");

	ZPlayerMoneyDay zmd = (ZPlayerMoneyDay) CollectionUtils.find(
		ossPlayerMoneys, new AndPredicate(new BeanPredicate(
			"loginName", new EqualPredicate(loginName)),
			new BeanPredicate("nickName", new EqualPredicate(
				nickName))));

	ossPlayerMoneys.clear();
	ossPlayerMoneys.add(zmd);
	if (ossPlayerMoneys != null && ossPlayerMoneys.size() == 1
		&& playerMoneyDay != null) {

	    setZItemNumber(baseAdminContext, playerMoneyDay.getPlayerBaseId());
	    setZEquipDay(baseAdminContext, playerMoneyDay.getPlayerBaseId());

	    setZPlayerSdata(baseAdminContext, playerMoneyDay.getPlayerId());
	}
    }

    public void setFirstFlag(boolean firstFlag) {
	this.firstFlag = firstFlag;
    }

    public void setItemNumber(ZItemNumberDay itemNumber) {
	this.itemNumber = itemNumber;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOldNumber(Integer oldNumber) {
	this.oldNumber = oldNumber;
    }

    public void setOssEquips(List<ZEquipDay> ossEquips) {
	this.ossEquips = ossEquips;
    }

    public void setOssItemNumbers(List<ZItemNumberDay> ossItemNumbers) {
	this.ossItemNumbers = ossItemNumbers;
    }

    public void setOssPlayerMoneys(List<ZPlayerMoneyDay> ossPlayerMoneys) {
	this.ossPlayerMoneys = ossPlayerMoneys;
    }

    public void setOssPlayerSdata(PlayerSdata ossPlayerSdata) {
	this.ossPlayerSdata = ossPlayerSdata;
    }

    public void setOssPlayerSdataVo(PlayerSdataVo ossPlayerSdataVo) {
	this.ossPlayerSdataVo = ossPlayerSdataVo;
    }

    public void setPlayerMoneyDay(ZPlayerMoneyDay playerMoneyDay) {
	this.playerMoneyDay = playerMoneyDay;
    }

    public void setZequipDay(ZEquipDay zequipDay) {
	this.zequipDay = zequipDay;
    }

    @SuppressWarnings("unchecked")
    private void setZEquipDay(BaseAdminContext baseAdminContext,
	    Integer playerBaseId) throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ZPlayerMoneyHandler");
	object.put("methodName", "getEquips");
	object.put("playerBaseId", playerBaseId);
	object = CONNECTION.sendMsg(object);

	ossEquips = (List<ZEquipDay>) object.get("ossZEquipDayList");
    }

    @SuppressWarnings("unchecked")
    private void setZItemNumber(BaseAdminContext baseAdminContext,
	    Integer playerBaseId) throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ZPlayerMoneyHandler");
	object.put("methodName", "getItemNumber");
	object.put("loginName", loginName);
	object.put("nickName", nickName);
	object = CONNECTION.sendMsg(object);
	ossItemNumbers = (List<ZItemNumberDay>) object
		.get("ossZItemNumberList");
    }

    @SuppressWarnings("unchecked")
    private void setZPlayerMoney(BaseAdminContext baseAdminContext)
	    throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ZPlayerMoneyHandler");
	object.put("loginName", loginName);
	object.put("nickName", nickName);
	object = CONNECTION.sendMsg(object);

	ossPlayerMoneys = (List<ZPlayerMoneyDay>) object
		.get("ossZPlayerMoneyList");

	if (ossPlayerMoneys != null && ossPlayerMoneys.size() == 1) {

	    setZItemNumber(baseAdminContext, ossPlayerMoneys.get(0)
		    .getPlayerBaseId());
	    setZEquipDay(baseAdminContext, ossPlayerMoneys.get(0)
		    .getPlayerBaseId());

	    setZPlayerSdata(baseAdminContext, ossPlayerMoneys.get(0)
		    .getPlayerId());
	}
    }

    @SuppressWarnings("unchecked")
    private void setZPlayerSdata(BaseAdminContext baseAdminContext,
	    Integer playerId) throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ZPlayerMoneyHandler");
	object.put("methodName", "getPlayerSdataList");
	object.put("playerId", playerId);
	object = CONNECTION.sendMsg(object);

	List<PlayerSdata> playerSdataList = (List<PlayerSdata>) object
		.get("ossPlayerSdataList");

	ossPlayerSdataVo = PlayerSdataVo.convertSdataVoList(playerSdataList,
		playerId);
    }
}
