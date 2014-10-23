/**
 * 
 */
package com.jcwx.game.admin.assay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.AllOnlineDto;


@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class OnlineAction extends BasalAction {

    /**    
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<AllOnlineDto> allOnlineDtoList;

    private Integer onlineCount;

    /** 服务器列表 */
    private List<OssServer> ossServersList;

    /** 大区列表 */
    private Integer[] selectArray;

    public List<AllOnlineDto> getAllOnlineDtoList() {
	return allOnlineDtoList;
    }

    public Integer getOnlineCount() {
	return onlineCount;
    }

    public List<OssServer> getOssServersList() {
	return ossServersList;
    }

    public Integer[] getSelectArray() {
	return selectArray;
    }

    /**
     * 在线人数
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "queryAllOnlie", results = { @Result(name = "success", location = "/admin/assay/queryAllOnlie.jsp") })
    public String payAssay() throws Exception {

	PerformanceTimer timer = new PerformanceTimer();

	ossServersList = getBaseAdminContext().getOssServers();

	if (selectArray == null)
	    return "success";

	onlineCount = 0;
	StringBuffer buf = new StringBuffer();
	setRemoteRunTime(0L);
	setContentLength(0);
	allOnlineDtoList = new ArrayList<AllOnlineDto>();
	for (Integer id : selectArray) {
	    OssServer ossServer = getBaseAdminContext().getOssServerById(id);
	    if (ossServer == null) {
		buf.append("<span class='color-red'>serverId" + id
			+ " not find  </span>");
		continue;
	    }
	    try {
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("handlerName", "QueryOnlinePlayerHandler");
		// object.put("methodName", "querySimpleOnline"); TODO 新版本使用这个
		object = CONNECTION.interfaceSendMsg(id,object);
		long rtime = (Long) object.get("remoteRunTime");
		super.handleKryoMap(object);

		Integer allNum = (Integer) object.get("allNum");

		AllOnlineDto dto = new AllOnlineDto();
		dto.setServerName(ossServer.getName());
		dto.setAllNum(allNum);

		onlineCount += allNum;
		allOnlineDtoList.add(dto);

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

	// 排序
	Collections.sort(allOnlineDtoList, new Comparator<AllOnlineDto>() {
	    @Override
	    public int compare(AllOnlineDto p1, AllOnlineDto p2) {
		int flag = p2.getAllNum().compareTo(p1.getAllNum());
		return flag;
	    }
	});

	return "success";

    }

    public void setAllOnlineDtoList(List<AllOnlineDto> allOnlineDtoList) {
	this.allOnlineDtoList = allOnlineDtoList;
    }

    public void setOnlineCount(Integer onlineCount) {
	this.onlineCount = onlineCount;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setSelectArray(Integer[] selectArray) {
	this.selectArray = selectArray;
    }

}