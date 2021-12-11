package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class ParticipantCourceModel {

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("status")
    private String status;

    @SerializedName("name_cource")
    private String nameCource;

    @SerializedName("phone")
    private String phone;

    @SerializedName("id_cource")
    private String idCource;

    @SerializedName("descript_cource")
    private String descript_cource;

    public ParticipantCourceModel(String idCource) {
        this.idCource = idCource;
    }

    public String getDescript_cource() {
        return descript_cource;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getStatus() {
        return status;
    }

    public String getNameCource() {
        return nameCource;
    }

    public String getPhone() {
        return phone;
    }

    public String getIdCource() {
        return idCource;
    }
}
