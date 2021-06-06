package com.example.vaichover.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.vaichover.R;
import com.example.vaichover.adapter.ClimaAdapter;
import com.example.vaichover.service.WeatherService;
import com.example.vaichover.model.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<WeatherResponse> climas;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private String LIST_KEY = "CITY_LIST";
    private EditText editTextCidade;
    private ImageButton imageButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        setViews();

        sharedPreferences = this.getSharedPreferences(LIST_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        climas = getList();

        recyclerView = findViewById(R.id.recycler_view_cidades);
        ClimaAdapter adapter = new ClimaAdapter(climas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CityListActivity.this));

        WeatherService weatherService = new WeatherService();



        imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<WeatherResponse> call = weatherService.consultarApiCidade(editTextCidade.getText().toString());


                call.enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                        adapter.addItem(response.body());

                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        Toast.makeText(CityListActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                editTextCidade.setText("");
            }
        });
    }

    private void setViews() {
        editTextCidade = findViewById(R.id.editTextCidade);
        imageButtonAdd = findViewById(R.id.imageButtonAdd);
    }

    private <T> void setList(String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }

    private static void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    private List<WeatherResponse> getList(){
        List<WeatherResponse> arrayItems;
        String serializedObject = sharedPreferences.getString(LIST_KEY, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<WeatherResponse>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);

            return arrayItems;
        }
        return new ArrayList<WeatherResponse>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setList(LIST_KEY, climas);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}