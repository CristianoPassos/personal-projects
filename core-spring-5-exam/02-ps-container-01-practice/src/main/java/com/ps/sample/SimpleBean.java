package com.ps.sample;

public class SimpleBean {
    public static final SimpleBean DEFAULT_SIMPLE_BEAN = new SimpleBean("DEFAULT");

    private String name;

    public SimpleBean() {
    }

    public SimpleBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
