package com.CoffeControl.backend.model;

import com.CoffeControl.backend.form.SolicitationPostForm;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    @JsonBackReference
    private User assignedUser;
    @Getter @Setter
    @OneToMany(mappedBy = "solicitation")
    @JsonManagedReference
    private List<Contribution> contributions;
    

    public Solicitation(SolicitationPostForm form, User user) {
        this.name= form.getName();
        this.solicitation_date=new Date(LocalDate.now().getYear(),LocalDate.now().getMonthValue(),LocalDate.now().getDayOfMonth());
        this.assignedUser=user;
    }
}
