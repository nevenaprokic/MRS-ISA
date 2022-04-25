package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdventureReporitory extends JpaRepository<Adventure, Integer> {
    List<Adventure> findCottageByInstructorEmail(String email);
    Optional<Adventure> findById(Integer id);
}
