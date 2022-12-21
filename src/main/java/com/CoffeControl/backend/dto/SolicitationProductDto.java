package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Contribution;
import com.CoffeControl.backend.model.ContributionProduct;
import com.CoffeControl.backend.model.Solicitation;
import com.CoffeControl.backend.model.SolicitationProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

public class SolicitationProductDto {
    @Getter
    @Setter
    private Integer productId;
    @Getter @Setter
    private Integer requiredAmount;
    @Getter @Setter
    private Integer totalGivenAmount;

    public SolicitationProductDto(SolicitationProduct sp) {
        this.productId = sp.getProduct().getId();
        this.requiredAmount = sp.getAmountAsked();
        Solicitation s=sp.getSolicitation();
        if(s.getContributions() == null) {
            this.totalGivenAmount=0;
        } else{
            Integer givenAmount=0;
            for(Contribution c : s.getContributions()) {
                ContributionProduct currentCp=c.getProducts().stream().filter((ContributionProduct cp) -> {
                    return cp.getProduct().getId() == productId;
                }).collect(Collectors.toList()).get(0);
                givenAmount += currentCp.getGivenAmount();
            }
            this.totalGivenAmount=givenAmount;
        }
    }
}
