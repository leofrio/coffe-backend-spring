package com.CoffeControl.backend.controller;

import com.CoffeControl.backend.dto.StorageDetailedDto;
import com.CoffeControl.backend.dto.StorageDto;
import com.CoffeControl.backend.form.StorageUpdateQuantityForm;
import com.CoffeControl.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;
    @GetMapping
    public Page<StorageDto> list(@RequestParam(required = false,defaultValue = "0") Integer page, @RequestParam(required = false,defaultValue = "10") Integer limit) {
        return storageService.list(page,limit);
    }
    @GetMapping("/{id}")
    public StorageDetailedDto getSpecificStorage(@PathVariable("id") Integer id) throws Exception {
        return storageService.getSpecificStorage(id);
    }
    @PutMapping("/{id}/updateQuantity")
    public StorageDto updateQuantity(@PathVariable("id") Integer id, @RequestBody StorageUpdateQuantityForm form) throws Exception {
        return storageService.updateQuantity(id,form);
    }
    @GetMapping("/{id}/byProductId")
    public StorageDetailedDto getByProductId(@PathVariable("id") Integer product_id) throws Exception {
        return storageService.getByProductId(product_id);
    }

}
