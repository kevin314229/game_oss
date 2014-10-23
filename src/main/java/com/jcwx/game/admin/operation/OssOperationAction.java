/**
 * 
 */
package com.jcwx.game.admin.operation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.OperationServer;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.OssOperationServer;
import com.jcwx.game.service.oss.IOperationServerServer;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;

/**
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class OssOperationAction extends BasalAction /*
						     * implements
						     * ModelDriven<OssOperation>
						     */{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // OssOperation model = new OssOperation();

    /*
     * @Override public OssOperation getModel() { // TODO Auto-generated method
     * stub return model; }
     */
    /**  */
    private Integer id;

    private String[] leftSideCartoonCharacters;

    @Autowired
    private IOperationServerServer operationServerServer;

    private OssOperation ossOperation;

    private List<OssOperation> ossOperationList;

    /** 运营商关联的服务器 **/
    private List<OssOperationServer> ossOperationServerList;

    @Autowired
    private IOssOperationService ossOperationService;

    /** 服务区列表 */
    private List<OssServer> ossServerList;

    @Autowired
    private IOssServerService ossServerService;

    private String[] rightSideCartoonCharacters;

    /** 服务器详情 ***/
    private String serverInfo;

    /** 新增运营商 */
    @Action(value = "ossOperation_addOssOperation", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "ossOperation_operationList", "namespace",
		    "/admin/base", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../admin/operation/addOssOperation.jsp") })
    public String addOssOperation() throws Exception {
	ossOperationService.createOssOperation(ossOperation);
	setActionMsg("创建成功！");
	return SUCCESS;
    }

    @Action(value = "ossOperation_addOssOperationIndex", results = { @Result(name = "success", location = "../../admin/operation/addOssOperation.jsp") })
    public String addOssServerIndex() {
	return SUCCESS;
    }

    @Action(value = "ossOperation_addOssServerOperationIndex", results = { @Result(name = "success", location = "../../admin/operation/addOssServerOperation.jsp") })
    public String addOssServerOperationIndex() {
	ossOperationList = ossOperationService.getOssOperationList();
	if (ossOperationList.size() > 0) {
	    OssOperation ossOperation = ossOperationList.get(0);
	    ossServerList = ossServerService
		    .getOssServerListByOperationId(ossOperation.getId());
	}
	int operationId = Integer.valueOf(ServletActionContext.getRequest()
		.getParameter("id"));
	ossOperation = ossOperationService.getOssOperationByID(operationId);
	List list = operationServerServer
		.getOperationServerByOperationID(operationId);
	this.ossOperationServerList = new ArrayList<OssOperationServer>();
	for (int j = 0; j < list.size(); j++) {
	    Map map = (Map) list.get(j);
	    OssOperationServer ossOperationServer = new OssOperationServer();
	    ossOperationServer.setServerId(Integer.valueOf(map.get("serverId")
		    .toString()));
	    ossOperationServer.setServerName(map.get("serverName").toString());
	    ossOperationServerList.add(ossOperationServer);
	}

	return SUCCESS;
    }

    /** 删除运营商 */
    @Action(value = "ossOperation_deleteOssOperation", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "ossOperation_operationList", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}" }) })
    public String deleteOssOperation() throws Exception {
	// 登录环境
	String id = ServletActionContext.getRequest().getParameter("id");
	int k = ossOperationService
		.deleteOssOperationByID(Integer.parseInt(id));
	if (k > 0) {
	    setActionMsg("删除成功！");
	} else {
	    setActionMsg("删除失败！");
	}
	return SUCCESS;
    }

    @Action(value = "ossOperation_getOssServerListByOperationId")
    public String getEquipPropertyList() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	int operationId = Integer.valueOf(ServletActionContext.getRequest()
		.getParameter("id"));
	ossServerList = ossServerService
		.getOssServerListByOperationId(operationId);
	out.print(JSON.toJSON(ossServerList).toString());
	out.close();
	return null;
    }

    public Integer getId() {
	return id;
    }

    public String[] getLeftSideCartoonCharacters() {
	return leftSideCartoonCharacters;
    }

    public IOperationServerServer getOperationServerServer() {
	return operationServerServer;
    }

    public OssOperation getOssOperation() {
	return ossOperation;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public List<OssOperationServer> getOssOperationServerList() {
	return ossOperationServerList;
    }

    public IOssOperationService getOssOperationService() {
	return ossOperationService;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    public IOssServerService getOssServerService() {
	return ossServerService;
    }

    public String[] getRightSideCartoonCharacters() {
	return rightSideCartoonCharacters;
    }

    public String getServerInfo() {
	return serverInfo;
    }

    @Action(value = "ossOperation_operationList", results = { @Result(name = "success", location = "../../admin/operation/listOssOperation.jsp") })
    public String index() throws Exception {
	try {
	    if (getActionMsg() != null && !"".equals(getActionMsg())) {
		// actionMsg = new String(actionMsg.getBytes("ISO8859-1"),
		// "utf-8");
		addActionMessage(getActionMsg());
	    }
	    this.ossOperationList = ossOperationService.getOssOperationList();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setLeftSideCartoonCharacters(String[] leftSideCartoonCharacters) {
	this.leftSideCartoonCharacters = leftSideCartoonCharacters;
    }

    public void setOperationServerServer(
	    IOperationServerServer operationServerServer) {
	this.operationServerServer = operationServerServer;
    }

    public void setOssOperation(OssOperation ossOperation) {
	this.ossOperation = ossOperation;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public void setOssOperationServerList(
	    List<OssOperationServer> ossOperationServerList) {
	this.ossOperationServerList = ossOperationServerList;
    }

    public void setOssOperationService(IOssOperationService ossOperationService) {
	this.ossOperationService = ossOperationService;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public void setOssServerService(IOssServerService ossServerService) {
	this.ossServerService = ossServerService;
    }

    public void setRightSideCartoonCharacters(
	    String[] rightSideCartoonCharacters) {
	this.rightSideCartoonCharacters = rightSideCartoonCharacters;
    }

    public void setServerInfo(String serverInfo) {
	this.serverInfo = serverInfo;
    }

    /**
     * 分配服务器给运营商
     * 
     * @return
     */
    @Action(value = "ossOperation_updateAddOssServerOperation", results = { @Result(name = "toAddOssServerOperation", type = "redirectAction", params = {
	    "actionName", "ossOperation_addOssServerOperationIndex",
	    "namespace", "/admin/base", "actionMsg", "${actionMsg}", "id",
	    "${id}" }) })
    public String updateAddOssServerManagerr() {
	String id = ServletActionContext.getRequest().getParameter("id");
	Integer operationId = Integer.parseInt(id);// 服务器ID
	Date dateTime = new java.util.Date();
	OperationServer operationServer = null;
	this.operationServerServer
		.deleteOperationServerByoperationID(operationId.toString());
	String serverIds[] = serverInfo.split(",");
	if (serverIds.length > 0 && !serverIds[0].equals("")) {
	    for (String serverId : serverIds) {// 已分配人员
		operationServer = new OperationServer();
		operationServer.setOperationId(operationId);
		operationServer.setServerId(Integer.valueOf(serverId));
		operationServer.setCreateTime(dateTime);
		this.operationServerServer
			.createOperationServer(operationServer);
	    }
	}
	setActionMsg("授权成功！");
	return "toAddOssServerOperation";
    }

    /** 修改游戏区 */
    @Action(value = "ossOperation_updateOssOperation", results = { @Result(name = "success", location = "../../admin/operation/updateOssOperation.jsp") })
    public String updateOssOperation() throws Exception {
	this.ossOperationService.updateOssOperation(ossOperation);
	this.setActionMsg("修改成功!");
	return SUCCESS;
    }

    /** 修改游戏区 */
    @Action(value = "ossOperation_updateOssOperationIndex", results = { @Result(name = "success", location = "../../admin/operation/updateOssOperation.jsp") })
    public String updateOssOperationIndex() throws Exception {
	String id = ServletActionContext.getRequest().getParameter("id");
	if (StringUtils.isNumeric(id)) {
	    ossOperation = ossOperationService.getOssOperationByID(Integer
		    .parseInt(id));
	}
	return SUCCESS;
    }

}
