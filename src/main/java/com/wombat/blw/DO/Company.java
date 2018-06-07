package com.wombat.blw.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Company {

    private Integer companyId;
    private String name;
    private String description;
    private String taxId;
    private String accountBank;
    private BigDecimal account;
}
