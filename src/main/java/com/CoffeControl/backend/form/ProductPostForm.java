package com.CoffeControl.backend.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class ProductPostForm {
    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty @Length(min = 3)
    private String description;
    @NotNull @NotEmpty
    private Integer minUserAmount;
    private Boolean enabled=false;
    @NotNull @NotEmpty
    private Integer minAmount=1;
    private Integer currentAmount=0;
}
