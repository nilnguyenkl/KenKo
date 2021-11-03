package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class CourceModel {

    @SerializedName("status_code")
    private String statusCode;

    @SerializedName("result_code")
    private int resultCode;

    @SerializedName("id_cource")
    private String idCource;

    @SerializedName("name_cource")
    private String nameCource;




    public CourceModel(String idCource, String nameCource) {
        this.idCource = idCource;
        this.nameCource = nameCource;
    }

    public String getIdCource() {
        return idCource;
    }

    public void setIdCource(String idCource) {
        this.idCource = idCource;
    }

    public String getNameCource() {
        return nameCource;
    }

    public void setNameCource(String nameCource) {
        this.nameCource = nameCource;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
