package com.jcwx.game.common;

public class ClassInfo implements java.io.Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = -4249413665207843255L;

    private Object args;

    private String beanName;

    private String methodName;

    public ClassInfo() {

    }

    public ClassInfo(String beanName, String methodName, Object args) {
	this.beanName = beanName;
	this.methodName = methodName;
	this.args = args;
    }

    public Object getArgs() {
	return args;
    }

    public String getBeanName() {
	return beanName;
    }

    public String getMethodName() {
	return methodName;
    }

    public void setArgs(Object args) {
	this.args = args;
    }

    public void setBeanName(String beanName) {
	this.beanName = beanName;
    }

    public void setMethodName(String methodName) {
	this.methodName = methodName;
    }
}
