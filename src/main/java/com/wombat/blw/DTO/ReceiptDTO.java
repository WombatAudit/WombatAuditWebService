package com.wombat.blw.DTO;

import lombok.Data;

import java.io.File;

@Data
public class ReceiptDTO {

    private int rcptId;
    private File receipt;
    private File invoice;
    private File transaction;
    private File attachment;
}
