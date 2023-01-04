package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.ProductDto;
import com.CoffeControl.backend.dto.ProductFilterDto;
import com.CoffeControl.backend.form.ProductFilterForm;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.form.ProductUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public ProductDto update(Integer id, ProductUpdateForm form) throws Exception;

    Page<ProductDto> list(Integer page, Integer limit);

    ProductDto register(ProductPostForm form);

    ProductDto enable(Integer id) throws Exception;

    ProductDto disable(Integer id) throws Exception;

    Page<ProductFilterDto> filter(ProductFilterForm form,Integer page, Integer limit);
}
