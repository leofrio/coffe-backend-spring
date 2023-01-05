package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.ContributionDto;
import com.CoffeControl.backend.exception.GenericException;
import com.CoffeControl.backend.model.*;
import com.CoffeControl.backend.repository.ContributionProductRepository;
import com.CoffeControl.backend.repository.ContributionRepository;
import com.CoffeControl.backend.repository.SolicitationRepository;
import com.CoffeControl.backend.repository.StorageRepository;
import com.CoffeControl.backend.service.ContributionService;
import com.CoffeControl.backend.service.SolicitationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ContributionServiceImpl implements ContributionService {
    @Autowired
    private ContributionRepository contributionRepository;
    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private ContributionProductRepository contributionProductRepository;
    @Autowired
    private SolicitationService solicitationService;
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public Page<ContributionDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Contribution> contributions = contributionRepository.findAll(pagination);
        return ContributionDto.convert(contributions);
    }

    @Override
    @Transactional
    public String delete(Integer id) throws Exception {

        Contribution contribution=contributionRepository.findById(id).orElseThrow(() -> new GenericException("CONTRIBUTION NOT FOUND!", "Contribution with id " + id + " not found during the delete of one!", HttpStatus.BAD_REQUEST));
        Solicitation solicitation=solicitationRepository.findById(contribution.getSolicitation().getId()).orElseThrow(() -> new GenericException("SOLICITATION NOT FOUND!", "Solicitation with id " + contribution.getSolicitation().getId() + " not found during the delete of contribution!", HttpStatus.BAD_REQUEST));
        solicitation.getContributions().removeIf(c -> Objects.equals(c.getId(), id));
        solicitationRepository.save(solicitation);
        solicitationService.checkIfFinished(solicitation.getId());
        contributionRepository.deleteById(id);
        solicitation.getProducts().stream().map(SolicitationProduct::getProduct).map(Product::getId).forEach(pid ->{
            Storage s= storageRepository.findByProductId(pid).stream().findFirst().get();
            Integer value= contribution.getProducts().stream()
                    .filter(cp -> Objects.equals(cp.getId().getProductId(), pid))
                    .findFirst()
                    .map(ContributionProduct::getGivenAmount).get();
            s.setCurrentAmount(s.getCurrentAmount() - value);
            storageRepository.save(s);
        });

        return "contribution " +id + " deleted" ;
    }
}
