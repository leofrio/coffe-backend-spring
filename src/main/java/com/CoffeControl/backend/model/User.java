package com.CoffeControl.backend.model;


import com.CoffeControl.backend.dto.UserFilterDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@NamedNativeQuery(name = "User.filter", query = "select " +
        " u.id,  " +
        " u.full_name as username,  " +
        " u.registration,  " +
        " u.pword,  " +
        " pf.profile_type,  " +
        " (select count(*) from solicitations s where s.assigned_user_id=u.id) as amount_of_solicitations,  " +
        " (select count(*) from contributions c where c.user_id=u.id) as amount_of_contributions  " +
        "from  " +
        " users u  " +
        "inner join profiles pf on  " +
        " pf.id = u.profile_id  " +
        "where  " +
        " (lower(u.full_name) like concat('%', lower( :name ) , '%')  " +
        "  or :name is null)  " +
        " and    " +
        "   (lower(pf.profile_type) like concat('%',lower( :profileType ) , '%')  " +
        "  or :profileType is null)  " +
        " and   " +
        "   (u.registration like concat( :registration , '%')  " +
        "  or :registration is null)  " +
        " and   " +
        "   (u.pword = :password  " +
        "  or :password is null)  " +
        " and   " +
        "   ((select count(*) from solicitations s where s.assigned_user_id=u.id) >= :amountSolicitations  " +
        "  or :amountSolicitations is null)  " +
        " and   " +
        "   ((select count(*) from contributions c where c.user_id=u.id) >= :amountContributions  " +
        "  or :amountContributions is null);",resultSetMapping = "UserFiltering")
@SqlResultSetMapping(name = "UserFiltering",classes = @ConstructorResult(targetClass = UserFilterDto.class,columns = {
        @ColumnResult(name = "id",type = Integer.class),
        @ColumnResult(name = "username",type = String.class),
        @ColumnResult(name = "registration",type = String.class),
        @ColumnResult(name = "pword",type = String.class),
        @ColumnResult(name = "profile_type",type = String.class),
        @ColumnResult(name = "amount_of_solicitations",type = Integer.class),
        @ColumnResult(name = "amount_of_contributions",type = Integer.class)
}))
@Entity(name = "users")
@NoArgsConstructor
public class User implements UserDetails {
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
    @JsonManagedReference
    @OneToMany(mappedBy = "assignedUser")
    private List<Solicitation> solicitations;
    @Getter @Setter
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Contribution> contributions;
    @Getter @Setter
    @ManyToOne
    @JsonBackReference
    private Profile profile;

    public User(String name, String registration, String password, Profile profile) {
        this.name = name;
        this.registration = registration;
        this.password = password;
        this.profile = profile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(profile.getName()));
    }

    @Override
    public String getUsername() {
        return this.registration;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
