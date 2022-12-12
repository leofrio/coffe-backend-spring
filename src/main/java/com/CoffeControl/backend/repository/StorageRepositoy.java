package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepositoy extends JpaRepository<Storage,Integer> {
    @Query(value ="SELECT * FROM product_storage s WHERE s.product_id = :product_id" ,nativeQuery = true)
    public List<Storage> findByProductId(Integer product_id);
    @Query(value = "SELECT s.* FROM product_storage s " +
            " INNER JOIN products p on p.id=s.product_id " +
            " WHERE LOWER(p.product_name) LIKE :name%", nativeQuery = true)
    public Page<Storage> findByProductName(String name, Pageable pagination);
}
