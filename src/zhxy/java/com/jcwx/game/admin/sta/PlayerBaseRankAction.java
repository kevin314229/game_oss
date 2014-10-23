package com.jcwx.game.admin.sta;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.AdminSystemConstant;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.PayInfo;
import com.jcwx.game.domain.PlayerBaseRank;
import com.jcwx.game.domain.PlayerChatLog;
import com.jcwx.game.domain.Project;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IPlayerBaseRankService;
import com.jcwx.game.service.oss.IPlayerChatLogService;
import com.jcwx.game.service.oss.IProjectService;
import com.jcwx.game.util.ServerListToMap;

@ParentPackage("base")
@Namespace("/zhxy/base")
@ResultPath("/")
public class PlayerBaseRankAction extends BasalAction {

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    // 游戏ID
    private int gameId;
    // 大区 ID
    private String areaId;
    // 大区 ID列表多选
    private String[] areas;
    // 平台标识
    private String ptId;
    
    private String ptCode;
    // 开始时间
    private String beginDate;
    // 结束时间
    private String endDate;
    // 帐号
    private String loginName;
    // 角色名
    private String nickName;
    
    private Integer playerBaseId;
    
    /** 平台列表*/
    private List<OssServer> OssServersPt;
    
    private Map<String, String> serverMap;
    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;
    // 用户管理的项目列表
    private List<Project> projectList;
    private List<PlayerBaseRank> rankList;
    
    private List<PayInfo> payInfoList;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IOssOperationService ossOperationService;
    @Autowired
    private IPlayerChatLogService playerChatLogService;
    @Autowired
    private IPlayerBaseRankService playerBaseRankService;
    /** 总记录数 */
    private Integer allNum;

    /** 总页数 */
    private Integer pages;

    /** 当前页数 */
    private Integer currPageNO;

    /** 每页记录数 */
    private Integer onePageNum;
    
    /** 起始页号 */
    private Integer beginNum;

    /** 查看封禁账号 */
    @Action(value = "playerBaseRank_index", results = { @Result(name = "success", location = "../../zhxy/sta/playerBaseRank_index.jsp") })
    public String queryPlayerBaseRankList() throws Exception {
	try {
	    BaseAdminContext baseAdminContext = (BaseAdminContext) ServletActionContext
			.getRequest().getSession()
			.getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY);
	    //初始化日期信息
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
	 // 获取所有平台标识
	    this.ossOperationList = ossOperationService.getOssOperationList();
	    
	    List<OssServer> list=new ArrayList<OssServer>();
	    gameId = baseAdminContext.getProject().getProjectId();
	    OssServersPt = baseAdminContext.getOssServersPt();
	    list= baseAdminContext.getOssServers();
	    //将大区ID列表转换为字符串
	    if(ptCode!=null&&!"".equals(ptCode)){
  		
  		serverMap = ServerListToMap.convert(list, ptCode);
	    }else{
		serverMap=ServerListToMap.convert(list);
	    }
	    areaId=ServerListToMap.arrayToString(list, areas, ptCode);
	    if(areas!=null&&areas.length==1){
	  	areas=areas[0].split(",");
	  	areas=ServerListToMap.strToArr(areaId);
	    }
	    //初始化分页信息
	    if (currPageNO == null || currPageNO.intValue() == 0) {
		currPageNO = 1;
	    }
	    if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
		setOnePageNum(20);
	    }
	    beginNum = (currPageNO - 1) * onePageNum;
	    //查询参数封装成Map
	    Map<String,Object> params = new HashMap<String, Object>();
//	    params.put("beginDate", beginDate);
//	    params.put("endDate", endDate);
	    params.put("beginNum", beginNum);
	    params.put("onePageNum", onePageNum);
	    params.put("gameId", gameId);
	    params.put("ptId", ptId);
	    params.put("areaId", areaId);
	    
	    //查询结果集
	    rankList = playerBaseRankService.getPlayerBaseRankList(params);
//	    
//	    //查询结果集
	    allNum=playerBaseRankService.getPlayerBaseRankCount(params);
	   //出来分页信息
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum/onePageNum;
//		// 当前页设置
	    if (currPageNO.intValue() > pages) {
		  currPageNO = pages;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    
    /** 查看封禁账号 */
    @Action(value = "playerBaseRank_info", results = { @Result(name = "success", location = "../../zhxy/sta/playerBaseRank_info.jsp") })
    public String queryPlayerBaseRankInfo() throws Exception {
	try {
	    BaseAdminContext baseAdminContext = (BaseAdminContext) ServletActionContext
			.getRequest().getSession()
			.getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY);
	   
	    
//	    //初始化分页信息
	    if (currPageNO == null || currPageNO.intValue() == 0) {
		currPageNO = 1;
	    }
	    if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
		setOnePageNum(20);
	    }
	    beginNum = (currPageNO - 1) * onePageNum;
	    //查询参数封装成Map
	    Map<String,Object> params = new HashMap<String, Object>();
//	    params.put("beginDate", beginDate);
//	    params.put("endDate", endDate);
	    params.put("beginNum", beginNum);
	    params.put("onePageNum", onePageNum);
	    params.put("gameId", gameId);
	    params.put("ptId", ptId);
	    params.put("areaId", areaId);
	    params.put("playerBaseId", playerBaseId);
	    //查询结果集
	    payInfoList = playerBaseRankService.getPayInfoList(params);
//	    
	    //查询结果集
	    allNum=playerBaseRankService.getPayInfoCount(params);
	   //出来分页信息
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum/onePageNum;
//		// 当前页设置
	    if (currPageNO.intValue() > pages) {
		  currPageNO = pages;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String getPtId() {
        return ptId;
    }

    public void setPtId(String ptId) {
        this.ptId = ptId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Map<String, String> getServerMap() {
        return serverMap;
    }

    public void setServerMap(Map<String, String> serverMap) {
        this.serverMap = serverMap;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getCurrPageNO() {
        return currPageNO;
    }

    public void setCurrPageNO(Integer currPageNO) {
        this.currPageNO = currPageNO;
    }

    public Integer getOnePageNum() {
        return onePageNum;
    }

    public void setOnePageNum(Integer onePageNum) {
        this.onePageNum = onePageNum;
    }

    public Integer getBeginNum() {
        return beginNum;
    }

    public void setBeginNum(Integer beginNum) {
        this.beginNum = beginNum;
    }

    public List<OssOperation> getOssOperationList() {
        return ossOperationList;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
        this.ossOperationList = ossOperationList;
    }

    public List<PlayerBaseRank> getRankList() {
        return rankList;
    }

    public void setRankList(List<PlayerBaseRank> rankList) {
        this.rankList = rankList;
    }

    public Integer getPlayerBaseId() {
        return playerBaseId;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
        this.playerBaseId = playerBaseId;
    }

    public List<PayInfo> getPayInfoList() {
        return payInfoList;
    }

    public void setPayInfoList(List<PayInfo> payInfoList) {
        this.payInfoList = payInfoList;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public List<OssServer> getOssServersPt() {
        return OssServersPt;
    }

    public void setOssServersPt(List<OssServer> ossServersPt) {
        OssServersPt = ossServersPt;
    }
    
}
