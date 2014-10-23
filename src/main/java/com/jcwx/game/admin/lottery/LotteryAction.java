package com.jcwx.game.admin.lottery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.MD5Service;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.domain.OssLottery;
import com.jcwx.game.http.domain.SendBaseProperty;
import com.jcwx.game.service.oss.ILotteryService;
import com.jcwx.game.service.oss.IOssLogService;
import com.jcwx.game.util.BocHttpClient;
import com.jcwx.game.util.PropertiesUtil;
import com.jcwx.game.util.ServerListToMap;
import com.jcwx.game.util.transdata.ITransfer;


@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/lottery")
@ResultPath("/")
public class LotteryAction extends BasalAction {
    private static Logger logger = Logger.getLogger(LotteryAction.class);

    private final static String key="25mnDSghfMV6e1NOmK2PVg==";
    
    private final static String key2="l;jkasdlb5^jkalsjdke7f4bfeb3@4466aal;j;lk1925";
    //String转Integer
    private Integer areaId;//服务器ID
    private Integer gold;  //扣除魔晶
    private Integer itemId;
    private Integer itemNum;
    private Integer playerId;
    private Integer playerBaseId;
    
    
    //公共
    private String sign;
    private String  serverId;
    private String gameCode;
   
    
    //查询角色
    private String serverCode;
    private String userId;
    
    //删除魔晶
    private String productId;
    private String roleId;
    
    //发送奖励
    private String packageId;
    private String packageNum;
    private String num;
    private String serialNo;
    private String activityCode;
    private String title;
    private String content;
    
    
    private static Map<Integer, String> serverMap=new HashMap<Integer, String>();
    
