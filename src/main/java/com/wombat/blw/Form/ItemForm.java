package com.wombat.blw.Form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ItemForm {

    @NotBlank
    private String type;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal amount;
}
