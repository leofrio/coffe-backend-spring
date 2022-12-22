package com.CoffeControl.backend.model;

import com.CoffeControl.backend.dto.ProductFilterDto;
import com.CoffeControl.backend.form.ProductPostForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedNativeQuery;

import java.util.List;

@NamedNativeQuery(name = "Product.filter", query = "select p.id,p.product_name,p.description,p.min_user_amount,s.current_amount,s.min_amount,p.enabled  from products p " +
        " inner join product_storage s on s.product_id =p.id " +
        " where (lower(p.product_name)  like CONCAT( lower( :name ) , '%')or :name is null ) and " +
        " (lower(p.description) like CONCAT('%', lower( :description ) , '%') or :description  is null) and " +
        " (p.min_user_amount = :minUserAmount  or :minUserAmount is null) and "  +
        " (p.enabled  = :enabled or :enabled is null) and " +
        " (s.current_amount = :currentAmount or :currentAmount is null) and " +
        " (s.min_amount = :minAmount or :minAmount is null);",resultSetMapping = "ProductFiltering")
@SqlResultSetMapping(name = "ProductFiltering",classes = @ConstructorResult(targetClass = ProductFilterDto.class,columns = {
        @ColumnResult(name = "id",type = Integer.class),
        @ColumnResult(name = "product_name",type = String.class),
        @ColumnResult(name = "description",type = String.class),
        @ColumnResult(name = "min_user_amount",type = Integer.class),
        @ColumnResult(name = "current_amount",type = Integer.class),
        @ColumnResult(name = "min_amount",type = Integer.class),
        @ColumnResult(name = "enabled",type = Boolean.class)
}))

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    /*@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="contribution_product", joinColumns=
            {@JoinColumn(name="contribution_id")}, inverseJoinColumns=
            {@JoinColumn(name="product_id")})
    private List<Contribution> contributions;*/
    @Getter @Setter
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ContributionProduct> contributions;
    /*@Getter @Setter
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="solicitation_product", joinColumns=
            {@JoinColumn(name="solicitation_id")}, inverseJoinColumns=
            {@JoinColumn(name="product_id")})
    private List<Solicitation> solicitations;*/
    @Getter @Setter
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<SolicitationProduct> solicitations;

    public Product(ProductPostForm form) {
        this.name = form.getName();
        this.description = form.getDescription();
        this.minUserAmount = form.getMinUserAmount();
        this.enabled = form.getEnabled();
    }
}
