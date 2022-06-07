package com.booking.ISAbackend.model;

import javax.persistence.*;

@Entity
public class Admin extends MyUser {

    public Admin() {
    }

    public Admin(String firstName, String lastName, String password, String phoneNumber, String email, Boolean deleted, Role role, Address address) {
        super(firstName, lastName, password, phoneNumber, email, deleted, role, address);
    }
}
