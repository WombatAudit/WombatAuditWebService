package com.wombat.blw.DO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class User {

    private Integer userId;
    private String username;
    private String password;
    private String realName;
    private Integer role;
    private Integer gender;
    private BigDecimal tel;
    private String email;
    private Integer companyId;
    private Date createTime;
}
