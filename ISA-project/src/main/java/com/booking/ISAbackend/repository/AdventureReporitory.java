package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Adventure;
import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdventureReporitory extends JpaRepository<Adventure, Integer> {
    List<Adventure> findCottageByInstructorEmail(String email);
}
