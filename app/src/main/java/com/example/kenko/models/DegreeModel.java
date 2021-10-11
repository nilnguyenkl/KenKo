package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class DegreeModel {

    @SerializedName("status")
    private String status;

    @SerializedName("result_code")
    private int resultCode;

    @SerializedName("specialize")
    private String specialize;

    @SerializedName("place_of_issue")
    private String placeOfIssue;

    @SerializedName("date_of_issue")
    private String dateOfIssue;

    @SerializedName("workplace")
    private String workPlace;

    public String getStatus() {
        return status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getSpecialize() {
        return specialize;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public String getWorkPlace() {
        return workPlace;
    }
}
