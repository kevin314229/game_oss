package com.jcwx.game.admin.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.MD5Service;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.PlayerBaseInfor;
import com.jcwx.game.http.domain.PlayerInfor;
import com.jcwx.game.service.oss.IOssDictService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.util.ServerListToMap;


/** 玩家详细信息 */
@ParentPackage("base")
@Namespace("/send")
@ResultPath("/")
public class SendPlayerInfoAction extends BasalAction {

    /** 氏族 */
    // private ClanCache clanCache;

    private static Logger logger = Logger.getLogger(SendPlayerInfoAction.class);

    private static final long serialVersionUID = -70539059362263612L;

    private Integer playerId;
    
    private Integer serverCode;
    
    private String sign;
    
    private PlayerInfor playerInfor;
    
    private List<PlayerBaseInfor> playerBaseInfors;
    @Autowired
    private IOssDictService dictService;
    /** 查看玩家详细信息 */
    @Action(value = "playerInfo_sendPlayerInfo")
    public void sendPlayerInfo() throws Exception {
	//查询成功
	String  code="0000";
	JSONObject jsonObject=new JSONObject();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("playerId", playerId);
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "queryPlayerDetail");
//	List<OssDictData> dictData = dictService.getOssDictDataList(DictTypeConstant.AREA_COMPARE, DictTypeConstant.GAME_FM);
	
	//传入参数为空
	if(playerId<=0||serverCode<=0){
	    code="1000";
	    jsonObject.put("code", code);
	    getJSONResponse().responseJson(jsonObject.toString());
	    return;
	}
	//加密钥匙不匹配
	String signServer=MD5Service.encryptString("25mnDSghfMV6e1NOmK2PVg=="+playerId+serverCode);
	if(!signServer.equals(sign)){
    	    code="0011";
    	    jsonObject.put("code", code);
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
	//查询数据
	Map<String,String> dictMap=ServerListToMap.queryDictMap(1, 100);
	String areaId = dictMap.get(serverCode+"");
	if(areaId==null){
	    areaId="-1";
	}
	IOssServerService ossServerService = (IOssServerService) SpringService
		.getBean("ossServerService");
	OssServer ossServer = ossServerService.getOssServerByID(Integer.parseInt(areaId));
	//服务器不存在
	if (ossServer == null){
	    code="0111";
	    jsonObject.put("code", code);
	    getJSONResponse().responseJson(jsonObject.toString());
	    return;
	}
	try {
	    object = CONNECTION.interfaceSendMsg(serverCode,object);
	    playerInfor = (PlayerInfor) object.get("playerInfor"); // 玩家集合
	    //该用户不存在角色
	    if (object== null || playerInfor== null) {
		code="0010";
		jsonObject.put("code", code);
		getJSONResponse().responseJson(jsonObject.toString());
		return;
	    }else{
		playerBaseInfors = playerInfor.getPlayerBaseInfors();
		jsonObject.put("list", playerBaseInfors);
	    }
	} catch (Exception e) {
//	    e.printStackTrace();
	    logger.error("系统异常", e);
	    code="0100";
	}
	
	jsonObject.put("code", code);
	getJSONResponse().responseJson(jsonObject.toString());
    }

    public Integer getServerCode() {
        return serverCode;
    }

    public void setServerCode(Integer serverCode) {
        this.serverCode = serverCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public PlayerInfor getPlayerInfor() {
        return playerInfor;
    }

    public void setPlayerInfor(PlayerInfor playerInfor) {
        this.playerInfor = playerInfor;
    }

    public List<PlayerBaseInfor> getPlayerBaseInfors() {
        return playerBaseInfors;
    }

    public void setPlayerBaseInfors(List<PlayerBaseInfor> playerBaseInfors) {
        this.playerBaseInfors = playerBaseInfors;
    }
    
}
