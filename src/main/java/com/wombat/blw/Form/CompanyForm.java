package com.wombat.blw.Form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompanyForm {

    private String name;
    private String description;
    private String taxId;
    private String accountBank;
    private BigDecimal account;
}
