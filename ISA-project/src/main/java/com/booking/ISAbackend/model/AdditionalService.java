package com.booking.ISAbackend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;

    public AdditionalService(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public AdditionalService() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
