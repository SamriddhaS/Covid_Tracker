package com.example.covid_19tracker.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndiaDetailData {

    @SerializedName("cases_time_series")
    private List<IndiaDataByTime> dataByTimeList ;

    @SerializedName("statewise")
    private List<IndiaStateData> stateDataList ;

    @SerializedName("tested")
    private List<IndiaTested> indiaTestedList ;

    public List<IndiaDataByTime> getDataByTimeList() {
        return dataByTimeList;
    }

    public void setDataByTimeList(List<IndiaDataByTime> dataByTimeList) {
        this.dataByTimeList = dataByTimeList;
    }

    public List<IndiaStateData> getStateDataList() {
        return stateDataList;
    }

    public void setStateDataList(List<IndiaStateData> stateDataList) {
        this.stateDataList = stateDataList;
    }

    public List<IndiaTested> getIndiaTestedList() {
        return indiaTestedList;
    }

    public void setIndiaTestedList(List<IndiaTested> indiaTestedList) {
        this.indiaTestedList = indiaTestedList;
    }
}
