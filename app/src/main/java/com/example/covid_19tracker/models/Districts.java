package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

public class Districts {


    @SerializedName("notes")
    private String note ;

    @SerializedName("active")
    private String activeCase ;

    @SerializedName("confirmed")
    private String totalCase ;

    @SerializedName("recovered")
    private String recovered ;

    @SerializedName("deceased")
    private String deaths ;

    @SerializedName("delta")
    private DeltaData todayData ;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

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

    public DeltaData getTodayData() {
        return todayData;
    }

    public void setTodayData(DeltaData todayData) {
        this.todayData = todayData;
    }
}
