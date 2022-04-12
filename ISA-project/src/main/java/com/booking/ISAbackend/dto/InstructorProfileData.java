package com.booking.ISAbackend.dto;

public class InstructorProfileData {

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public InstructorProfileData(String email, String firstName, String lastName, String phoneNumber, String street, String city, String state, String userCategory, String biography) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.userCategory = userCategory;
        this.biography = biography;
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

    public String getUserCategory() {
        return userCategory;
    }

    public String getBiography() {
        return biography;
    }

    private String street;
    private String city;
    private String state;
    private String userCategory;
    private String biography;


}
