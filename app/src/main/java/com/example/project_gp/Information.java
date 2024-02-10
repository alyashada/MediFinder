package com.example.project_gp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Information {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("lng")
    @Expose
    public String lng;
    @SerializedName("timestamp")
    @Expose
    public String timestamp;

}