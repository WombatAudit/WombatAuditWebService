package com.wombat.blw.DO;

import lombok.Data;

@Data
public class Receipt {

    private int rcptId;
    private String receipt;
    private String invoice;
    private String transaction;
    private String attachment;
}
