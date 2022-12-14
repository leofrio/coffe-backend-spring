package com.CoffeControl.backend.controller;

import com.CoffeControl.backend.dto.ContributionDto;
import com.CoffeControl.backend.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contributions")
public class ContributionController {
    @Autowired
    private ContributionService contributionService;

    @GetMapping
    public Page<ContributionDto> list(@RequestParam(required = false,defaultValue = "0") Integer page, @RequestParam(required = false,defaultValue = "10") Integer limit) {
        return contributionService.list(page,limit);
    }
}
