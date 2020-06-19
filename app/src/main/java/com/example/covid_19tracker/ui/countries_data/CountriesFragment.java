package com.example.covid_19tracker.ui.countries_data;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.covid_19tracker.others.AlertDialogClass;
import com.example.covid_19tracker.others.CheckConnection;
import com.example.covid_19tracker.models.CountryData;
import com.example.covid_19tracker.R;
import com.example.covid_19tracker.adapters.CountryAdaptar;
import com.example.covid_19tracker.others.SharedPrefSingleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountriesFragment extends Fragment implements CountryAdaptar.CountryAdapterListener, AlertDialogClass.AlertDialogClickInterface {


    private RecyclerView countryRecycler;
    private CountryAdaptar adaptar;
    private LinearLayoutManager layoutManager;
    private ProgressBar progressBar;
    private CountryViewModel viewModel;
    private NavController navController;
    private List<CountryData> data = new ArrayList<>();
    private AlertDialogClass alertDialog;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public CountriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = (ProgressBar) view.findViewById(R.id.simpleArcLoader1);
        countryRecycler = (RecyclerView) view.findViewById(R.id.countriesRecyclerView);
        navController = Navigation.findNavController(view);
        SharedPrefSingleton.init(getContext());
        alertDialog = new AlertDialogClass(getContext(), this);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(CountryViewModel.class);


        setRecyclerView();
        getDataFromViewModel();

    }

    private void getDataFromViewModel() {

        try {
            if (CheckConnection.isConnected()) {

                /*We only try to access data from repository if their is internet available connection.Otherwise "countryList" variable
                * will be initialize to an empty data.And when we set observer on an empty data variable no data will be shown.
                *
                * init() method is only called when the fragment is created. This method is not called once "countryList" is assigned
                * from null.When refreshing "countryList" we don't call init method again instead we use "refreshViewModelData()" method
                * to do that.
                *  */
                viewModel.init(SharedPrefSingleton.read(SharedPrefSingleton.API_SORT_KEY, null));
                viewModel.getCountryList().observe(getViewLifecycleOwner(), new Observer<List<CountryData>>() {
                    @Override
                    public void onChanged(List<CountryData> countryData) {

                        adaptar.setCountryData(countryData);
                        setProgressBar(false);

                    }
                });

            } else {
                alertDialog.show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void refreshViewModelData() {

        try {
            if (CheckConnection.isConnected()){

                /* We refresh the variable("countryList") inside view model every time their is a change in sorting key*/
                viewModel.refreshViewModelData(SharedPrefSingleton.read(SharedPrefSingleton.API_SORT_KEY, null));

                /*After refreshing the data in view model with a different sorting key we need to change the view of our layout.
                * This is why getDataFromViewModel() method is called again.It will update our recycler view. */
                getDataFromViewModel();

            }else{

                /*When refreshing "countryList" if connection is not available then we don't want to trigger repository for doing an api call.
                * An api call without internet will return an empty data which will cause our view to show empty.
                * */
                alertDialog.show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProgressBar(boolean setLoding) {

        if (setLoding) {
            progressBar.setVisibility(View.VISIBLE);
            countryRecycler.setVisibility(View.GONE);
        } else {

            progressBar.setVisibility(View.GONE);
            countryRecycler.setVisibility(View.VISIBLE);
        }


    }

    private void setRecyclerView() {


        adaptar = new CountryAdaptar(getContext(), this);
        layoutManager = new LinearLayoutManager(getContext());
        countryRecycler.setLayoutManager(layoutManager);
        countryRecycler.setHasFixedSize(true);
        countryRecycler.setAdapter(adaptar);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.sortActiveCase:

                item.setChecked(true);
                SharedPrefSingleton.write(SharedPrefSingleton.API_SORT_KEY, SharedPrefSingleton.read(SharedPrefSingleton.KEY_ACTIVE_CASE, null));
                refreshViewModelData();
                return true;
            case R.id.sortTotalCase:

                item.setChecked(true);
                SharedPrefSingleton.write(SharedPrefSingleton.API_SORT_KEY, SharedPrefSingleton.read(SharedPrefSingleton.KEY_TOTAL_CASE, null));
                refreshViewModelData();
                return true;

            case R.id.sortRecovered:

                item.setChecked(true);
                SharedPrefSingleton.write(SharedPrefSingleton.API_SORT_KEY, SharedPrefSingleton.read(SharedPrefSingleton.KEY_RECOVERED, null));
                refreshViewModelData();
                return true;

            case R.id.sortDeaths:

                item.setChecked(true);
                SharedPrefSingleton.write(SharedPrefSingleton.API_SORT_KEY, SharedPrefSingleton.read(SharedPrefSingleton.KEY_TOTAL_DEATHS, null));
                refreshViewModelData();
                return true;

            case R.id.sortA_Z:

                item.setChecked(true);
                SharedPrefSingleton.write(SharedPrefSingleton.API_SORT_KEY, SharedPrefSingleton.read(SharedPrefSingleton.KEY_A_Z, null));
                refreshViewModelData();
                return true;

            case R.id.sortTodayCase:

                item.setChecked(true);
                SharedPrefSingleton.write(SharedPrefSingleton.API_SORT_KEY, SharedPrefSingleton.read(SharedPrefSingleton.KEY_TODAY_CASE, null));
                refreshViewModelData();
                return true;
            case R.id.sortTodayDeaths:

                item.setChecked(true);
                SharedPrefSingleton.write(SharedPrefSingleton.API_SORT_KEY, SharedPrefSingleton.read(SharedPrefSingleton.KEY_TODAY_DEATHS, null));
                refreshViewModelData();
                return true;
            default:
                return false;


        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.country_menu, menu);


        // setChecked for the item that is used to sort result
        if (SharedPrefSingleton.read(SharedPrefSingleton.API_SORT_KEY, null) == null) {
            menu.findItem(R.id.sortA_Z).setChecked(true);
        } else if (SharedPrefSingleton.read(SharedPrefSingleton.API_SORT_KEY, null)
                .equals(SharedPrefSingleton.read(SharedPrefSingleton.KEY_TOTAL_CASE, null))) {
            menu.findItem(R.id.sortTotalCase).setChecked(true);
        } else if (SharedPrefSingleton.read(SharedPrefSingleton.API_SORT_KEY, null)
                .equals(SharedPrefSingleton.read(SharedPrefSingleton.KEY_ACTIVE_CASE, null))) {
            menu.findItem(R.id.sortActiveCase).setChecked(true);
        } else if (SharedPrefSingleton.read(SharedPrefSingleton.API_SORT_KEY, null)
                .equals(SharedPrefSingleton.read(SharedPrefSingleton.KEY_TODAY_CASE, null))) {
            menu.findItem(R.id.sortTodayCase).setChecked(true);
        } else if (SharedPrefSingleton.read(SharedPrefSingleton.API_SORT_KEY, null)
                .equals(SharedPrefSingleton.read(SharedPrefSingleton.KEY_TOTAL_DEATHS, null))) {
            menu.findItem(R.id.sortDeaths).setChecked(true);
        } else if (SharedPrefSingleton.read(SharedPrefSingleton.API_SORT_KEY, null)
                .equals(SharedPrefSingleton.read(SharedPrefSingleton.KEY_TODAY_DEATHS, null))) {
            menu.findItem(R.id.sortTodayDeaths).setChecked(true);
        } else if (SharedPrefSingleton.read(SharedPrefSingleton.API_SORT_KEY, null)
                .equals(SharedPrefSingleton.read(SharedPrefSingleton.KEY_RECOVERED, null))) {
            menu.findItem(R.id.sortRecovered).setChecked(true);
        }


        //Implementing Search Function
        MenuItem searchItem = menu.findItem(R.id.searchCountry);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adaptar.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCountryItemClickListener(CountryData data) {

        if (navController != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(CountryDetailFragment.KEY_TRANSFAR_DATA, data);

            navController.navigate(R.id.action_countriesFragment_to_countryDetailFragment, bundle);

        }
    }

    @Override
    public void OnAlertButtonClicked() {


        alertDialog.dismiss();
        refreshViewModelData();/*When "try again" button is pressed We again try to load the data from view model using this method.*/
        /*Alert dialog will be shown in two situation 1.When Loading the fragment 2.When trying to sort using an different sorting key.
        * For both cases refreshViewModelData does the job for us.
        *
        * */
    }
}
