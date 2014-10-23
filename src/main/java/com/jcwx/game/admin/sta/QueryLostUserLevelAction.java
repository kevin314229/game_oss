package com.jcwx.game.admin.sta;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.ZQueryActiveUser;
import com.jcwx.game.domain.ZQueryLostUserLevel;
import com.jcwx.game.domain.ZQueryLostUserLevelDetail;
import com.jcwx.game.service.IQueryActiveUserService;
import com.jcwx.game.service.IQueryLostUserLevelDetail;
import com.jcwx.game.service.IQueryLostUserLevelService;
import com.jcwx.game.service.oss.impl.OssUserServerService;
import com.jcwx.game.util.ServerListToMap;

@ParentPackage("base")
@Namespace("/admin/queryLostUser")
@ResultPath("/")
/**
 * 查看活跃用户留存率
 * @author Administrator
 *
 */
public class QueryLostUserLevelAction extends BasalAction {

    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;

    /** 具体流失用户所在大区id */

    private String areaId;

    /** 开始等级 */
    private Integer beginLevel;

    private Date beginTime;

    /** 当前页数 */
    private Integer currPageNO;

    /** 终止等级 */
    private Integer endLevel;

    private Date endTime;

    /**
     * 用来判断是否添加超链接查看流失玩家详细 当选中的数组长度大于1时说明选中了多个服务器，不用附加详细， 或者单选中所有服务器的时候也不用附加
     * */
    private int firstElementOfSelectArray;

    /** 游戏标识 **/
    private Integer gameId;
    private int lengthofSelectArray;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 服务器列表 */
    private List<OssServer> ossServerList;

    /** 平台列表 */
    private List<OssServer> OssServersPt;

    @Autowired
    private OssUserServerService ossUserServerService;

    /** 总页数 */
    private Integer pages;
    /** 具体流失某用户的等级 */
    private Integer playerLevel;
    private String ptId;
    /**
     * 七日流失玩家数量分析，借助登陆分析做差即可
     */
    private List<ZQueryActiveUser> queryActiveUserList;
    /** 流失详细信息 */
    private List<ZQueryLostUserLevelDetail> queryLostUserLevelDetailList;
    private List<ZQueryLostUserLevel> queryLostUserlevelList;
    /** 查询类型用于区别查询的是流失详细或是留存详细 */
    private String queryType;

    /** 大区列表 */
    private String[] selectArray;
    private String selectString;
    private Map<String, String> serverMap;

    /** 年月日格式的统计日期 */
    private Date statisticsDate;
    private String[] totalList;

    @Override
    @Action(value = "queryLostUser_Level", results = { @Result(name = "success", location = "../../admin/sta/queryLostUserLevel.jsp") })
    public String execute() throws Exception {
	IQueryLostUserLevelService queryLostUserLevelService = (IQueryLostUserLevelService) SpringService
		.getBean("queryLostUserLevelService");
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0) {
	    beginNum = 0;
	}
	if (beginTime == null || beginTime.toString().equals("")) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null || endTime.toString().equals("")) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	}
	if (beginLevel == null || beginLevel == 0) {
	    beginLevel = 1;
	}
	if (endLevel == null || endLevel == 0) {
	    endLevel = 20;
	}
