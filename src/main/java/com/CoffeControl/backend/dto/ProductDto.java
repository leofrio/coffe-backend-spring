package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Product;
import lombok.Getter;
import org.springframework.data.domain.Page;

public class ProductDto {
    @Getter
    private Integer id;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Integer minUserAmount;
    @Getter
    private boolean enabled;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.minUserAmount = product.getMinUserAmount();
        this.enabled = product.getEnabled();
    }
    public static Page<ProductDto> convert(Page<Product> products) {
        return products.map(ProductDto::new);
    }

}
