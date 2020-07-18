package com.example.covid_19tracker.ui.global_data;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.covid_19tracker.others.AlertDialogClass;
import com.example.covid_19tracker.others.CheckConnection;
import com.example.covid_19tracker.models.GlobalData;
import com.example.covid_19tracker.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalDataFragment extends Fragment implements AlertDialogClass.AlertDialogClickInterface {

    private TextView totalCase, activeCase, recovered, caseToday, casePM, totalDeath;
    private TextView todayDeath, deathsPM, criticalCondition, totalTested, testsPM, affectedCountries;
    private PieChart pieChart;
    private ProgressBar simpleArcLoader;
    private ScrollView nestedScrollView;
    private GlobalDataViewModel viewModel;
    private NavController navController;
    private AlertDialogClass alertDialogClass;

    public GlobalDataFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(GlobalDataViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_global_data, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        totalCase = (TextView) view.findViewById(R.id.tvTotalCases);
        activeCase = (TextView) view.findViewById(R.id.tvActiveCases);
        recovered = (TextView) view.findViewById(R.id.tvRecovered);
        caseToday = (TextView) view.findViewById(R.id.tvTodayCase);
        casePM = (TextView) view.findViewById(R.id.tvCasesPM);
        todayDeath = (TextView) view.findViewById(R.id.tvTodayDeaths);
        totalDeath = (TextView) view.findViewById(R.id.tvTotalDeaths);
        deathsPM = (TextView) view.findViewById(R.id.tvDeathsPM);
        criticalCondition = (TextView) view.findViewById(R.id.tvCriticalCase);
        totalTested = (TextView) view.findViewById(R.id.tvTested);
        testsPM = (TextView) view.findViewById(R.id.tvTestsPM);
        affectedCountries = (TextView) view.findViewById(R.id.tvAffectedCountries);
        simpleArcLoader = (ProgressBar) view.findViewById(R.id.arcLoader);
        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        nestedScrollView = (ScrollView) view.findViewById(R.id.nestedScrollView);
        navController = Navigation.findNavController(view);
        alertDialogClass = new AlertDialogClass(getContext(), this);


        getDataFromViewModel();


    }

    private void getDataFromViewModel() {
        try {
            if (CheckConnection.isConnected()) {

                viewModel.init();
                viewModel.getGlobalData().observe(getViewLifecycleOwner(), this::setViewData);

            } else {

                alertDialogClass.show();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setViewData(GlobalData globalData) {

        if (globalData != null) {
            totalCase.setText(globalData.getCases());
            activeCase.setText(globalData.getActive());
            recovered.setText(globalData.getRecovered());
            caseToday.setText(globalData.getTodayCases());
            casePM.setText(globalData.getCasesPerOneMillion());
            totalCase.setText(globalData.getCases());
            todayDeath.setText(globalData.getTodayDeaths());
            deathsPM.setText(globalData.getDeathsPerOneMillion());
            totalDeath.setText(globalData.getDeaths());
            criticalCondition.setText(globalData.getCritical());
            totalTested.setText(globalData.getTests());
            testsPM.setText(globalData.getTestsPerOneMillion());
            affectedCountries.setText(globalData.getAffectedCountries());


            setPieGraph(globalData);
            simpleArcLoader.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.VISIBLE);


        }
    }

    private void setPieGraph(GlobalData globalData) {

        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(Float.parseFloat(globalData.getActive()), "Active Case"));
        data.add(new PieEntry(Float.parseFloat(globalData.getCases()), "Total Case"));
        data.add(new PieEntry(Float.parseFloat(globalData.getRecovered()), "Recovered"));
        data.add(new PieEntry(Float.parseFloat(globalData.getDeaths()), "Deaths"));

        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.parseColor("#FFA48E"));
        colors.add(Color.parseColor("#BB86FC"));
        colors.add(Color.parseColor("#4ACFAC"));
        colors.add(Color.parseColor("#D87585"));

        //pieChart.setUsePercentValues(true);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleColor(Color.parseColor("#00000000"));
        pieChart.setHoleRadius(60f);
        //pieChart.setExtraOffsets(10,0,10,0);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setEntryLabelColor(Color.parseColor("#99FFFFFF"));
        pieChart.animateXY(1000, 1000);

        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);

        PieDataSet pieDataSet = new PieDataSet(data, null);
        pieDataSet.setSliceSpace(3);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setValueTextColor(Color.parseColor("#99FFFFFF"));
        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setTextColor(Color.parseColor("#99FFFFFF"));
        legend.setTextSize(12f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }

    @Override
    public void OnAlertButtonClicked() {

        alertDialogClass.dismiss();
        getDataFromViewModel();

    }
}