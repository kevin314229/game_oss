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
public class ItemValueTag extends TagSupport implements Serializable {

    private static final long serialVersionUID = -497254947569705171L;
    protected Integer gameId;

    protected String itemValue;

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
	OssDictData dictData = dictService.getOssDictDataByValue(gameId,
		Integer.parseInt(type), itemValue);
	JspWriter out = pageContext.getOut();
	try {
	    if (dictData != null)
		out.write(dictData.getDictName());
	    else {
//		out.write("字典中不存在值：" + itemValue);
		out.write(itemValue);
	    }
	} catch (IOException e) {
	    throw new JspException(e);
	}
	return SKIP_BODY;
    }

    public Integer getGameId() {
	return gameId;
    }

    public String getItemValue() {
	return itemValue;
    }

    public String getType() {
	return type;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setItemValue(String itemValue) {
	this.itemValue = itemValue;
    }

    public void setType(String type) {
	this.type = type;
    }

}
