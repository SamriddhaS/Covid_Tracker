package com.example.covid_19tracker.ui.global_data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covid_19tracker.data.repository.Repository;
import com.example.covid_19tracker.models.GlobalData;

public class GlobalDataViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<GlobalData> globalData ;

    public GlobalDataViewModel(@NonNull Application application) {
        super(application);

        repository = Repository.getInstance();


    }

    public void init() {
        if (globalData==null)
            globalData = repository.getGlobalData();
    }

    public LiveData<GlobalData> getGlobalData() {
        return globalData;
    }

}
