package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.DeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeleteRequestRepository extends JpaRepository<DeleteRequest, Integer> {

    @Query("SELECT r FROM DeleteRequest r JOIN FETCH r.myUser WHERE r.myUser.id = ?1 AND r.deleted = false")
    DeleteRequest alreadyExists(Integer id);
}
