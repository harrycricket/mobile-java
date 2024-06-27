package com.example.summarize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.summarize.roomdb1.RoomDB1Activity;
import com.example.summarize.roomdb2.RoomDB2Activity;
import com.example.summarize.sqlite.SQLiteActivity;

public class MainActivity extends AppCompatActivity {

    Button sqlite, roomdb, roomdb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sqlite = findViewById(R.id.sqlite);
        roomdb = findViewById(R.id.roomdb);
        roomdb2 = findViewById(R.id.roomdb2);

        sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(intent);
            }
        });

        roomdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoomDB1Activity.class);
                startActivity(intent);
            }
        });

        roomdb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoomDB2Activity.class);
                startActivity(intent);
            }
        });
    }
}