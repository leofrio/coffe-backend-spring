package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Solicitation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Date;

public class SolicitationDto {
    @Getter
    @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Date solicitation_date;
    @Getter @Setter
    private Integer assigned_User_Id;

    public SolicitationDto(Solicitation s) {
        this.id = s.getId();
        this.name = s.getName();
        this.solicitation_date = s.getSolicitation_date();
        this.assigned_User_Id=s.getAssignedUser().getId();
    }
    public static Page<SolicitationDto> convert(Page<Solicitation> solicitations) {
        return solicitations.map(SolicitationDto::new);
    }
}
