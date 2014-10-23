package com.jcwx.game.web;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/** 管理员在线监听 */
public class AdminOnlineListener implements HttpSessionListener,
	HttpSessionAttributeListener {

    private HttpSession session2 = null;

    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0) {
	HttpSession session = arg0.getSession();
	// System.out.println("**增加属性:" + arg0.getName() + "-->" +
	// arg0.getValue()) ;
	// if
	// ((arg0.getName()).equals(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY))
	// {
	// //管理员在线缓存
	// HashMap<String,HttpSession> map =
	// (HashMap<String,HttpSession>)CacheService.getFromCache(CacheConstant.ADMIN_ONLINE_SESSION_MAP);
	// map.put(session.getId(), session);
	//
	// }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
	// System.out.println("**删除属性:" + arg0.getName() + "-->" +
	// arg0.getValue()) ;
	// if
	// ((arg0.getName()).equals(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY))
	// {
	// HashMap<String,Object> map =
	// (HashMap)CacheService.getFromCache(CacheConstant.ADMIN_ONLINE_SESSION_MAP);
	// if(map!=null){
	// map.remove(arg0.getSession().getId());
	// }
	// }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
	// System.out.println("**更改属性:" + arg0.getName() + "-->" +
	// arg0.getValue()) ;

    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
	// this.session2 = arg0.getSession();
	// System.out.println("** 创建...") ;
	// System.out.println("SessionID:" + this.session2.getId()) ;

	// 查看，每次

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
	// System.out.println("**销毁...") ;

    }

}
