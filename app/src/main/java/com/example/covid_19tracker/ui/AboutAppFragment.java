package com.example.covid_19tracker.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.covid_19tracker.R;

public class AboutAppFragment extends Fragment {

    public AboutAppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_app, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView linkedin = view.findViewById(R.id.linkedIn);
        linkedin.setMovementMethod(LinkMovementMethod.getInstance());


        TextView gitgub = view.findViewById(R.id.github);
        gitgub.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
