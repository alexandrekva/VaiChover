package com.example.vaichover.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaichover.R;
import com.example.vaichover.controller.OpenWeatherController;
import com.example.vaichover.model.WeatherResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    private TextView textViewClima, textViewHumidade, textViewTempMax, textViewTempMin, textViewCidadeTemp, textViewClimaDescricao;
    private ImageView climaIconeImageView;
    private FloatingActionButton adicionarCidadeFAB;
    private WeatherResponse weatherResponse =  new WeatherResponse();
    private FusedLocationProviderClient fusedLocationProviderClient;
    private OpenWeatherController openWeatherController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        inicializarViews();
        openWeatherController = new OpenWeatherController();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions( WeatherActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


        adicionarCidadeFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListaCidades();
            }
        });

    }

    private void activityListaCidades(){
        Intent intent = new Intent(WeatherActivity.this, CityListActivity.class);
        startActivity(intent);
    }

    private void inicializarViews(){
        textViewHumidade = findViewById(R.id.humidade_text_view);
        textViewTempMax = findViewById(R.id.temperatura_maxima_text_view);
        textViewTempMin = findViewById(R.id.temperatura_minima_text_view);
        textViewCidadeTemp = findViewById(R.id.cidade_temperatura_text_view);
        textViewClimaDescricao = findViewById(R.id.clima_descricao_text_view);

        climaIconeImageView = findViewById(R.id.clima_icone_image_view);

        adicionarCidadeFAB = findViewById(R.id.adicionar_cidade_fab);
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Location> task) {
                Location location = task.getResult();
                Double lat = location.getLatitude();
                Double lon = location.getLongitude();

                getWeatherResponse(lat, lon);

            }
        });

    }

    private void getWeatherResponse(Double lat, Double lon) {

        Call<WeatherResponse> call = openWeatherController.consultarApiCordenadas(lat, lon);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                weatherResponse = response.body();
                setWeather();
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(WeatherActivity.this, "Consulta a API falhou!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setWeather() {

        textViewHumidade.setText(weatherResponse.getMain().getHumidity().toString() + "%");
        textViewTempMin.setText(weatherResponse.getMain().getTemp_min().toString() + "º");
        textViewTempMax.setText(weatherResponse.getMain().getTemp_max().toString() + "º");
        textViewCidadeTemp.setText(weatherResponse.getName() + ", " + weatherResponse.getMain().getTemp() + " graus.");
        textViewClimaDescricao.setText(weatherResponse.getWeather().get(0).getDescription());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 44) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(WeatherActivity.this, "É necessário acesso ao GPS para o funcionamento do app.", Toast.LENGTH_LONG).show();
            }
        }
    }
}