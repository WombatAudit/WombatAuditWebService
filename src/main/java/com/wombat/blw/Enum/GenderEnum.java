package com.wombat.blw.Enum;

public enum GenderEnum {

    MALE(0, "男"),
    FEMALE(1, "女");

    private int code;

    private String message;

    GenderEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
