package com.example.covid_19tracker.ui.countries_data;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covid_19tracker.models.CountryData;
import com.example.covid_19tracker.R;
import com.squareup.picasso.Picasso;

public class CountryDetailFragment extends Fragment {

    //Key For Transfaring Data Between CountriesFragment And CountryDetailFragment .
    public static final String KEY_TRANSFAR_DATA = "COUNTRY_INFORMATION_KEY";

    private TextView countryName1, countryName, continent, activeCase, totalCase, caseListedToday;
    private TextView casePM, totalDeaths, deathsToday, deathsPM, totalTested, testedPM, recovered;
    private ImageView countryImage;
    private CountryData countryData ;


    public CountryDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_detail, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        countryName = (TextView) view.findViewById(R.id.tvCountryName);
        countryName1 = (TextView) view.findViewById(R.id.tvCountryName1);
        continent = (TextView) view.findViewById(R.id.tvContinent);
        activeCase = (TextView) view.findViewById(R.id.tvActiveCase);
        totalCase = (TextView) view.findViewById(R.id.tvTotalCase);
        caseListedToday = (TextView) view.findViewById(R.id.tvListedToday);
        casePM = (TextView) view.findViewById(R.id.tvCasePM);
        totalDeaths = (TextView) view.findViewById(R.id.tvTotalDeath);
        deathsToday = (TextView) view.findViewById(R.id.tvDeathToday);
        deathsPM = (TextView) view.findViewById(R.id.tvDeathPM);
        totalTested = (TextView) view.findViewById(R.id.tvPeopleTested);
        testedPM = (TextView) view.findViewById(R.id.tvTestPM);
        recovered = (TextView) view.findViewById(R.id.tvRecovered);
        countryImage = (ImageView) view.findViewById(R.id.ivCountryFlag);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            countryData = (CountryData) bundle.getSerializable(KEY_TRANSFAR_DATA);
        }

        if (countryData != null)
            setViewCountryDetail(countryData);

    }

    private void setViewCountryDetail(CountryData countryData) {

        Picasso.get().load(countryData.getCountryInfo().getFlag()).into(countryImage);
        countryName.setText(countryData.getCountry());
        countryName1.setText(countryData.getCountry());
        continent.setText(countryData.getContinent());
        activeCase.setText(countryData.getActive());
        totalCase.setText(countryData.getCases());
        caseListedToday.setText(countryData.getTodayCases());
        casePM.setText(countryData.getCasesPerOneMillion());
        totalDeaths.setText(countryData.getDeaths());
        deathsToday.setText(countryData.getTodayDeaths());
        deathsPM.setText(countryData.getDeathsPerOneMillion());
        totalTested.setText(countryData.getTests());
        testedPM.setText(countryData.getTestsPerOneMillion());
        recovered.setText(countryData.getRecovered());


    }
}
