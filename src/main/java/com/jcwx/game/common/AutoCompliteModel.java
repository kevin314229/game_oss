package com.jcwx.game.common;

public class AutoCompliteModel {
    private String code;
    private String name;
    private String help;

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getHelp() {
	return help;
    }

    public void setHelp(String help) {
	this.help = help;
    }

    public static AutoCompliteModel createAutoCompliteModel(String code, String name, String help) {
	AutoCompliteModel model = new AutoCompliteModel();
	model.setCode(code);
	model.setName(name);
	model.setHelp(help);
	return model;
    }

}
