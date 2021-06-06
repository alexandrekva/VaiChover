package com.example.vaichover.service;

import com.example.vaichover.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherService {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public WeatherResponse weather = new WeatherResponse();



    public Call consultarApiCidade(String cidade) {

        IWeatherService IWeatherService = retrofit.create( IWeatherService.class );
        Call<WeatherResponse> call = IWeatherService.recuperarClimaCidade(
                cidade,
                "ff38d6ed505acc0b54cf1bf742b4818b",
                "metric",
                "pt_br");

        return call;
    }

    public Call consultarApiCordenadas(Double lat, Double lon) {

        IWeatherService IWeatherService = retrofit.create( IWeatherService.class );
        Call<WeatherResponse> call = IWeatherService.recuperarClimaCordenadas(
                lat,
                lon,
                "ff38d6ed505acc0b54cf1bf742b4818b",
                "metric",
                "pt_br");

        return call;
    }

}

