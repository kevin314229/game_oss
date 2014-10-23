package com.jcwx.game.admin.assay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
import com.jcwx.game.exception.ActionValidateException;
import com.jcwx.game.http.domain.OssPlatformLCI;
import com.jcwx.game.http.domain.PlatformExpandInfo;
import com.jcwx.game.service.oss.IOssOperationService;


/**
 * @Title: PlatformLCIAction.java
 * @Package com.jcwx.game.admin.assay
 * @Description: 平台等级职业信息
 * @author liushang 364173778@qq.com
 * @date 2013年12月25日 上午11:05:29
 * @version V1.0
 */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class PlatformLCIAction extends BasalAction {
    private static final String ALL = "all";
    /** 射手类型 */
    private static final int ARCHER_NUMBER = 3;
    private static final Logger logger = Logger
	    .getLogger(PlatformLCIAction.class);
    /** 法师类型 */
    private static final int MASTER_NUMBER = 2;
    /** 战士类型 */
    private static final int WARRIOR_NUMBER = 1;
    /** 结束时间 */
    private String endTime;

    /** 平台列表 */
    private List<OssOperation> ossOperationList;
    @Autowired
    private IOssOperationService ossOperationService;
    /** 运营商ID */
    private Integer ossServerId;
    private String ossServerName;
    /** 服务器列表 */
    private List<OssServer> ossServersList;

    /** 战士，法师，弓箭手 所有的等级职业人数信息 */
    private List<OssPlatformLCI> platformAllList = Collections.emptyList();

    /** 弓箭手的等级职业人数信息 */
    private List<OssPlatformLCI> platformArcherList = Collections.emptyList();

    private List<PlatformExpandInfo> platformExpandList = Collections
	    .emptyList();
    /** 法师的等级职业人数信息 */
    private List<OssPlatformLCI> platformMasterList = Collections.emptyList();

    /** 战士的等级职业人数信息 */
    private List<OssPlatformLCI> platformWarriorList = Collections.emptyList();

    /** 平台ID */
    private String selectptId;

    private String selectptName;

    /** 开始时间 */
    private String startTime;

    private Date getAndValidateEndTime() {
	Date endTimeTmp = DateService.getCurrentUtilDate();

	if (endTime == null || "".equals(endTime)) {
	    endTimeTmp = DateService.getCurrentDayLastUtilDate();
	    endTime = DateService.getDateFormatStr(endTimeTmp, "yyyy-MM-dd");
	} else {
	    endTimeTmp = DateService.getDateLastTime(endTime);
	}
	return endTimeTmp;
    }

    private Date getAndValidateStartTime() {
	Date beginTime = DateService.getCurrentUtilDate();
	if (startTime != null && !"".equals(startTime)) {
	    beginTime = DateService.getDateFirstTime(startTime);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    startTime = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	return beginTime;
    }

    /**
     * @Title: getContainsLevel
     * @Description: 找到集合中相同等级的内容
     * @throws
     */
    private OssPlatformLCI getContainsLevel(OssPlatformLCI level,
	    List<OssPlatformLCI> list) {
	for (OssPlatformLCI lci : list) {
	    if (lci.getLevel().equals(level.getLevel())) {
		return lci;
	    }
	}
	return null;
    }

    public String getEndTime() {
	return this.endTime;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public IOssOperationService getOssOperationService() {
	return ossOperationService;
    }

    public Integer getOssServerId() {
	return ossServerId;
    }

    public String getOssServerName() {
	return ossServerName;
    }

    public List<OssServer> getOssServersList() {
	return ossServersList;
    }

    public List<OssPlatformLCI> getPlatformAllList() {
	return this.platformAllList;
    }

    public List<OssPlatformLCI> getPlatformArcherList() {
	return this.platformArcherList;
    }

    public List<PlatformExpandInfo> getPlatformExpandList() {
	return this.platformExpandList;
    }

    public List<OssPlatformLCI> getPlatformMasterList() {
	return this.platformMasterList;
    }

    public List<OssPlatformLCI> getPlatformWarriorList() {
	return this.platformWarriorList;
    }

    public String getSelectptId() {
	return this.selectptId;
    }

    public String getSelectptName() {
	return this.selectptName;
    }

    public String getStartTime() {
	return this.startTime;
    }

    private List<OssPlatformLCI> initialAllList(
	    List<OssPlatformLCI> platformLCIList) {
	List<OssPlatformLCI> list = new ArrayList<OssPlatformLCI>();
	for (OssPlatformLCI lci : platformLCIList) {
	    OssPlatformLCI lciLevel = null;
	    if ((lciLevel = getContainsLevel(lci, list)) != null) {

		try {
		    OssPlatformLCI newLci = (OssPlatformLCI) BeanUtils
			    .cloneBean(lci);

		    newLci.setNumber(lciLevel.getNumber() + newLci.getNumber());

		    list.set(list.indexOf(lciLevel), newLci);

		} catch (Exception e) {
		    e.printStackTrace();
		}

	    } else {
		list.add(lci);
	    }
	}
	Collections.sort(list, new Comparator<OssPlatformLCI>() {

	    @Override
	    public int compare(OssPlatformLCI o1, OssPlatformLCI o2) {
		return o1.getLevel() - o2.getLevel();
	    }

	});
	return list;
    }

    private List<OssPlatformLCI> initialList(
	    List<OssPlatformLCI> platformLCIList, int numberType) {
	List<OssPlatformLCI> list = new ArrayList<OssPlatformLCI>();
	for (OssPlatformLCI lci : platformLCIList) {
	    if (lci.getOccupation().intValue() == numberType) {
		list.add(lci);
	    }
	}
	return list;
    }

    private String ptName() {
	for (OssServer server : getBaseAdminContext().getOssServersPt()) {
	    if (server.getServerCode().equals(getSelectptId())) {
		return server.getServerProvider();
	    }
	}
	return StringUtils.EMPTY;
    }

    /**
     * 
     * @Title: query
     * @Description: 根据大区 找到所有的运营商平台， 在查询出OSS当前用户权限以内的所有平台信息
     */
    @Action(value = "platformlci_index", results = { @Result(name = SUCCESS, location = "../../admin/assay/platformlci.jsp") })
    public String query() {

	// 要用 23:59:59 数据库为datatime
	try {
	    // 平台用户
	    if (getBaseAdminContext().getOssUser().getIsOperator().equals("1")) {
		ossServersList = getBaseAdminContext().getOssServers();
		if (ossServerId == null && ossServersList.size() > 0) {
		    ossServerId = ossServersList.get(0).getId();
		}
		OssServer ossServer = (OssServer) CollectionUtils.find(
			ossServersList, new BeanPredicate("id",
				new EqualPredicate(ossServerId)));
		ossServerName = ossServer.getName();
		selectptId = getBaseAdminContext().getOssUser()
			.getCarrierOperator();
		setSelectptName(selectptId);
	    } else {
		ossServerId = getBaseAdminContext().getServerId();
		ossServerName = getBaseAdminContext().getName();
		this.ossOperationList = ossOperationService
			.getOssOperationList();
		setSelectptName(ptName());
	    }

	    Date beginTime = null, endTimeTmp = null;

	    beginTime = getAndValidateStartTime();

	    endTimeTmp = getAndValidateEndTime();

	    validateDateBetween(beginTime, endTimeTmp);

	    validateAfter(beginTime, endTimeTmp);

	    validateSelectptId();

	    List<OssPlatformLCI> platformLCIList = Collections.emptyList();

	    Map<String, Object> object = new HashMap<String, Object>();
	    /** object put param */
	    {
		object.put("startTime", beginTime);

		object.put("endTime", endTimeTmp);

		if (!ALL.equals(selectptId)) {
		    object.put("selectptId", selectptId);
		}

		object.put("handlerName", "ZPlatformLCIHandler");
	    }
	    if (object != null && !object.isEmpty()) {

		object = CONNECTION.sendMsg(object);

		platformLCIList = (List<OssPlatformLCI>) object
			.get("ossZPlatformLCIList");

		platformWarriorList = initialList(platformLCIList,
			WARRIOR_NUMBER);
		platformMasterList = initialList(platformLCIList, MASTER_NUMBER);
		platformArcherList = initialList(platformLCIList, ARCHER_NUMBER);
		platformAllList = initialAllList(platformLCIList);

		platformExpandList = (List<PlatformExpandInfo>) object
			.get("ossPlatformExpandInfo");

	    }
	} catch (ActionValidateException e) {
	    setActionMsg(e.getMessage());
	    return e.getActionMessage();
	} catch (Exception e1) {
	    logger.error(e1.getMessage(), e1);
	}

	return SUCCESS;

    }

    public void setEndTime(String endTime) {
	this.endTime = endTime;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public void setOssOperationService(IOssOperationService ossOperationService) {
	this.ossOperationService = ossOperationService;
    }

    public void setOssServerId(Integer ossServerId) {
	this.ossServerId = ossServerId;
    }

    public void setOssServerName(String ossServerName) {
	this.ossServerName = ossServerName;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setPlatformAllList(List<OssPlatformLCI> platformAllList) {
	this.platformAllList = platformAllList;
    }

    public void setPlatformArcherList(List<OssPlatformLCI> platformArcherList) {
	this.platformArcherList = platformArcherList;
    }

    public void setPlatformExpandList(
	    List<PlatformExpandInfo> platformExpandList) {
	this.platformExpandList = platformExpandList;
    }

    public void setPlatformMasterList(List<OssPlatformLCI> platformMasterList) {
	this.platformMasterList = platformMasterList;
    }

    public void setPlatformWarriorList(List<OssPlatformLCI> platformWarriorList) {
	this.platformWarriorList = platformWarriorList;
    }

    public void setSelectptId(String selectptId) {
	this.selectptId = selectptId;
    }

    public void setSelectptName(String selectptName) {
	this.selectptName = selectptName;
    }

    public void setStartTime(String startTime) {
	this.startTime = startTime;
    }

    /**
     * @Title: validateAfter
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @throws
     */
    private void validateAfter(Date beginTime, Date endTimeTmp) {
	if (beginTime.after(endTimeTmp)) {
	    throw new ActionValidateException(ActionValidateException.SUCCESS,
		    "开始时间必须小于结束时间");
	}
    }

    private void validateDateBetween(Date beginTime, Date endTimeTmp) {
	if (DateService.DayBetween(beginTime, endTimeTmp) > 7) {
	    throw new ActionValidateException(ActionValidateException.SUCCESS,
		    "时间必须小于7天");
	}
    }

    private void validateSelectptId() {
	if (StringUtils.isBlank(selectptId)) {
	    throw new ActionValidateException(ActionValidateException.SUCCESS,
		    "请选择");
	}
    }

}
