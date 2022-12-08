package com.CoffeControl.backend.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

public class UserPostForm {
    @Getter @Setter
    @NotNull @NotEmpty
    private String name;
    @Getter @Setter
    @NotNull @NotEmpty
    private String registration;
    @Getter @Setter
    @NotNull @NotEmpty @Length(min = 6)
    private String password;
    @Getter @Setter
    private String profile_type="regular";

}
