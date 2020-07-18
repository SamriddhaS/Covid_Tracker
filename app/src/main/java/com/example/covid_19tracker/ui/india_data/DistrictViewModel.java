package com.example.covid_19tracker.ui.india_data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid_19tracker.data.repository.Repository;
import com.example.covid_19tracker.models.DistrictName;


public class DistrictViewModel extends AndroidViewModel {


    private Repository repository ;
    private LiveData<DistrictName> districtData = new MutableLiveData<>() ;

    public DistrictViewModel(@NonNull Application application) {
        super(application);

        repository = Repository.getInstance() ;
        districtData = repository.getDistrictMap() ;
    }

    public LiveData<DistrictName> getDistrictData() {
        return districtData;
    }

}
