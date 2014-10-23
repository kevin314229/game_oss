package com.jcwx.game.web;

public enum NoCheckUrlEnum {

    	ADMIN("/admin/"), BOTTOM("/admin/bottom.jsp"), DWZ_ZH_CN_FRAG(
	    "/admin/dwz.frag_zh_CN.xml"), DWZ_KO_KR_FRAG(
	    "/admin/dwz.frag_ko_KR.xml"), DWZ_EN_US_FRAG(
	    "/admin/dwz.frag_en_US.xml"), DWZ_VI_FRAG("/admin/dwz.frag_vi.xml"), DWZ_RU_FRAG(
	    "/admin/dwz.frag_ru.xml"), findOnlineInfo(
	    "/admin/findOnlineInfo.action"), findServer(
	    "/admin/findServer.action"), GameNotice("/admin/gameNotice.action"), JSONEDITOR(
	    "/admin/base/jsoneditor.action"), LEFT("/admin/left.jsp"), LEFTMENU(
	    "/admin/leftMenu.action"), LOGINACTION("/admin/login.action"), LOGINTO(
	    "/admin/login_toLogin.action"), LOGOUTACTION("/admin/logout.action"), MAIN(
	    "/admin/main.jsp"), MIDDLE("/admin/middle.jsp"), NOPURVIEW(
	    "/admin/nopurview.jsp"), selectOssPorject(
	    "/admin/selectOssPorject.action"), SELECTOSSSERVER(
	    "/admin/selectOssServer.action"), SELECTSERVER(
	    "/admin/selectServer.jsp"), SELECTSERVERPt(
	    "/admin/selectOssServerPt.action"), SERVER_INFO(
	    "/admin/system/serverInf.jsp"), TOP("/admin/top.jsp"),TIP("/admin/tip.action"),TEST("/admin/test.action");

    private String code;

    private NoCheckUrlEnum(String code) {
	this.code = code;
    }

    public String getCode() {
	return code;
    }

}
