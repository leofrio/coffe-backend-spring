package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.model.Contribution;
import com.CoffeControl.backend.model.ContributionProduct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Date;

public class ContributionDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private Integer solicitation_id;
    @Getter @Setter
    private Integer user_id;
    @Getter @Setter
    private Date contributionDate;

    public ContributionDto(Contribution c) {
        this.id = c.getId();
        this.solicitation_id=c.getSolicitation().getId();
        this.contributionDate = c.getContributionDate();
        this.user_id=c.getUser().getId();
    }
    public ContributionDto(ContributionProduct c) {
        this.id = c.getContribution().getId();
        this.solicitation_id=c.getContribution().getSolicitation().getId();
        this.contributionDate = c.getContribution().getContributionDate();
        this.user_id=c.getContribution().getUser().getId();
    }

    public static Page<ContributionDto> convert(Page<Contribution> contributions) {
        return contributions.map(ContributionDto::new);
    }
}
