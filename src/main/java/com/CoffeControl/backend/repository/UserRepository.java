package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.dto.UserFilterDto;
import com.CoffeControl.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    public Optional<User> findByName(String name);

    @Query(nativeQuery = true)
    public List<UserFilterDto> filter(@Param("name") String name,@Param("registration")  String registration, @Param("password")  String password, @Param("profileType")  String profileType,@Param("amountSolicitations")  Integer amountSolicitations,@Param("amountContributions") Integer amountContributions);
}
