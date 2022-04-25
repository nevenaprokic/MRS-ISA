package com.booking.ISAbackend.dto;

public class ShipOwnerProfileInfoDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String street;
    private String city;
    private String state;
    private String userCategory;

    public ShipOwnerProfileInfoDTO(String email, String firstName, String lastName, String phoneNumber, String street, String city, String state, String userCategory) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.userCategory = userCategory;
    }
    public ShipOwnerProfileInfoDTO(){}

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getUserCategory() {
        return userCategory;
    }


}
