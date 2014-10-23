package com.jcwx.game.service.oss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.ServerNotice;
import com.jcwx.game.service.oss.IServerNoticeService;

@Service
public class ServerNoticeService implements IServerNoticeService {

    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public Integer createServerNotice(ServerNotice serverNotice) {
	return (Integer) this.baseDAO.insert("ServerNotice.createServerNotice",
		serverNotice);
    }

    @Override
    public void deleteServerNoticeByID(String noticeId) {
	this.baseDAO.delete("ServerNotice.deleteServerNoticeByID", noticeId);

    }

    @Override
    public ServerNotice getServerNoticeByID(String noticeId) {
	return (ServerNotice) this.baseDAO.queryForObject(
		"ServerNotice.getServerNoticeByID", noticeId);
    }

    @Override
    public List<ServerNotice> getServerNoticeByServerID(int ServerID) {
	return this.baseDAO.queryForList(
		"ServerNotice.getServerNoticeByServerID", ServerID);
    }

    @Override
    public ServerNotice getServerNoticeByUrl(String url) {
	List<ServerNotice> serverNoticeList = this.baseDAO.queryForList(
		"ServerNotice.getServerNoticeByUrl", url);
	return serverNoticeList.size() > 0 ? serverNoticeList.get(0) : null;
    }

    @Override
    public List<ServerNotice> getServerNoticeList() {
	return this.baseDAO.queryForList("ServerNotice.getServerNoticeList");
    }

    @Override
    public void updateServerNotice(ServerNotice serverNotice) {

	this.baseDAO.update("ServerNotice.updateServerNotice", serverNotice);

    }

}
