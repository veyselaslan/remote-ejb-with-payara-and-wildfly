package com.wildfly.model;

public enum Category {
	SCIENCE, BIOLOGY, PROGRAMMING, COMICS;
	
    public String value() {
        return name();
    }

    public static Category fromValue(String v) {
        return valueOf(v);
    }
}
