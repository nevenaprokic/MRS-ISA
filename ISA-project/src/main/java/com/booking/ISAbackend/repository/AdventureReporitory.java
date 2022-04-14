package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Adventure;
import com.booking.ISAbackend.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureReporitory extends JpaRepository<Adventure, Integer> {

}
