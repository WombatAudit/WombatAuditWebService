package com.wombat.blw.Enum;

import lombok.Getter;

@Getter
public enum CompanyRoleEnum implements CodeEnum {

    ADMIN(0, "管理员"),
    GENERAL(1, "普通成员"),
    RESIGNED(2, "离任");

    private Integer code;

    private String message;

    CompanyRoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
