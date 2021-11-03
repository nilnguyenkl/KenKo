package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class CourceDetailsModel {

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
