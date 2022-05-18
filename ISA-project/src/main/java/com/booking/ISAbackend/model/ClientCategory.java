package com.booking.ISAbackend.model;

import javax.persistence.*;

@Entity
public class ClientCategory {
//	CASUAL_CLIENT,
//    CLOSE_CLIENT,
//    BEST_CLIENT
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name")
    String name;

    @Column(name="discount")
    Double discount; //popusta za svaku rezervaciju

    @Column
    Integer reservationPoints; //broj poena od svake rezervacije

    @Column
    Integer lowLimitPoints;

    @Column
    Integer heighLimitPoints;

    public ClientCategory(Long id, String name, Double discount, Integer reservationPoints, Integer lowLimitPoints, Integer heighLimitPoints) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.reservationPoints = reservationPoints;
        this.lowLimitPoints = lowLimitPoints;
        this.heighLimitPoints = heighLimitPoints;
    }

    public ClientCategory() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getDiscount() {
        return discount;
    }

    public Integer getReservationPoints() {
        return reservationPoints;
    }

    public Integer getLowLimitPoints() {
        return lowLimitPoints;
    }

    public Integer getHeighLimitPoints() {
        return heighLimitPoints;
    }
}
