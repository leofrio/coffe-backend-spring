package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.form.SolicitationProductForm;
import com.CoffeControl.backend.model.Solicitation;
import com.CoffeControl.backend.model.SolicitationProduct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SolicitationDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private LocalDateTime solicitation_date;
    @Getter @Setter
    private LocalDateTime solicitation_expiration;
    @Getter @Setter
    private Integer assigned_User_Id;
    @Getter @Setter
    private Boolean enabled;
    @Getter @Setter
    private List<SolicitationProductForm> products;

    public SolicitationDto(Solicitation s) {
        this.id = s.getId();
        this.name = s.getName();
        this.solicitation_date = s.getSolicitation_date();
        this.solicitation_expiration=s.getSolicitation_expiration();
        this.assigned_User_Id=s.getAssignedUser().getId();
        this.enabled=s.getEnabled();
        this.products= s.getProducts() != null ?  s.getProducts().stream().map(SolicitationProductForm::new).collect(Collectors.toList()) : new ArrayList<SolicitationProductForm>();

    }
    public SolicitationDto(SolicitationProduct s) {
        this.id = s.getSolicitation().getId();
        this.name = s.getSolicitation().getName();
        this.solicitation_date = s.getSolicitation().getSolicitation_date();
        this.solicitation_expiration=s.getSolicitation().getSolicitation_expiration();
        this.assigned_User_Id=s.getSolicitation().getAssignedUser().getId();
        this.enabled=s.getSolicitation().getEnabled();
        this.products= s.getSolicitation().getProducts() != null ?  s.getSolicitation().getProducts().stream().map(SolicitationProductForm::new).collect(Collectors.toList()) : new ArrayList<SolicitationProductForm>();
    }

    public static Page<SolicitationDto> convert(Page<Solicitation> solicitations) {
        return solicitations.map(SolicitationDto::new);
    }
}
