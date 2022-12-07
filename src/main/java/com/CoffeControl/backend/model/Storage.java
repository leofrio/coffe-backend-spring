package com.CoffeControl.backend.model;

import com.CoffeControl.backend.form.ProductPostForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    private Integer currentAmount=0;
    @Getter @Setter
    @Column(name = "min_amount")
    private Integer minAmount=0;

    public Storage(Product p, ProductPostForm form) {
        this.product = p;
        this.minAmount=form.getMinAmount();
        this.currentAmount = form.getCurrentAmount();
    }
}
