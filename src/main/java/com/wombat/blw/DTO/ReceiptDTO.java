package com.wombat.blw.DTO;

import lombok.Data;

@Data
public class ReceiptDTO {

    private int rcptId;
    private String receipt;
    private String invoice;
    private String transaction;
    private String attachment;
}
