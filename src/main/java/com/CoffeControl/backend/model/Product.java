package com.CoffeControl.backend.model;

import com.CoffeControl.backend.form.ProductPostForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "products")
@NoArgsConstructor
public class Product {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter @Setter
    @Column(name = "product_name")
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    @Column(name = "min_user_amount")
    private Integer minUserAmount;
    @Getter @Setter
    private Boolean enabled;
    @ManyToMany
    @JoinTable(name="contribution_product", joinColumns=
            {@JoinColumn(name="contribution_id")}, inverseJoinColumns=
            {@JoinColumn(name="product_id")})
    private List<Contribution> contributions = new ArrayList<>();
    @Getter @Setter
    @ManyToMany
    @JoinTable(name="solicitation_product", joinColumns=
            {@JoinColumn(name="solicitation_id")}, inverseJoinColumns=
            {@JoinColumn(name="product_id")})
    private List<Solicitation> solicitations = new ArrayList<>();

    public Product(ProductPostForm form) {
        this.name = form.getName();
        this.description = form.getDescription();
        this.minUserAmount = form.getMinUserAmount();
        this.enabled = form.isEnabled();
    }
}
