package com.CoffeControl.backend.form;

import com.CoffeControl.backend.model.SolicitationProduct;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter @Setter
public class SolicitationProductForm {
    @NotEmpty @NotNull
    private Integer productId;
    @NotNull @NotEmpty
    private Integer requiredAmount;

    public SolicitationProductForm(Integer productId, Integer requiredAmount) {
        this.productId = productId;
        this.requiredAmount = requiredAmount;
    }
    public SolicitationProductForm(SolicitationProduct sp) {
        this.productId = sp.getId().getProductId();
        this.requiredAmount = sp.getAmountAsked();
    }
}
