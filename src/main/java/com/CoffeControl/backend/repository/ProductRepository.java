package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.dto.ProductFilterDto;
import com.CoffeControl.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
   @Query(value = "select p.id,p.product_name,p.description,p.min_user_amount,s.current_amount,s.min_amount,p.enabled  from products p \n" +
           "\tinner join product_storage s on s.product_id =p.id   \n" +
           "\t\twhere (lower(p.product_name)  like :name% or :name is null ) and \n" +
           "\t\t(lower(p.description) like %:description% or :description  is null) and \n" +
           "\t\t(p.min_user_amount = :minUserAmount  or :minUserAmount is null) and \n" +
           "\t\t(p.enabled  = :enabled or :enabled is null) and \n" +
           "\t\t(s.current_amount = :currentAmount or :currentAmount is null) and \n" +
           "\t\t(s.min_amount = :minAmount or :minAmount is null);",nativeQuery = true)
    public List<ProductFilterDto> filter(String name, String description, Integer minUserAmount, Integer currentAmount, Integer minAmount, Boolean enabled);
}
