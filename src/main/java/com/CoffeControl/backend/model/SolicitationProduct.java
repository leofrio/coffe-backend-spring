package com.CoffeControl.backend.model;

import com.CoffeControl.backend.model.compositeKey.SolicitationProductId;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Solicitation solicitation;
    @Getter @Setter
    @ManyToOne
    @MapsId("productId")
    @JsonBackReference
    private Product product;
    @Getter @Setter
    @Column(name = "asked_amount")
    private Integer amountAsked;

    public SolicitationProduct(SolicitationProductId id,Integer amountAsked) {
        this.id = id;
        this.amountAsked = amountAsked;
    }
}
