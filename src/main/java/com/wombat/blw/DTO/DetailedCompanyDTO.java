package com.wombat.blw.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetailedCompanyDTO {

    private Integer companyId;
    private String name;
    private String description;
    private String taxId;
    private String accountBank;
    private BigDecimal account;
}
