package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.StorageDetailedDto;
import com.CoffeControl.backend.dto.StorageDto;
import com.CoffeControl.backend.dto.StorageLessDetailedDto;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.form.ProductUpdateForm;
import com.CoffeControl.backend.form.StorageUpdateForm;
import com.CoffeControl.backend.form.StorageUpdateQuantityForm;
import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.model.Storage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface StorageService {
    public Page<StorageDto> list(Integer page, Integer limit);
    public Storage insertNewProduct(Product p, ProductPostForm form);

    StorageDto updateQuantity(Integer id, StorageUpdateQuantityForm form) throws  Exception;


    StorageDetailedDto getSpecificStorage(Integer id) throws Exception;

    StorageDetailedDto getByProductId(Integer product_id) throws Exception;

    StorageDetailedDto updateProduct(Integer id, ProductUpdateForm form) throws Exception;

    StorageDto deleteStorage(Integer id) throws Exception;

    StorageDetailedDto updateStorage(Integer id, StorageUpdateForm form) throws Exception;

    Page<StorageLessDetailedDto> startsWithProductName(String name, Integer page, Integer limit) throws Exception;
}
