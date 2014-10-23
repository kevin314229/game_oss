package com.jcwx.game.admin.constant;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public class OssDateFormatUtils extends DateFormatUtils{
    public static final FastDateFormat ISO_NO_T_DATETIME_FORMAT
    = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    
}
