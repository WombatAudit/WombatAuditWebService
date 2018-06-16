package com.wombat.blw.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimpleItemDTO {

    private Integer itemId;
    private String type;
    private String name;
    private Integer quantity;
    private BigDecimal amount;

    public SimpleItemDTO(Integer itemId, String type, String name, Integer quantity, BigDecimal amount) {
        this.itemId = itemId;
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
    }

    public SimpleItemDTO() {
    }
}
