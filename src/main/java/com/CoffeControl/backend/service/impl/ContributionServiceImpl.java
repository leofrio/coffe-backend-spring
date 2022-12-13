package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.ContributionDto;
import com.CoffeControl.backend.dto.ProductDto;
import com.CoffeControl.backend.model.Contribution;
import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.repository.ContributionRepository;
import com.CoffeControl.backend.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContributionServiceImpl implements ContributionService {
    @Autowired
    private ContributionRepository contributionRepository;

    @Override
    public Page<ContributionDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Contribution> contributions = contributionRepository.findAll(pagination);
        return ContributionDto.convert(contributions);
    }
}
