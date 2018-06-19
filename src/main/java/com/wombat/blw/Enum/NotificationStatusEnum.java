package com.wombat.blw.Enum;

import lombok.Getter;

@Getter
public enum NotificationStatusEnum implements CodeEnum {

    NOT_READ(0, "未读"),
    READ(1, "已读");

    private Integer code;
    private String message;

    NotificationStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
