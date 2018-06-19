package com.wombat.blw.Form;

import lombok.Data;

@Data
public class NotificationForm {

    private Integer senderId;
    private String receiveListId;
    private String content;
}
