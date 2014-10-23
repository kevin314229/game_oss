package com.jcwx.game.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jcwx.game.common.SpringService;
import com.jcwx.game.domain.Hint;
import com.jcwx.game.domain.OssMenu;
import com.jcwx.game.service.oss.IHintService;
import com.jcwx.game.service.oss.IOssMenuService;
import com.jcwx.game.service.oss.impl.OssMenuService;

public class InitHint {

    private static Map<String, Map<String, String>> cache = new ConcurrentHashMap<String, Map<String, String>>();

    public static boolean init() {
	IHintService hintService = SpringService.getBean(IHintService.class);
	List<Hint> hintList = hintService.getHintList();
	for (Hint hint : hintList) {
	    Map<String, String> menuListMap = (Map<String, String>) cache
		    .get(hint.getMenuId());
	    if (menuListMap == null) {
		menuListMap = new HashMap<String, String>();
	    }
	    menuListMap.put(hint.getHintKey(), hint.getHintValue());
	    cache.put(hint.getMenuId(), menuListMap);
	}
	return true;
    }
   

    public static Map<String, String> getHintCacheByMenuId(String menuId) {
	Map<String, String> menuMap = cache.get(menuId);
	if (menuMap == null) {
	    menuMap = new HashMap<String, String>();
	    cache.put(menuId, menuMap);
	}
	return menuMap;
    }

    public static Map<String, String> getHintCache(Integer projectId, String url) {
	IOssMenuService ossMenuService = SpringService
		.getBean(OssMenuService.class);
	OssMenu ossMenu = ossMenuService.getOssMenuByPageUrl(projectId, url);
	if (ossMenu == null) {
	    return Collections.emptyMap();
	}
	// Validate.notNull(ossMenu,String.format("没有找到菜单,请确认url:%s",url));
	Map<String, String> hintMap = cache.get(ossMenu.getOssMenuID());
	return hintMap == null ? Collections.<String, String> emptyMap()
		: hintMap;
    }

}
