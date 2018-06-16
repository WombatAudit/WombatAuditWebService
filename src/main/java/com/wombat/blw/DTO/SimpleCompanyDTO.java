package com.wombat.blw.DTO;

import lombok.Data;

@Data
public class SimpleCompanyDTO {

    private Integer companyId;
    private String name;

    public SimpleCompanyDTO(Integer companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public SimpleCompanyDTO() {
    }
}
