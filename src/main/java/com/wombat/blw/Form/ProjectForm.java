package com.wombat.blw.Form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProjectForm {

    @NotBlank
    private String name;

    @NotNull
    private Integer orgId;

    @NotBlank
    private String description;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;
}
