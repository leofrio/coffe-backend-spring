package com.CoffeControl.backend.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

public class ProductUpdateForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    @Length(min = 3)
    private String description;
    @Getter @Setter
    private Integer minUserAmount;
    @Getter @Setter
    private Boolean enabled=false;
}
