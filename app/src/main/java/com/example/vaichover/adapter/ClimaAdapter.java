package com.example.vaichover.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaichover.R;
import com.example.vaichover.model.WeatherResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ViewHolder> {

    private List<WeatherResponse> climas;

    public ClimaAdapter(List<WeatherResponse>climas) {
        this.climas = climas;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_city_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        WeatherResponse clima = climas.get(position);
    holder.cidade.setText(clima.getName());
    holder.tempMin.setText(clima.getMain().getTemp_min().toString() + "º");
    holder.tempMax.setText(clima.getMain().getTemp_max().toString() + "º");

    }

    @Override
    public int getItemCount() {
        return climas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cidade, tempMin, tempMax;
        private ImageView icone;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            cidade = itemView.findViewById(R.id.item_cidade);
            tempMin = itemView.findViewById(R.id.item_temp_min);
            tempMax = itemView.findViewById(R.id.item_temp_max);
            icone = itemView.findViewById(R.id.item_icone);

        }
    }

    public void addItem(WeatherResponse clima) {
        climas.add(clima);
        notifyDataSetChanged();
    }


}
