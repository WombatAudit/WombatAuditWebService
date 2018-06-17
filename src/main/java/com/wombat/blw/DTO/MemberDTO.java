package com.wombat.blw.DTO;

import lombok.Data;

@Data
public class MemberDTO {

    private Integer userId;
    private String realName;

    public MemberDTO(Integer userId, String realName) {
        this.userId = userId;
        this.realName = realName;
    }

    public MemberDTO() {
    }
}
