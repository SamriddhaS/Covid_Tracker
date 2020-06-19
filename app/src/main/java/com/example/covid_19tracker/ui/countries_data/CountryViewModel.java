package com.example.covid_19tracker.ui.countries_data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covid_19tracker.models.CountryData;
import com.example.covid_19tracker.db.repository.Repository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private Repository repository ;
    private LiveData<List<CountryData>> countryList ;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance() ;
    }

    public void init(String sortingKey){

        if (countryList==null){
            countryList = repository.getCountryList(sortingKey);
            /*We do a null check here for stop fetching data from view model if the data is
            * already their and the countryList is not empty.This help us handel configuration changes.
            * Also we don't need to trigger repository if countryList data is same(Means sortingKey is not changed),we can show same data
            * in that case without triggering repository. Because view model is life cycle aware and still holds the data even if the fragment
            * encounters some changes(Mostly configuration changes/Rotation of the screen).
            */
        }
    }

    public LiveData<List<CountryData>> getCountryList(){

        return  countryList ;
    }

    public void refreshViewModelData(String sortingKey){

        countryList = repository.getCountryList(sortingKey) ;
        /*But when refreshing the countryList with a different sorting key data then if countryList already has data it still needs to
        * refresh it with new data. This is why we don't use an null check. We want to trigger repository every time refreshViewModelData
        * is called.
        * */

    }


}
