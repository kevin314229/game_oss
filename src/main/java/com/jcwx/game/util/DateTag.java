package com.jcwx.game.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

    public class DateTag extends TagSupport {

	    private static final long serialVersionUID = 6464168398214506236L;
	   
	    private String value;
	    private String format;
	   
	    @Override
	    public int doStartTag() throws JspException {
	        String vv = ""+value ;
	        String s="";
	        if("".equals(vv)){
	            
	        }else{
	            long time = Long.valueOf(vv);
		    Calendar c = Calendar. getInstance();
		    c.setTimeInMillis(time);
		    if(format==null||"".equals(format)){
			format="yyyy-MM-dd hh:mm:ss";
		    }
		    SimpleDateFormat dateformat = new SimpleDateFormat(format);
		    s = dateformat.format(c.getTime());
	        }
	      
	        try {
	            pageContext.getOut().write(s);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return super .doStartTag();
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

	    public void setFormat(String format) {
	        this.format = format;
	    }

	}

