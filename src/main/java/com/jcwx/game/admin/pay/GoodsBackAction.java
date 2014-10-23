package com.jcwx.game.admin.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.http.domain.OssGoodsBack;


/**
 * 物品回滚表
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/pay")
@ResultPath("/")
public class GoodsBackAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 总记录数 */
    private Integer allNum;
    /** 开始时间 */
    private Date beginTime;
    /** 当前页数 */
    private Integer currPageNO;
    /** 结束时间 */
    private Date endTime;
    // 登录名
    private String loginName;

    // 角色名
    private String nickName;

    /** 每页记录数 */
    private Integer onePageNum;

    private List<OssGoodsBack> ossGoodsBackList;

    /** 总页数 */
    private Integer pages;

    /** 类型 */
    private String type;

    public Integer getAllNum() {
	return allNum;
    }

    public Date getBeginTime() {
	return beginTime;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public Date getEndTime() {
	return endTime;
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

    public List<OssGoodsBack> getOssGoodsBackList() {
	return ossGoodsBackList;
    }

    public Integer getPages() {
	return pages;
    }

    public String getType() {
	return type;
    }

    @Action(value = "goodsBack_index", results = { @Result(name = "success", location = "../../admin/pay/goodsBack.jsp") })
    public String query() throws Exception {

	if (beginTime == null || "".equals(beginTime)) {
	    beginTime = DateService.getCurrentMonthFirstUtilDate();
	} else {
	    beginTime = DateService.getDateFirstTime(beginTime);
	}

	if (endTime == null || "".equals(endTime)) {
	    endTime = DateService.getCurrentDayLastUtilDate();

	} else {
	    endTime = DateService.getDateLastTime(endTime);
	}
	if ("0".equals(type) || "".equals(type)) {
	    type = null;
	}
	if (nickName != null && loginName != null && !nickName.equals("")
		&& !loginName.equals("")) {
	    setErrorInfo("帐号和角色名只能填写一个!");
	    return "success";
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	onePageNum = 20; // 每页20条记录
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("type", type);
	object.put("loginName", loginName);
	object.put("nickName", nickName);
	object.put("handlerName", "GoodsBackHandler");
	object = CONNECTION.sendMsg(object);
	allNum = (Integer) object.get("allNum"); // 总记录数
	ossGoodsBackList = (List<OssGoodsBack>) object.get("ossGoodsBackList");
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}

	return "success";

    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
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

    public void setOssGoodsBackList(List<OssGoodsBack> ossGoodsBackList) {
	this.ossGoodsBackList = ossGoodsBackList;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setType(String type) {
	this.type = type;
    }

}
