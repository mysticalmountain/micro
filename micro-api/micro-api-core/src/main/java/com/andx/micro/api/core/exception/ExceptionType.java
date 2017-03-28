package com.andx.micro.api.core.exception;

/**
 * Created by andongxu on 16-8-17.
 */
public enum ExceptionType {

    UNKNOWN(0, "未知异常"),
    NETWORK(1, "网络异常"),
    TIMEOUT(2, "超时异常"),
    BUSINESS(3, "业务异常"),
    PERMISSION(4, "权限异常");

    private int value;

    private String description;

    private ExceptionType(int value, String description) {
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
