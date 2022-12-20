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
    @Query(value = "select s.* from solicitation_product sp " +
                " inner join solicitations s on s.id = sp.solicitation_id " +
                " inner join products p on p.id = sp.product_id " +
                " inner join users u on u.id = s.assigned_user_id " +
                " where(lower(s.solicitation_name) like concat('%', :name , '%') or :name  is null) and " +
                " (lower(p.description) like concat('%', :description , '%') or :description is null) and" +
                " (lower(u.full_name) like concat('%', :username ) or :username is null) and " +
                " (s.enabled = :enabled or :enabled is null)  and " +
                " (u.pword = :password or :password is null)and " +
                " (lower(p.product_name) like concat('%', :productName , '%')  or :productName is null) and " +
                " (sp.asked_amount = :askedAmount  or :askedAmount is null) " +
            " group by s.id ",nativeQuery = true)
    public List<Solicitation> filter(String name, String description, Boolean enabled, String password, String productName, Integer askedAmount, String username);

}
