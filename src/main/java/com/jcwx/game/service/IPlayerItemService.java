package com.jcwx.game.service;

import java.util.List;

import com.jcwx.game.domain.BaseEquip;
import com.jcwx.game.domain.BaseProperty;
import com.jcwx.game.domain.PlayerEquipProp;
import com.jcwx.game.domain.PlayerItem;

public interface IPlayerItemService {
    public Integer addItem(Integer playerId, Integer itemType, Integer itemNum);

    public Integer createPlayerItem(PlayerItem playerItem);

    public Integer deleteItem(Integer playerId, PlayerItem playerItem);

    public void deletePlayerBoxById(Integer playerBoxId);

    public void deletePlayerBoxByPlayerItemId(Integer playerItemId);

    public Integer exchangeItem(Integer firstItemId, Integer secondItemId);

    public List<BaseEquip> getBaseEquipList();

    public List<BaseProperty> getBasePropertyList();

    public PlayerEquipProp getEquipPropByEquipId(Integer playerEquipPropId);

    // 获取玩家装备物品列表
    public List<PlayerItem> getPlayerEquipItemList(Integer playerId);

    public PlayerItem getPlayerItem(Integer playerId, Integer playerItemId);

    // 获取背包物品列表
    public List<PlayerItem> getPlayerItemList(Integer playerId);

    public Integer updatePlayerItem(PlayerItem playerItem);
}
