package com.example.vaichover.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.vaichover.R;
import com.example.vaichover.adapter.ClimaAdapter;
import com.example.vaichover.controller.OpenWeatherController;
import com.example.vaichover.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<WeatherResponse> climas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        recyclerView = findViewById(R.id.recycler_view_cidades);

        OpenWeatherController openWeatherController = new OpenWeatherController();

        Call<WeatherResponse> call = openWeatherController.consultarApiCidade("salvador");
        ClimaAdapter adapter = new ClimaAdapter(climas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CityListActivity.this));

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

            adapter.addItem(response.body());

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}