package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

public class UserDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String registration;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String profile_type;

    public UserDto(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.registration = u.getRegistration();
        this.password = u.getPassword();
        this.profile_type = u.getProfile().getName();
    }

    public static Page<UserDto> convert(Page<User> users) {
        return users.map(UserDto::new);
    }
}
