package com.example.covid_19tracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19tracker.models.IndiaStateData;
import com.example.covid_19tracker.R;

import java.util.ArrayList;
import java.util.List;

public class AllStateAdaptar extends RecyclerView.Adapter<AllStateAdaptar.StateViewHolder> implements Filterable {

    private List<IndiaStateData> stateDataList;
    private List<IndiaStateData> allStateDataList;
    private Context mContext;
    private IndiaStateAdaptarInterface mInterface;

    public AllStateAdaptar(Context mContext) {
        this.mContext = mContext;
        stateDataList = new ArrayList<>();

    }

    public void setmInterface(IndiaStateAdaptarInterface mInterface) {
        this.mInterface = mInterface;
    }

    public void setStateDataList(List<IndiaStateData> stateDataList) {


        this.stateDataList = stateDataList;
        allStateDataList = new ArrayList<>(stateDataList);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            return new StateViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.state_item, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {


            holder.stateName.setText(stateDataList.get(position).getStateName());
            holder.totalCase.setText(stateDataList.get(position).getTotalCase());
            holder.activeCase.setText(stateDataList.get(position).getActiveCase());
            holder.recovered.setText(stateDataList.get(position).getTotalRecovered());
            holder.totalDeaths.setText(stateDataList.get(position).getTotalDeaths());
            holder.deathsToday.setText(stateDataList.get(position).getTodayDeaths());
            holder.todayCase.setText(stateDataList.get(position).getTodayCase());
            holder.updated.setText(stateDataList.get(position).getLastUpdated());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mInterface.OnRecyclerItemClickListener(position);
                }
            });


    }


    @Override
    public int getItemCount() {

        if (stateDataList != null && stateDataList.size() > 0)
            return stateDataList.size();
        else
            return 0;

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

    public interface IndiaStateAdaptarInterface {

        void OnRecyclerItemClickListener(int position);

    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<IndiaStateData> filteredData = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredData.addAll(allStateDataList);
            } else {
                String filterInputText = constraint.toString().toLowerCase().trim();

                for (IndiaStateData data : allStateDataList) {
                    if (data.getStateName().toLowerCase().contains(filterInputText)) {
                        filteredData.add(data);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredData;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            stateDataList.clear();
            stateDataList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
