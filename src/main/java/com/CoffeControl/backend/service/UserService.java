package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.UserDetailedDto;
import com.CoffeControl.backend.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Page<UserDto> list(Integer page, Integer limit);

    UserDetailedDto getSpecificUser(Integer id) throws Exception;
}
