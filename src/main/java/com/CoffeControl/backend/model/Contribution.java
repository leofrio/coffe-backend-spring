package com.CoffeControl.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private User user;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "solicitation_id")
    private Solicitation solicitation;
}
