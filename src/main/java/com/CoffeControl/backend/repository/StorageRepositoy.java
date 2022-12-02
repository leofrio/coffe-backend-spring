package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepositoy extends JpaRepository<Storage,Integer> {

}
