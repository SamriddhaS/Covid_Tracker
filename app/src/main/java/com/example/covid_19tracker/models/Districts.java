package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

public class Districts {

    @SerializedName("active")
    private String activeCase ;

    @SerializedName("confirmed")
    private String totalCase ;

    @SerializedName("recovered")
    private String recovered ;

    @SerializedName("deceased")
    private String deaths ;

    public String getActiveCase() {
        return activeCase;
    }

    public void setActiveCase(String activeCase) {
        this.activeCase = activeCase;
    }

    public String getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(String totalCase) {
        this.totalCase = totalCase;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

}
