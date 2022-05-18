package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.OwnerCategory;
import com.booking.ISAbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerCategoryRepository extends JpaRepository<OwnerCategory, Integer> {
    List<OwnerCategory> findByName(String name);
}
