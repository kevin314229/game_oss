/**
 * 
 */
package com.jcwx.game.service.oss;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jcwx.game.domain.OssLog;

/**
 * @author Administrator
 * 
 */
public interface IOssLogService {

    /**
     * 推荐使用 createOssLog(BaseAdminContext baseAdminContext, String
     * operationType, String operationMsg, String remark) 通过上下文创建 创建日志记录
     * 
     * @param request
     * @param operationType
     *            操作类型
     * @param operationMsg
     *            操作内容
     * @param remark
     *            备注
     * @return
     */
    public Integer createOssLog(HttpServletRequest request,
	    String operationType, String operationMsg, String remark);

    /**
     * 创建日志记录
     * 
     * @param operationType
     *            操作类型
     * @param operationMsg
     *            操作内容
     */
    public void createOssLog(String operationType, String operationMsg);

    /**
     * 创建日志记录
     * 
     * @param operationType
     *            操作类型
     * @param operationMsg
     *            操作内容
     * @param remark
     *            备注
     * @return
     */
    public void createOssLog(String operationType, String operationMsg,
	    String remark);

    /**
     * 通过主键ID删除OssLog
     * 
     * @param id
     * @return 数据影响条数
     */
    public Integer deleteOssLogByID(String id);

    /**
     * 模糊查询总数
     * 
     * @param beginTime
     * @param endTime
     * @param name
     * @param operationType
     * @param ossServerId
     * @return
     */
    public Integer getAllNumByQueryCondition(Date beginTime, Date endTime,
	    String name, String operationType, Integer ossServerId);

    /**
     * 通过主键ID查询OssLog
     * 
     * @param id
     * @return OssLog
     */
    public OssLog getOssLogByID(String id);

    public Integer getOssLogCount();

    /**
     * 查询所有的OssLog
     * 
     * @return OssLog的集合
     */
    public List<OssLog> getOssLogList();

    public List<OssLog> getOssLogListByPage(Integer beginNum, Integer onePageNum);

    /**
     * 模糊查询
     * 
     * @param beginNum
     * @param onePageNum
     * @param beginTime
     * @param endTime
     * @param name
     * @param operationType
     * @param ossServerId
     * @return
     */
    public List<OssLog> getOssLogListByQueryCondition(Integer beginNum,
	    Integer onePageNum, Date beginTime, Date endTime, String name,
	    String operationType, Integer ossServerId);

    /**
     * 修改OssLog
     * 
     * @param ossLog
     * @return 数据影响条数
     */
    public Integer updateOssLog(OssLog ossLog);

}
