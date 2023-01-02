package com.CoffeControl.backend.model;

import com.CoffeControl.backend.form.SolicitationPostForm;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "solicitations")
@NoArgsConstructor
@Getter @Setter
public class Solicitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "solicitation_name")
    private String name;
    private LocalDateTime solicitation_date=LocalDateTime.now();
    private LocalDateTime solicitation_expiration;
    private Boolean enabled=true;
    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    @JsonBackReference
    private User assignedUser;
    @OneToMany(mappedBy = "solicitation")
    @JsonManagedReference
    private List<Contribution> contributions;
    @OneToMany(mappedBy = "solicitation")
    @JsonManagedReference
    private List<SolicitationProduct> products;
    

    public Solicitation(SolicitationPostForm form, User user) {
        this.name= form.getName();
        this.assignedUser=user;
        this.solicitation_expiration = this.solicitation_date.plusDays(getSolicitation_date().getMonth().maxLength());
    }
}
