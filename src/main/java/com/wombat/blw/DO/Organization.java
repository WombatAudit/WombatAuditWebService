package com.wombat.blw.DO;

import lombok.Data;

@Data
public class Organization {

    private Integer organizationId;
    private Integer companyId;
    private String name;
    private String description;
    private double budget;

}
