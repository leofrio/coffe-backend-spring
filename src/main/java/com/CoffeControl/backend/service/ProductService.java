package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.ProductDto;
import com.CoffeControl.backend.form.ProductPostForm;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface ProductService {
    Page<ProductDto> list(Integer page,  Integer limit);

    ResponseEntity<ProductDto> register(ProductPostForm form, UriComponentsBuilder uriBuilder);
}
