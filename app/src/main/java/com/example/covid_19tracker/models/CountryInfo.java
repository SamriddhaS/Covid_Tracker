package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryInfo implements Serializable {

    private String _id ;
    private String iso2 ;
    private String iso3 ;
    private String lat ;

    @SerializedName("long")
    private String longe;

    private String flag ;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLonge() {
        return longe;
    }

    public void setLonge(String longe) {
        this.longe = longe;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
