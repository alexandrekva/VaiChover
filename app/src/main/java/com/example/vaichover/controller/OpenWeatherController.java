package com.example.vaichover.controller;

import com.example.vaichover.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OpenWeatherController {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public WeatherResponse weather = new WeatherResponse();



    public Call consultarApiCidade(String cidade) {

        WeatherService weatherService = retrofit.create( WeatherService.class );
        Call<WeatherResponse> call = weatherService.recuperarClimaCidade(cidade, "ff38d6ed505acc0b54cf1bf742b4818b", "metric");

        return call;
    }

}

