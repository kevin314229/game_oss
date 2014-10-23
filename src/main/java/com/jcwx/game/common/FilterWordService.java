package com.jcwx.game.common;

import java.io.File;
import java.util.ResourceBundle;

public class FilterWordService {

    private static final String CONFIG_FILE = "config" + File.separator
	    + "filterword";
    private static ResourceBundle rb = null;

    public static String getProperty(String key) {
	if (rb == null) {
	    rb = ResourceBundle.getBundle(CONFIG_FILE);
	}
	return rb.getString(key);
    }

    private FilterWordService() {

    }

}
