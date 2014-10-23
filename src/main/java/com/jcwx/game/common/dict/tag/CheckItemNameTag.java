package com.jcwx.game.common.dict.tag;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.jcwx.game.common.SpringService;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.service.oss.IOssDictService;

/**
 * @author tanfl 改类用来封装专业数据中具体的数据项
 */
public class CheckItemNameTag extends TagSupport implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** select class 样式 */
    protected String cssClass;

    private boolean disabled = false;
    /** select样式 */
    protected String expression;
    /** 游戏id */
    protected int gameId;
    /** select ID */
    protected String id;
    /** select name */
    protected String name;
    /** select onchage事件 */
    protected String onchange;
    /** select style样式 */
    protected String style;
    /**
     * 字典类别
     */
    protected int type;
    /** select value值 */
    protected String[] value;

    protected void addOption(StringBuffer sb, String label, String value,
	    boolean matched) {
	sb.append("<input type='checkbox' value=\"");
	sb.append(value);
	sb.append("\"");

	if (matched)
	    sb.append(" checked=\"checked\"");
	// if (style != null) {
	// sb.append(" style=\"");
	// sb.append(style);
	// sb.append("\"");
	// }
	//
	// if (cssClass != null) {
	// sb.append(" class=\"");
	// sb.append(cssClass);
	// sb.append("\"");
	// }
	if (onchange != null && !"".equals(onchange)) {
	    sb.append(" onchange='" + onchange + "'");
	}
	if (cssClass != null && !"".equals(cssClass)) {
	    sb.append(" class='" + cssClass + "'");
	}
	if (style != null && !"".equals(style)) {
	    sb.append(" style='" + style + "'");
	}
	if (disabled) {
	    sb.append(" disabled");
	}
	sb.append(" />&nbsp;&nbsp;");
	sb.append(label);
    }

    @Override
    public int doEndTag() throws JspException {
	//
	return EVAL_PAGE;
    }

    // @Autowired
    // private IOssDictService dictService ;
    //
    @Override
    public int doStartTag() throws JspException {
	// IOssDictService dictService = (IOssDictService)
	// SpringContextUtil.getBean("userService");
	IOssDictService dictService = (IOssDictService) SpringService
		.getBean("ossDictService");
	// try{
	// if (value.startsWith("${")) {
	// value = value.substring(2, value.lastIndexOf("}"));
	// String bean = value;
	//
	// if (value.indexOf(".") != -1) {
	// bean = value.substring(0, value.indexOf("."));
	// }
	// Object obj = pageContext.findAttribute(bean);
	// if (obj == null)
	// throw new JspException("dict:select error,会话中不存在属性：" + bean);
	//
	// if (value.indexOf(".") != -1) {
	// String prop = value.substring(value.indexOf(".") + 1);
	//
	// // if(obj instanceof NiceForm)
	// // prop="$("+prop+")";
	//
	// try {
	// // Object tmp = TagUtils.buildNamespace(arg0, arg1,
	// pageContext.getRequest())//
	// Object tmp = PropertyUtils.getProperty(obj, prop);
	// if (tmp != null)
	// value = tmp.toString();
	// else
	// value = null;
	// } catch (Exception e) {
	// throw new JspException("dict:select error," + bean + "对象中不存在属性：" +
	// prop, e);
	// }
	// } else
	// value = obj.toString();
	// }
	// } catch (Exception e) {
	// throw new JspException(e);
	// }

	List<OssDictData> list = dictService.getOssDictDataList(gameId, type);
	StringBuffer sb = new StringBuffer();
	String stringLabel;
	String stringValue;
	for (OssDictData dictData : list) {
	    stringLabel = dictData.getDictName();
	    stringValue = dictData.getDictValue();
	    if (stringValue == null)
		stringValue = "";
	    boolean matched = false;

	    if (value != null) {
		for (String tempValue : value) {
		    if (tempValue.equals(stringValue)) {
			matched = true;
			break;
		    }
		}
	    }

	    addOption(sb, stringLabel, stringValue, matched);
	}
	JspWriter out = pageContext.getOut();
	try {
	    out.write(sb.toString());
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return SKIP_BODY;
    }

    public String getCssClass() {
	return cssClass;
    }

    public String getExpression() {
	return expression;
    }

    public int getGameId() {
	return gameId;
    }

    public String getName() {
	return name;
    }

    public String getOnchange() {
	return onchange;
    }

    public String getStyle() {
	return style;
    }

    public String[] getValue() {
	return value;
    }

    public boolean isDisabled() {
	return disabled;
    }

    public void setCssClass(String cssClass) {
	this.cssClass = cssClass;
    }

    public void setDisabled(boolean disabled) {
	this.disabled = disabled;
    }

    public void setExpression(String expression) {
	this.expression = expression;
    }

    public void setGameId(int gameId) {
	this.gameId = gameId;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOnchange(String onchange) {
	this.onchange = onchange;
    }

    public void setStyle(String style) {
	this.style = style;
    }

    public void setType(int type) {
	this.type = type;
    }

    public void setValue(String[] value) {
	this.value = value;
    }

}
