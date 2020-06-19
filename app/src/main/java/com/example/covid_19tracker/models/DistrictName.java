package com.example.covid_19tracker.models;

import java.util.HashMap;

public class DistrictName {

    public HashMap<String,DistrictValue> districtValue ;

    public HashMap<String, DistrictValue> getDistrictValue() {
        return districtValue;
    }

    public void setDistrictValue(HashMap<String, DistrictValue> districtValue) {
        this.districtValue = districtValue;
    }

    public DistrictName() {
        this.districtValue = new HashMap<>() ;
    }

}