//	List<OssServer> list = getBaseAdminContext().getOssServers();
//	if (gameId == null || gameId == 0) {
//	    gameId = getBaseAdminContext().getProject().getProjectId();
//	    OssServersPt = getBaseAdminContext().getOssServersPt();
//	    list = getBaseAdminContext().getOssServers();
//
//	} else {
//	    OssServersPt = ossUserServerService
//		    .getOssServerPtListByUserAndProjectId(getBaseAdminContext()
//			    .getOssUser().getUsername(), gameId);
//	    list = ossUserServerService.getOssServerListByUserAndProjectId(
//		    getBaseAdminContext().getOssUser().getUsername(), gameId);
//	}
	
	/*if (ptId != null && !ptId.equals("-1")) {
	    serverMap = new LinkedHashMap<String, String>();
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId().toString().equals(ptId)) {
		    serverMap.put(ossServer.getId().toString(),
			    ossServer.getName());
		}
	    }
	    if (selectArray != null && selectArray.length > 0) {
		if (selectArray.length == 1) {
		    selectArray = selectArray[0].split(",");
		}
		firstElementOfSelectArray = Integer.parseInt(selectArray[0]);
		lengthofSelectArray = selectArray.length;
		StringBuffer sb = new StringBuffer();
		if (selectArray[0].equals("-1")) { // -1 为全选
		    for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getServerId().toString().equals(ptId)) {
			    sb.append(list.get(i).getId().toString() + ",");
			}
		    }
		    sb.deleteCharAt(sb.length() - 1);
		    selectString = sb.toString();
		} else {
		    for (int i = 0; i < selectArray.length; i++) {
			sb.append(selectArray[i] + ",");
		    }
		    sb.deleteCharAt(sb.length() - 1);
		    selectString = sb.toString();
		}
	    } else {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
		    if (list.get(i).getServerId().toString().equals(ptId)) {
			sb.append(list.get(i).getId().toString() + ",");
		    }
		}
		sb.deleteCharAt(sb.length() - 1);
		selectString = sb.toString();
	    }
	    // 将字符串数组转化为字符串传到sql

	} else {
	    if (selectArray != null && selectArray.length > 0) {
		if (selectArray.length == 1) {
		    selectArray = selectArray[0].split(",");
		}

		firstElementOfSelectArray = Integer.parseInt(selectArray[0]);
		lengthofSelectArray = selectArray.length;
		StringBuffer sb = new StringBuffer();
		if (selectArray[0].equals("-1")) { // -1 为全选
		    String serverArray[] = new String[list.size()];
		    for (int i = 0; i < list.size(); i++) {
			OssServer ossServer = list.get(i);
			serverArray[i] = ossServer.getId().toString();
		    }
		    selectArray = serverArray;
		}
		for (int i = 0; i < selectArray.length; i++) {
		    sb.append(selectArray[i] + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		selectString = sb.toString();
	    } else {
		StringBuffer sb = new StringBuffer();
		String serverArray[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
		    OssServer ossServer = list.get(i);
		    serverArray[i] = ossServer.getId().toString();
		}
		selectArray = serverArray;
		for (int i = 0; i < selectArray.length; i++) {
		    sb.append(selectArray[i] + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		selectString = sb.toString();
	    }
	    serverMap = new LinkedHashMap<String, String>();
	    for (OssServer ossServer : list) {
		serverMap
			.put(ossServer.getId().toString(), ossServer.getName());
	    }
	}*/
	 List<OssServer> list = new ArrayList<OssServer>();
	 gameId = getBaseAdminContext().getProject().getProjectId();
	 OssServersPt = getBaseAdminContext().getOssServersPt();
	 list = getBaseAdminContext().getOssServers();
	 if (ptId != null && !"-1".equals(ptId)) {
	     serverMap = ServerListToMap.convert(list, Integer.parseInt(ptId));
	 } else {
	     serverMap = ServerListToMap.convert(list);
	 }
