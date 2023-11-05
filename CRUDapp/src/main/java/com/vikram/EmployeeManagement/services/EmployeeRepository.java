package com.vikram.EmployeeManagement.services;

import com.vikram.EmployeeManagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<User, Long> {
//    @Query("SELECT t FROM Ticket t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%'))")
//    List<User> searchUsers(@Param("query") String query);
      List<User> findByFirstName(String firstName);
}
