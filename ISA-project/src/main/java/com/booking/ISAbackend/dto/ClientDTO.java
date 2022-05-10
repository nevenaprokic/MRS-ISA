package com.booking.ISAbackend.dto;

public class ClientDTO {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String street;
    private String city;
    private String state;
    private String clientCategory;
    private Integer penal;

    public ClientDTO() {}

    public ClientDTO(String email, String firstName, String lastName, String phoneNumber, String street, String city, String state, String clientCategory, Integer penal) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.clientCategory = clientCategory;
        this.penal = penal;
    }

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

    public String getClientCategory() {
        return clientCategory;
    }

    public Integer getPenal() {
        return penal;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setClientCategory(String clientCategory) {
        this.clientCategory = clientCategory;
    }

    public void setPenal(Integer penal) {
        this.penal = penal;
    }
}
