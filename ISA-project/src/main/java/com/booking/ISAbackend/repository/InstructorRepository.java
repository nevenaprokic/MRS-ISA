package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Instructor findById(int id);


    Instructor findByEmail(String email);

}
