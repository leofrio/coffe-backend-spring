package com.CoffeControl.backend.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ContributionProductForm {
    @Getter @Setter @NotEmpty @NotNull
    private Integer productId;
    @Getter @Setter @NotNull @NotEmpty
    private Integer givenAmount;
}
