package com.wombat.blw.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetailedAssignmentDTO {

    private String type;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal amount;
    private ReceiptDTO receiptDTO;
}
