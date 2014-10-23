package com.jcwx.game.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.dao.IPlayerItemDAO;
import com.jcwx.game.domain.BaseEquip;
import com.jcwx.game.domain.BaseProperty;
import com.jcwx.game.domain.PlayerEquipProp;
import com.jcwx.game.domain.PlayerItem;

@Repository
public class PlayerItemDAO implements IPlayerItemDAO {
    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public Integer createPlayerItem(PlayerItem playerItem) {
	baseDAO.insert("PlayerItem.createPlayerItem", playerItem);
	// if ( playerItem.getItemType() == 1 ) {
	// playerItem.getPlayerEquipProp().setPlayerEquipId(playerItem.getPlayerItemId());
	// baseDAO.insert("PlayerEquipProp.createPlayerEquipProp",
	// playerItem.getPlayerEquipProp());
	// }
	return null;
    }

    @Override
    public void deletePlayerBoxById(Integer playerBoxId) {
	baseDAO.delete("PlayerBox.deletePlayerBoxByID", playerBoxId);
    }

    @Override
    public void deletePlayerBoxByPlayerItemId(Integer playerItemId) {
	baseDAO.delete("PlayerBox.deletePlayerBoxByPlayerItemID", playerItemId);
    }

    @Override
    public Integer deletePlayerItemByID(Integer playerId, PlayerItem playerItem) {
	// 缓存清除
	return null;
    }

    @Override
    public List<BaseEquip> getBaseEquipList() {
	return baseDAO.queryForList("BaseEquip.getBaseEquipList");
    }

    @Override
    public List<BaseProperty> getBasePropertyList() {
	return baseDAO.queryForList("BaseProperty.getBasePropertyList");
    }

    @Override
    public PlayerEquipProp getEquipPropByEquipId(Integer playerEquipPropId) {
	return (PlayerEquipProp) baseDAO.queryForObject(
		"PlayerItem.getEquipPropByEquipId", playerEquipPropId);
    }

    @Override
    public List<PlayerItem> getPlayerEquipItemList(Integer playerId) {
	return baseDAO.queryForList("PlayerItem.getPlayerEquipItemList");
    }

    @Override
    public PlayerItem getPlayerItemByID(Integer playerId, Integer playerItemId) {
	PlayerItem pi = null;
	if (pi == null)
	    return (PlayerItem) baseDAO.queryForObject(
		    "PlayerItem.getPlayerItemByID", playerItemId);
	return pi;
    }

    @Override
    public List<PlayerItem> getPlayerItemByPlayerID(Integer playerId) {
	return baseDAO.queryForList("PlayerItem.getPlayerItemByPlayerID",
		playerId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PlayerItem> getPlayerItemList(Integer playerId) {
	return baseDAO.queryForList("PlayerItem.getPlayerItemListByPlayerId",
		playerId);
    }

    @Override
    public Integer updatePlayerItem(PlayerItem playerItem) {
	baseDAO.update("PlayerItem.updatePlayerItem", playerItem);
	return null;
    }

}