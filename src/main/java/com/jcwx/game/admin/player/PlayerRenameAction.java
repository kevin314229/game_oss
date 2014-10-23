package com.jcwx.game.admin.player;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.PlayerLastLoginInfo;

/** 玩家改名 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class PlayerRenameAction extends BasalAction {

    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;

    /** 当前页数 */
    private Integer currPageNO;

    /** 搜索关键字 */
    private String keyword;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 排序标记 */
    private String orderFlag;

    /** 总页数 */
    private Integer pages;

    /** 玩家id */
    private Integer playerID;

    /** 玩家信息 */
    private List<PlayerLastLoginInfo> playerList;

    /** 玩家名称 */
    private String playerName;

    // @Autowired
    // private IPlayerService playerService;

    @Action(value = "playerRename_browsePlayerRename", results = { @Result(name = "success", location = "../../admin/player/browsePlayerRename.jsp") })
    public String browsePlayerRename() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}

	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "DESC";
	}
	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// 总记录数
	// allNum =
	// playerService.getPlayerLastLoginInfoListByStateCount(keyword, null);
	allNum = 1;
	onePageNum = 20; // 每页20条记录
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	} else if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	// 玩家集合
	// playerList =
	// playerService.getPlayerLastLoginInfoListByState(keyword,null,
	// orderFlag, beginNum, onePageNum);
	// System.out.println("测试::"+playerList.size());
	return SUCCESS;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public String getKeyword() {
	return keyword;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public String getOrderFlag() {
	return orderFlag;
    }

    public Integer getPages() {
	return pages;
    }

    public Integer getPlayerID() {
	return playerID;
    }

    public List<PlayerLastLoginInfo> getPlayerList() {
	return playerList;
    }

    public String getPlayerName() {
	return playerName;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setKeyword(String keyword) {
	this.keyword = keyword;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOrderFlag(String orderFlag) {
	this.orderFlag = orderFlag;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPlayerID(Integer playerID) {
	this.playerID = playerID;
    }

    public void setPlayerList(List<PlayerLastLoginInfo> playerList) {
	this.playerList = playerList;
    }

    public void setPlayerName(String playerName) {
	this.playerName = playerName;
    }

    /**
     * 修改玩家名称
     * 
     * @throws Exception
     */
    public String updatePlayerRename() throws Exception {
	HttpServletResponse response = ServletActionContext.getResponse();
	// HttpServletRequest request=ServletActionContext.getRequest();
	String result = "fail";
	PrintWriter out = response.getWriter();

	if (playerID != null && playerID != 0 && playerName != null
		&& !"".equals(playerName)) {

	    // IPlayerService playerService =
	    // (IPlayerService)SpringService.getBean("playerService");

	    // if(playerService.getPlayerIDByPlayerName(playerName)!=null){//不可重名
	    // result = "samename";
	    // }else{
	    // Integer k = playerService.updatePlayerName(playerID, playerName);
	    // if(k>0){
	    // result="success";
	    // //更新缓存，玩家缓存
	    // playerService.getPlayerCacheByPlayerID(playerID).setName(playerName);
	    // }
	    //
	    // }

	} else {
	    result = "fail";
	}
	response.setContentType("text/html");
	out.print(result);
	return null;

    }

}
