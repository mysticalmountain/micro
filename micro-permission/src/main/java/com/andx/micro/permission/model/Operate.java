package com.andx.micro.permission.model;

/**
 * Created by andongxu on 16-8-25.
 */
public enum Operate {

    READ(4, "读"),
    WRITE(2, "写"),
    READ_AND_WRITE(6, "读和写");

    private int value;

    private String description;

    private Operate(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
