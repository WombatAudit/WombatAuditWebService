package com.wombat.blw.Form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrganizationForm {

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotBlank(message = "描述不能为空")
    private String description;

    @NotNull(message = "")
    private Integer companyId;
}
