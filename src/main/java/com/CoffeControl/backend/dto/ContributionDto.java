package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Contribution;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class ContributionDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private Date contributionDate;

    @Getter @Setter
    private Integer solicitation_id;
    @Getter @Setter
    private Integer user_id;

    public ContributionDto(Contribution c) {
        this.id = c.getId();
        this.solicitation_id=c.getSolicitation().getId();
        this.contributionDate = c.getContributionDate();
        this.user_id=c.getUser().getId();
    }
}
