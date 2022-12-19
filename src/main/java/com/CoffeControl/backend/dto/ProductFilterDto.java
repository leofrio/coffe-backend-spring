package com.CoffeControl.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer currentAmount;
    @Getter
    private Integer minAmount;
    @Getter
    private Boolean enabled;

}
