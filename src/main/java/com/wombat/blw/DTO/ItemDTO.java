package com.wombat.blw.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDTO {

    private Integer itemId;
    private String type;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal amount;
    private String prjName;
    private Integer prjId;

    public ItemDTO() {
    }

    public ItemDTO(Integer itemId, String type, String name, String description, Integer quantity, BigDecimal amount, String prjName, Integer prjId) {
        this.itemId = itemId;
        this.type = type;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.amount = amount;
        this.prjName = prjName;
        this.prjId = prjId;
    }
}
