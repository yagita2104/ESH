package com.yagita.esh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yagita.esh.R;
import com.yagita.esh.model.Statistic;

import java.util.List;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.ViewHolder> {
    List list;
    Context context;

    public StatisticAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_statistic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Statistic statistic = (Statistic) list.get(position);
        holder.txtStatistic.setText(statistic.getStatis());
        holder.txtUnit.setText(statistic.getUnit());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtStatistic, txtUnit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStatistic = itemView.findViewById(R.id.txtStatistic);
            txtUnit = itemView.findViewById(R.id.txtUnit);

        }
    }
}
