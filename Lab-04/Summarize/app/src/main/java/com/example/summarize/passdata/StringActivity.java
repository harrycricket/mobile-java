package com.example.summarize.passdata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.summarize.R;

public class StringActivity extends AppCompatActivity {

    private TextView txtData;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_string);

        txtData = findViewById(R.id.content);
        btnBack = findViewById(R.id.button);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        txtData.setText(data);

        btnBack.setOnClickListener(v -> {
            Intent intentToMain = new Intent(StringActivity.this, PassData.class);
            startActivity(intentToMain);
        });
    }
}