package com.example.summarize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.summarize.foodanddrink.FoodAndDrinkActivity;
import com.example.summarize.lifecycle.LifeCycleActivity;
import com.example.summarize.passdata.PassData;

public class MainActivity extends AppCompatActivity {

    Button foodAndDrink, lifeCycle, passData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        foodAndDrink = findViewById(R.id.foodAndDrink);
        lifeCycle = findViewById(R.id.lifeCycle);
        passData = findViewById(R.id.passData);

        foodAndDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodAndDrinkActivity.class);
                startActivity(intent);
            }
        });

        lifeCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LifeCycleActivity.class);
                startActivity(intent);
            }
        });

        passData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PassData.class);
                startActivity(intent);
            }
        });
    }
}