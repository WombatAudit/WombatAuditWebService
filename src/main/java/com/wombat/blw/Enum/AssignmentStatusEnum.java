package com.wombat.blw.Enum;

import lombok.Getter;

@Getter
public enum AssignmentStatusEnum implements CodeEnum {

    IN_PROGRESS(0, "进行中"),
    COMPLETED(1, "已完成");

    private Integer code;
    private String message;

    AssignmentStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
