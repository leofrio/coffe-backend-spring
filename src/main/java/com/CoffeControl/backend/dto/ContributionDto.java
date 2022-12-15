package com.CoffeControl.backend.dto;

import com.CoffeControl.backend.form.ContributionProductForm;
import com.CoffeControl.backend.model.Contribution;
import com.CoffeControl.backend.model.ContributionProduct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContributionDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private Integer solicitation_id;
    @Getter @Setter
    private Integer user_id;
    @Getter @Setter
    private LocalDateTime contributionDate;
    @Getter @Setter
    private List<ContributionProductForm> products;
    public ContributionDto(Contribution c) {
        this.id = c.getId();
        this.solicitation_id=c.getSolicitation().getId();
        this.contributionDate = c.getContributionDate();
        this.user_id=c.getUser().getId();
        this.products=c.getProducts() != null ?  c.getProducts().stream().map(ContributionProductForm::new).collect(Collectors.toList()) : new ArrayList<ContributionProductForm>();
    }
    public ContributionDto(ContributionProduct c) {
        this.id = c.getContribution().getId();
        this.solicitation_id=c.getContribution().getSolicitation().getId();
        this.contributionDate = c.getContribution().getContributionDate();
        this.user_id=c.getContribution().getUser().getId();
        this.products=c.getContribution().getProducts() != null ?  c.getContribution().getProducts().stream().map(ContributionProductForm::new).collect(Collectors.toList()) : new ArrayList<ContributionProductForm>();
    }

    public static Page<ContributionDto> convert(Page<Contribution> contributions) {
        return contributions.map(ContributionDto::new);
    }
}
