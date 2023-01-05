package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.ProductDto;
import com.CoffeControl.backend.dto.ProductFilterDto;
import com.CoffeControl.backend.exception.GenericException;
import com.CoffeControl.backend.form.ProductFilterForm;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.form.ProductUpdateForm;
import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.repository.ProductRepository;
import com.CoffeControl.backend.repository.StorageRepository;
import com.CoffeControl.backend.service.ProductService;
import com.CoffeControl.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private StorageService storageService;

    @Override
    public ProductDto update(Integer id, ProductUpdateForm form) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new GenericException("PRODUCT NOT FOUND!", "Product with id " + id + " not found during the update process!", HttpStatus.BAD_REQUEST));
        product.setName(form.getName() != null ? form.getName() :  product.getName());
        product.setDescription(form.getDescription() != null ? form.getDescription() :  product.getDescription());
        product.setEnabled(form.getEnabled() != null ? form.getEnabled() :  product.getEnabled());
        product.setMinUserAmount(form.getMinUserAmount() != null ? form.getMinUserAmount() :  product.getMinUserAmount());
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Override
    public Page<ProductDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Product> products = productRepository.findAll(pagination);
        return ProductDto.convert(products);
    }

    @Override
    public ProductDto register(ProductPostForm form) {
        Product product =new Product(form);
        product=productRepository.save(product);
        storageService.insertNewProduct(product,form);
        return new ProductDto(product);
    }

    @Override
    public ProductDto enable(Integer id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new GenericException("PRODUCT NOT FOUND!", "Product with id " + id + " not found during the enable process!", HttpStatus.BAD_REQUEST));
        product.setEnabled(true);
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Override
    public ProductDto disable(Integer id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new GenericException("PRODUCT NOT FOUND!", "Product with id " + id + " not found during the disable process!", HttpStatus.BAD_REQUEST));
        product.setEnabled(false);
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Override
    public Page<ProductFilterDto> filter(ProductFilterForm form,Integer page, Integer limit) {
        List<ProductFilterDto> productsList=productRepository.filter(form.getName(), form.getDescription(), form.getMinUserAmount(), form.getCurrentAmount(), form.getMinUserAmount(), form.getEnabled());
        Pageable pagination= PageRequest.of(page,limit);
        int start = (int) pagination.getOffset();
        int end = Math.min((start + pagination.getPageSize()), productsList.size());
        if(start > productsList.size())
            return new PageImpl<ProductFilterDto>(new ArrayList<>(), pagination, productsList.size());
        return new PageImpl<ProductFilterDto>(productsList.subList(start, end), pagination, productsList.size());
    }
}
