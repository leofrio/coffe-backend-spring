package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Integer> {
}
