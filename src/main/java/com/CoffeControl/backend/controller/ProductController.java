package com.CoffeControl.backend.controller;

import com.CoffeControl.backend.dto.ProductDto;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping
    public Page<ProductDto> list( @RequestParam(required = false,defaultValue = "0") Integer page, @RequestParam(required = false,defaultValue = "10") Integer limit) {
        return productService.list(page,limit);
    }
    @PostMapping
    public ResponseEntity<ProductDto> register(@RequestBody  ProductPostForm form, UriComponentsBuilder uriBuilder) {
        return productService.register(form,uriBuilder);
    }

}
