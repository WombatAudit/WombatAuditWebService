package com.wombat.blw.DO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Organization {

    private Integer organizationId;
    private Integer companyId;
    private String name;
    private String description;
    private BigDecimal budget;
    private Date createTime;
}
