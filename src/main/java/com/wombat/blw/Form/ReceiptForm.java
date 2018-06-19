package com.wombat.blw.Form;

import lombok.Data;

import java.io.File;

@Data
public class ReceiptForm {

    private String invoice;
    private String receipt;
    private String transaction;
    private String attachment;
}
