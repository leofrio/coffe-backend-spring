package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.model.Storage;
import com.CoffeControl.backend.repository.StorageRepositoy;
import com.CoffeControl.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepositoy storageRepositoy;

    @Override
    public Page<Storage> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Storage> storage = storageRepositoy.findAll(pagination);
        return storage;
    }

    @Override
    public ResponseEntity<Storage> insertNewProduct(Product p, UriComponentsBuilder uriBuilder) {
        Storage storage =new Storage(p);
        storageRepositoy.save(storage);
        URI uri= uriBuilder.path("storage/{id}").buildAndExpand(storage.getId()).toUri();
        return ResponseEntity.created(uri).body(new Storage(p));
    }
}
