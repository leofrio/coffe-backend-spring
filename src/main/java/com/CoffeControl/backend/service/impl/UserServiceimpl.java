package com.CoffeControl.backend.service.impl;

import com.CoffeControl.backend.dto.UserDetailedDto;
import com.CoffeControl.backend.dto.UserDto;
import com.CoffeControl.backend.model.User;
import com.CoffeControl.backend.repository.UserRepository;
import com.CoffeControl.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {


    @Autowired
    private UserRepository userRepository;


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
}
