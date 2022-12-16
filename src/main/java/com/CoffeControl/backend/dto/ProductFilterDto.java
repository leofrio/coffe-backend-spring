package com.CoffeControl.backend.dto;

import lombok.Getter;

public class ProductFilterDto {
    @Getter
    private Integer id;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Integer minUserAmount;
    @Getter
    private Boolean enabled;
    @Getter
    private Integer minAmount=1;
    @Getter
    private Integer currentAmount=0;

}
