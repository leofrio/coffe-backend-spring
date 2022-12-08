package com.CoffeControl.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "users")
@NoArgsConstructor
public class User {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter @Setter
    @Column(name = "full_name")
    private String name;
    @Getter @Setter
    private String registration;
    @Getter @Setter
    @Column(name = "pword")
    private String password;
    @Getter @Setter
    @OneToMany(mappedBy = "assignedUser")
    private List<Solicitation> solicitations;
    @Getter @Setter
    @OneToMany(mappedBy = "user")
    private List<Contribution> contributions;
    @Getter @Setter
    @ManyToOne
    private Profile profile;
}