//	    if(!"-1".equals(ptId)||(selectArray.length>0) )
	selectString = ServerListToMap.arrayToStringByPtId(list, selectArray, ptId);
	if (selectArray != null && selectArray.length == 1) {
		//		selectArray=selectArray[0].split(",");
	    selectArray = ServerListToMap.strToArr(selectString);
	}
	List<ZQueryLostUserLevel> tempList = new ArrayList<ZQueryLostUserLevel>();
	queryLostUserlevelList = new ArrayList<ZQueryLostUserLevel>();
	tempList = queryLostUserLevelService.QueryLostUserLevelByDay(beginTime,
		endTime, beginNum, onePageNum, gameId, selectString);
	if (tempList.size() > 0) {
	    int tempIndex = 0;
	    String str = tempList.get(tempIndex).getFinalString();
	    Date tempDate1 = tempList.get(tempIndex).getStatisticsDate();
	    Integer tempGameId = tempList.get(tempIndex).getGameId();
	    for (int i = 1; i < tempList.size(); i++) {
		Date tempDate2 = tempList.get(i).getStatisticsDate();
		String[] str1 = str.split("#");
		if (tempDate1.getTime() == tempDate2.getTime()) {
		    String[] str2 = tempList.get(i).getFinalString().split("#");
		    for (int j = 0; j < str2.length; j++) {
			// 累加并且附上#

			str1[j] = (Integer.valueOf(str1[j]) + Integer
				.valueOf(str2[j])) + "#";

		    }
		    StringBuffer sb = new StringBuffer();
		    for (int t = 0; t < str1.length; t++) {
			sb.append(str1[t]);
		    }
		    str = sb.toString();
		} else {
		    ZQueryLostUserLevel tempLostUserLevel = new ZQueryLostUserLevel();
		    tempLostUserLevel.setStatisticsDate(tempDate1);
		    tempLostUserLevel.setGameId(tempGameId);
		    tempLostUserLevel.setFinalString(str);
		    queryLostUserlevelList.add(tempLostUserLevel);
		    tempIndex = i;
		    str = tempList.get(tempIndex).getFinalString();
		    tempDate1 = tempList.get(tempIndex).getStatisticsDate();
		    tempGameId = tempList.get(tempIndex).getGameId();
		}
	    }
	    ZQueryLostUserLevel tempLostUserLevel = new ZQueryLostUserLevel();
	    tempLostUserLevel.setStatisticsDate(tempDate1);
	    tempLostUserLevel.setGameId(tempGameId);
	    tempLostUserLevel.setFinalString(str);
	    queryLostUserlevelList.add(tempLostUserLevel);

	}

	/*
	 * allNum = (Integer) queryLostUserLevelService
	 * .QueryLostUserLevelAllrecord(beginTime, endTime, gameId,
	 * selectString);// 总记录数 if (allNum == null) { allNum = 0; } pages =
	 * allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum /
	 * onePageNum; // 当前页设置 if (currPageNO.intValue() > pages) { currPageNO
	 * = pages; }
	 */

	return SUCCESS;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public String getAreaId() {
	return areaId;
    }

    public Integer getBeginLevel() {
	return beginLevel;
    }

    public Date getBeginTime() {
	return beginTime;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public Integer getEndLevel() {
	return endLevel;
    }

    public Date getEndTime() {
	return endTime;
    }

    public int getFirstElementOfSelectArray() {
	return firstElementOfSelectArray;
    }

    public Integer getGameId() {
	return gameId;
    }

    public int getLengthofSelectArray() {
	return lengthofSelectArray;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    public List<OssServer> getOssServersPt() {
	return OssServersPt;
    }

    public OssUserServerService getOssUserServerService() {
	return ossUserServerService;
    }

    public Integer getPages() {
	return pages;
    }

    public Integer getPlayerLevel() {
	return playerLevel;
    }

    public String getPtId() {
	return ptId;
    }

    public List<ZQueryActiveUser> getQueryActiveUserList() {
	return queryActiveUserList;
    }

    public List<ZQueryLostUserLevelDetail> getQueryLostUserLevelDetailList() {
	return queryLostUserLevelDetailList;
    }

    public List<ZQueryLostUserLevel> getQueryLostUserlevelList() {
	return queryLostUserlevelList;
    }

    public String getQueryType() {
	return queryType;
    }

    public String[] getSelectArray() {
	return selectArray;
    }

    public String getSelectString() {
	return selectString;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    public Date getStatisticsDate() {
	return statisticsDate;
    }

    public String[] getTotalList() {
	return totalList;
    }

    @Action(value = "queryLostUser_LevelDetail", results = { @Result(name = "success", location = "../../admin/sta/queryLostUserLevelDetail.jsp") })
    public String querLostUserLevelDetail() {
	IQueryLostUserLevelDetail queryLostUserLevelDetail = (IQueryLostUserLevelDetail) SpringService
		.getBean("queryLostUserLevelDetail");
	Date firstTime = DateService.getDateFirstTime(statisticsDate);
	Date lastTime = DateService.getDateLastTime(statisticsDate);
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("gameId", gameId);
	object.put("areaId", areaId);
	object.put("firstTime", firstTime);
	object.put("lastTime", lastTime);
	object.put("playerLevel", playerLevel);
	queryLostUserLevelDetailList = new ArrayList<ZQueryLostUserLevelDetail>();
	// 1代表流失，2代表留存
	if (queryType.equals("1")) {
	    queryLostUserLevelDetailList = queryLostUserLevelDetail
		    .getQueryLostUserLevelDetailList(firstTime, lastTime,
			    gameId, areaId, playerLevel);
	} else {
	    queryLostUserLevelDetailList = queryLostUserLevelDetail
		    .getQueryActiveUserLevelDetailList(firstTime, lastTime,
			    gameId, areaId, playerLevel);
	}

	return SUCCESS;

    }

    @Action(value = "queryActiveUser_Level", results = { @Result(name = "success", location = "../../admin/sta/queryActiveUserLevel.jsp") })
    public String queryActiveUserLevel() throws Exception {
	IQueryLostUserLevelService queryLostUserLevelService = (IQueryLostUserLevelService) SpringService
		.getBean("queryLostUserLevelService");
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0) {
	    beginNum = 0;
	}
	if (beginTime == null || beginTime.toString().equals("")) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null || endTime.toString().equals("")) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	}
	if (beginLevel == null || beginLevel == 0) {
	    beginLevel = 1;
	}
	if (endLevel == null || endLevel == 0) {
	    endLevel = 20;
	}
	List<OssServer> list = new ArrayList<OssServer>();
	gameId = getBaseAdminContext().getProject().getProjectId();
	OssServersPt = getBaseAdminContext().getOssServersPt();
	list = getBaseAdminContext().getOssServers();
	if (ptId != null && !"-1".equals(ptId)) {

	    serverMap = ServerListToMap.convert(list, Integer.parseInt(ptId));
	} else {
	    serverMap = ServerListToMap.convert(list);
	}
	if(!"-1".equals(ptId)||(selectArray.length>0) )
	selectString = ServerListToMap.arrayToStringByPtId(list, selectArray, ptId);
	if (selectArray != null && selectArray.length == 1) {
		//		selectArray=selectArray[0].split(",");
	    selectArray = ServerListToMap.strToArr(areaId);
	}
	List<ZQueryLostUserLevel> tempList = new ArrayList<ZQueryLostUserLevel>();
	queryLostUserlevelList = new ArrayList<ZQueryLostUserLevel>();
	tempList = queryLostUserLevelService.QueryActiveUserLevelByDay(
		beginTime, endTime, beginNum, onePageNum, gameId, selectString);
	if (tempList.size() > 0) {
	    int tempIndex = 0;
	    String str = tempList.get(tempIndex).getFinalString();
	    Date tempDate1 = tempList.get(tempIndex).getStatisticsDate();
	    Integer tempGameId = tempList.get(tempIndex).getGameId();
	    for (int i = 1; i < tempList.size(); i++) {
		Date tempDate2 = tempList.get(i).getStatisticsDate();
		String[] str1 = str.split("#");
		if (tempDate1.getTime() == tempDate2.getTime()) {
		    String[] str2 = tempList.get(i).getFinalString().split("#");
		    for (int j = 0; j < str2.length; j++) {
			// 累加并且附上#

			str1[j] = (Integer.valueOf(str1[j]) + Integer
				.valueOf(str2[j])) + "#";

		    }
		    StringBuffer sb = new StringBuffer();
		    for (int t = 0; t < str1.length; t++) {
			sb.append(str1[t]);
		    }
		    str = sb.toString();
		} else {
		    ZQueryLostUserLevel tempLostUserLevel = new ZQueryLostUserLevel();
		    tempLostUserLevel.setStatisticsDate(tempDate1);
		    tempLostUserLevel.setGameId(tempGameId);
		    tempLostUserLevel.setFinalString(str);
		    queryLostUserlevelList.add(tempLostUserLevel);
		    tempIndex = i;
		    str = tempList.get(tempIndex).getFinalString();
		    tempDate1 = tempList.get(tempIndex).getStatisticsDate();
		    tempGameId = tempList.get(tempIndex).getGameId();
		}
	    }
	    ZQueryLostUserLevel tempLostUserLevel = new ZQueryLostUserLevel();
	    tempLostUserLevel.setStatisticsDate(tempDate1);
	    tempLostUserLevel.setGameId(tempGameId);
	    tempLostUserLevel.setFinalString(str);
	    queryLostUserlevelList.add(tempLostUserLevel);

	}

	/*
	 * allNum = (Integer) queryLostUserLevelService
	 * .QueryLostUserLevelAllrecord(beginTime, endTime, gameId,
	 * selectString);// 总记录数 if (allNum == null) { allNum = 0; } pages =
	 * allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum /
	 * onePageNum; // 当前页设置 if (currPageNO.intValue() > pages) { currPageNO
	 * = pages; }
	 */

	return SUCCESS;
    }

    @Action(value = "queryLostUser_Count", results = { @Result(name = "success", location = "../../admin/sta/queryLostUserCount.jsp") })
    public String queryLostUser() {
	IQueryActiveUserService queryActiveUserService = (IQueryActiveUserService) SpringService
		.getBean("queryActiveUserService");

	try {
	    if (onePageNum == null || onePageNum.intValue() == 0) {
		onePageNum = 20;
	    }
	    if (currPageNO == null || currPageNO.intValue() == 0) {
		currPageNO = 1;
	    }
	    Integer beginNum = (currPageNO - 1) * onePageNum;
	    if (beginNum < 0) {
		beginNum = 0;
	    }
	    if (beginTime == null || beginTime.toString().equals("")) {
		beginTime = DateService.getCurrentMonthFirstDay();
	    }
	    if (endTime == null || endTime.toString().equals("")) {
		endTime = DateService.getCurrentDayLastUtilDate();
	    }
	    //	List<OssServer> list = getBaseAdminContext().getOssServers();
	    //	if (gameId == null || gameId == 0) {
	    //	    gameId = getBaseAdminContext().getProject().getProjectId();
	    //	    OssServersPt = getBaseAdminContext().getOssServersPt();
	    //	    list = getBaseAdminContext().getOssServers();
	    //
	    //	} else {
	    //	    OssServersPt = ossUserServerService
	    //		    .getOssServerPtListByUserAndProjectId(getBaseAdminContext()
	    //			    .getOssUser().getUsername(), gameId);
	    //	    list = ossUserServerService.getOssServerListByUserAndProjectId(
	    //		    getBaseAdminContext().getOssUser().getUsername(), gameId);
	    //	}
	    //	if (ptId != null && !ptId.equals("-1")) {
	    //	    serverMap = new LinkedHashMap<String, String>();
	    //	    for (OssServer ossServer : list) {
	    //		if (ossServer.getServerId().toString().equals(ptId)) {
	    //		    serverMap.put(ossServer.getId().toString(),
	    //			    ossServer.getName());
	    //		}
	    //	    }
	    //	    if (selectArray != null && selectArray.length > 0) {
	    //		StringBuffer sb = new StringBuffer();
	    //		if (selectArray[0].equals("-1")) { // -1 为全选
	    //		    for (int i = 0; i < list.size(); i++) {
	    //			if (list.get(i).getServerId().toString().equals(ptId)) {
	    //			    sb.append(list.get(i).getId().toString() + ",");
	    //			}
	    //		    }
	    //		    sb.deleteCharAt(sb.length() - 1);
	    //		    selectString = sb.toString();
	    //		} else {
	    //		    for (int i = 0; i < selectArray.length; i++) {
	    //			sb.append(selectArray[i] + ",");
	    //		    }
	    //		    sb.deleteCharAt(sb.length() - 1);
	    //		    selectString = sb.toString();
	    //		}
	    //	    } else {
	    //		StringBuffer sb = new StringBuffer();
	    //		for (int i = 0; i < list.size(); i++) {
	    //		    if (list.get(i).getServerId().toString().equals(ptId)) {
	    //			sb.append(list.get(i).getId().toString() + ",");
	    //		    }
	    //		}
	    //		sb.deleteCharAt(sb.length() - 1);
	    //		selectString = sb.toString();
	    //	    }
	    //	    // 将字符串数组转化为字符串传到sql
	    //
	    //	} else {
	    //	    if (selectArray != null && selectArray.length > 0) {
	    //		StringBuffer sb = new StringBuffer();
	    //		if (selectArray[0].equals("-1")) { // -1 为全选
	    //		    String serverArray[] = new String[list.size()];
	    //		    for (int i = 0; i < list.size(); i++) {
	    //			OssServer ossServer = list.get(i);
	    //			serverArray[i] = ossServer.getId().toString();
	    //		    }
	    //		    selectArray = serverArray;
	    //		}
	    //		for (int i = 0; i < selectArray.length; i++) {
	    //		    sb.append(selectArray[i] + ",");
	    //		}
	    //		sb.deleteCharAt(sb.length() - 1);
	    //		selectString = sb.toString();
	    //	    } else {
	    //		StringBuffer sb = new StringBuffer();
	    //		String serverArray[] = new String[list.size()];
	    //		for (int i = 0; i < list.size(); i++) {
	    //		    OssServer ossServer = list.get(i);
	    //		    serverArray[i] = ossServer.getId().toString();
	    //		}
	    //		selectArray = serverArray;
	    //		for (int i = 0; i < selectArray.length; i++) {
	    //		    sb.append(selectArray[i] + ",");
	    //		}
	    //		sb.deleteCharAt(sb.length() - 1);
	    //		selectString = sb.toString();
	    //	    }
	    //
	    //	    serverMap = new LinkedHashMap<String, String>();
	    //	    for (OssServer ossServer : list) {
	    //		serverMap
	    //			.put(ossServer.getId().toString(), ossServer.getName());
	    //	    }
	    //	}
	    List<OssServer> list = new ArrayList<OssServer>();
	    gameId = getBaseAdminContext().getProject().getProjectId();
	    OssServersPt = getBaseAdminContext().getOssServersPt();
	    list = getBaseAdminContext().getOssServers();
	    if (ptId != null && !"-1".equals(ptId)) {

		serverMap = ServerListToMap.convert(list, Integer.parseInt(ptId));
	    } else {
		serverMap = ServerListToMap.convert(list);
	    }
//	    if(!"-1".equals(ptId)||(selectArray.length>0) )
	    selectString = ServerListToMap.arrayToStringByPtId(list, selectArray, ptId);
	    if (selectArray != null && selectArray.length == 1) {
		//		selectArray=selectArray[0].split(",");
		selectArray = ServerListToMap.strToArr(selectString);
	    }
	    queryActiveUserList = queryActiveUserService.QueryActiveUserByDay(
		    beginTime, endTime, beginNum, onePageNum, gameId,
		    selectString);
	    allNum = queryActiveUserService.QueryActiveUserAllNum(beginTime,
		    endTime, gameId, selectString);// 总记录数
	    if (allNum == null) {
		allNum = 0;
	    }
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		    / onePageNum;
	    // 当前页设置
	    if (currPageNO.intValue() > pages) {
		currPageNO = pages;
	    }
	} catch (Exception e) {
	   e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setAreaId(String areaId) {
	this.areaId = areaId;
    }

    public void setBeginLevel(Integer beginLevel) {
	this.beginLevel = beginLevel;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndLevel(Integer endLevel) {
	this.endLevel = endLevel;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setFirstElementOfSelectArray(int firstElementOfSelectArray) {
	this.firstElementOfSelectArray = firstElementOfSelectArray;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setLengthofSelectArray(int lengthofSelectArray) {
	this.lengthofSelectArray = lengthofSelectArray;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public void setOssServersPt(List<OssServer> ossServersPt) {
	OssServersPt = ossServersPt;
    }

    public void setOssUserServerService(
	    OssUserServerService ossUserServerService) {
	this.ossUserServerService = ossUserServerService;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPlayerLevel(Integer playerLevel) {
	this.playerLevel = playerLevel;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setQueryActiveUserList(
	    List<ZQueryActiveUser> queryActiveUserList) {
	this.queryActiveUserList = queryActiveUserList;
    }

    public void setQueryLostUserLevelDetailList(
	    List<ZQueryLostUserLevelDetail> queryLostUserLevelDetailList) {
	this.queryLostUserLevelDetailList = queryLostUserLevelDetailList;
    }

    public void setQueryLostUserlevelList(
	    List<ZQueryLostUserLevel> queryLostUserlevelList) {
	this.queryLostUserlevelList = queryLostUserlevelList;
    }

    public void setQueryType(String queryType) {
	this.queryType = queryType;
    }

    public void setSelectArray(String[] selectArray) {
	this.selectArray = selectArray;
    }

    public void setSelectString(String selectString) {
	this.selectString = selectString;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

    public void setStatisticsDate(Date statisticsDate) {
	this.statisticsDate = statisticsDate;
    }

    public void setTotalList(String[] totalList) {
	this.totalList = totalList;
    }

}
