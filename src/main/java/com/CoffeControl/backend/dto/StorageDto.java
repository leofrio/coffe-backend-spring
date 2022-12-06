package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Storage;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

public class StorageDto {
    @Getter
    @Setter
    private Integer id;
    @Getter @Setter
    @OneToOne
    private Integer productId;
    @Getter @Setter
    @Column(name = "current_amount")
    private Integer currentAmount;
    @Getter @Setter
    @Column(name = "min_amount")
    private Integer minAmount;

    public StorageDto(Storage storage) {
        this.id = storage.getId();
        this.productId = storage.getProduct().getId();
        this.currentAmount = storage.getCurrentAmount();
        this.minAmount = storage.getMinAmount();
    }
    public static Page<StorageDto> convert(Page<Storage> storages) {
        return storages.map(StorageDto::new);
    }

}
