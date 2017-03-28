package com.andx.micro.user.model.enums;

/**
 * Created by andongxu on 16-6-14.
 */
public enum  AuthType {

    PHONENO("手机号", 1), USERNAME("用户名", 2);

    private String name;

    private int index;

    private AuthType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (UserType userType : UserType.values()) {
            if (userType.getIndex() == index) {
                return userType.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
