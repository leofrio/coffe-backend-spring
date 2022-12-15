package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.SolicitationProduct;
import com.CoffeControl.backend.model.compositeKey.SolicitationProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationProductRepository extends JpaRepository<SolicitationProduct, SolicitationProductId> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM solicitation_product sp " +
            "WHERE sp.solicitation_id= :solicitationId AND " +
            "sp.product_id = :productId )",nativeQuery = true)
    Boolean checkIfProductExistsInSolicitation(Integer solicitationId, Integer productId);
}
