package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Contribution;
import com.CoffeControl.backend.model.Solicitation;
import com.CoffeControl.backend.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDetailedDto {
    @Getter
    @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String registration;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private ProfileDto profile;
    @Getter @Setter
    private List<SolicitationDto> solicitations;
    @Getter @Setter
    private List<ContributionDto> contributions;

    public UserDetailedDto(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.registration = u.getRegistration();
        this.password = u.getPassword();
        this.profile = new ProfileDto(u.getProfile());
        List<Solicitation> auxsol=new ArrayList<Solicitation>();
        auxsol.addAll(u.getSolicitations());
        this.solicitations=auxsol.stream().map(SolicitationDto::new).collect(Collectors.toList());
        List<Contribution> auxcon=new ArrayList<Contribution>();
        auxcon.addAll(u.getContributions());
        this.contributions=auxcon.stream().map(ContributionDto::new).collect(Collectors.toList());
    }
}
