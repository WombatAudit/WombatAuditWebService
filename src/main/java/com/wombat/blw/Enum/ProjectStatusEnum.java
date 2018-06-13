package com.wombat.blw.Enum;

import lombok.Getter;

@Getter
public enum ProjectStatusEnum implements CodeEnum {

    NOT_STARTED(0, "未开始"),
    REQUEST_CREATION(1, "请求创建"),
    IN_PROGRESS(2, "进行中"),
    REQUEST_REIMBURSEMENT(3, "请求报销"),
    COMPLETED(4, "已完成"),
    DEFERRED(5, "延期");

    private Integer code;

    private String message;

    ProjectStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
