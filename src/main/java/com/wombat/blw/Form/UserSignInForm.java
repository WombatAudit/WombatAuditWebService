package com.wombat.blw.Form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserSignInForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
