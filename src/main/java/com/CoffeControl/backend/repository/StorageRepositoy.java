package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepositoy extends JpaRepository<Storage,Integer> {
    @Query(value ="SELECT * FROM product_storage s WHERE s.product_id = :product_id" ,nativeQuery = true)
    public List<Storage> findByProductId(Integer product_id);

}
