package com.jcwx.game.admin.assay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.admin.vo.PageMessage;
import com.jcwx.game.http.domain.Constellation;
import com.jcwx.game.http.domain.OssPlayerConstellation;


/**
 * 星座等级统计
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class PlayerConstellationAction extends BaseInfoAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<Constellation> ossConstellationList = new ArrayList<Constellation>();

    private PageMessage pageMessage = PageMessage.getOkMessage();

    public List<Constellation> getOssConstellationList() {
	return ossConstellationList;
    }

    @Action(value = "playerConstellation_index", results = { @Result(name = "success", location = "../../admin/assay/playerConstellationAssay.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "PlayerConstellationHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		List<OssPlayerConstellation> pclist = (List<OssPlayerConstellation>) object
			.get("ossPlayerConstellationList");
		int index = 0;
		Constellation ctl = null;
		for (int i = 0; i < pclist.size(); i++) {
		    OssPlayerConstellation opc = pclist.get(i);
		    if (i == 0) {
			index = opc.getLevel();
			ctl = new Constellation();
			ctl.setLevel(opc.getLevel());
		    } else if (index != opc.getLevel()) {
			index = opc.getLevel();
			ossConstellationList.add(ctl);
			ctl = new Constellation();
			ctl.setLevel(opc.getLevel());
		    }
		    if (opc.getType() == 1) {
			ctl.setType1(opc.getNub());
		    } else if (opc.getType() == 2) {
			ctl.setType2(opc.getNub());
		    } else if (opc.getType() == 3) {
			ctl.setType3(opc.getNub());
		    } else if (opc.getType() == 4) {
			ctl.setType4(opc.getNub());
		    } else if (opc.getType() == 5) {
			ctl.setType5(opc.getNub());
		    } else if (opc.getType() == 6) {
			ctl.setType6(opc.getNub());
		    } else if (opc.getType() == 7) {
			ctl.setType7(opc.getNub());
		    } else if (opc.getType() == 8) {
			ctl.setType8(opc.getNub());
		    } else if (opc.getType() == 9) {
			ctl.setType9(opc.getNub());
		    } else if (opc.getType() == 10) {
			ctl.setType10(opc.getNub());
		    } else if (opc.getType() == 11) {
			ctl.setType11(opc.getNub());
		    } else if (opc.getType() == 12) {
			ctl.setType12(opc.getNub());
		    }
		}
		ossConstellationList.add(ctl);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setOssConstellationList(List<Constellation> ossConstellationList) {
	this.ossConstellationList = ossConstellationList;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
