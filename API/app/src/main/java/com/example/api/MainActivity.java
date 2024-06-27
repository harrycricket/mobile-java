package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.adapter.TraineeAdapter;
import com.example.api.model.Trainee;
import com.example.api.service.TraineeAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TraineeAdapter adapter;
    private RecyclerView recyclerView;
    private List<Trainee> list;
    private Button btnNew;
    private final String REQUIRE = "Require";
    private TraineeAPIService traineeAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        btnNew = (Button) findViewById(R.id.btnNew);

        // init data
        init();
    }

    private void init() {
        traineeAPIService = new TraineeAPIService();
        initEventListeners();
        loadListToUI();
    }

    private void loadListToUI() {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (adapter == null) {
            adapter = new TraineeAdapter(MainActivity.this, list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
        list.clear();
        Toast.makeText(MainActivity.this, "Waiting for loading...", Toast.LENGTH_SHORT).show();

        traineeAPIService.getAll(new Callback<Trainee[]>() {
            @Override
            public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                if (response.isSuccessful()) {
                    Trainee[] trainees = response.body();
                    if (trainees != null) {
                        for (Trainee trainee : trainees) {
                            list.add(trainee);
                            Log.d(" : " + trainee.getId() , trainee.getName());
                        }
                    }
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Loaded " + list.size() + " items successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Trainee[]> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEventListeners() {
        btnNew.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SaveActivity.class);
            startActivity(intent);
        });
    }
}