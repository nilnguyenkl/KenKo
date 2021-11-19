package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class TeacherModel {

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

    @SerializedName("specialize")
    private String specialize;

    @SerializedName("place_of_issue")
    private String placeOfIssue;

    @SerializedName("date_of_issue")
    private String dateOfIssue;

    @SerializedName("workplace")
    private String workPlace;

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

    public String getSpecialize() {
        return specialize;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public String getWorkPlace() {
        return workPlace;
    }
}
