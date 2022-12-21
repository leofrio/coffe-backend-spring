package com.CoffeControl.backend.service;

import com.CoffeControl.backend.dto.ContributionDto;
import com.CoffeControl.backend.dto.UserDetailedDto;
import com.CoffeControl.backend.dto.UserDto;
import com.CoffeControl.backend.dto.UserFilterDto;
import com.CoffeControl.backend.form.ContributionPostForm;
import com.CoffeControl.backend.form.UserFilterForm;
import com.CoffeControl.backend.form.UserPostForm;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface UserService {
    Page<UserDto> list(Integer page, Integer limit);

    UserDetailedDto getSpecificUser(Integer id) throws Exception;

    ResponseEntity<UserDto> register(UserPostForm form, UriComponentsBuilder uriBuilder);

    ResponseEntity<ContributionDto> newContribution(Integer id, ContributionPostForm form, UriComponentsBuilder uriBuilder) throws Exception;

    Page<UserFilterDto> filter(UserFilterForm form,Integer page,Integer limit);
}
