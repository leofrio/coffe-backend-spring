package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.SolicitationDetailedDto;
import com.CoffeControl.backend.dto.SolicitationDto;
import com.CoffeControl.backend.form.SolicitationPostForm;
import com.CoffeControl.backend.model.Product;
import com.CoffeControl.backend.model.Solicitation;
import com.CoffeControl.backend.model.SolicitationProduct;
import com.CoffeControl.backend.model.User;
import com.CoffeControl.backend.model.compositeKey.SolicitationProductId;
import com.CoffeControl.backend.repository.ProductRepository;
import com.CoffeControl.backend.repository.SolicitationProductRepository;
import com.CoffeControl.backend.repository.SolicitationRepository;
import com.CoffeControl.backend.repository.UserRepository;
import com.CoffeControl.backend.service.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @Override
    public Page<SolicitationDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<Solicitation> solicitations = solicitationRepository.findAll(pagination);
        return SolicitationDto.convert(solicitations);
    }

    @Override
    public ResponseEntity<SolicitationDto> register(SolicitationPostForm form, UriComponentsBuilder uriBuilder) throws Exception {
        User user= Optional.of(userRepository.findByName(form.getUsername()).get(0)).orElseThrow(() -> new Exception("user not found"));
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
    public SolicitationDetailedDto getSpecificSolicitation(Integer id) throws Exception {
        Solicitation solicitation= solicitationRepository.findById(id).orElseThrow(() -> new Exception("no solicitation found with that id"));
        return new SolicitationDetailedDto(solicitation);
    }

}
