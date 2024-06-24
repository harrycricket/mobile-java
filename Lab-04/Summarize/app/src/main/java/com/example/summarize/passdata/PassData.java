package com.example.summarize.passdata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.summarize.R;

public class PassData extends AppCompatActivity {

    private Button btnString;
    private Button btnNumber;
    private Button btnArray;
    private Button btnObject;
    private Button btnBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pass_data);

        btnString = findViewById(R.id.btnString);
        btnNumber = findViewById(R.id.btnNumber);
        btnArray = findViewById(R.id.btnArray);
        btnObject = findViewById(R.id.btnObject);
        btnBundle = findViewById(R.id.btnBundle);

        // create data
        String string = "Harry is coming";
        int number = 3006;
        String[] array = {"NextJS", "NodeJS", "ReactJS", "Tailwind", "React Native"};
        Dog dog = new Dog("Buck", "Yellow", 3);

        btnString.setOnClickListener(v -> {
            Intent intent = new Intent(PassData.this, StringActivity.class);
            intent.putExtra("data", string);
            startActivity(intent);
        });
        btnNumber.setOnClickListener(v -> {
            Intent intent = new Intent(PassData.this, NumberActivity.class);
            intent.putExtra("data", number);
            startActivity(intent);
        });
        btnArray.setOnClickListener(v -> {
            Intent intent = new Intent(PassData.this, ArrayActivity.class);
            intent.putExtra("data", array);
            startActivity(intent);
        });
        btnObject.setOnClickListener(v -> {
            Intent intent = new Intent(PassData.this, ObjectActivity.class);
            intent.putExtra("data", dog);
            startActivity(intent);
        });

        btnBundle.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("string", string);
            bundle.putInt("number", number);
            bundle.putStringArray("stringArray", array);
            bundle.putSerializable("object", dog);

            Intent intent = new Intent(PassData.this, BundleActivity.class);
            intent.putExtra("data", bundle);
            startActivity(intent);
        });
    }
}