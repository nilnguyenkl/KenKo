package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class UsersModel {

    @SerializedName("status")
    private String status;

    @SerializedName("result_code")
    private int resultCode;

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

    @SerializedName("object")
    private String object;

    @SerializedName("password_stt")
    private int password_stt;

    public int getPasswordStt() {
        return password_stt;
    }

    public String getStatus() {
        return status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getEmail() {
        return email;
    }

    public String getObject() {
        return object;
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
