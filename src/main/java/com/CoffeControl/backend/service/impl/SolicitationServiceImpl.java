package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.SolicitationDetailedDto;
import com.CoffeControl.backend.dto.SolicitationDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<SolicitationDto> register(SolicitationPostForm form, UriComponentsBuilder uriBuilder) throws Exception {
        User user= Optional.of(userRepository.findByName(form.getUsername()).get()).orElseThrow(() -> new Exception("user not found"));
        Solicitation solicitation = new Solicitation(form,user);
        solicitation= solicitationRepository.save(solicitation);
        for(int i =0; i < form.getProducts().length;i++) {
            Integer productId=form.getProducts()[i].getProductId();
            Integer requiredAmount=form.getProducts()[i].getRequiredAmount();
            Product product =productRepository.findById(productId).orElseThrow(() -> new Exception("product id invalid"));
            SolicitationProductId solicitationProductId = new SolicitationProductId(productId,solicitation.getId());
            SolicitationProduct solicitationProduct=new SolicitationProduct(solicitationProductId,requiredAmount);
            solicitationProduct.setSolicitation(solicitation);
            solicitationProduct.setProduct(product);
            solicitationProductRepository.save(solicitationProduct);
        }
        URI uri= uriBuilder.path("solications/{id}").buildAndExpand(solicitation.getId()).toUri();
        return ResponseEntity.created(uri).body(new SolicitationDto(solicitation));
    }

    @Override
    public Boolean checkIfFinished(Integer solicitationId) throws Exception {
       Solicitation solicitation= solicitationRepository.findById(solicitationId).orElseThrow(() -> new Exception("solicitation not found"));
       List<Contribution> contributions=solicitation.getContributions();
       Boolean finished=false;
       for(SolicitationProduct currentProduct : solicitation.getProducts()) {
           Integer requiredAmount=currentProduct.getAmountAsked();
           Integer givenAmount=0;
           for(Contribution currentContribution : solicitation.getContributions()) {
               List<ContributionProduct> productGivenList= currentContribution.getProducts().stream().filter((ContributionProduct p) ->
                       Objects.equals(p.getProduct().getId(), currentProduct.getProduct().getId())
               ).toList();
               if(productGivenList.isEmpty()) {
                   continue;
               }
               Integer individualAmount= productGivenList.get(0).getGivenAmount();
               givenAmount += individualAmount;

               solicitation.getProducts().forEach(x-> {
                   System.out.println("kldsmasda");
               });

//               if(currentContribution.getProducts().stream().anyMatch(p -> Objects.equals(p.getProduct().getId(), currentProduct.getProduct().getId()))) {
//                   int value = currentContribution.getProducts().stream()
//                           .filter(p -> Objects.equals(p.getProduct().getId(), currentProduct.getProduct().getId()))
//                           .findFirst()
//                           .map(ContributionProduct::getGivenAmount).get();
//                   givenAmount += individualAmount;
//               }


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
           return false;
       }
    }

    @Override
    public SolicitationDetailedDto getSpecificSolicitation(Integer id) throws Exception {
        Solicitation solicitation= solicitationRepository.findById(id).orElseThrow(() -> new Exception("no solicitation found with that id"));
        return new SolicitationDetailedDto(solicitation);
    }

}
