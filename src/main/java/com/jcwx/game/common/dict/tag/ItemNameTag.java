package com.jcwx.game.common.dict.tag;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.jcwx.game.common.SpringService;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.service.oss.IOssDictService;

/**
 * @author tanfl 改类用来封装专业数据中具体的数据项
 */
public class ItemNameTag extends TagSupport implements Serializable {

    private static final long serialVersionUID = -49725347569705171L;
    protected Integer gameId;

    protected String itemName;

    /**
     * 字典类别
     */
    protected String type;

    @Override
    public int doEndTag() throws JspException {
	//
	return EVAL_PAGE;
    }

    @Override
    public int doStartTag() throws JspException {
	// DictValue dict = new DictValue();
	IOssDictService dictService = (IOssDictService) SpringService
		.getBean("ossDictService");
	OssDictData dictData = dictService.getOssDictDataByName(gameId,
		Integer.parseInt(type), itemName, null);
	JspWriter out = pageContext.getOut();
	try {
	    if (dictData != null)
		out.write(dictData.getDictValue());
	    else {
//		out.write("字典中不存在名称：" + itemName);
		out.write(itemName);
	    }
	} catch (IOException e) {
	    throw new JspException(e);
	}
	return SKIP_BODY;
    }

    public Integer getGameId() {
	return gameId;
    }

    public String getItemName() {
	return itemName;
    }

    public String getType() {
	return type;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setItemName(String itemName) {
	this.itemName = itemName;
    }

    public void setType(String type) {
	this.type = type;
    }

}
