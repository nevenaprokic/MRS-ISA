package com.booking.ISAbackend.model;

import javax.persistence.*;

@Entity
public class AdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    public AdditionalService(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Column(nullable = false)
    private Double price;


    public AdditionalService() {

    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
