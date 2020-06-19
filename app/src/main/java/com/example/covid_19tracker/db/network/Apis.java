package com.example.covid_19tracker.db.network;

import com.example.covid_19tracker.models.CountryData;
import com.example.covid_19tracker.models.DistrictName;
import com.example.covid_19tracker.models.GlobalData;
import com.example.covid_19tracker.models.IndiaDetailData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Apis {


    @GET("v2/all")
    Call<GlobalData> getGlobalData() ;

    @GET("v2/countries")
    Call<List<CountryData>> getCountryList(@Query("sort")String sortId);

    @GET("v2/countries/{query}")
    Call<CountryData> getIndiaData(@Path("query")String countryPath) ;

    @GET("data.json")
    Call<IndiaDetailData> getDetailIndiaData() ;

    @GET("state_district_wise.json")
    Call<DistrictName> getDistrictsMap() ;


}
