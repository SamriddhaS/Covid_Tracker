package com.example.covid_19tracker.db.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.covid_19tracker.models.CountryData;
import com.example.covid_19tracker.models.DistrictName;
import com.example.covid_19tracker.models.IndiaDetailData;
import com.example.covid_19tracker.db.network.Apis;
import com.example.covid_19tracker.models.GlobalData;
import com.example.covid_19tracker.db.network.IndiaDetailService;
import com.example.covid_19tracker.db.network.MainApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository mInstance ;
    private Apis apis ;
    private Apis indiaDetailApi ;



    public Repository() {

        apis = MainApiService.getInstance().createService(Apis.class) ;
        indiaDetailApi = IndiaDetailService.getInstance().createService(Apis.class) ;

    }

    public static Repository getInstance(){

        if (mInstance==null)
            mInstance = new Repository() ;

        return mInstance ;
    }

    public MutableLiveData<GlobalData> getGlobalData(){

        MutableLiveData<GlobalData> globalData = new MutableLiveData<>() ;

        apis.getGlobalData().enqueue(new Callback<GlobalData>() {
            @Override
            public void onResponse(Call<GlobalData> call, Response<GlobalData> response) {

                if (response.isSuccessful())
                    globalData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<GlobalData> call, Throwable t) {

                globalData.setValue(null);
                Log.i("Error",t.getMessage()) ;

            }
        });
        return globalData ;
    }

    public MutableLiveData<CountryData> getIndiaData(){

        MutableLiveData<CountryData> indiaData = new MutableLiveData<>() ;

        apis.getIndiaData("india").enqueue(new Callback<CountryData>() {
            @Override
            public void onResponse(Call<CountryData> call, Response<CountryData> response) {

                if (response.isSuccessful())
                    indiaData.postValue(response.body());
                else
                    Log.i("Error",response.message()) ;

            }

            @Override
            public void onFailure(Call<CountryData> call, Throwable t) {

                Log.i("Error",t.getMessage()) ;
            }
        });

        return indiaData ;
    }

    public MutableLiveData<List<CountryData>> getCountryList(String sortId){

        MutableLiveData<List<CountryData>> countryData = new MutableLiveData<>();

        apis.getCountryList(sortId).enqueue(new Callback<List<CountryData>>() {
            @Override
            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                if (response.isSuccessful()){
                    List<CountryData> data = response.body() ;
                    if (data!=null){
                        countryData.setValue(data);
                    }
                }

            }
            @Override
            public void onFailure(Call<List<CountryData>> call, Throwable t) {
                Log.i("Error",t.getMessage()) ;
            }
        });

        return countryData ;

    }

    public MutableLiveData<IndiaDetailData> getIndiaDetailData(){

        MutableLiveData<IndiaDetailData> indiaDetailData = new MutableLiveData<>() ;

        indiaDetailApi.getDetailIndiaData().enqueue(new Callback<IndiaDetailData>() {
            @Override
            public void onResponse(Call<IndiaDetailData> call, Response<IndiaDetailData> response) {
                if (response.isSuccessful()){

                    indiaDetailData.postValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<IndiaDetailData> call, Throwable t) {

                Log.i("Error Massage",t.getMessage()) ;
            }
        });

        return indiaDetailData ;
    }

    public MutableLiveData<DistrictName> getDistrictMap(){

        MutableLiveData<DistrictName> data = new MutableLiveData<>() ;

        indiaDetailApi.getDistrictsMap().enqueue(new Callback<DistrictName>() {
            @Override
            public void onResponse(Call<DistrictName> call, Response<DistrictName> response) {

                if (response.isSuccessful())
                    data.postValue(response.body());

            }

            @Override
            public void onFailure(Call<DistrictName> call, Throwable t) {

                Log.i("Error Code",t.getMessage());
            }
        });
        return data ;
    }


}
