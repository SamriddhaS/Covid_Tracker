package com.example.covid_19tracker.ui.india_data;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

import com.example.covid_19tracker.adapters.IndiaStateAdaptar;
import com.example.covid_19tracker.others.AlertDialogClass;
import com.example.covid_19tracker.others.CheckConnection;
import com.example.covid_19tracker.models.CountryData;
import com.example.covid_19tracker.models.IndiaDataByTime;
import com.example.covid_19tracker.models.IndiaDetailData;
import com.example.covid_19tracker.models.IndiaStateData;
import com.example.covid_19tracker.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class IndiaStatFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AlertDialogClass.AlertDialogClickInterface {

    //private PieChart pieChart;
    private TextView activeCase, totalCase, caseListedToday, lastUpdated;
    private TextView casePM, totalDeaths, deathsToday, deathsPM, totalTested, testedPM, recovered, recoveredToday;
    private IndiaStatViewModel viewModel;
    private ProgressBar progressBar;
    private ScrollView nestedScrollView;
    public static RecyclerView stateRecyclerView;
    private IndiaStateAdaptar adaptar;
    public static Handler handler = new Handler();
    public static Runnable runnableViewSwitcher;
    private PieChart pieChart;
    private LineChart lineChart;
    private ViewSwitcher viewSwitcher;
    private ToggleButton toggleButton;
    private NavController navController;
    private List<IndiaStateData> stateDataList = new ArrayList<>();
    private TextView btnDistrict;
    private AlertDialogClass alertDialog;

    public IndiaStatFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_india_stat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activeCase = (TextView) view.findViewById(R.id.tvIndActiveCase);
        totalCase = (TextView) view.findViewById(R.id.tvIndTotalCase);
        caseListedToday = (TextView) view.findViewById(R.id.tvIndListedToday);
        casePM = (TextView) view.findViewById(R.id.tvIndCasePM);
        totalDeaths = (TextView) view.findViewById(R.id.tvIndDeaths);
        deathsToday = (TextView) view.findViewById(R.id.tvIndDeathsToday);
        deathsPM = (TextView) view.findViewById(R.id.tvIndDeathsPM);
        totalTested = (TextView) view.findViewById(R.id.tvIndTested);
        testedPM = (TextView) view.findViewById(R.id.tvIndTestedPM);
        recovered = (TextView) view.findViewById(R.id.tvIndRecovered);
        recoveredToday = (TextView) view.findViewById(R.id.tvIndRecoveredToday);
        lastUpdated = (TextView) view.findViewById(R.id.tvIndLastUpdated);
        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(IndiaStatViewModel.class);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        nestedScrollView = (ScrollView) view.findViewById(R.id.nestedScrollView);
        stateRecyclerView = (RecyclerView) view.findViewById(R.id.indiaStateRecycler);
        lineChart = (LineChart) view.findViewById(R.id.lineChart);
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcher);
        btnDistrict = (TextView) view.findViewById(R.id.btnStateAndDistrict);
        navController = Navigation.findNavController(view);
        toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
        alertDialog = new AlertDialogClass(getContext(), this);

        setRecyclerView();
        getDataFromViewModel();

        btnDistrict.setOnClickListener(this);
        toggleButton.setOnCheckedChangeListener(this);


    }

    private void setViewSwitcher() {

        //View Switcher Animation
        Animation in = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left); // load an animation
        viewSwitcher.setInAnimation(in);
        Animation out = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right); // load an animation
        viewSwitcher.setOutAnimation(out);


    }

    private void setRecyclerView() {


        adaptar = new IndiaStateAdaptar(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        stateRecyclerView.setLayoutManager(layoutManager);
        stateRecyclerView.setHasFixedSize(true);
        stateRecyclerView.setItemAnimator(new DefaultItemAnimator());
        stateRecyclerView.setAdapter(adaptar);


    }

    private void setViewIndiaData(CountryData indiaData, IndiaStateData data) {

        if (indiaData != null) {


            casePM.setText(indiaData.getCasesPerOneMillion());
            deathsPM.setText(indiaData.getDeathsPerOneMillion());
            totalTested.setText(indiaData.getTests());
            testedPM.setText(indiaData.getTestsPerOneMillion());

        }
        if (data != null) {

            activeCase.setText(data.getActiveCase());
            totalCase.setText(data.getTotalCase());
            totalDeaths.setText(data.getTotalDeaths());
            caseListedToday.setText(data.getTodayCase());
            deathsToday.setText(data.getTodayDeaths());
            recovered.setText(data.getTotalRecovered());
            recoveredToday.setText(data.getTodayRecovered());
            lastUpdated.setText(data.getLastUpdated());


            setPieGraph(data);
            setViewSwitcher();

            progressBar.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.VISIBLE);
            viewSwitcher.setVisibility(View.VISIBLE);


        }


    }

    private void getDataFromViewModel() {


        try {
            if (CheckConnection.isConnected()) {

                viewModel.init();

                viewModel.getIndiaData().observe(getViewLifecycleOwner(), new Observer<CountryData>() {
                    @Override
                    public void onChanged(CountryData data) {

                        setViewIndiaData(data, null);
                    }
                });

                viewModel.getIndiaDetailData().observe(getViewLifecycleOwner(), new Observer<IndiaDetailData>() {
                    @Override
                    public void onChanged(IndiaDetailData indiaDetailData) {

                        setViewIndiaData(null, indiaDetailData.getStateDataList().get(0)); // Oth position is the total country data.
                        setLineChart(indiaDetailData.getDataByTimeList());
                        adaptar.setStateDataList(indiaDetailData.getStateDataList());

                        if (stateDataList.isEmpty())
                            stateDataList.addAll(indiaDetailData.getStateDataList());

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

    private void setPieGraph(IndiaStateData indiaStateData) {

        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(Float.parseFloat(indiaStateData.getActiveCase()), "Active Case"));
        data.add(new PieEntry(Float.parseFloat(indiaStateData.getTotalCase()), "Total Case"));
        data.add(new PieEntry(Float.parseFloat(indiaStateData.getTotalRecovered()), "Recovered"));
        data.add(new PieEntry(Float.parseFloat(indiaStateData.getTotalDeaths()), "Deaths"));

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

    private void setLineChart(List<IndiaDataByTime> dataByTime) {

        //Setting Up Data For Graph
        ArrayList<Entry> yValue = new ArrayList<>();
        ArrayList<Entry> yValue1 = new ArrayList<>();
        ArrayList<Entry> yValue2 = new ArrayList<>();

        if (dataByTime != null) {
            float i = 0;
            final float constant = 50;
            //Inserting Data From View Model Into ArrayList
            for (IndiaDataByTime data : dataByTime) {

                if (i > constant) {
                    yValue.add(new Entry(i, Float.parseFloat(data.getTotalCase())));
                    yValue1.add(new Entry(i, Float.parseFloat(data.getTotalDeath())));
                    yValue2.add(new Entry(i, Float.parseFloat(data.getTotalRecovered())));
                }
                i++;
            }
        }

        // Formatting value for X-axis
        ValueFormatter formatter = new ValueFormatter() {

            @Override
            public String getAxisLabel(float value, AxisBase axis) {

                if (value == 60f)
                    return "Mar-30";

                if (value == 70f)
                    return "Apr-9";

                if (value == 80f)
                    return "Apr-19";

                if (value == 90f)
                    return "Apr-29";

                if (value == 100f)
                    return "May-9";

                if (value == 110f)
                    return "May-19";

                if (value == 120f)
                    return "May-29";

                if (value == 130f)
                    return "Jun-8";

                if (value == 140f)
                    return "Jun-18";

                if (value == 150f)
                    return "Jun-28";

                if (value == 160f)
                    return "Jul-8";

                if (value == 170f)
                    return "Jul-18";

                if (value == 180f)
                    return "Jul-28";

                if (value == 190f)
                    return "Aug-7";

                if (value == 200f)
                    return "Aug-17";

                return String.valueOf(value);

            }
        };

        //Setting XAxis Data
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(formatter);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.parseColor("#99FFFFFF"));


        //Modifying YAxis
        YAxis y = lineChart.getAxisLeft();
        y.setEnabled(false);
        YAxis yAxis = lineChart.getAxisRight();
        yAxis.setTextColor(Color.parseColor("#99FFFFFF"));


        LineDataSet set1 = new LineDataSet(yValue, "Cases");
        LineDataSet set2 = new LineDataSet(yValue1, "Deaths");
        LineDataSet set3 = new LineDataSet(yValue2, "Recovered");


        //set1.enableDashedLine(10f, 5f, 0f);
        //set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor((Color.parseColor("#BB86FC")));
        set1.setValueTextColor(Color.parseColor("#99FFFFFF"));
        set1.setCircleColor(Color.WHITE);
        set1.setLineWidth(2f);
        set1.setCircleRadius(1f);
        set1.setDrawCircleHole(true);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(false);

        set2.setColor((Color.parseColor("#D87585")));
        set2.setValueTextColor(Color.parseColor("#99FFFFFF"));
        set2.setCircleColor(Color.WHITE);
        set2.setLineWidth(2f);
        set2.setCircleRadius(1f);
        set2.setDrawCircleHole(true);
        set2.setValueTextSize(9f);
        set2.setDrawFilled(false);

        set3.setColor((Color.parseColor("#4ACFAC")));
        set3.setValueTextColor(Color.parseColor("#99FFFFFF"));
        set3.setCircleColor(Color.WHITE);
        set3.setLineWidth(2f);
        set3.setCircleRadius(1f);
        set3.setDrawCircleHole(true);
        set3.setValueTextSize(9f);
        set3.setDrawFilled(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        Description description = new Description();
        description.setText("India Covid-19 Graph");
        description.setTextColor(Color.parseColor("#99FFFFFF"));
        description.setTextSize(12f);
        lineChart.setDescription(description);

        Legend legend = lineChart.getLegend();
        legend.setTextSize(13f);
        legend.setTextColor(Color.parseColor("#99FFFFFF"));
        legend.setXEntrySpace(12);
        legend.setForm(Legend.LegendForm.CIRCLE);

        lineChart.setPinchZoom(true);
        lineChart.setDrawBorders(false);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnStateAndDistrict:

                if (navController != null) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(AllStatesFragment.DATA_KEY, (Serializable) stateDataList);
                    navController.navigate(R.id.action_indiaStatFragment_to_allStatesFragment, bundle);
                }
                break;


        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId() == R.id.toggleButton) {

            if (isChecked)
                viewSwitcher.showNext();
            else
                viewSwitcher.showPrevious();

        }

    }


    @Override
    public void OnAlertButtonClicked() {

        alertDialog.dismiss();
        getDataFromViewModel();

    }
}



