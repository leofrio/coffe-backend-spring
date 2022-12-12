package com.CoffeControl.backend.model;

import com.CoffeControl.backend.model.compositeKey.SolicitationProductId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "solicitation_product")
@NoArgsConstructor
public class SolicitationProduct {
    @Getter @Setter
    @EmbeddedId
    private SolicitationProductId id=new SolicitationProductId();
    @Getter @Setter
    @ManyToOne
    @MapsId("solicitationId")
    private Solicitation solicitation;
    @Getter @Setter
    @ManyToOne
    @MapsId("productId")
    private Product product;
    @Getter @Setter
    @Column(name = "asked_amount")
    private Integer amountAsked;

}
