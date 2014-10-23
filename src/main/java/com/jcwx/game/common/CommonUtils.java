package com.jcwx.game.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;

/** 公共工具类 */
public class CommonUtils {

    /** 实体间复制属性,若为空就不传值 */
    public static void copyPropertiesExtNull(Object dest, Object orig)
	    throws IllegalAccessException, InvocationTargetException {
	if (dest == null)
	    throw new IllegalArgumentException("No destination bean specified");
	if (orig == null)
	    throw new IllegalArgumentException("No origin bean specified");
	if (orig instanceof DynaBean) {
	    DynaProperty origDescriptors[] = ((DynaBean) orig).getDynaClass()
		    .getDynaProperties();
	    for (int i = 0; i < origDescriptors.length; i++) {
		String name = origDescriptors[i].getName();
		if (PropertyUtils.isWriteable(dest, name)) {
		    Object value = ((DynaBean) orig).get(name);
		    if (value != null)
			BeanUtils.copyProperty(dest, name, value);
		}
	    }

	} else if (orig instanceof Map) {
	    for (Iterator names = ((Map) orig).keySet().iterator(); names
		    .hasNext();) {
		String name = (String) names.next();
		if (PropertyUtils.isWriteable(dest, name)) {
		    Object value = ((Map) orig).get(name);
		    if (value != null)
			BeanUtils.copyProperty(dest, name, value);
		}
	    }

	} else {
	    PropertyDescriptor origDescriptors[] = PropertyUtils
		    .getPropertyDescriptors(orig);
	    for (int i = 0; i < origDescriptors.length; i++) {
		String name = origDescriptors[i].getName();
		if (!"class".equals(name)
			&& PropertyUtils.isReadable(orig, name)
			&& PropertyUtils.isWriteable(dest, name))
		    try {
			Object value = PropertyUtils.getSimpleProperty(orig,
				name);
			if (value != null)
			    BeanUtils.copyProperty(dest, name, value);
		    } catch (NoSuchMethodException e) {
		    }
	    }

	}
    }

    /** 字符串转数值 */
    public static Integer getInteger(Object object) {
	Integer i = null;
	try {
	    i = Integer.valueOf(object.toString());
	} catch (Exception e) {

	}
	return i;
    }

    /** 字符串转数值 */
    public static Integer getInteger(String str) {
	Integer i = null;
	try {
	    i = Integer.valueOf(str);
	} catch (Exception e) {

	}
	return i;
    }
}
