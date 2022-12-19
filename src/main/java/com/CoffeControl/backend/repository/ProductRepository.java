package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.dto.ProductFilterDto;
import com.CoffeControl.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
   @Query(nativeQuery = true)
   public List<ProductFilterDto> filter(@Param("name") String name,@Param("description") String description,@Param("minUserAmount") Integer minUserAmount,@Param("currentAmount") Integer currentAmount,@Param("minAmount") Integer minAmount,@Param("enabled") Boolean enabled);
}
