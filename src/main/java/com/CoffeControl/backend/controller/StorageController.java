package com.CoffeControl.backend.controller;

import com.CoffeControl.backend.dto.StorageDetailedDto;
import com.CoffeControl.backend.dto.StorageDto;
import com.CoffeControl.backend.dto.StorageLessDetailedDto;
import com.CoffeControl.backend.form.ProductUpdateForm;
import com.CoffeControl.backend.form.StorageUpdateForm;
import com.CoffeControl.backend.form.StorageUpdateQuantityForm;
import com.CoffeControl.backend.service.StorageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/{id}/updateProduct")
    public StorageDetailedDto updateProduct(@PathVariable("id") Integer id,@RequestBody ProductUpdateForm form) throws Exception {
        return storageService.updateProduct(id, form);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<StorageDto> deleteStorage(@PathVariable("id") Integer id) throws Exception {
        return storageService.deleteStorage(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StorageDetailedDto> updateStorage(@PathVariable("id") Integer id,@RequestBody StorageUpdateForm form) throws Exception {
       return  storageService.updateStorage(id,form);
    }
    @GetMapping("/productName/{name}")
    public Page<StorageLessDetailedDto> findByProductName(@PathVariable("name") String name, @RequestParam(required = false,defaultValue = "0") Integer page, @RequestParam(required = false,defaultValue = "3") Integer limit) {
        return  storageService.startsWithProductName(name,page,limit);
    }
}
