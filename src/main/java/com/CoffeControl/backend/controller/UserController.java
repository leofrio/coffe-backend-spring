package com.CoffeControl.backend.controller;

import com.CoffeControl.backend.dto.ContributionDto;
import com.CoffeControl.backend.dto.UserDetailedDto;
import com.CoffeControl.backend.dto.UserDto;
import com.CoffeControl.backend.form.ContributionPostForm;
import com.CoffeControl.backend.form.UserPostForm;
import com.CoffeControl.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserDto> list(@RequestParam(required = false,defaultValue = "0") Integer page, @RequestParam(required = false,defaultValue = "10") Integer limit) {
        return userService.list(page,limit);
    }
    @GetMapping("/{id}")
    public UserDetailedDto findSpecificUser(@PathVariable("id") Integer id) throws Exception {
        return userService.getSpecificUser(id);
    }
    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody UserPostForm form,UriComponentsBuilder uriBuilder) {
        return userService.register(form,uriBuilder);
    }
    @PostMapping("/{id}/addContribution")
    public ResponseEntity<ContributionDto> makeContribution(@PathVariable("id") Integer id, @RequestBody ContributionPostForm form, UriComponentsBuilder uriBuilder) throws Exception {
        return userService.newContribution(id,form,uriBuilder);
    }
}
