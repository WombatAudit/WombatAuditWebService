package com.wombat.blw.Enum;

import lombok.Getter;

@Getter
public enum GenderEnum implements CodeEnum {

    MALE(0, "男"),
    FEMALE(1, "女");

    private Integer code;

    private String message;

    GenderEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
