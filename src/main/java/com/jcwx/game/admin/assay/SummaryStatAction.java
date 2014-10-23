package com.jcwx.game.admin.assay;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.ZSysViewDay;
import com.jcwx.game.service.oss.IOperationServerServer;


@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class SummaryStatAction extends BasalAction {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // 开始时间
    private Date beginTime;
    // 结束时间
    private Date endTime;

    /** 运营商平台关联server **/
    @Autowired
    private IOperationServerServer operationServerServer;

    /** 服务器列表 */
    private List<OssServer> ossServersList;

    /** 大区列表 */
    private Integer[] selectArray;

    private List<ZSysViewDay> sysViewDays;

    @SuppressWarnings("unchecked")
    private void forearhServer(BaseAdminContext baseAdminContext,
	    StringBuffer buf, int id) {
	OssServer ossServer = baseAdminContext.getOssServerById(id);
	if (ossServer == null) {
	    buf.append("<span class='color-red'>serverId" + id
		    + " not find  </span>");
	    return;
	}
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("beginTime", beginTime);
	    object.put("endTime", endTime);
	    object.put("handlerName", "ZSysViewDayHandler");
	    object = CONNECTION.sendMsg(object);
	    /**
	     * 得到所有数据，并且展示
	     */
	    sysViewDays.addAll((List<ZSysViewDay>) object
		    .get("ossZSysViewDayList"));
	    // buf.append("<span class='color-gr'>"+ossServer.getName()+"</span> success "
	    // + rtime + "(ms)  ");
	} catch (Exception e) {
	    e.printStackTrace();
	    buf.append("<span class='color-red'>" + ossServer.getName()
		    + "</span> fail " + "error:" + e.getMessage() + " ");
	}
    }

    public Date getBeginTime() {
	return beginTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public List<ZSysViewDay> getSysViewDays() {
	return sysViewDays;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setSysViewDays(List<ZSysViewDay> sysViewDays) {
	this.sysViewDays = sysViewDays;
    }

    /**
     * 总览分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "systemview_index", results = { @Result(name = "success", location = "../../admin/assay/zSysView.jsp") })
    public String summarystat() throws Exception {

	PerformanceTimer timer = new PerformanceTimer();

	if (beginTime == null) {

	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null) {
	    endTime = new java.util.Date();
	}

	ossServersList = getBaseAdminContext().getOssServers();
	Integer serverArray[] = new Integer[ossServersList.size()];
	for (int i = 0; i < ossServersList.size(); i++) {
	    OssServer ossServer = ossServersList.get(i);
	    serverArray[i] = ossServer.getId();
	}
	selectArray = serverArray;
	StringBuffer buf = new StringBuffer();
	setRemoteRunTime(0L);
	setContentLength(0);
	sysViewDays = new ArrayList<ZSysViewDay>();
	// for (Integer id : selectArray) {
	forearhServer(getBaseAdminContext(), buf, 114);
	// }

	setLocalRunTime(timer.get());

	return "success";

    }
}
