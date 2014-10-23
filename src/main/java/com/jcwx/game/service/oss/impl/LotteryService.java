package com.jcwx.game.service.oss.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssLottery;
import com.jcwx.game.service.oss.ILotteryService;

@Service
public class LotteryService implements ILotteryService {
    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public Integer createOssLottery(OssLottery ossLottery) {
	return (Integer) baseDAO.insert("OssLottery.createOssLottery", ossLottery);
    }

    @Override
    public OssLottery getOssLotteryByServialNo(String ServialNo) {
	return (OssLottery) baseDAO.queryForObject("OssLottery.getOssLotteryBySerialNo", ServialNo);
    }


}
