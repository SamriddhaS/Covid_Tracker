package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryInfo implements Serializable {

    private String flag ;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
