package com.CoffeControl.backend.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ContributionPostForm {
    @Getter @Setter @NotEmpty @NotNull
    private Integer solicitationId;
    @Getter @Setter @NotEmpty @NotNull
    private List<ContributionProductForm> products;
}
