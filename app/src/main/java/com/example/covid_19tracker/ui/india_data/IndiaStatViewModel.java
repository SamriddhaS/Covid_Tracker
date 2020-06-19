package com.example.covid_19tracker.ui.india_data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covid_19tracker.models.CountryData;
import com.example.covid_19tracker.models.IndiaDetailData;
import com.example.covid_19tracker.db.repository.Repository;

public class IndiaStatViewModel extends AndroidViewModel {

    private Repository repository ;
    private LiveData<CountryData> indiaData ;
    private LiveData<IndiaDetailData> indiaDetailData ;


    public IndiaStatViewModel(@NonNull Application application) {
        super(application);

        repository = Repository.getInstance() ;

    }

    public void init(){
        if (indiaData==null)
            indiaData = repository.getIndiaData() ;
        if (indiaDetailData==null)
            indiaDetailData = repository.getIndiaDetailData() ;
    }

    public LiveData<CountryData> getIndiaData(){

        return indiaData ;
    }

    public void setIndiaDetailData() {

    }

    public LiveData<IndiaDetailData> getIndiaDetailData() {
        return indiaDetailData;
    }
}
