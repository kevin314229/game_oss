package com.jcwx.game.admin.sta;

import java.util.ArrayList;
import java.util.Date;
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
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.PlayerChatLog;
import com.jcwx.game.domain.Project;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IPlayerChatLogService;
import com.jcwx.game.service.oss.IProjectService;
import com.jcwx.game.util.ServerListToMap;

@ParentPackage("base")
@Namespace("/zhxy/base")
@ResultPath("/")
public class PlayerChatLogAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;
    // 大区 ID
    private String areaId;
    // 大区 ID列表多选
    private String[] areas;
    // 开始时间
    private String beginDate;
    /** 起始页号 */
    private Integer beginNum;
    // 消息内容
    private String content;
    /** 当前页数 */
    private Integer currPageNO;
    // 结束时间
    private String endDate;
    // 游戏ID
    private int gameId;

    // 帐号
    private String loginName;

    // 角色名
    private String nickName;
    /** 每页记录数 */
    private Integer onePageNum;
    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;

    @Autowired
    private IOssOperationService ossOperationService;

    /** 平台列表 */
    private List<OssServer> OssServersPt;
    /** 总页数 */
    private Integer pages;
    // 聊天记录
    private List<PlayerChatLog> playerChatLogList;

    @Autowired
    private IPlayerChatLogService playerChatLogService;

    // 用户管理的项目列表
    private List<Project> projectList;

    @Autowired
    private IProjectService projectService;

    // 平台标识
    private String ptId;

    private Map<String, String> serverMap;

    /** 查看封禁账号 */
    @Action(value = "playerChatLog_index", results = { @Result(name = "success", location = "../../zhxy/sta/playerChatLog.jsp") })
    public String browseBanAccounts() throws Exception {
	try {
	    // 初始化日期信息
	    Date beginTime = new Date();
	    Date endTime = new Date();

	    if (beginDate == null) {
		beginTime = DateService.getCurrentMonthFirstDay();
		beginDate = DateService.getDateFormatStr(beginTime,
			"yyyy-MM-dd");
	    }
	    if (endDate == null) {
		endTime = DateService.getCurrentDayLastUtilDate();
		endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	    }
	    List<OssServer> list = new ArrayList<OssServer>();
	    gameId = getBaseAdminContext().getProject().getProjectId();
	    OssServersPt = getBaseAdminContext().getOssServersPt();
	    list = getBaseAdminContext().getOssServers();
	    // 将大区ID列表转换为字符串
	    if (ptId != null && !"".equals(ptId)) {

		serverMap = ServerListToMap.convert(list, ptId);
	    } else {
		serverMap = ServerListToMap.convert(list);
	    }
	    areaId = ServerListToMap.arrayToString(list, areas, ptId);
	    if (areas != null && areas.length == 1) {
		areas = areas[0].split(",");
	    }
	    // 获取游戏列表
	    projectList = new ArrayList<Project>();
	    projectList = projectService
		    .getProjectListbyUserName(getBaseAdminContext()
			    .getOssUser().getUsername());
	    // 初始化分页信息
	    if (currPageNO == null || currPageNO.intValue() == 0) {
		currPageNO = 1;
	    }
	    if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
		setOnePageNum(20);
	    }
	    beginNum = (currPageNO - 1) * onePageNum;
	    // 查询结果集
	    playerChatLogList = playerChatLogService
		    .getPlayerChatLogListByInfo(beginNum, onePageNum, gameId,
			    areaId, beginDate, endDate, loginName, nickName,
			    content);

	    // 查询结果集
	    allNum = playerChatLogService.getPlayerChatLogCountByInfo(beginNum,
		    onePageNum, gameId, areaId, beginDate, endDate, loginName,
		    nickName, content);
	    // 出来分页信息
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		    / onePageNum;
	    // // 当前页设置
	    if (currPageNO.intValue() > pages) {
		currPageNO = pages;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public String getAreaId() {
	return areaId;
    }

    public String[] getAreas() {
	return areas;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public Integer getBeginNum() {
	return beginNum;
    }

    public String getContent() {
	return content;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public String getEndDate() {
	return endDate;
    }

    public int getGameId() {
	return gameId;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public List<OssServer> getOssServersPt() {
	return OssServersPt;
    }

    public Integer getPages() {
	return pages;
    }

    public List<PlayerChatLog> getPlayerChatLogList() {
	return playerChatLogList;
    }

    public List<Project> getProjectList() {
	return projectList;
    }

    public String getPtId() {
	return ptId;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setAreaId(String areaId) {
	this.areaId = areaId;
    }

    public void setAreas(String[] areas) {
	this.areas = areas;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setBeginNum(Integer beginNum) {
	this.beginNum = beginNum;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setGameId(int gameId) {
	this.gameId = gameId;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public void setOssServersPt(List<OssServer> ossServersPt) {
	OssServersPt = ossServersPt;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPlayerChatLogList(List<PlayerChatLog> playerChatLogList) {
	this.playerChatLogList = playerChatLogList;
    }

    public void setProjectList(List<Project> projectList) {
	this.projectList = projectList;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }
}
