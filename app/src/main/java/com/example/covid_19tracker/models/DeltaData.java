package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

public class DeltaData {

    @SerializedName("confirmed")
    private String todayCase ;

    @SerializedName("deceased")
    private String todayDeaths ;

    @SerializedName("recovered")
    private String todayRecovered ;

    public String getTodayCase() {
        return todayCase;
    }

    public void setTodayCase(String todayCase) {
        this.todayCase = todayCase;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }
}
