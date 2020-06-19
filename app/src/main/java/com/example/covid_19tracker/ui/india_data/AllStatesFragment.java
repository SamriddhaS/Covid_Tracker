package com.example.covid_19tracker.ui.india_data;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
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

import com.example.covid_19tracker.adapters.AllStateAdaptar;
import com.example.covid_19tracker.models.IndiaStateData;
import com.example.covid_19tracker.R;

import java.util.ArrayList;
import java.util.List;


public class AllStatesFragment extends Fragment implements AllStateAdaptar.IndiaStateAdaptarInterface{


    private RecyclerView recyclerView ;
    private AllStateAdaptar adaptar ;
    public static final String DATA_KEY = "ALL_STATE_DATA" ;
    private NavController navController ;
    private List<IndiaStateData> allStateData = new ArrayList<>() ;

    public AllStatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_states, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view) ;
        recyclerView = (RecyclerView)view.findViewById(R.id.indiaAllStatesRecycler);
        setUpRecyclerView() ;


        if (getArguments()!=null){
            Bundle bundle = getArguments();
            allStateData = (List<IndiaStateData>) bundle.getSerializable(DATA_KEY) ;

        }

        if (allStateData!=null){
            adaptar.setStateDataList(allStateData);

        }



    }

    private void setUpRecyclerView() {


        adaptar = new AllStateAdaptar(getContext());
        adaptar.setmInterface(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptar);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.all_states_menu,menu);

        MenuItem search = menu.findItem(R.id.searchState);

        SearchView searchView = (SearchView) search.getActionView() ;

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
    public void OnRecyclerItemClickListener(int position) {

        String stateName = allStateData.get(position).getStateName() ;

        if (navController != null){

            Bundle bundle = new Bundle() ;
            bundle.putString(AllDistrictsFragment.STATE_NAME_KEY,stateName);
            navController.navigate(R.id.action_allStatesFragment_to_allDistrictsFragment,bundle);

        }
    }
}
