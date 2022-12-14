package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.ContributionProduct;
import com.CoffeControl.backend.model.compositeKey.ContributionProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionProductRepository extends JpaRepository<ContributionProduct, ContributionProductId> {

}
