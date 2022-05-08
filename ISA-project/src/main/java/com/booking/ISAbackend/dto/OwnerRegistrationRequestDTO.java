package com.booking.ISAbackend.dto;


public class OwnerRegistrationRequestDTO {

    private String explanation;

    private String type;

    private String firstName;

    private String lastName;

    private String password;

    private String confirmPassword;

    private String phoneNumber;

    private String email;

    private String street;

    private String city;

    private String state;

    public OwnerRegistrationRequestDTO(){}

    public OwnerRegistrationRequestDTO(String explanation, String type, String firstName, String lastName, String password, String confirmPassword, String phoneNumber, String email, String street, String city, String state) {
        this.explanation = explanation;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getType() {
        return type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword(){
        return confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
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
}
