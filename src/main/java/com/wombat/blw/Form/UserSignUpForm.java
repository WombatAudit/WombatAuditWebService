package com.wombat.blw.Form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class UserSignUpForm {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String confirmation;

    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @NotBlank(message = "角色不能为空")
    private Integer role;

    @NotBlank(message = "性别不能为空")
    private Integer gender;

    @NotBlank(message = "手机号码不能为空")
    private BigDecimal tel;

    @Email(message = "电子邮箱不合法")
    private String email;

    @NotBlank(message = "公司不能为空")
    private Integer companyId;
}
