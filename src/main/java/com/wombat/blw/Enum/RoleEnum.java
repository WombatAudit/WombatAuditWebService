package com.wombat.blw.Enum;

public enum RoleEnum {

    ADMIN(0, "管理员"),
    GENERAL(1, "普通成员"),
    RESIGNED(2, "离任");

    private int code;

    private String message;

    RoleEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
