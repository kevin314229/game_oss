package com.jcwx.game.admin.player;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;

/**
 * 批量发放资源
 * 
 * @Title: ResourcesProvideAction.java
 * @Description:
 * @author Run
 * @date 2011-8-19
 * @version V1.0
 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class ResourcesProvideAction extends BasalAction {

    private static final long serialVersionUID = 1L;
    /** 一级条件所有用户 */
    public static final int USER_SCOPE_ALL = 0;
    /** 一级条件所有在线用户 */
    public static final int USER_SCOPE_ONLINE = 1;
    /** 粮食 */
    private Integer foods = 0;

    /** 荣誉 */
    private Integer glorys = 0;

    /** 等级段用户筛选1 */
    private Integer gradeScope1 = 1;
    /** 等级段用户筛选2 */
    private Integer gradeScope2 = 100;

    // 资源
    /** 金币 */
    private Integer lmoneys = 0;

    /** 银币 */
    private Integer moneys = 0;
    //
    /** 错误消息通知 */
    private String msgNotice;

    /** 声望 */
    private Integer renowns = 0;
    /** X日内有登陆记录的用户 */
    private Integer scopeDay = 3;
    private Boolean second1 = false;// 1x日限制条件
    private Boolean second2 = false;// 2等级限制条件
    /** 成功消息 */
    private String successMsg = "";
    /** 一级条件 */
    private Integer userScope = 1;

    public String view;

    /** 木材 */
    private Integer woods = 0;

    // @Autowired
    // private IPlayerService playerService;

    @Action(value = "addResources_addResources", results = {
	    @Result(name = "success", location = "../../admin/player/resources/addResources.jsp"),
	    @Result(name = "input", location = "../../admin/player/resources/addResources.jsp") })
    public String addResources() throws Exception {

	// 首次进入
	if (view != null && view.equals("view")) {
	    return SUCCESS;
	}

	// 条件参数
	Integer levelBegin = null;
	Integer levelEnd = null;
	Date lastLoginTimeValid = null;

	// 资源条件
	if (lmoneys == null || moneys == null || foods == null || woods == null
		|| glorys == null || renowns == null || lmoneys < 0
		|| moneys < 0 || foods < 0 || woods < 0 || glorys < 0
		|| renowns < 0) {
	    msgNotice = "资源参数不正确";
	    return SUCCESS;
	}

	StringBuffer sbu = new StringBuffer();

	// 发送资源
	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// IVillageService villageService =
	// (IVillageService)SpringService.getBean("villageService");

	if (userScope == USER_SCOPE_ONLINE || userScope == USER_SCOPE_ALL) {
	    if (second1 == true || second2 == true) {// 有二级条件
		if (second1) {// x日
		    if (scopeDay == null || scopeDay < 0) {
			msgNotice = "X日内有登陆记录的用户具体参数不正确";
			return SUCCESS;
		    } else {
			lastLoginTimeValid = new Date(
				System.currentTimeMillis()
					- (scopeDay * 1000 * 60 * 60 * 24L));
		    }
		}
		if (second2) {// 等级
		    if (gradeScope1 == null || gradeScope2 == null
			    || gradeScope1 < 1 || gradeScope2 < 1) {
			msgNotice = "等级段用户筛选具体参数不正确";
			return SUCCESS;
		    } else {
			levelBegin = gradeScope1;
			levelEnd = gradeScope2;
		    }
		}

	    } else {// 没有选择二级条件
		levelBegin = null;
		levelEnd = null;
		lastLoginTimeValid = null;
	    }

	    /*
	     * //发送资源 if(userScope==USER_SCOPE_ONLINE){
	     * 
	     * if(renowns>0 || glorys>0 || lmoneys>0){ Integer num
	     * =playerService.batchAddResourcesByOnline(renowns, glorys,
	     * lmoneys, levelBegin, levelEnd, lastLoginTimeValid);
	     * sbu.append("[ 金币："+lmoneys); sbu.append(" 声望："+renowns);
	     * sbu.append(" 荣誉："+glorys); sbu.append(" (总共"+num+"人收到)]"); }
	     * if(woods>0 || foods>0 || moneys>0){ Integer num =
	     * villageService.batchAddVillageResourcesOnline(woods, foods,
	     * moneys, 0, levelBegin, levelEnd, lastLoginTimeValid);
	     * sbu.append("  [ 木材："+woods); sbu.append(" 粮食："+foods);
	     * sbu.append(" 银币："+moneys); sbu.append(" (总共"+num+"人收到)]"); }
	     * }else{ if(renowns>0 || glorys>0 || lmoneys>0){ Integer num =
	     * playerService.batchAddResourcesByAll(renowns, glorys, lmoneys,
	     * levelBegin, levelEnd, lastLoginTimeValid);
	     * sbu.append("[ 金币："+lmoneys); sbu.append(" 声望："+renowns);
	     * sbu.append(" 荣誉："+glorys); sbu.append(" (总共"+num+"人收到)]"); }
	     * if(woods>0 || foods>0 || moneys>0){ Integer num =
	     * villageService.batchAddVillageResourcesByAll(woods, foods,
	     * moneys, 0, levelBegin, levelEnd, lastLoginTimeValid);
	     * sbu.append("  [ 木材："+woods); sbu.append(" 粮食："+foods);
	     * sbu.append(" 银币："+moneys); sbu.append(" (总共"+num+"人收到)]"); } }
	     */

	} else {
	    msgNotice = "参数不正确";
	    return SUCCESS;
	}

	successMsg = sbu.toString().trim();
	if ("".equals(successMsg.trim())) {
	    msgNotice = "请先输入参数！";
	}

	return SUCCESS;
    }

    public Integer getFoods() {
	return foods;
    }

    public Integer getGlorys() {
	return glorys;
    }

    public Integer getGradeScope1() {
	return gradeScope1;
    }

    public Integer getGradeScope2() {
	return gradeScope2;
    }

    public Integer getLmoneys() {
	return lmoneys;
    }

    public Integer getMoneys() {
	return moneys;
    }

    public String getMsgNotice() {
	return msgNotice;
    }

    public Integer getRenowns() {
	return renowns;
    }

    public Integer getScopeDay() {
	return scopeDay;
    }

    public Boolean getSecond1() {
	return second1;
    }

    public Boolean getSecond2() {
	return second2;
    }

    public String getSuccessMsg() {
	return successMsg;
    }

    public Integer getUserScope() {
	return userScope;
    }

    public String getView() {
	return view;
    }

    public Integer getWoods() {
	return woods;
    }

    @Action(value = "addResources_index", results = { @Result(name = "success", location = "../../admin/player/resources/addResources.jsp") })
    public String index() {
	return SUCCESS;
    }

    public void setFoods(Integer foods) {
	this.foods = foods;
    }

    public void setGlorys(Integer glorys) {
	this.glorys = glorys;
    }

    public void setGradeScope1(Integer gradeScope1) {
	this.gradeScope1 = gradeScope1;
    }

    public void setGradeScope2(Integer gradeScope2) {
	this.gradeScope2 = gradeScope2;
    }

    public void setLmoneys(Integer lmoneys) {
	this.lmoneys = lmoneys;
    }

    public void setMoneys(Integer moneys) {
	this.moneys = moneys;
    }

    public void setMsgNotice(String msgNotice) {
	this.msgNotice = msgNotice;
    }

    public void setRenowns(Integer renowns) {
	this.renowns = renowns;
    }

    public void setScopeDay(Integer scopeDay) {
	this.scopeDay = scopeDay;
    }

    public void setSecond1(Boolean second1) {
	this.second1 = second1;
    }

    public void setSecond2(Boolean second2) {
	this.second2 = second2;
    }

    public void setSuccessMsg(String successMsg) {
	this.successMsg = successMsg;
    }

    public void setUserScope(Integer userScope) {
	this.userScope = userScope;
    }

    public void setView(String view) {
	this.view = view;
    }

    public void setWoods(Integer woods) {
	this.woods = woods;
    }

    // -------------

    // -----------------------------------------------------

}
