package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByEmail(String email);

    @Query("SELECT c.penal from Client c WHERE c.email = ?1")
    Integer getPenalties(String email);
}
