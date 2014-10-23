package com.jcwx.game.common.logicobject;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Key implements Serializable {
    boolean required;
    String value;

    public Key() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Key(boolean required, String value) {
	super();
	this.required = required;
	this.value = value;
    }

    public String getValue() {
	return this.value;
    }

    public boolean isRequired() {
	return this.required;
    }

    public void setRequired(boolean required) {
	this.required = required;
    }

    public void setValue(String value) {
	this.value = value;
    }

}
