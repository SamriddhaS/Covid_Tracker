package com.example.covid_19tracker.ui.india_data;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.covid_19tracker.adapters.DistrictsAdaptar;
import com.example.covid_19tracker.models.DistrictName;
import com.example.covid_19tracker.R;

public class AllDistrictsFragment extends Fragment {

    private DistrictViewModel viewModel;
    public static final String STATE_NAME_KEY = "STATE_NAME_KEY";
    private String stateName = null;
    private RecyclerView recyclerView;
    private DistrictsAdaptar adaptar;
    private TextView tvStateName ;
    private ProgressBar progressBar ;
    private LinearLayout linearLayout ;

    public AllDistrictsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_districts, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar =(ProgressBar)view.findViewById(R.id.progressBar) ;
        linearLayout=(LinearLayout)view.findViewById(R.id.linearLayout);
        tvStateName = (TextView)view.findViewById(R.id.tvStateName);
        recyclerView = (RecyclerView) view.findViewById(R.id.districtRecycler);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(DistrictViewModel.class);


        if (getArguments() != null) {
            Bundle bundle = getArguments();
            stateName = bundle.getString(STATE_NAME_KEY);
        }


        setUpRecyclerView();

        getDataFromViewModel();

    }

    private void getDataFromViewModel() {

        viewModel.getDistrictData().observe(getViewLifecycleOwner(), new Observer<DistrictName>() {
            @Override
            public void onChanged(DistrictName districtName) {

                if (stateName != null && districtName != null){
                    adaptar.setDistrictsHashMap(districtName.getDistrictValue().get(stateName).getDistricts());
                    linearLayout.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    tvStateName.setText(stateName);

                }
            }
        });
    }

    private void setUpRecyclerView() {

        adaptar = new DistrictsAdaptar(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptar);

    }

}


