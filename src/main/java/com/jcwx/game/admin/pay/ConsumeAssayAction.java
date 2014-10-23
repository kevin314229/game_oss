package com.jcwx.game.admin.pay;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.ZConsumeDay;
import com.jcwx.game.http.domain.ZConsumeDayDto;


@ParentPackage("base")
@Namespace("/admin/pay")
@ResultPath("/")
public class ConsumeAssayAction extends BasalAction {

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    /** 服务器列表 */
    private List<OssServer> ossServersList;

    /** 大区列表 */
    private Integer[] selectArray;

    private List<ZConsumeDayDto> zconsumeDayDtoList;

    /**
     * 消耗分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "consumeAssay", results = { @Result(name = "success", location = "../../admin/pay/consumeAssay.jsp") })
    public String consumeAssay() throws Exception {

	PerformanceTimer timer = new PerformanceTimer();

	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	// //////////////////
	ossServersList = getBaseAdminContext().getOssServers();

	if (selectArray == null)
	    return "success";

	StringBuffer buf = new StringBuffer();
	setRemoteRunTime(0L);
	setContentLength(0);
	zconsumeDayDtoList = new ArrayList<ZConsumeDayDto>();
	for (Integer id : selectArray) {
	    OssServer ossServer = getBaseAdminContext().getOssServerById(id);
	    if (ossServer == null) {
		buf.append("<span class='color-red'>serverId" + id
			+ " not find  </span>");
		continue;
	    }
	    try {
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("beginTime", beginTime);
		object.put("endTime", endTime);
		object.put("handlerName", "AssayConsumeHandler");
		object.put("methodName", "consumeAssay");
		object = CONNECTION.sendMsg(object);
		long rtime = (Long) object.get("remoteRunTime");
		super.handleKryoMap(object);
		List<ZConsumeDay> list = (List<ZConsumeDay>) object
			.get("zconsumeDayList");

		// 封装计算所需数据
		for (ZConsumeDay z : list) {
		    ZConsumeDayDto dto = new ZConsumeDayDto();
		    BeanUtils.copyProperties(dto, z);
		    dto.setServerName(ossServer.getName());
		    dto.setSingleConsume(dto.getConsumeTimes() == 0 ? 0.0 : dto
			    .getConsumeGoldTotal()
			    * 1.0
			    / dto.getConsumeTimes());
		    dto.setAcpu(dto.getLoginTotal() == 0 ? 0.0 : dto
			    .getConsumeGoldTotal() * 1.0 / dto.getLoginTotal());
		    dto.setAcppu(dto.getConsumeUserNum() == 0 ? 0.0 : dto
			    .getConsumeGoldTotal()
			    * 1.0
			    / dto.getConsumeUserNum());
		    zconsumeDayDtoList.add(dto);
		}
		buf.append("<span class='color-gr'>" + ossServer.getName()
			+ "</span> success " + rtime + "(ms)  ");
	    } catch (Exception e) {
		// e.printStackTrace();
		buf.append("<span class='color-red'>" + ossServer.getName()
			+ "</span> fail " + "error:" + e.getMessage() + " ");
	    }
	}

	setActionMsg(buf.toString());

	setLocalRunTime(timer.get());

	return "success";

    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<OssServer> getOssServersList() {
	return ossServersList;
    }

    public Integer[] getSelectArray() {
	return selectArray;
    }

    public List<ZConsumeDayDto> getZconsumeDayDtoList() {
	return zconsumeDayDtoList;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setSelectArray(Integer[] selectArray) {
	this.selectArray = selectArray;
    }

    public void setZconsumeDayDtoList(List<ZConsumeDayDto> zconsumeDayDtoList) {
	this.zconsumeDayDtoList = zconsumeDayDtoList;
    }

}
