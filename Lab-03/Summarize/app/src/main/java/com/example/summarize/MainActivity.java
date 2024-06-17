package com.example.summarize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.summarize.football.FootballActivity;
import com.example.summarize.fruitlist.FruitListActivity;
import com.example.summarize.listview.ListViewActivity;

public class MainActivity extends AppCompatActivity {

    Button listView, fruitList, football;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        fruitList = findViewById(R.id.fruitList);
        football = findViewById(R.id.football);

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        fruitList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FruitListActivity.class);
                startActivity(intent);
            }
        });

        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FootballActivity.class);
                startActivity(intent);
            }
        });
    }
}