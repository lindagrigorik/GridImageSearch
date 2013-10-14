package com.codepath.gridimagesearch;

import java.io.Serializable;

public class SettingFilter implements Serializable {

    private static final long serialVersionUID = -4472572628786354642L;
    private String size;
    private String color;
    private String type;
    private String site;

    public SettingFilter(String size, String color, String type, String site) {
	this.size = size;
	this.color = color;
	this.type = type;
	this.site = site;
    }

    public String getSize() {
	return size;
    }

    public String getColor() {
	return color;
    }

    public String getType() {
	return type;
    }

    public String getSite() {
	return site;
    }
}
