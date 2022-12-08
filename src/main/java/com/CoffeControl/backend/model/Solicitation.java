package com.CoffeControl.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "solicitations")
@NoArgsConstructor
public class Solicitation {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter @Setter
    @Column(name = "solicitation_name")
    private String name;
    @Getter @Setter
    private Date solicitation_date;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;
    @Getter @Setter
    @OneToMany(mappedBy = "solicitation")
    private List<Contribution> contributions;
}
