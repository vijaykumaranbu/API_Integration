package com.vijay.apiIntegration.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Company implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("catchPhrase")
    private String catchPhrase;

    @SerializedName("bs")
    private String bs;

    public String getName() {
        return name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getBs() {
        return bs;
    }
}

