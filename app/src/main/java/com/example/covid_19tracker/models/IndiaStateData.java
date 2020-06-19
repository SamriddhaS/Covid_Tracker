package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IndiaStateData implements Serializable {

    @SerializedName("active")
    private String activeCase ;

    @SerializedName("confirmed")
    private String totalCase ;

    @SerializedName("deaths")
    private String totalDeaths ;

    @SerializedName("recovered")
    private String totalRecovered ;

    @SerializedName("deltaconfirmed")
    private String todayCase ;

    @SerializedName("deltadeaths")
    private String todayDeaths ;

    @SerializedName("deltarecovered")
    private String todayRecovered ;

    @SerializedName("state")
    private String stateName ;

    @SerializedName("statecode")
    private String stateCode ;

    @SerializedName("statenotes")
    private String stateNotes ;

    @SerializedName("lastupdatedtime")
    private String lastUpdated ;

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

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateNotes() {
        return stateNotes;
    }

    public void setStateNotes(String stateNotes) {
        this.stateNotes = stateNotes;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }



}
