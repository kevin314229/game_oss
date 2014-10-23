/**
 * 
 */
package com.jcwx.game.service.oss.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.OssContext;
import com.jcwx.game.common.WebUtilService;
import com.jcwx.game.common.ip.IPSeeker;
import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssLog;
import com.jcwx.game.service.oss.IOssLogService;

/**
 * @author Administrator
 * 
 */
@Service
public class OssLogService implements IOssLogService {

    @Autowired
    private IBaseDAO baseDao;

    @Override
    public Integer createOssLog(HttpServletRequest request,
	    String operationType, String operationMsg, String remark) {

	BaseAdminContext baseAdminContext = OssContext
		.getBaseAdminContext(false);

	if (baseAdminContext == null)
	    return null;

	if (operationMsg != null && operationMsg.length() >= 1024) {
	    operationMsg = operationMsg.substring(0, 1024);
	}

	OssLog ossLog = new OssLog();
	ossLog.setCreateTime(new Date());
	ossLog.setLoginIp(WebUtilService.getIpAddr(request));
	ossLog.setIpAddress(IPSeeker.getInstance().getAddress(
		ossLog.getLoginIp()));
	ossLog.setName(baseAdminContext.getOssUser().getUsername());
	ossLog.setOperationMsg(operationMsg);
	ossLog.setOperationType(operationType);
	ossLog.setRemark(remark);
	ossLog.setBusinessId(baseAdminContext.getOperationId());
	ossLog.setBusinessName(baseAdminContext.getOperationName());
	ossLog.setServerId(baseAdminContext.getServerId());
	ossLog.setServerName(baseAdminContext.getName());

	baseDao.insert("OssLog.createOssLog", ossLog);
	return null;
    }

    /**
     * 
     * @param operationType
     *            OssLogConstant的常量，用注解表示是什么类型，属于哪一个模块
     * @param operationMsg
     *            要记录的内容
     */
    @Override
    public void createOssLog(String operationType, String operationMsg) {
	BaseAdminContext baseAdminContext = OssContext.getBaseAdminContext();
	Validate.notNull(baseAdminContext);

	if (operationMsg != null && operationMsg.length() >= 1024) {
	    operationMsg = operationMsg.substring(0, 1024);
	}

	OssLog ossLog = new OssLog();
	ossLog.setCreateTime(new Date());
	ossLog.setLoginIp(baseAdminContext.getIpAddr());
	ossLog.setIpAddress(IPSeeker.getInstance().getAddress(
		baseAdminContext.getIpAddr()));
	ossLog.setName(baseAdminContext.getOssUser().getUsername());
	ossLog.setOperationMsg(operationMsg);
	ossLog.setOperationType(operationType);
	ossLog.setRemark(StringUtils.EMPTY);
	ossLog.setBusinessId(baseAdminContext.getOperationId());
	ossLog.setBusinessName(baseAdminContext.getOperationName());
	ossLog.setServerId(baseAdminContext.getServerId());
	ossLog.setServerName(baseAdminContext.getName());

	baseDao.insert("OssLog.createOssLog", ossLog);
    }

    @Override
    public void createOssLog(String operationType, String operationMsg,
	    String remark) {
	BaseAdminContext baseAdminContext = OssContext.getBaseAdminContext();
	Validate.notNull(baseAdminContext);

	if (operationMsg != null && operationMsg.length() >= 1024) {
	    operationMsg = operationMsg.substring(0, 1024);
	}

	OssLog ossLog = new OssLog();
	ossLog.setCreateTime(new Date());
	ossLog.setLoginIp(baseAdminContext.getIpAddr());
	ossLog.setIpAddress(IPSeeker.getInstance().getAddress(
		baseAdminContext.getIpAddr()));
	ossLog.setName(baseAdminContext.getOssUser().getUsername());
	ossLog.setOperationMsg(operationMsg);
	ossLog.setOperationType(operationType);
	ossLog.setRemark(remark);
	ossLog.setBusinessId(baseAdminContext.getOperationId());
	ossLog.setBusinessName(baseAdminContext.getOperationName());
	ossLog.setServerId(baseAdminContext.getServerId());
	ossLog.setServerName(baseAdminContext.getName());

	baseDao.insert("OssLog.createOssLog", ossLog);
    }

    @Override
    public Integer deleteOssLogByID(String id) {
	return baseDao.delete("OssLog.deleteOssLogByID", id);
    }

    @Override
    public Integer getAllNumByQueryCondition(Date beginTime, Date endTime,
	    String name, String operationType, Integer ossServerId) {
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("name", name);
	parms.put("operationType", operationType);
	parms.put("ossServerId", ossServerId);
	return (Integer) baseDao.queryForObject(
		"OssLog.getAllNumByQueryCondition", parms);
    }

    @Override
    public OssLog getOssLogByID(String id) {
	return (OssLog) baseDao.queryForObject("OssLog.getOssLogByID", id);

    }

    @Override
    public Integer getOssLogCount() {
	return (Integer) baseDao.queryForObject("OssLog.getOssLogCount");
    }

    @Override
    public List<OssLog> getOssLogList() {
	return baseDao.queryForList("OssLog.getOssLogList");

    }

    @Override
    public List<OssLog> getOssLogListByPage(Integer beginNum, Integer onePageNum) {
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginNum", beginNum);
	parms.put("onePageNum", onePageNum);
	return baseDao.queryForList("OssLog.getOssLogListByPage", parms);
    }

    @Override
    public List<OssLog> getOssLogListByQueryCondition(Integer beginNum,
	    Integer onePageNum, Date beginTime, Date endTime, String name,
	    String operationType, Integer ossServerId) {
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginNum", beginNum);
	parms.put("onePageNum", onePageNum);
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("name", name);
	parms.put("operationType", operationType);
	parms.put("ossServerId", ossServerId);
	return baseDao.queryForList("OssLog.getOssLogListByQueryCondition",
		parms);
    }

    @Override
    public Integer updateOssLog(OssLog ossLog) {
	return baseDao.update("OssLog.updateOssLog", ossLog);

    }

}
