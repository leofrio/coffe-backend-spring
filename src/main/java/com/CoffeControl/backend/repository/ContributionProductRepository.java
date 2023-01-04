package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.ContributionProduct;
import com.CoffeControl.backend.model.compositeKey.ContributionProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionProductRepository extends JpaRepository<ContributionProduct, ContributionProductId> {
    @Query(value = "select cp.* from contribution_product cp  " +
            " where cp.contribution_id = :cid ",nativeQuery = true)
    List<ContributionProduct> findByContributionId(Integer cid);
}
