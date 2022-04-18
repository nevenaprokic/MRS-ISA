package com.booking.ISAbackend.model;

import javax.persistence.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String path;

    public Photo(String url) {
        this.path = url;
    }

    public Photo() {

    }
    public String getPath(){
        return path;
    }
}
