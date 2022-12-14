package com.CoffeControl.backend.model;

import com.CoffeControl.backend.model.compositeKey.ContributionProductId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "contribution_product")
@NoArgsConstructor
public class ContributionProduct {
    @Getter @Setter
    @EmbeddedId
    private ContributionProductId id=new ContributionProductId();
    @Getter @Setter
    @ManyToOne
    @MapsId("contributionId")
    @JsonBackReference
    private Contribution contribution;
    @Getter @Setter
    @ManyToOne
    @MapsId("productId")
    @JsonBackReference
    private Product product;
    @Getter @Setter
    @Column(name="amount_given")
    private Integer GivenAmount;

    public ContributionProduct(ContributionProductId id, Integer givenAmount) {
        this.id = id;
        GivenAmount = givenAmount;
    }
}
