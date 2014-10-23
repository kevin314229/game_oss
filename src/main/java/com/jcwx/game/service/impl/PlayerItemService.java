package com.jcwx.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.common.constants.ItemConstants;
import com.jcwx.game.dao.IPlayerItemDAO;
import com.jcwx.game.domain.BaseEquip;
import com.jcwx.game.domain.BaseProperty;
import com.jcwx.game.domain.PlayerEquipProp;
import com.jcwx.game.domain.PlayerItem;
import com.jcwx.game.service.IPlayerItemService;

@Service
public class PlayerItemService implements IPlayerItemService {
    @Autowired
    private IPlayerItemDAO playerItemDAO;

    @Override
    public Integer addItem(Integer playerId, Integer itemType, Integer itemNum) {
	PlayerItem playerItem = new PlayerItem();
	playerItem.setItemPlace(ItemConstants.ITEM_WARE); // 新增物品，都在仓库
	// 设置物品在仓库的位置-待定

	playerItemDAO.createPlayerItem(playerItem);

	return null;
    }

    @Override
    public Integer createPlayerItem(PlayerItem playerItem) {
	return playerItemDAO.createPlayerItem(playerItem);
    }

    @Override
    public Integer deleteItem(Integer playerId, PlayerItem playerItem) {
	return playerItemDAO.deletePlayerItemByID(playerId, playerItem);
    }

    @Override
    public void deletePlayerBoxById(Integer playerBoxId) {
	// TODO Auto-generated method stub
	playerItemDAO.deletePlayerBoxById(playerBoxId);
    }

    @Override
    public void deletePlayerBoxByPlayerItemId(Integer playerItemId) {
	// TODO Auto-generated method stub
	playerItemDAO.deletePlayerBoxByPlayerItemId(playerItemId);
    }

    @Override
    public Integer exchangeItem(Integer firstItemId, Integer secondItemId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<BaseEquip> getBaseEquipList() {
	return playerItemDAO.getBaseEquipList();
    }

    @Override
    public List<BaseProperty> getBasePropertyList() {
	return playerItemDAO.getBasePropertyList();
    }

    @Override
    public PlayerEquipProp getEquipPropByEquipId(Integer playerEquipPropId) {
	// TODO Auto-generated method stub
	return playerItemDAO.getEquipPropByEquipId(playerEquipPropId);
    }

    @Override
    public List<PlayerItem> getPlayerEquipItemList(Integer playerId) {
	return playerItemDAO.getPlayerEquipItemList(playerId);
    }

    @Override
    public PlayerItem getPlayerItem(Integer playerId, Integer playerItemId) {
	return playerItemDAO.getPlayerItemByID(playerId, playerItemId);
    }

    @Override
    public List<PlayerItem> getPlayerItemList(Integer playerId) {
	return playerItemDAO.getPlayerItemList(playerId);
    }

    @Override
    public Integer updatePlayerItem(PlayerItem playerItem) {
	// TODO Auto-generated method stub
	playerItemDAO.updatePlayerItem(playerItem);
	return null;
    }

}
