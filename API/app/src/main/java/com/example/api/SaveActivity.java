package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.api.model.Trainee;
import com.example.api.service.TraineeAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveActivity extends AppCompatActivity {
    private EditText etName, etPhone, etEmail, etGender;
    private Button btnSave;
    private final String REQUIRE = "Require";
    private TraineeAPIService traineeAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        traineeAPIService = new TraineeAPIService();

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etGender = findViewById(R.id.etGender);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();
            String email = etEmail.getText().toString();
            String gender = etGender.getText().toString();

            if (TextUtils.isEmpty(name)) {
                etName.setError(REQUIRE);
                Toast.makeText(this, "Please enter name!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                etPhone.setError(REQUIRE);
                Toast.makeText(this, "Please enter phone!", Toast.LENGTH_SHORT).show();
                return;
            } else if (!isValidPhone(phone)) {
                etPhone.setError("Invalid phone number!");
                Toast.makeText(this, "Please enter a valid phone number!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(email)) {
                etEmail.setError(REQUIRE);
                Toast.makeText(this, "Please enter email!", Toast.LENGTH_SHORT).show();
                return;
            } else if (!isValidEmail(email)) {
                etEmail.setError("Invalid email address!");
                Toast.makeText(this, "Please enter a valid email address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(gender)) {
                etGender.setError(REQUIRE);
                Toast.makeText(this, "Please enter gender!", Toast.LENGTH_SHORT).show();
                return;
            }

            Trainee trainee = new Trainee(name, email, phone, gender);
            traineeAPIService.create(trainee, new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(SaveActivity.this, "Save successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SaveActivity.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(SaveActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(SaveActivity.this, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SaveActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        });
    }
    private boolean isValidPhone(String phone) {
        String phonePattern = "^[0-9]{10}$";
        return phone.matches(phonePattern);
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}