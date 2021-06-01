package com.example.vaichover.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.vaichover.R;
import com.example.vaichover.controller.OpenWeatherController;
import com.example.vaichover.model.WeatherResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    private TextView textViewClima, textViewHumidade, textViewTempMax, textViewTempMin, textViewCidadeTemp, textViewClimaDescricao;
    private ImageView climaIconeImageView;
    private FloatingActionButton adicionarCidadeFAB;
    private WeatherResponse weatherResponse =  new WeatherResponse();

    private OpenWeatherController openWeatherController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        inicializarViews();
        openWeatherController = new OpenWeatherController();


        Call<WeatherResponse> call = openWeatherController.consultarApiCidade("salvador");


        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                weatherResponse = response.body();

                textViewClimaDescricao.setText(weatherResponse.getWeather().get(0).getDescription());
                textViewCidadeTemp.setText(weatherResponse.getName() );

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });




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
        textViewClima = findViewById(R.id.clima_text_view);
        textViewHumidade = findViewById(R.id.humidade_text_view);
        textViewTempMax = findViewById(R.id.temperatura_maxima_text_view);
        textViewTempMin = findViewById(R.id.temperatura_minima_text_view);
        textViewCidadeTemp = findViewById(R.id.cidade_temperatura_text_view);
        textViewClimaDescricao = findViewById(R.id.clima_descricao_text_view);

        climaIconeImageView = findViewById(R.id.clima_icone_image_view);

        adicionarCidadeFAB = findViewById(R.id.adicionar_cidade_fab);
    }
}