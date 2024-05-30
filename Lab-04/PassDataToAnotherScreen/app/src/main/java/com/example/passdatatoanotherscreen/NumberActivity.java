package com.example.passdatatoanotherscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class NumberActivity extends AppCompatActivity {

    private TextView txtData;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_number);

        txtData = findViewById(R.id.content);
        btnBack = findViewById(R.id.button);

        Intent intent = getIntent();
        int data = intent.getIntExtra("data", 0);
        txtData.setText("" + data);

        btnBack.setOnClickListener(v -> {
            Intent intentToMain = new Intent(NumberActivity.this, MainActivity.class);
            startActivity(intentToMain);
        });
    }
}