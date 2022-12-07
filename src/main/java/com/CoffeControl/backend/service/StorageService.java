package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.StorageDetailedDto;
import com.CoffeControl.backend.dto.StorageDto;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.form.StorageUpdateQuantityForm;
import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.model.Storage;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface StorageService {
    public Page<StorageDto> list(Integer page, Integer limit);
    public ResponseEntity<Storage> insertNewProduct(Product p, ProductPostForm form, UriComponentsBuilder uriBuilder);

    StorageDto updateQuantity(Integer id, StorageUpdateQuantityForm form) throws  Exception;


    StorageDetailedDto getSpecificStorage(Integer id) throws Exception;

    StorageDetailedDto getByProductId(Integer product_id) throws Exception;
}
