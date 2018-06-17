package com.wombat.blw.DTO;

import lombok.Data;

@Data
public class SimpleUserDTO {

    private String username;
    private String companyName;
    private Integer companyId;

    public SimpleUserDTO(String username, String companyName, Integer companyId) {
        this.username = username;
        this.companyName = companyName;
        this.companyId = companyId;
    }

    public SimpleUserDTO() {
    }
}
