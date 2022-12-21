package com.CoffeControl.backend.form;

import lombok.Getter;
import lombok.Setter;

public class UserFilterForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String registration;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String  profileType;
    @Getter @Setter
    private Integer  amountOfSolicitations;
    @Getter @Setter
    private Integer amountOfContributions;
}
