package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class CourceDetailsModel {

    @SerializedName("status")
    private String status;

    @SerializedName("name_cource")
    private String nameCource;

    @SerializedName("startday")
    private String startDay;

    @SerializedName("stopday")
    private String stopDay;

    @SerializedName("starttime")
    private String startTime;

    @SerializedName("stoptime")
    private String stopTime;

    @SerializedName("daysofweek")
    private String daysOfWeek;

    @SerializedName("address")
    private String address;

    @SerializedName("descript_cource")
    private String descript;

    @SerializedName("price")
    private String price;

    @SerializedName("member")
    private String member;

    @SerializedName("participant")
    private String participant;

    @SerializedName("status_code")
    private String status_code;

    @SerializedName("result_code")
    private int result_code;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getParticipant() {
        return participant;
    }

    public String getStatus_code() {
        return status_code;
    }

    public int getResult_code() {
        return result_code;
    }

    public String getStatus() {
        return status;
    }

    public String getNameCource() {
        return nameCource;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getStopDay() {
        return stopDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public String getAddress() {
        return address;
    }

    public String getDescript() {
        return descript;
    }

    public String getPrice() {
        return price;
    }

    public String getMember() {
        return member;
    }
}
