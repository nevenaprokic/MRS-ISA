package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Instructor findById(int id);
    Instructor findByEmail(String email);



}
