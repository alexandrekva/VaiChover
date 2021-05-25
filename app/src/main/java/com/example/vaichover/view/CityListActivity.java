package com.example.vaichover.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.vaichover.R;

public class CityListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}