package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    public List<User> findByName(String name);
}
