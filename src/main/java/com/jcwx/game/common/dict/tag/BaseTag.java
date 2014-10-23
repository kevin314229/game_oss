package com.jcwx.game.common.dict.tag;

import java.io.Serializable;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author tanfl 改类用来封装专业数据中具体的数据项
 */
public class BaseTag extends TagSupport implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String itemValue;

    public String generateContent() {
	// if(data != null)
	// {
	// try
	// {
	// if(getItemValue() != null)
	// {
	// return data.getItemName(getItemValue());
	// }
	// else if(t_value != null)
	// {
	// return data.getItemName(t_value);
	// }
	// }
	// catch (ProfessionDataManagerException e)
	// {
	// e.printStackTrace();
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	//
	// return "";
	// }
	// else
	// {
	return "字典[" + "]不存在";
	// }
    }

    /**
     * @return
     */
    public String getItemValue() {
	return itemValue;
    }

    /**
     * @param string
     */
    public void setItemValue(String string) {
	itemValue = string;
    }

}
