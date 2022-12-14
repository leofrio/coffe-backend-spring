package com.CoffeControl.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "contributions")
@NoArgsConstructor
public class Contribution {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter @Setter
    @Column(name = "contribution_date")
    private Date contributionDate;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @Getter @Setter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "solicitation_id")
    @JsonBackReference
    private Solicitation solicitation;


    public Contribution(User user, Solicitation solicitation) {
        this.user=user;
        this.solicitation=solicitation;
        this.contributionDate=new Date(LocalDate.now().getYear(),LocalDate.now().getMonthValue(),LocalDate.now().getDayOfMonth());

    }
}
