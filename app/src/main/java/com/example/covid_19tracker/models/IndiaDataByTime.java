package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

public class IndiaDataByTime {

    @SerializedName("totalconfirmed")
    private String totalCase ;

    @SerializedName("totaldeceased")
    private String totalDeath ;

    @SerializedName("totalrecovered")
    private String totalRecovered ;

    @SerializedName("date")
    private String date ;


    public String getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(String totalCase) {
        this.totalCase = totalCase;
    }

    public String getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(String totalDeath) {
        this.totalDeath = totalDeath;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
