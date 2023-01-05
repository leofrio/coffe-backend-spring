package com.CoffeControl.backend.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class SolicitationPostForm {
    @Getter @Setter @NotNull @NotEmpty
    private Integer userId;
    @Getter @Setter @NotEmpty @NotNull
    private String name;
    @Getter @Setter @NotNull @NotEmpty
    private SolicitationProductForm[] products;
}
