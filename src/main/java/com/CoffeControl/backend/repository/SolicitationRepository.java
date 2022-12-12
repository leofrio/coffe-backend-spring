package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationRepository extends JpaRepository<Solicitation,Integer> {
}
