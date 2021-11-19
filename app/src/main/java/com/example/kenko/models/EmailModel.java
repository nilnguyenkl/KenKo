package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class EmailModel {

    @SerializedName("email_group")
    private String email_group;

    @SerializedName("status_code")
    private String statusCode;

    @SerializedName("result_code")
    private int result_code;

    @SerializedName("name_cource")
    private String name_cource;

    public int getResult_code() {
        return result_code;
    }

    public String getName_cource() {
        return name_cource;
    }

    public String getEmail_group() {
        return email_group;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
