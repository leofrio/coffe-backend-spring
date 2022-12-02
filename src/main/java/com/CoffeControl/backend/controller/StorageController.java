package com.CoffeControl.backend.controller;

import com.CoffeControl.backend.model.Storage;
import com.CoffeControl.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;


    @GetMapping
    public Page<Storage> list(@RequestParam(required = false,defaultValue = "0") Integer page, @RequestParam(required = false,defaultValue = "10") Integer limit) {
        return storageService.list(page,limit);
    }
}
