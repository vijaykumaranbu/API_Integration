package com.vijay.apiIntegration.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geo implements Serializable {

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}

