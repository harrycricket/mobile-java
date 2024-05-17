package com.example.calculator;

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

public class MainActivity extends AppCompatActivity {
    private boolean isValidNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button addition = (Button) findViewById(R.id.addition);
        Button subtraction = (Button) findViewById(R.id.subtraction);
        Button multiplication = (Button) findViewById(R.id.multiplication);
        Button division = (Button) findViewById(R.id.division);

        TextInputEditText first = (TextInputEditText) findViewById(R.id.first);
        TextInputEditText second = (TextInputEditText) findViewById(R.id.second);

        TextView finalResult = (TextView) findViewById(R.id.result);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstString = first.getText().toString();
                String secondString = second.getText().toString();

//                 check type of input (must be number)
                if (!isValidNumber(firstString) || !isValidNumber(secondString)) {
                    Toast.makeText(getApplicationContext(), "Min and Max value must be number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int firstNumber = Integer.parseInt(firstString);
                int secondNumber = Integer.parseInt(secondString);
                int value = firstNumber + secondNumber;
                finalResult.setText(String.valueOf(value));
            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstString = first.getText().toString();
                String secondString = second.getText().toString();

                // check type of input (must be number)
                if (!isValidNumber(firstString) || !isValidNumber(secondString)) {
                    Toast.makeText(getApplicationContext(), "The value must be number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int firstNumber = Integer.parseInt(firstString);
                int secondNumber = Integer.parseInt(secondString);
                int value = firstNumber - secondNumber;
                finalResult.setText(String.valueOf(value));
            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstString = first.getText().toString();
                String secondString = second.getText().toString();

                // check type of input (must be number)
                if (!isValidNumber(firstString) || !isValidNumber(secondString)) {
                    Toast.makeText(getApplicationContext(), "The value must be number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int firstNumber = Integer.parseInt(firstString);
                int secondNumber = Integer.parseInt(secondString);
                int value = firstNumber * secondNumber;
                finalResult.setText(String.valueOf(value));
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstString = first.getText().toString();
                String secondString = second.getText().toString();

                // check type of input (must be number)
                if (!isValidNumber(firstString) || !isValidNumber(secondString)) {
                    Toast.makeText(getApplicationContext(), "The value must be number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double firstNumber = Double.parseDouble(firstString);
                double secondNumber = Double.parseDouble(secondString);

                if (secondNumber == 0) {
                    // Handle division by zero error
                    Toast.makeText(getApplicationContext(), "Can not be divided by zero!", Toast.LENGTH_SHORT).show();
                } else {
                    double value = firstNumber / secondNumber;
                    String formattedResult = String.format("%.2f", value);
                    finalResult.setText(formattedResult);
                }
            }
        });
    }
}