package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.UnavailableOfferDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnavailabelOfferDatesRepository extends JpaRepository<UnavailableOfferDates, Integer> {
    List<UnavailableOfferDates> findByOfferId(Integer id);
}
