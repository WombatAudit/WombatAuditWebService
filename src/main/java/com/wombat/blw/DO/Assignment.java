package com.wombat.blw.DO;

import lombok.Data;

import java.util.Date;

@Data
public class Assignment {
    private int userId;
    private int itemId;
    private int status;
    private Date startDate;
}
