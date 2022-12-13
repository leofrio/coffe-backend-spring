package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDetailedDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Integer minUserAmount;
    @Getter @Setter
    private Boolean enabled;
    @Getter @Setter
    private List<SolicitationDto> solicitations;
    @Getter @Setter
    private List<ContributionDto> contributions;


    public ProductDetailedDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.minUserAmount = product.getMinUserAmount();
        this.enabled = product.getEnabled();
        this.solicitations=product.getSolicitations().stream().map(SolicitationDto::new).collect(Collectors.toList());
        this.contributions=product.getContributions().stream().map(ContributionDto::new).collect(Collectors.toList());
    }
}
