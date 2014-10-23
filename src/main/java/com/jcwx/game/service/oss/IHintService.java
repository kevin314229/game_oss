package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.Hint;

public interface IHintService {

    /**
     * 创建Hint
     * 
     * @param hint
     * @return 数据影响条数
     */
    public Integer createHint(Hint hint);

    /**
     * 修改Hint
     * 
     * @param hint
     * @return 数据影响条数
     */
    public void updateHint(Hint hint);

    /**
     * 通过主键ID删除Hint
     * 
     * @param hintId
     * @return 数据影响条数
     */
    public void deleteHintByID(String hintId);

    /**
     * 通过主键ID查询Hint
     * 
     * @param hintId
     * @return Hint
     */
    public Hint getHintByID(String hintId);

    /**
     * 查询所有的Hint
     * 
     * @return Hint的集合
     */
    public List<Hint> getHintList();
    /**
     * 查询所有的Hint
     * 
     * @return Hint的集合
     */
    public List<Hint> getHintListByMenuId(String menuId);

}
