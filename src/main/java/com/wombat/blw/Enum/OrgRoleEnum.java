package com.wombat.blw.Enum;

import lombok.Getter;

@Getter
public enum OrgRoleEnum implements CodeEnum {

    MANAGER(0, "管理员"),
    MEMBER(1, "普通成员");

    private Integer code;

    private String message;

    OrgRoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
