package com.CoffeControl.backend.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

public class ProductPostForm {
    @Getter @Setter
    @NotNull @NotEmpty
    private String name;
    @Getter @Setter
    @NotNull @NotEmpty @Length(min = 3)
    private String description;
    @Getter @Setter
    @NotNull @NotEmpty
    private Integer minUserAmount;
    @Getter @Setter
    private Boolean enabled=false;
    @Getter @Setter
    private Integer minAmount;
    @Getter @Setter
    private Integer currentAmount=0;
}
