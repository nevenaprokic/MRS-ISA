package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.ClientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCategoryRepository extends JpaRepository<ClientCategory, Integer> {
    List<ClientCategory> findByName(String name);
    List<ClientCategory> findAll();
}
