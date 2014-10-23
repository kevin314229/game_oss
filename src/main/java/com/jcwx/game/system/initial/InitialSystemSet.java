package com.jcwx.game.system.initial;

import java.io.FileInputStream;
import java.util.Properties;




import org.apache.commons.beanutils.BeanUtilsBean;

import com.jcwx.game.common.CacheManager;
import com.jcwx.game.system.Initial;

public class InitialSystemSet implements Initial{

    @Override
    public void initial() throws Exception {
	Properties pro = new Properties();
	pro.load(new FileInputStream(InitialSystemSet.class.getResource("/system.properties").getFile()));
	BeanUtilsBean.getInstance().populate(CacheManager.SYSTEM_SET, pro);
    }

}
