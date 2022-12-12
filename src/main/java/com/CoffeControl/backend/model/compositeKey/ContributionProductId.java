package com.CoffeControl.backend.model.compositeKey;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class ContributionProductId implements Serializable {
    @Getter @Setter
    private Integer contributionId;
    @Getter @Setter
    private Integer productId;

    public ContributionProductId(Integer contributionId, Integer productId) {
        super();
        this.contributionId = contributionId;
        this.productId = productId;
    }
}
