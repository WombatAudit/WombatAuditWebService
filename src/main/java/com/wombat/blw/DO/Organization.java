package com.wombat.blw.DO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Organization {

    private Integer organizationId;
    private Integer companyId;
    private String name;
    private String description;
    private BigDecimal budget;

}
