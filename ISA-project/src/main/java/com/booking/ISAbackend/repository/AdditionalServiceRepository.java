package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Integer> {
}
