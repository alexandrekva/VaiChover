package com.example.vaichover.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.vaichover.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarClima();
            }
        }, 2000);
    }

    private void mostrarClima(){
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        startActivity(intent);
        finish();
    }
}