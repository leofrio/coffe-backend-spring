package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.ContributionDto;
import com.CoffeControl.backend.dto.UserDetailedDto;
import com.CoffeControl.backend.dto.UserDto;
import com.CoffeControl.backend.form.ContributionPostForm;
import com.CoffeControl.backend.form.ContributionProductForm;
import com.CoffeControl.backend.form.UserPostForm;
import com.CoffeControl.backend.model.*;
import com.CoffeControl.backend.model.compositeKey.ContributionProductId;
import com.CoffeControl.backend.repository.*;
import com.CoffeControl.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private  ContributionRepository contributionRepository;
    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ContributionProductRepository contributionProductRepository;
    @Autowired
    private SolicitationProductRepository solicitationProductRepository;


    @Override
    public Page<UserDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<User> users = userRepository.findAll(pagination);
        return UserDto.convert(users);
    }

    @Override
    public UserDetailedDto getSpecificUser(Integer id) throws Exception {
        User user= userRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
        System.out.println("user ");
        System.out.println("id :" + user.getId());
        System.out.println("name :" + user.getName());
        System.out.println("registration :" + user.getRegistration());
        System.out.println("password :" + user.getPassword());
        System.out.println("profile_name :" + user.getProfile().getName());
        System.out.println("solicitations :" + user.getSolicitations());
        System.out.println("contributions :" + user.getContributions());
        return new UserDetailedDto(user);
    }

    @Override
    public ResponseEntity<UserDto> register(UserPostForm form, UriComponentsBuilder uriBuilder) {
        Profile profile=profileRepository.findByName(form.getProfile_type()).get(0);
        User user = new User(form.getName(),form.getRegistration(),form.getPassword(),profile);
        userRepository.save(user);
        URI uri= uriBuilder.path("users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

    @Override
    public ResponseEntity<ContributionDto> newContribution(Integer id, ContributionPostForm form, UriComponentsBuilder uriBuilder) throws Exception {
        Solicitation solicitation=solicitationRepository.findById(form.getSolicitationId()).orElseThrow(() -> new Exception("no solicitation found with that id"));
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("no user found with that id"));
        Contribution contribution= new Contribution(user,solicitation);
        contribution=contributionRepository.save(contribution);
        for(ContributionProductForm current : form.getProducts()) {
            Integer productId=current.getProductId();
            Integer givenAmount=current.getGivenAmount();
            if(!solicitationProductRepository.checkIfProductExistsInSolicitation(solicitation.getId(),productId)) {
                Integer contributionId= contribution.getId();
                contributionRepository.deleteById(contributionId);
                throw new Exception("product id " + productId + " not in solicitation");
            }
            Product product=productRepository.findById(productId).orElseThrow(() -> new Exception("no product found with id: " +productId));
            ContributionProductId contributionProductId=new ContributionProductId(contribution.getId(), productId);
            ContributionProduct contributionProduct=new ContributionProduct(contributionProductId,givenAmount);
            contributionProduct.setContribution(contribution);
            contributionProduct.setProduct(product);
            contributionProductRepository.save(contributionProduct);
        }
        URI uri= uriBuilder.path("contributions/{id}").buildAndExpand(contribution.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContributionDto(contribution));
    }
}
