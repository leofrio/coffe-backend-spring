package com.CoffeControl.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "profiles")
@NoArgsConstructor
public class Profile {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter @Setter
    @Column(name = "profile_type")
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    @OneToMany(mappedBy = "profile")
    @JsonManagedReference
    private List<User> users;
}
