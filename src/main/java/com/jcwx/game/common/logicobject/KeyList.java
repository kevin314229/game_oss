package com.jcwx.game.common.logicobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@SuppressWarnings("serial")
public class KeyList implements Serializable {
    List<Key> keys;
    String value;

    public void addKeys(Key key) {
	if (keys == null) {
	    keys = new ArrayList<Key>();
	}
	keys.add(key);
    }

    public List<Key> getKeys() {
	return this.keys;
    }

    public String getValue() {
	return this.value;
    }

    public void setKeys(List<Key> keys) {
	this.keys = keys;
    }

    public void setValue(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

}
