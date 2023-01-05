package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.SolicitationDetailedDto;
import com.CoffeControl.backend.dto.SolicitationDto;
import com.CoffeControl.backend.exception.GenericException;
import com.CoffeControl.backend.form.SolicitationPostForm;
import com.CoffeControl.backend.model.*;
import com.CoffeControl.backend.model.compositeKey.SolicitationProductId;
import com.CoffeControl.backend.repository.*;
import com.CoffeControl.backend.service.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SolicitationServiceImpl implements SolicitationService {

    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SolicitationProductRepository solicitationProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ContributionRepository contributionRepository;

    @Override
    public Page<SolicitationDto> list(Integer page,Integer limit,String name, String description,Boolean enabled, String password, String productName,  Integer askedAmount,  String username) {
        Pageable pagination= PageRequest.of(page,limit);
        if(name == null && description == null && enabled == null && password == null && productName == null && askedAmount == null && username == null) {
            Page<Solicitation> solicitations = solicitationRepository.findAll(pagination);
            return SolicitationDto.convert(solicitations);
        }else {
            List<Solicitation> solicitationList=solicitationRepository.filter(name,description,enabled,password,productName,askedAmount,username);
            int start = (int) pagination.getOffset();
            int end = Math.min((start + pagination.getPageSize()), solicitationList.size());
            Page<Solicitation> solicitations=new PageImpl<Solicitation>(solicitationList.subList(start, end), pagination, solicitationList.size());
            return SolicitationDto.convert(solicitations);
        }
    }

    @Override
    public SolicitationDto register(SolicitationPostForm form) throws Exception {
        //User user= Optional.of(userRepository.findByName(form.getUsername()).get()).orElseThrow(() -> new GenericException("USER NOT FOUND!", "User with name " + form.getUsername() + " not found during the creation of solicitation!", HttpStatus.BAD_REQUEST));
        User user=userRepository.findById(form.getUserId()).orElseThrow(() -> new GenericException("USER NOT FOUND!", "User with id " + form.getUserId() + " not found during the creation of solicitation!", HttpStatus.BAD_REQUEST));
        Solicitation solicitation = new Solicitation(form,user);
        solicitation= solicitationRepository.save(solicitation);
        for(int i =0; i < form.getProducts().length;i++) {
            Integer productId=form.getProducts()[i].getProductId();
            Integer requiredAmount=form.getProducts()[i].getRequiredAmount();
            Product product =productRepository.findById(productId).orElseThrow(() -> new GenericException("PRODUCT NOT FOUND!", "Product with id " + productId + " not found during the creation of solicitation!", HttpStatus.BAD_REQUEST));
            SolicitationProductId solicitationProductId = new SolicitationProductId(productId,solicitation.getId());
            SolicitationProduct solicitationProduct=new SolicitationProduct(solicitationProductId,requiredAmount);
            solicitationProduct.setSolicitation(solicitation);
            solicitationProduct.setProduct(product);
            solicitationProductRepository.save(solicitationProduct);
        }
        return new SolicitationDto(solicitation);
    }

    @Override
    public Boolean checkIfFinished(Integer solicitationId) throws Exception {
       Solicitation solicitation= solicitationRepository.findById(solicitationId).orElseThrow(() -> new GenericException("SOLICITATION NOT FOUND!", "Solicitaion with id " + solicitationId + " not found during the request!", HttpStatus.BAD_REQUEST));
       List<Contribution> contributions=solicitation.getContributions();
       Boolean finished=false;
       for(SolicitationProduct currentProduct : solicitation.getProducts()) {
           Integer requiredAmount=currentProduct.getAmountAsked();
           Integer givenAmount=0;
           for(Contribution currentContribution : solicitation.getContributions()) {
               if(currentContribution.getProducts().stream().anyMatch(p -> Objects.equals(p.getProduct().getId(), currentProduct.getProduct().getId()))) {
                   int value = currentContribution.getProducts().stream()
                           .filter(p -> Objects.equals(p.getProduct().getId(), currentProduct.getProduct().getId()))
                           .findFirst()
                           .map(ContributionProduct::getGivenAmount).get();
                   givenAmount += value;
               }


           }
           if(givenAmount < requiredAmount) {
               finished=false;
               break;
           }
           finished=true;
       }
       if(finished) {
           solicitation.setEnabled(false);
           solicitationRepository.save(solicitation);
           return true;
       }
       else {
           solicitation.setEnabled(true);
           solicitationRepository.save(solicitation);
           return false;
       }
    }

    @Override
    public SolicitationDetailedDto getSpecificSolicitation(Integer id) throws Exception {
        Solicitation solicitation= solicitationRepository.findById(id).orElseThrow(() -> new GenericException("SOLICITATION NOT FOUND!", "Solicitation with id " + id + " not found during the request!", HttpStatus.BAD_REQUEST));
        return new SolicitationDetailedDto(solicitation);
    }

}
