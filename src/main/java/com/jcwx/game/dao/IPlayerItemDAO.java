package com.jcwx.game.dao;

import java.util.List;

import com.jcwx.game.domain.BaseEquip;
import com.jcwx.game.domain.BaseProperty;
import com.jcwx.game.domain.PlayerEquipProp;
import com.jcwx.game.domain.PlayerItem;

public interface IPlayerItemDAO {

    /**
     * 创建PlayerItem
     * 
     * @param playerItem
     * @return 数据影响条数
     */
    public Integer createPlayerItem(PlayerItem playerItem);

    public void deletePlayerBoxById(Integer playerBoxId);

    public void deletePlayerBoxByPlayerItemId(Integer playerItemId);

    /**
     * 通过主键ID删除PlayerItem
     * 
     * @param playerItemId
     * @return 数据影响条数
     */
    public Integer deletePlayerItemByID(Integer playerId, PlayerItem playerItem);

    public List<BaseEquip> getBaseEquipList();

    public List<BaseProperty> getBasePropertyList();

    public PlayerEquipProp getEquipPropByEquipId(Integer playerEquipPropId);

    public List<PlayerItem> getPlayerEquipItemList(Integer playerId);

    /**
     * 通过主键ID查询PlayerItem
     * 
     * @param playerItemId
     * @return PlayerItem
     */
    public PlayerItem getPlayerItemByID(Integer playerId, Integer playerItemId);

    public List<PlayerItem> getPlayerItemByPlayerID(Integer playerId);

    /**
     * 查询所有的PlayerItem
     * 
     * @return PlayerItem的集合
     */
    public List<PlayerItem> getPlayerItemList(Integer playerId);

    /**
     * 修改PlayerItem
     * 
     * @param playerItem
     * @return 数据影响条数
     */
    public Integer updatePlayerItem(PlayerItem playerItem);
}