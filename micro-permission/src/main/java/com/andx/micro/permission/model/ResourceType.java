package com.andx.micro.permission.model;

/**
 * Created by andongxu on 16-11-14.
 */
public enum  ResourceType {

    MENU(10, "菜单"),
    DATA(11, "数据"),
    FILE(12, "文件"),
    CHANNEL(13, "渠道"),
    SERVICE(14, "服务");

    private int value;

    private String description;

    private ResourceType (int value, String description) {
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
