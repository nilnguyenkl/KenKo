package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class SearchModel {

    @SerializedName("id_cource")
    private String id_cource;

    @SerializedName("name_cource")
    private String name_cource;

    @SerializedName("descript_cource")
    private String descript_cource;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;


    public SearchModel(String id_cource, String name_cource, String descript_cource, String firstname, String lastname) {
        this.id_cource = id_cource;
        this.name_cource = name_cource;
        this.descript_cource = descript_cource;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getId_cource() {
        return id_cource;
    }

    public String getName_cource() {
        return name_cource;
    }

    public String getDescript_cource() {
        return descript_cource;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
