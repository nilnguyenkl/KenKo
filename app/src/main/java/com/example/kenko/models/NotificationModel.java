package com.example.kenko.models;

import com.google.gson.annotations.SerializedName;

public class NotificationModel {

    @SerializedName("id_notification")
    private String id_notification;

    @SerializedName("id_cource")
    private String id_cource;

    @SerializedName("content")
    private String content;

    @SerializedName("date_notification")
    private String date_notification;

    @SerializedName("status_code")
    private String status_code;

    @SerializedName("result_code")
    private int result_code;

    public NotificationModel(String id_cource, String content, String date_notification) {
        this.id_cource = id_cource;
        this.content = content;
        this.date_notification = date_notification;
    }

    public String getId_notification() {
        return id_notification;
    }

    public String getId_cource() {
        return id_cource;
    }

    public String getContent() {
        return content;
    }

    public String getDate_notification() {
        return date_notification;
    }

    public String getStatus_code() {
        return status_code;
    }

    public int getResult_code() {
        return result_code;
    }
}
