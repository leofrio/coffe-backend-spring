package com.CoffeControl.backend.model.compositeKey;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class SolicitationProductId implements Serializable {
    @Getter @Setter
    private Integer solicitationId;
    @Getter @Setter
    private Integer productId;

    public SolicitationProductId(Integer solicitationId, Integer productId) {
        super();
        this.solicitationId = solicitationId;
        this.productId = productId;
    }
}
