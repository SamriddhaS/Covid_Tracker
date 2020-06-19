package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class DistrictValue {

    @SerializedName("districtData")
    public HashMap<String,Districts> districts ;

    @SerializedName("statecode")
    private String stateCode ;

    public HashMap<String, Districts> getDistricts() {
        return districts;
    }

    public void setDistricts(HashMap<String, Districts> districts) {
        this.districts = districts;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public DistrictValue() {
        this.districts = new HashMap<>();
    }
}
