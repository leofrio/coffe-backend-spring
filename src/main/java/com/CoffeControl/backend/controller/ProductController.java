package com.CoffeControl.backend.controller;

import com.CoffeControl.backend.dto.ProductDto;
import com.CoffeControl.backend.form.ProductPostForm;
import com.CoffeControl.backend.form.ProductUpdateForm;
import com.CoffeControl.backend.service.ProductService;
import org.jetbrains.annotations.NotNull;
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
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable("id") Integer id,@RequestBody @NotNull ProductUpdateForm form) throws Exception {
        return productService.update(id,form);
    }
    @GetMapping("/{id}/enable")
    public ProductDto enable(@PathVariable("id") Integer id) throws Exception{
        return productService.enable(id);
    }
    @GetMapping("/{id}/disable")
    public ProductDto disable(@PathVariable("id") Integer id) throws Exception{
        return productService.disable(id);
    }
}
