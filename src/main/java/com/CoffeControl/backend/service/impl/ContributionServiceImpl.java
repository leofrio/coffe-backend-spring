package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.ContributionDto;
import com.CoffeControl.backend.model.Contribution;
import com.CoffeControl.backend.model.Solicitation;
import com.CoffeControl.backend.repository.ContributionProductRepository;
import com.CoffeControl.backend.repository.ContributionRepository;
import com.CoffeControl.backend.repository.SolicitationRepository;
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
    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private ContributionProductRepository contributionProductRepository;

    @Override
    public Page<ContributionDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Contribution> contributions = contributionRepository.findAll(pagination);
        return ContributionDto.convert(contributions);
    }

    @Override
    public String delete(Integer id) throws Exception {
        Contribution contribution=contributionRepository.findById(id).orElseThrow(() -> new Exception("contribution not found"));
        Solicitation solicitation=solicitationRepository.findById(contribution.getSolicitation().getId()).orElseThrow(() -> new Exception("soliicitation not found"));
        System.out.println(solicitation.getContributions().size());
        solicitation.getContributions().removeIf(c -> c.getId() == id);
        System.out.println(solicitation.getContributions().size());
        solicitationRepository.save(solicitation);
        contributionRepository.deleteById(id);
        return "contribution " +id + " deleted" ;
    }
}
