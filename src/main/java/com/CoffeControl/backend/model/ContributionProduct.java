package com.CoffeControl.backend.model;

import com.CoffeControl.backend.model.compositeKey.ContributionProductId;
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
    private Contribution contribution;
    @Getter @Setter
    @ManyToOne
    @MapsId("productId")
    private Product product;
    @Getter @Setter
    @Column(name="amount_given")
    private Integer GivenAmount;
}
