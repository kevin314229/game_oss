package com.jcwx.game.service.oss.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.PayInfo;
import com.jcwx.game.domain.PlayerBaseRank;
import com.jcwx.game.service.oss.IPlayerBaseRankService;

@Service
public class PlayerBaseRankService implements IPlayerBaseRankService{
    
    @Autowired
    private IBaseStaDAO baseDAO;

    @Override
    public Integer createPlayerBaseRank(PlayerBaseRank PlayerBaseRank) {
	 
	return (Integer) baseDAO.insert("", PlayerBaseRank);
    }

    @Override
    public Integer updatePlayerBaseRank(PlayerBaseRank PlayerBaseRank) {
	// TODO Auto-generated method stub
	return  (Integer) baseDAO.update("", PlayerBaseRank);
    }

    @Override
    public Integer deletePlayerBaseRankByID(String gameId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public PlayerBaseRank getPlayerBaseRankByID(String gameId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<PlayerBaseRank> getPlayerBaseRankList(Map params) {
	return baseDAO.queryForList("PlayerBaseRank.getPlayerBaseRankList", params);
    }

    @Override
    public Integer getPlayerBaseRankCount(Map params) {

	return (Integer) baseDAO.queryForObject("PlayerBaseRank.getPlayerBaseRankCount",params);
    }

    public List<PayInfo> getPayInfoList(Map params){
	return baseDAO.queryForList("PlayerBaseRank.getPayInfoList", params);

    }
    public Integer getPayInfoCount(Map params){
	return (Integer) baseDAO.queryForObject("PlayerBaseRank.getPayInfoCount",params);
    }

}
