package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.ContributionDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ContributionService {
    Page<ContributionDto> list(Integer page, Integer limit);
}
