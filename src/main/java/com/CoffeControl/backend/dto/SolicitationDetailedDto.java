package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Solicitation;
import com.CoffeControl.backend.model.SolicitationProduct;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SolicitationDetailedDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private LocalDateTime solicitation_date;
    @Getter @Setter
    private Boolean enabled;
    @Getter @Setter
    private UserDto assignedUser;
    @Getter @Setter
    private List<SolicitationProductDto> products;
    @Getter @Setter
    private List<ContributionDto> contributions;



    public SolicitationDetailedDto(Solicitation s) {
        this.id = s.getId();
        this.name = s.getName();
        this.solicitation_date = s.getSolicitation_date();
        this.assignedUser=new UserDto(s.getAssignedUser());
        this.contributions=s.getContributions().stream().map(ContributionDto::new).collect(Collectors.toList());
        this.enabled=s.getEnabled();
        this.products=s.getProducts().stream().map(SolicitationProductDto::new).collect(Collectors.toList());

    }
    public SolicitationDetailedDto(SolicitationProduct s) {
        this.id = s.getSolicitation().getId();
        this.name = s.getSolicitation().getName();
        this.solicitation_date = s.getSolicitation().getSolicitation_date();
        this.assignedUser=new UserDto(s.getSolicitation().getAssignedUser());
        this.contributions=s.getSolicitation().getContributions().stream().map(ContributionDto::new).collect(Collectors.toList());
        this.enabled=s.getSolicitation().getEnabled();
        this.products=s.getSolicitation().getProducts().stream().map(SolicitationProductDto::new).collect(Collectors.toList());
    }
}