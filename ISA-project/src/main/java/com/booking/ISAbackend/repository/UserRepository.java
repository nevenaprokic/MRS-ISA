package com.booking.ISAbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.ISAbackend.model.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Integer>{
	MyUser findByEmail(String email);
}
