package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.ReservationReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationReportRepository extends JpaRepository<ReservationReport, Integer> {
}
