package com.CoffeControl.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserFilterDto {
    @Getter
    private Integer id;
    @Getter
    private String name;
    @Getter
    private String registration;
    @Getter
    private String password;
    @Getter
    private String  profileType;
    @Getter
    private Integer  amountOfSolicitations;
    @Getter
    private Integer amountOfContributions;


}
