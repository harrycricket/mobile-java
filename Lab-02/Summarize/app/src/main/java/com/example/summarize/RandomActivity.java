package com.example.summarize;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_random);

        TextView result = (TextView) findViewById(R.id.result);
        Button generate = (Button) findViewById(R.id.button);

        TextInputEditText min = (TextInputEditText) findViewById(R.id.min);
        TextInputEditText max = (TextInputEditText) findViewById(R.id.max);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String minString = min.getText().toString();
                String maxString = max.getText().toString();

                // check type of input (must be number)
                if (!isValidNumber(minString) || !isValidNumber(maxString)) {
                    Toast.makeText(getApplicationContext(), "Min and Max value must be number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int minValue = Integer.parseInt(minString);
                int maxValue = Integer.parseInt(maxString);

                if (minValue >= maxValue) {
                    Toast.makeText(getApplicationContext(), "Min value must be less than Max value!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Random random = new Random();
                int value = random.nextInt(maxValue - minValue) + minValue;
                result.setText(String.valueOf(value));
            }
        });
    }

    private boolean isValidNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}