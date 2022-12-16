package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.ProductDto;
import com.CoffeControl.backend.dto.ProductFilterDto;
import com.CoffeControl.backend.form.ProductFilterForm;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.form.ProductUpdateForm;
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
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageRepositoy storageRepositoy;
    @Autowired
    private StorageService storageService;

    @Override
    public ResponseEntity<ProductDto> update(Integer id, ProductUpdateForm form) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("product not found"));
        product.setName(form.getName() != null ? form.getName() :  product.getName());
        product.setDescription(form.getDescription() != null ? form.getDescription() :  product.getDescription());
        product.setEnabled(form.getEnabled() != null ? form.getEnabled() :  product.getEnabled());
        product.setMinUserAmount(form.getMinUserAmount() != null ? form.getMinUserAmount() :  product.getMinUserAmount());
        productRepository.save(product);
        return ResponseEntity.ok(new ProductDto(product));
    }

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

    @Override
    public ProductDto enable(Integer id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("product not found"));
        product.setEnabled(true);
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Override
    public ProductDto disable(Integer id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("product not found"));
        product.setEnabled(false);
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Override
    public List<ProductFilterDto> filter(ProductFilterForm form) {
        return productRepository.filter(form.getName(),form.getDescription(),form.getMinUserAmount(),form.getCurrentAmount(),form.getMinUserAmount(),form.getEnabled());
    }
}
