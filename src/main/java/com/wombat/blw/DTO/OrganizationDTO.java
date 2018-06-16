package com.wombat.blw.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrganizationDTO {

    private Integer organizationId;
    private String name;
    private String description;
    private BigDecimal budget;
    private Date createTime;

    public OrganizationDTO(Integer organizationId, String name, String description, BigDecimal budget, Date createTime) {
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.createTime = createTime;
    }

    public OrganizationDTO() {
    }
}
