package com.CoffeControl.backend.controller;

import com.CoffeControl.backend.dto.SolicitationDetailedDto;
import com.CoffeControl.backend.dto.SolicitationDto;
import com.CoffeControl.backend.form.SolicitationPostForm;
import com.CoffeControl.backend.service.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/solicitations")
public class SolicitationController {
    @Autowired
    private SolicitationService solicitationService;

    @GetMapping
    public Page<SolicitationDto> list(@RequestParam(required = false,defaultValue = "0") Integer page, @RequestParam(required = false,defaultValue = "10") Integer limit,@RequestParam(required = false) String name,@RequestParam(required = false) String description,@RequestParam(required = false) Boolean enabled,@RequestParam(required = false) String password,@RequestParam(required = false) String productName,@RequestParam(required = false) Integer askedAmount,@RequestParam(required = false) String username) {
        return solicitationService.list(page,limit,name,description,enabled,password,productName,askedAmount,username);
    }

    @PostMapping
    public ResponseEntity<SolicitationDto> newSolicitation(@RequestBody SolicitationPostForm form, UriComponentsBuilder uriBuilder) throws Exception {
        return solicitationService.register(form,uriBuilder);
    }
    @GetMapping("/{id}")
    public SolicitationDetailedDto getSpecificSolicitation(@PathVariable("id") Integer id) throws Exception {
        return solicitationService.getSpecificSolicitation(id);
    }
}
