package com.jcwx.game.service.oss.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.Hint;
import com.jcwx.game.service.oss.IHintService;
import com.jcwx.game.web.InitHint;

@Service
public class HintService implements IHintService {

    @Autowired
    private IBaseDAO baseDAO;

    public Integer createHint(Hint hint) {
	    // 添加到缓存中
	    Map<String, String> hintMap = InitHint.getHintCacheByMenuId(hint
		    .getMenuId());
	    hintMap.put(hint.getHintKey(), hint.getHintValue());

	    return (Integer) this.baseDAO.insert("Hint.createHint", hint);
    }

    public void updateHint(Hint hint) {
	
	  Map<String, String> hintMap = InitHint.getHintCacheByMenuId(hint
		    .getMenuId());
	   hintMap.put(hint.getHintKey(), hint.getHintValue());
	   
	this.baseDAO.update("Hint.updateHint", hint);
    }

    public void deleteHintByID(String hintId) {//FIXED 需要删除缓存内容
	Hint hint =getHintByID( hintId);
	Map<String, String> hintMap = InitHint.getHintCacheByMenuId(hint.getMenuId());
	hintMap.remove(hint.getHintKey());
	this.baseDAO.delete("Hint.deleteHintByID", hintId);
    }

    public Hint getHintByID(String hintId) {
	return (Hint) this.baseDAO.queryForObject("Hint.getHintByID", hintId);
    }

    @SuppressWarnings("unchecked")
    public List<Hint> getHintList() {
	return this.baseDAO.queryForList("Hint.getHintList");
    }

    @SuppressWarnings("unchecked")
    public List<Hint> getHintListByMenuId(String menuId) {
	return this.baseDAO.queryForList("Hint.getHintListByMenuId", menuId);
    }

}
