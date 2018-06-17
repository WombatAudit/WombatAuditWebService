package com.wombat.blw.DTO;

import lombok.Data;

@Data
public class SimpleOrganizationDTO {

    private Integer orgId;
    private String name;

    public SimpleOrganizationDTO(Integer orgId, String name) {
        this.orgId = orgId;
        this.name = name;
    }

    public SimpleOrganizationDTO() {
    }
}
