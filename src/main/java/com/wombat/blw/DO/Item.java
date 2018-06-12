package com.wombat.blw.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private int itemId;
    private String type;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal amount;
    private int rcptId;
}
