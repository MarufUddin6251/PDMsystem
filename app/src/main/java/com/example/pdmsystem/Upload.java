package com.example.pdmsystem;

import com.google.firebase.database.Exclude;

public class Upload {
    private String imagename;
    private String imageuri;
    private String location;
    private String addresses;
    private String rentAmount;
    private String name;
    private String phoneNumber;
    private String email;
    private String key;
@Exclude
    public String getKey() {
        return key;
    }
@Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public Upload()
    {

    }

    public Upload(String imagename, String imageuri, String location, String addresses, String rentAmount, String name, String phoneNumber, String email) {
        this.imagename = imagename;
        this.imageuri = imageuri;
        this.location = location;
        this.addresses = addresses;
        this.rentAmount = rentAmount;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(String rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
