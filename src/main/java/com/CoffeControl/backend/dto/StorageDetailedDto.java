package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Storage;
import lombok.Getter;
import lombok.Setter;

public class StorageDetailedDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private Integer currentAmount=0;
    @Getter @Setter
    private Integer minAmount=0;
    @Getter @Setter
    private ProductDetailedDto product;

    public StorageDetailedDto(Storage s) {
        this.id = s.getId();
        this.currentAmount = s.getCurrentAmount();
        this.minAmount = s.getMinAmount();
        this.product = new ProductDetailedDto(s.getProduct());
    }
}
