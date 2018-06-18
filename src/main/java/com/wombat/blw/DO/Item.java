package com.wombat.blw.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private Integer itemId;
    private String type;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal amount;
    private Integer rcptId;
}
