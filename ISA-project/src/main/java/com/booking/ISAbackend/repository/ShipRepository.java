package com.booking.ISAbackend.repository;

import com.booking.ISAbackend.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Integer> {
    List<Ship> findShipByShipOwnerEmail(String email);
    Ship findShipById(Integer id);

    @Query("SELECT c FROM Ship c JOIN FETCH c.address WHERE (lower(c.address.city) LIKE lower(concat('%', :address, '%')) OR lower(:address) LIKE lower(concat('%', c.address.city, '%'))" +
            " OR lower(c.address.street) LIKE lower(concat('%', :address, '%')) OR lower(:address) LIKE lower(concat('%', c.address.street, '%'))"+
            " OR lower(c.address.state) LIKE lower(concat('%', :address, '%')) OR lower(:address) LIKE lower(concat('%', c.address.state, '%')))"+
            " AND (lower(c.name) LIKE lower(concat('%', :name, '%')) OR lower(:name) LIKE lower(concat('%', c.name, '%')))"+
            " AND (c.numberOfPerson = :maxPeople OR :maxPeople = -1) AND (c.price = :price OR :price = -1) ")
    List<Ship> searchShips(@Param("name") String name, @Param("maxPeople") int maxPeople, @Param("address")String address, @Param("price") double price);
}
