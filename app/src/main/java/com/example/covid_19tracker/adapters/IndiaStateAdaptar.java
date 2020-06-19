package com.example.covid_19tracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19tracker.models.IndiaStateData;
import com.example.covid_19tracker.R;

import java.util.ArrayList;
import java.util.List;

public class IndiaStateAdaptar extends RecyclerView.Adapter<IndiaStateAdaptar.StateViewHolder> {

    private List<IndiaStateData> stateDataList;
    private Context mContext;


    public IndiaStateAdaptar(Context mContext) {
        this.mContext = mContext;
        stateDataList = new ArrayList<>();

    }

    public void setStateDataList(List<IndiaStateData> stateDataList) {


        this.stateDataList = stateDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new StateViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.state_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {


        holder.stateName.setText(stateDataList.get(position).getStateName());
        holder.totalCase.setText(stateDataList.get(position).getTotalCase());
        holder.activeCase.setText(stateDataList.get(position).getActiveCase());
        holder.recovered.setText(stateDataList.get(position).getTotalRecovered());


    }


    @Override
    public int getItemCount() {

        return 7;
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {

        TextView totalCase, activeCase, todayCase, recovered, totalDeaths, deathsToday, stateName, updated;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);

            totalCase = (TextView) itemView.findViewById(R.id.tvItemTotalCase);
            activeCase = (TextView) itemView.findViewById(R.id.tvItemActiveCase);
            todayCase = (TextView) itemView.findViewById(R.id.tvItemTodayCase);
            recovered = (TextView) itemView.findViewById(R.id.tvItemRecovered);
            totalDeaths = (TextView) itemView.findViewById(R.id.tvItemTotalDeath);
            deathsToday = (TextView) itemView.findViewById(R.id.tvItemDeathToday);
            stateName = (TextView) itemView.findViewById(R.id.tvItemIndStateName);
            updated = (TextView) itemView.findViewById(R.id.tvItemIndStateUpdated);

        }


    }

}
