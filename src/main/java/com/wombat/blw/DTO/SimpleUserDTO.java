package com.wombat.blw.DTO;

import lombok.Data;

@Data
public class SimpleUserDTO {

    private String username;
    private String companyName;

    public SimpleUserDTO(String username, String companyName) {
        this.username = username;
        this.companyName = companyName;
    }

    public SimpleUserDTO() {
    }
}
