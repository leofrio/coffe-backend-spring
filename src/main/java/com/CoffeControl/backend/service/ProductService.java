package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.ProductDto;
import com.CoffeControl.backend.dto.ProductFilterDto;
import com.CoffeControl.backend.form.ProductFilterForm;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.form.ProductUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface ProductService {
    public ResponseEntity<ProductDto> update(Integer id, ProductUpdateForm form) throws Exception;

    Page<ProductDto> list(Integer page, Integer limit);

    ResponseEntity<ProductDto> register(ProductPostForm form, UriComponentsBuilder uriBuilder);

    ProductDto enable(Integer id) throws Exception;

    ProductDto disable(Integer id) throws Exception;

    Page<ProductFilterDto> filter(ProductFilterForm form,Integer page, Integer limit);
}
