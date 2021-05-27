package com.example.vaichover.controller;

import com.example.vaichover.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherService {

    @GET("weather?q=salvador&appid=ff38d6ed505acc0b54cf1bf742b4818b&units=metric")
    Call<WeatherResponse> recuperarClima();

}
