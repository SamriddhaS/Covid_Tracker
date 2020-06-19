package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

public class IndiaDataByTime {

    @SerializedName("dailyconfirmed")
    private String todayCase ;

    @SerializedName("dailydeceased")
    private String todayDeath ;

    @SerializedName("dailyrecovered")
    private String todayRecovered ;

    @SerializedName("totalconfirmed")
    private String totalCase ;

    @SerializedName("totaldeceased")
    private String totalDeath ;

    @SerializedName("totalrecovered")
    private String totalRecovered ;

    @SerializedName("date")
    private String date ;

    public String getTodayCase() {
        return todayCase;
    }

    public void setTodayCase(String todayCase) {
        this.todayCase = todayCase;
    }

    public String getTodayDeath() {
        return todayDeath;
    }

    public void setTodayDeath(String todayDeath) {
        this.todayDeath = todayDeath;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

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
