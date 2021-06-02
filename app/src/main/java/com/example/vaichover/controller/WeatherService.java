package com.example.vaichover.controller;

import com.example.vaichover.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<WeatherResponse> recuperarClimaCidade(
            @Query("q") String cidade,
            @Query("appid") String appid,
            @Query("units") String units,
            @Query("lang") String lang
    );

    @GET("weather")
    Call<WeatherResponse> recuperarClimaCordenadas(
            @Query("lat") Double lat,
            @Query("lon") Double lon,
            @Query("appid") String appid,
            @Query("units") String units,
            @Query("lang") String lang
    );



}
