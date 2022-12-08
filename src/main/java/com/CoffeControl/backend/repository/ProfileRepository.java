package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    public List<Profile> findByName(String name);
}
