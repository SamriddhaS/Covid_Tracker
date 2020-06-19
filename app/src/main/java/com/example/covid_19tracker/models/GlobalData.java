package com.example.covid_19tracker.models;

public class GlobalData {

    private String updated ;
    private String cases ;
    private String todayCases ;
    private String deaths ;
    private String todayDeaths ;
    private String recovered ;
    private String active ;
    private String critical ;
    private String casesPerOneMillion ;
    private String deathsPerOneMillion ;
    private String tests ;
    private String testsPerOneMillion ;
    private String affectedCountries ;

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public void setCasesPerOneMillion(String casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public void setDeathsPerOneMillion(String deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public void setTestsPerOneMillion(String testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public void setAffectedCountries(String affectedCountries) {
        this.affectedCountries = affectedCountries;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCases() {
        return cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getActive() {
        return active;
    }

    public String getCritical() {
        return critical;
    }

    public String getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public String getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public String getTests() {
        return tests;
    }

    public String getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public String getAffectedCountries() {
        return affectedCountries;
    }
}
