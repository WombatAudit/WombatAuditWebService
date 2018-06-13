package com.wombat.blw.Enum;

import lombok.Getter;

@Getter
public enum RoleEnum implements CodeEnum {

    ADMIN(0, "管理员"),
    GENERAL(1, "普通成员"),
    RESIGNED(2, "离任");

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
