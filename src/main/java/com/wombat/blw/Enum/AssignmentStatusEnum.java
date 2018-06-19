package com.wombat.blw.Enum;

import lombok.Getter;

@Getter
public enum AssignmentStatusEnum implements CodeEnum {

    IN_PROGRESS(0, "进行中"),
    SUBMITTED(1, "已提交"),
    CHECKING(2, "正在审核"),
    COMPLETED(3, "已完成"),
    DEFERRED(4, "已延期");

    private Integer code;
    private String message;

    AssignmentStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
