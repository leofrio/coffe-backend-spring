package com.CoffeControl.backend.service;

import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.model.Storage;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface StorageService {
    Page<Storage> list(Integer page, Integer limit);
    public ResponseEntity<Storage> insertNewProduct(Product p, UriComponentsBuilder uriBuilder);
}
