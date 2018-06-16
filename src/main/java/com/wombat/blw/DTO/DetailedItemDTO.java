package com.wombat.blw.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetailedItemDTO {

    private Integer itemId;
    private String type;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal amount;
    private String receipt;
    private String invoice;
    private String transaction;
    private String attachment;
    private String prjName;
}
