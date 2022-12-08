package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Profile;
import lombok.Getter;
import lombok.Setter;

public class ProfileDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;

    public ProfileDto(Profile p) {
        this.id = p.getId();
        this.name = p.getName();
        this.description=p.getDescription();
    }
}
