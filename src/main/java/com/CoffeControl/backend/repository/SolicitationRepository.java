package com.CoffeControl.backend.repository;

import com.CoffeControl.backend.model.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitationRepository extends JpaRepository<Solicitation,Integer> {
    @Query(value = "SELECT sp.asked_amount  FROM solicitation_product sp " +
            " inner join solicitations s on s.id=sp.solicitation_id " +
            " where sp.solicitation_id = :solicitationId;",nativeQuery = true)
    public List<Integer> getRequiredAmounts(Integer solicitationId);


}
