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

public class BundleActivity extends AppCompatActivity {

    private TextView txtString;
    private TextView txtNumber;
    private TextView txtArray;
    private TextView txtObject;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bundle);

        txtString = findViewById(R.id.contentString);
        txtNumber= findViewById(R.id.contentNumber);
        txtArray = findViewById(R.id.contentArray);
        txtObject = findViewById(R.id.contentObject);
        btnBack = findViewById(R.id.button);

        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        if (data != null) {
            // string
            String string = data.getString("string");
            txtString.setText(string);
            int number = data.getInt("number", 0);
            txtNumber.setText("" + number);
            String message = "Elements of array are: \n\n";
            for(String str : data.getStringArray("stringArray"))
                message = message + str + "\n";
            txtArray.setText(message);
            Dog dog = (Dog) data.getSerializable("object");
            txtObject.setText(dog.toString());
        }


        btnBack.setOnClickListener(v -> {
            Intent intentToMain = new Intent(BundleActivity.this, PassData.class);
            startActivity(intentToMain);
        });
    }
}