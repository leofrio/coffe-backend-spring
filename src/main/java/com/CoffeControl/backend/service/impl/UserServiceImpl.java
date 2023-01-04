package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.ContributionDto;
import com.CoffeControl.backend.dto.UserDetailedDto;
import com.CoffeControl.backend.dto.UserDto;
import com.CoffeControl.backend.dto.UserFilterDto;
import com.CoffeControl.backend.form.ContributionPostForm;
import com.CoffeControl.backend.form.ContributionProductForm;
import com.CoffeControl.backend.form.UserFilterForm;
import com.CoffeControl.backend.form.UserPostForm;
import com.CoffeControl.backend.model.*;
import com.CoffeControl.backend.model.compositeKey.ContributionProductId;
import com.CoffeControl.backend.repository.*;
import com.CoffeControl.backend.service.ContributionService;
import com.CoffeControl.backend.service.SolicitationService;
import com.CoffeControl.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private  ContributionRepository contributionRepository;
    @Autowired
    private ContributionService contributionService;
    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private SolicitationService solicitationService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ContributionProductRepository contributionProductRepository;
    @Autowired
    private SolicitationProductRepository solicitationProductRepository;
    @Autowired
    private StorageRepositoy storageRepositoy;


    @Override
    public Page<UserDto> list(Integer page, Integer limit) {
        Pageable pagination= PageRequest.of(page,limit);
        Page<User> users = userRepository.findAll(pagination);
        return UserDto.convert(users);
    }

    @Override
    public UserDetailedDto getSpecificUser(Integer id) throws Exception {
        User user= userRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
        return new UserDetailedDto(user);
    }

    @Override
    public UserDto register(UserPostForm form) {
        Profile profile=profileRepository.findByName(form.getProfile_type()).get(0);
        User user = new User(form.getName(),form.getRegistration(),form.getPassword(),profile);
        userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public ContributionDto newContribution(Integer id, ContributionPostForm form) throws Exception {
        Solicitation solicitation=solicitationRepository.findById(form.getSolicitationId()).orElseThrow(() -> new Exception("no solicitation found with that id"));
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("no user found with that id"));
        Contribution contribution= new Contribution(user,solicitation);
        contribution=contributionRepository.save(contribution);
        for(ContributionProductForm current : form.getProducts()) {
            contribution.setProducts(contribution.getProducts() == null ? new ArrayList<ContributionProduct>() : contribution.getProducts());
            Integer productId=current.getProductId();
            Integer givenAmount=current.getGivenAmount();
            if(!solicitationProductRepository.checkIfProductExistsInSolicitation(solicitation.getId(),productId)) {
                continue;
            }
            Product product=productRepository.findById(productId).orElseThrow(() -> new Exception("no product found with id: " +productId));
            ContributionProductId contributionProductId=new ContributionProductId(contribution.getId(), productId);
            ContributionProduct contributionProduct=new ContributionProduct(contributionProductId,givenAmount);
            contributionProduct.setContribution(contribution);
            contributionProduct.setProduct(product);
            contributionProduct= contributionProductRepository.save(contributionProduct);
            contribution.getProducts().add(contributionProduct);
            Storage storage=storageRepositoy.findByProductId(productId).get(0);
            storage.setCurrentAmount(storage.getCurrentAmount() + givenAmount);
            storageRepositoy.save(storage);
        } 

        contribution=contributionRepository.save(contribution);
        solicitationService.checkIfFinished(solicitation.getId());
        return new ContributionDto(contribution);
    }

    @Override
    public Page<UserFilterDto> filter(UserFilterForm form,Integer page,Integer limit) {
        List<UserFilterDto> usersList=userRepository.filter(form.getName(),form.getRegistration(),form.getPassword(),form.getProfileType(),form.getAmountOfSolicitations(),form.getAmountOfContributions());
        Pageable pagination= PageRequest.of(page,limit);
        int start = (int) pagination.getOffset();
        int end = Math.min((start + pagination.getPageSize()), usersList.size());
        if(start > usersList.size())
            return new PageImpl<UserFilterDto>(new ArrayList<>(), pagination, usersList.size());
        return new PageImpl<UserFilterDto>(usersList.subList(start, end), pagination, usersList.size());
    }
}
