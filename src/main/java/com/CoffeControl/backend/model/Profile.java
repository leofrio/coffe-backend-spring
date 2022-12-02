package com.CoffeControl.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "profiles")
@NoArgsConstructor
public class Profile {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter @Setter
    @Column(name = "full_name")
    private String name;
    @Getter @Setter
    private String description;
}
