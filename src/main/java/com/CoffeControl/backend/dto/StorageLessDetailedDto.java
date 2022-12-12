package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Storage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

public class StorageLessDetailedDto {
    @Getter
    @Setter
    private Integer id;
    @Getter @Setter
    private Integer currentAmount=0;
    @Getter @Setter
    private Integer minAmount=0;
    @Getter @Setter
    private ProductDto product;

    public StorageLessDetailedDto(Storage s) {
        this.id = s.getId();
        this.currentAmount = s.getCurrentAmount();
        this.minAmount = s.getMinAmount();
        this.product = new ProductDto(s.getProduct());
    }
    public static Page<StorageLessDetailedDto> convert(Page<Storage> storages) {
        return storages.map(StorageLessDetailedDto::new);
    }
}
