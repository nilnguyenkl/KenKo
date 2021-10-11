package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class ClassModel {
    @SerializedName("status")
    private String status;

    @SerializedName("result_code")
    private int resultCode;

    public String getStatus() {
        return status;
    }

    public int getResultCode() {
        return resultCode;
    }
}
