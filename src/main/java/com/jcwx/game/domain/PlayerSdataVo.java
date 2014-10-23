package com.jcwx.game.domain;

import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.lang.Validate;
import org.springframework.util.Assert;

import com.jcwx.game.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class PlayerSdataVo extends BaseDomain {

    public static PlayerSdataVo convertSdataVoList(List<PlayerSdata> sdatas,
	    Integer playerId) throws IllegalArgumentException {
	// Validate.notNull(sdatas);
	// Validate.notNull(playerId);
	Assert.isTrue(sdatas.size() <= 2, "sdatas.size must be less than 2");
	PlayerSdataVo vo = new PlayerSdataVo();
	for (PlayerSdata sdata : sdatas) {
	    if (sdata.getType().intValue() == 944) {
		vo.setFirstType(sdata.getVal());
		vo.setFirstId(playerId);
	    }
	    if (sdata.getType().intValue() == 945) {
		List<Integer> vos = new ArrayList<Integer>();
		String sdataReceive = Integer.toBinaryString(sdata.getVal());
		for (int i = 0; i < 8; i++) {
		    if (i >= sdataReceive.length()) {
			vos.add(0);
			continue;
		    }
		    vos.add(Integer.valueOf(String.valueOf(sdataReceive
			    .charAt(sdataReceive.length() - 1 - i))));
		}
		vo.setReceive(vos);
		vo.setId(sdata.getId());
		vo.setVal(sdata.getVal());
	    }

	}
	if (vo.getReceive() == null) {
	    List<Integer> vos = new ArrayList<Integer>();
	    for (int i = 0; i < 8; i++) {
		vos.add(0);
	    }
	    vo.setReceive(vos);
	}

	vo.setPlayerId(playerId);
	return vo;
    }

    private Integer firstId;
    /**  */
    private Integer firstType;

    /**  */
    private Integer id;

    /**  */
    private Integer playerId;

    private List<Integer> receive;

    private Integer val;

    public Integer getFirstId() {
	return this.firstId;
    }

    public Integer getFirstType() {
	return this.firstType;
    }

    public Integer getId() {
	return this.id;
    }

    public Integer getPlayerId() {
	return this.playerId;
    }

    public List<Integer> getReceive() {
	return this.receive;
    }

    public Integer getVal() {
	return this.val;
    }

    public void setFirstId(Integer firstId) {
	this.firstId = firstId;
    }

    public void setFirstType(Integer firstType) {
	this.firstType = firstType;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setReceive(List<Integer> receive) {
	this.receive = receive;
    }

    public void setVal(Integer val) {
	this.val = val;
    }

}