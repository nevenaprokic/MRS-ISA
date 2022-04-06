package com.booking.ISAbackend.model;

import javax.persistence.*;

@Entity
public class AdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
}
