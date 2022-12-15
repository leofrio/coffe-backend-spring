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
    @JoinColumn(name = "contribution_id")
    @JsonBackReference
    private Contribution contribution;
    @Getter @Setter
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
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
