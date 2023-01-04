package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.ContributionDto;
import com.CoffeControl.backend.dto.UserDetailedDto;
import com.CoffeControl.backend.dto.UserDto;
import com.CoffeControl.backend.dto.UserFilterDto;
import com.CoffeControl.backend.form.ContributionPostForm;
import com.CoffeControl.backend.form.UserFilterForm;
import com.CoffeControl.backend.form.UserPostForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Page<UserDto> list(Integer page, Integer limit);

    UserDetailedDto getSpecificUser(Integer id) throws Exception;

    UserDto register(UserPostForm form);

    ContributionDto newContribution(Integer id, ContributionPostForm form) throws Exception;

    Page<UserFilterDto> filter(UserFilterForm form,Integer page,Integer limit);
}
