package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
}
