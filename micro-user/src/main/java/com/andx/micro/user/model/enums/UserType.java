package com.andx.micro.user.model.enums;

/**
 * Created by andongxu on 16-6-14.
 */
public enum  UserType {

    PERSONAL("个人", 1), MERCHANT("商户", 2);

    private String name;

    private int index;

    private UserType(String name, int index) {
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
