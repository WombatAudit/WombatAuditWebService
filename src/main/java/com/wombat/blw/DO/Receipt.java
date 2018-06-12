package com.wombat.blw.DO;

import lombok.Data;

import java.io.File;
@Data
public class Receipt {
    private int rcptId;
    private File receipt;
    private File invoice;
    private File transaction;
    private File attachment;
}
