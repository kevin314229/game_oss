package com.jcwx.game.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BeanUtil {

    public static Object copyPropertie(Object source, Object target) {
	try {
	    BeanInfo targetbean = Introspector.getBeanInfo(target.getClass());
	    PropertyDescriptor[] propertyDescriptors = targetbean
		    .getPropertyDescriptors();
	    for (int i = 0; i < propertyDescriptors.length; i++) {
		PropertyDescriptor pro = propertyDescriptors[i];
		Method wm = pro.getWriteMethod();
		if (wm != null) {
		    BeanInfo sourceBean = Introspector.getBeanInfo(source
			    .getClass());
		    PropertyDescriptor[] sourcepds = sourceBean
			    .getPropertyDescriptors();
		    for (int j = 0; j < sourcepds.length; j++) {
			if (sourcepds[j].getName().equals(pro.getName())) {
			    Method rm = sourcepds[j].getReadMethod();
			    if (!Modifier.isPublic(rm.getDeclaringClass()
				    .getModifiers())) {
				rm.setAccessible(true);
			    }
			    Object value = rm.invoke(source, new Object[0]);
			    if (!Modifier.isPublic(wm.getDeclaringClass()
				    .getModifiers())) {
				wm.setAccessible(true);
			    }
			    wm.invoke(target, new Object[] { value });
			    break;
			}
		    }
		}
	    }
	} catch (IntrospectionException e) {
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (InvocationTargetException e) {
	    e.printStackTrace();
	}

	return target;
    }

    public static boolean isEmpty(final String value) {
	return value == null || value.trim().length() == 0
		|| "null".endsWith(value);
    }

    public static void main(String[] args) {
	Map map = new HashMap<String, String>();
	for (int i = 0; i < 1000; i++) {
	    map.put("str" + i, "strstrstrstrstrstrstrstrstrstrstrstr" + i);
	}
	long a = System.currentTimeMillis();
	List list = mapToList(map);
	long b = System.currentTimeMillis();
	System.out.println("time:" + (b - a) + ", list:" + list.size());
    }

    public static List<?> mapToList(Map<?, ?> map) {
	Iterator it = map.keySet().iterator();

	List list = new ArrayList();
	while (it.hasNext()) {
	    list.add(map.get(it.next()));
	}
	return list;
    }
}