    @Autowired
    private ILotteryService lotteryService;
    @Autowired
    private IOssLogService ossLogService;
    /**
     * 登录平台获取服务器列表
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "login")
    public void  login() throws Exception {

	HttpSession session = ServletActionContext.getRequest().getSession();
//	session.

    }

    /**
     *选择服务器获取角色列表
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "queryPlayerInfo")
    public void  queryPlayerInfo() throws Exception {

	//验证信息
	String code="0000";
	JSONObject jsonObject=new JSONObject();
	
	if(serverCode==null||"".equals(serverCode)||userId==null||"".equals(userId)){
	    code="1000";
	    jsonObject.put("code", code);
	    getJSONResponse().responseJson(jsonObject.toString());
	    return;
	}
	try {
	    areaId = Integer.parseInt(serverCode);
	} catch (Exception e1) {
	    code="0100";
    	    jsonObject.put("code", code);
    	    jsonObject.put("msg", "数据类型错误！");
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
	//加密钥匙不匹配
	String signServer=MD5Service.encryptString(userId+serverCode+key2);
	if(!signServer.equals(sign)){
    	    code="0011";
    	    jsonObject.put("code", code);
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
	
	 //从中心查询玩家对应的loginName和大区id，游戏的web访问地址
	String carrierOperator=null; 
    	String notify_addr = null;
    	try {
	    notify_addr = queryServerAddr(areaId, userId);
	} catch (Exception e) {
	    logger.error("系统异常", e);
	    code="1111";
	    jsonObject.put("code",code);
	    jsonObject.put("msg","网络通信异常，获取游戏服务器访问地址失败！");
	    getJSONResponse().responseJson(jsonObject.toString());
	    return;
	}
	if(notify_addr==null||"".equals(notify_addr)){
	    code="0111";
    	    jsonObject.put("code", code);
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	} 
	//从游戏查询对应的角色列表
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("loginName", userId);
	object.put("areaId", areaId);
	object.put("carrierOperator", carrierOperator);
	object.put("methodName", "queryPlayerInfo");
	object.put("handlerName", "LotteryHandle");
	try {
	    object = CONNECTION.sendMsg(notify_addr, key,  
	    	ITransfer.ContentTypeEnum.convertToContentType("application/json;charset=utf-8").getContentType(),object);
		Map<String, Object> returnMap = new HashMap<String, Object>();
	//查询结果转换为json格式返回
		List<Map<String, Object>>playerList = (List<Map<String, Object>>) object.get("playerList");
		jsonObject.put("code", object.get("code"));
		jsonObject.put("list", playerList);
	} catch (Exception e) {
	    logger.error("系统异常", e);
	    code="0100";
	    jsonObject.put("code",code);
	}
	getJSONResponse().responseJson(jsonObject.toString());

    }

   
    /**
     *抽奖删除魔晶
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "deleteGold")
    public void  deleteGold() throws Exception {
	
	String code="1000";
	JSONObject jsonObject=new JSONObject();
	if(roleId==null||"".equals(roleId)||num==null||"".equals(num)){
	    code="1003";
	    jsonObject.put("code", code);
	    getJSONResponse().responseJson(jsonObject.toString());
	    return;
	}
	//加密钥匙不匹配
	String signServer=MD5Service.encryptString(userId+roleId+serverId+gameCode+productId+num+key2);
	if(!signServer.equals(sign)){
    	    code="1010";
    	    jsonObject.put("code", code);
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
	try {
	    areaId = Integer.parseInt(serverId);
	    gold= Integer.parseInt(num);
	    playerBaseId= Integer.parseInt(roleId);
	    
	} catch (Exception e1) {
	    code="0100";
    	    jsonObject.put("code", code);
    	    jsonObject.put("msg", "数据类型错误！");
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
	Map<String, Object> object = new HashMap<String, Object>();
	String notify_addr = queryServerAddr(areaId,userId);
	if(notify_addr==null||"".equals(notify_addr)){
	    code="0111";
    	    jsonObject.put("code", code);
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
	
	object.put("gold", gold);
	
	object.put("playerBaseId", playerBaseId);
	object.put("loginName", userId);
	object.put("methodName", "deleteGold");
	object.put("handlerName", "LotteryHandle");
	try {
	    object = CONNECTION.sendMsg("http://localhost:8081", key, ITransfer.ContentTypeEnum
		    .convertToContentType("application/json;charset=utf-8")
		    .getContentType(), object);
	    int returnCode = (Integer) object.get("code");
	    if(returnCode==1){
		  code="1040";
	    }else if(returnCode==2){
		 code="1002";
	    }else if(returnCode==3){
		 code="1038";
	    }
	} catch (Exception e) {
	    logger.error("系统异常", e);
	    code="1002";
	}
	jsonObject.put("code",code);
	jsonObject.put("num", object.get("num"));
	getJSONResponse().responseJson(jsonObject);
    } 
    /**
     *发送中奖物品
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "sendReward")
    public void  sendReward() throws Exception {
	
	String code="1000";
	JSONObject jsonObject=new JSONObject();
	if(packageId==null||"".equals(packageId)){
	    code="1003";
	    jsonObject.put("code", code);
	    getJSONResponse().responseJson(jsonObject.toString());
	    return;
	}
	//加密钥匙不匹配
	String signServer=MD5Service.encryptString(userId+roleId+serverId+gameCode+serialNo+packageId+activityCode+key2);
	if(!signServer.equals(sign)){
    	    code="1010";
    	    jsonObject.put("code", code);
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
	OssLottery lottery = lotteryService.getOssLotteryByServialNo(serialNo);
	if(lottery!=null&&lottery.getLotteryId()>0){
	    
	    code="1006";
    	    jsonObject.put("code", code);
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
	try {
	    areaId = Integer.parseInt(serverId);
	    playerBaseId = Integer.parseInt(roleId);
	  //  itemId=Integer.parseInt(packageId);
	  //  itemNum=Integer.parseInt(packageNum);
	    //playerId=Integer.parseInt(s)
	} catch (Exception e1) {
	    code="0100";
    	    jsonObject.put("code", code);
    	    jsonObject.put("msg", "数据类型错误！");
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
//	String url = serverMap.get(areaId);
//	if(url==null||"".equals(url)){
//	    url = queryServerAddr(areaId, userId);
//	}
	String notify_addr = queryServerAddr(areaId,userId);
	if(notify_addr==null||"".equals(notify_addr)){
	    code="1039";
    	    jsonObject.put("code", code);
    	    getJSONResponse().responseJson(jsonObject.toString());
    	    return;
	}
//	if(url==null||"".equals(url)){
//	    code="1039";
//    	    jsonObject.put("code", code);
//    	    getJSONResponse().responseJson(jsonObject.toString());
//    	    return;
//	}
	Map<String, Object> object = new HashMap<String, Object>();
	List<String> nickNameList = new ArrayList<String>(); 
	List<SendBaseProperty> sendBaseProperties = new ArrayList<SendBaseProperty>();
	
	JSONArray testArray  = JSONArray.parseArray(packageId);
	for(int i=0;i<testArray.size();i++){
	    SendBaseProperty sendBaseProperty = new SendBaseProperty();  
	    JSONObject temp = (JSONObject) testArray.get(i);
	    sendBaseProperty.setNum(temp.getInteger("num"));
	    sendBaseProperty.setValue(temp.getInteger("itemId"));
	    sendBaseProperties.add(sendBaseProperty);
	}
	//sendBaseProperties = JSONArray.parseObject(packageId, List.class);
//	SendBaseProperty sendBaseProperty = new SendBaseProperty();
//	sendBaseProperty.setNum(itemNum);
//	sendBaseProperty.setValue(itemId);
////	sendBaseProperty.setName(itemName);
//	
//	sendBaseProperties.add(sendBaseProperty);
	nickNameList.add(userId);
	object.put("loginName", userId);
	object.put("flag", 0);
	object.put("nickNameList", nickNameList);
	object.put("sendBasePropertyList", sendBaseProperties);
	object.put("playerBaseId", playerBaseId);
	object.put("flag", 0);
	object.put("title",title);
	object.put("content", content);
	object.put("minGrade", "0");
	object.put("maxGrade", "0");
	object.put("methodName", "sendReward");
	object.put("handlerName", "LotteryHandle");
	OssLottery ossLottery = new OssLottery();
	try {
	    object = CONNECTION.sendMsg(notify_addr, key,  
	    	ITransfer.ContentTypeEnum.convertToContentType("application/json;charset=utf-8").getContentType(),object);
	//userId+roleId+serverId+gameCode+serialNo+packageId+packageNum+gameCode+activityCode+key);
	    ossLottery.setLoginName(userId);
	    ossLottery.setPlayerBaseId(roleId);
	    ossLottery.setAreaId(areaId);
	    ossLottery.setGameCode(gameCode);
	    ossLottery.setSerialNo(serialNo);
	    ossLottery.setItemId(itemId);
	    ossLottery.setItemNum(itemNum);
	    ossLottery.setTitle(title);
	    ossLottery.setContent(content);
	    ossLottery.setActivityCode(activityCode);
	    code =(String) object.get("code");
	    if("1000".equals(code))
		lotteryService.createOssLottery(ossLottery);
	
	} catch (Exception e) {
		e.printStackTrace();
	    logger.error("系统异常", e);
	    code="1002";
//	    ossLogService.createOssLog(OssLogConstant.OSS_LOTTERY,
//	 			"发送奖励失败："+JSONObject.toJSONString(ossLottery));
	}
//	ossLogService.createOssLog(OssLogConstant.OSS_LOTTERY,
//			"发送奖励成功："+JSONObject.toJSONString(ossLottery));
	jsonObject.put("code",code);
	getJSONResponse().responseJson(jsonObject);
    }

    private String queryServerAddr(Integer areaId,String userId) throws Exception {
   	Map<String,String> dictMap=ServerListToMap.queryDictMap(DictTypeConstant.GAME_FM, DictTypeConstant.LOTTERY_INFO);
       	String ver = dictMap.get("ver");
       	String url = dictMap.get("fm_activity_url");
       	if(url==null||"".equals(url)){
       	    url= PropertiesUtil.getValue("fm_activity_url");
       	}
       	Map<String, String> params = new HashMap<String, String>();
       	params.put("areaId", areaId+"");
       	params.put("userName", userId);
       	params.put("ver", ver);
       	String retunJson = BocHttpClient.sendPost(url, params);
       	JSONObject json = JSON.parseObject(retunJson);
       	
       	String notify_addr=json.getString("notify_addr");
       	serverMap.put(areaId, notify_addr);
   	return notify_addr;
       }
    public static void main(String[] args){
	
	String jsonStr="[{'itemId':1212,'num':11},{'itemId':12123,'num':22}]";
	List<SendBaseProperty> sendBaseProperties = new ArrayList<SendBaseProperty>();
	SendBaseProperty sendBaseProperty = new SendBaseProperty();
	sendBaseProperty.setNum(1);
	sendBaseProperty.setValue(2);
	sendBaseProperty.setName("test");
//	sendBaseProperties.add(sendBaseProperty);
	
	SendBaseProperty sendBaseProperty2 = new SendBaseProperty();
	sendBaseProperty2.setNum(2);
	sendBaseProperty2.setValue(4);
	sendBaseProperty2.setName("test2");
//	sendBaseProperties.add(sendBaseProperty);
	
	sendBaseProperties=(List<SendBaseProperty>) JSONArray.parse(jsonStr);
	System.out.println(sendBaseProperties.size());
	
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPlayerBaseId() {
        return playerBaseId;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
        this.playerBaseId = playerBaseId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(String packageNum) {
        this.packageNum = packageNum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
}
