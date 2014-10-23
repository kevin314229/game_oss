/**
 * 
 */
package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.SystemNotice;

/**
 * @author Administrator
 * 
 */
public interface ISystemNoticeService {

    /**
     * 创建SystemNotice
     * 
     * @param systemNotice
     * @return 数据影响条数
     */
    public Integer createSystemNotice(SystemNotice systemNotice);

    /**
     * 通过主键ID删除SystemNotice
     * 
     * @param systemNoticeId
     * @return 数据影响条数
     */
    public Integer deleteSystemNoticeByID(Integer systemNoticeId);

    /**
     * 通过主键ID查询SystemNotice
     * 
     * @param systemNoticeId
     * @return SystemNotice
     */
    public SystemNotice getSystemNoticeByID(Integer systemNoticeId);

    /**
     * 查询所有系统消息
     * 
     * @return
     */
    public List<SystemNotice> getSystemNoticeList();

    /**
     * 根据服务器Id查询对于服务器所属的系统消息SystemNotice
     * 
     * @param serverId
     *            服务器ID
     * @return List<SystemNotice>
     */
    public List<SystemNotice> getSystemNoticeListByServerId(Integer serverId);

    /**
     * 修改SystemNotice
     * 
     * @param systemNotice
     * @return 数据影响条数
     */
    public Integer updateSystemNotice(SystemNotice systemNotice);

}
