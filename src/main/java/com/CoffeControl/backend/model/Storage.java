package com.CoffeControl.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "product_storage")
@NoArgsConstructor
public class Storage {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter @Setter
    @OneToOne
    private Product product;
    @Getter @Setter
    @Column(name = "current_amount")
    private Integer currentAmount;
    @Getter @Setter
    @Column(name = "min_amount")
    private Integer minAmount;

    public Storage(Product p) {
        this.product = product;
        this.minAmount=0;
        this.currentAmount = 0;
    }
}
