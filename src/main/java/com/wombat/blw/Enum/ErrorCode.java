package com.wombat.blw.Enum;

import lombok.Getter;

@Getter
public enum ErrorCode {

    LOGOUT_SUCCESS(0, "注销成功"),
    USERNAME_NOT_EXIST(1, "用户名不存在"),
    INCORRECT_PASSWORD(2, "密码错误"),
    CREATE_SUCCESS(3, "创建成功"),
    DELETE_SUCCESS(4, "删除成功"),
    SUBMIT_SUCCESS(5, "提交成功"),
    REVIEW_SUCCESS(6, "审核成功"),;

    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
