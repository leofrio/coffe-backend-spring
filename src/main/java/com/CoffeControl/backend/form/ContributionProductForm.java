package com.CoffeControl.backend.form;

import com.CoffeControl.backend.model.ContributionProduct;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
public class ContributionProductForm {
    @Getter @Setter @NotEmpty @NotNull
    private Integer productId;
    @Getter @Setter @NotNull @NotEmpty
    private Integer givenAmount;

    public ContributionProductForm(Integer productId, Integer givenAmount) {
        this.productId = productId;
        this.givenAmount = givenAmount;
    }
    public ContributionProductForm(ContributionProduct cp) {
        this.productId =cp.getId().getProductId();
        this.givenAmount = cp.getGivenAmount();
    }

}
