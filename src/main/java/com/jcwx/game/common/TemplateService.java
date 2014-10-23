package com.jcwx.game.common;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 模板服务(基于FreeMarker)
 * 
 * @author derek
 * @version 1.0
 */
public class TemplateService {

    private static Configuration configuration = null;

    private static Object lock = new Object();

    /**
     * 套用FreeMarker模板格式化数据
     * 
     * @param ftlTemplate
     * @param contents
     * @return
     * @throws Exception
     */
    public static String format(String ftlTemplate, Object contents)
	    throws Exception {

	if (configuration == null) {
	    synchronized (lock) {
		if (configuration == null) {
		    configuration = new Configuration();
		    configuration.setDefaultEncoding("UTF-8");
		    File file = new File(new URI(Thread.currentThread()
			    .getContextClassLoader().getResource("")
			    + "templates"));
		    configuration.setDirectoryForTemplateLoading(file);
		    configuration.setObjectWrapper(new DefaultObjectWrapper());
		}
	    }
	}

	Template temp = configuration.getTemplate(ftlTemplate);

	Writer out = new StringWriter();

	temp.process(contents, out);

	out.flush();

	out.close();

	return out.toString();
    }

}
