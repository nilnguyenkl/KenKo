package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class StudentModel {

    @SerializedName("status_code")
    private String statusCode;

    @SerializedName("email")
    private String email;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    public StudentModel(String lastname, String firstname, String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
