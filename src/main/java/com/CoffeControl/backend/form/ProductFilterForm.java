package com.CoffeControl.backend.form;

import lombok.Getter;
import lombok.Setter;

public class ProductFilterForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Integer minUserAmount;
    @Getter @Setter
    private Boolean enabled;
    @Getter @Setter
    private Integer minAmount;
    @Getter @Setter
    private Integer currentAmount;
}
