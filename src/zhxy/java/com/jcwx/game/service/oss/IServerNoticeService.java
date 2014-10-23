package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.ServerNotice;

public interface IServerNoticeService {

    /**
     * 创建ServerNotice
     * 
     * @param serverNotice
     * @return 数据影响条数
     */
    public Integer createServerNotice(ServerNotice serverNotice);

    /**
     * 通过主键ID删除ServerNotice
     * 
     * @param noticeId
     * @return 数据影响条数
     */
    public void deleteServerNoticeByID(String noticeId);

    /**
     * 通过主键ID查询ServerNotice
     * 
     * @param noticeId
     * @return ServerNotice
     */
    public ServerNotice getServerNoticeByID(String noticeId);

    /**
     * 查询所有的ServerNotice
     * 
     * @return ServerNotice的集合
     */
    public List<ServerNotice> getServerNoticeByServerID(int ServerID);

    /**
     * 查询所有的ServerNotice
     * 
     * @return ServerNotice的集合
     */
    public ServerNotice getServerNoticeByUrl(String url);

    /**
     * 查询所有的ServerNotice
     * 
     * @return ServerNotice的集合
     */
    public List<ServerNotice> getServerNoticeList();

    /**
     * 修改ServerNotice
     * 
     * @param serverNotice
     * @return 数据影响条数
     */
    public void updateServerNotice(ServerNotice serverNotice);

}
