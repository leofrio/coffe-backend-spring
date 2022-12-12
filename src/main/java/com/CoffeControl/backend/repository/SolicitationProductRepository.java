package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.SolicitationProduct;
import com.CoffeControl.backend.model.compositeKey.SolicitationProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationProductRepository extends JpaRepository<SolicitationProduct, SolicitationProductId> {

}
