package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.ProductDto;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.repository.ProductRepository;
import com.CoffeControl.backend.repository.StorageRepositoy;
import com.CoffeControl.backend.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageRepositoy storageRepositoy;
    @Autowired
    private StorageService storageService;
    @Override
    public Page<ProductDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Product> products = productRepository.findAll(pagination);
        return ProductDto.convert(products);
    }

    @Override
    public ResponseEntity<ProductDto> register(ProductPostForm form, UriComponentsBuilder uriBuilder) {
        Product product =new Product(form);
        product=productRepository.save(product);
        storageService.insertNewProduct(product,form,UriComponentsBuilder.newInstance());
        URI uri= uriBuilder.path("products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(product));
    }
}
