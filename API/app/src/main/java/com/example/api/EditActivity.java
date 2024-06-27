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

import com.example.api.data.GlobalService;
import com.example.api.model.Trainee;
import com.example.api.service.TraineeAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    private EditText etName, etPhone, etEmail, etGender;
    private Button btnUpdate;
    private final String REQUIRE = "Require";
    private TraineeAPIService traineeAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        traineeAPIService = new TraineeAPIService();

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etGender = findViewById(R.id.etGender);
        btnUpdate = findViewById(R.id.btnUpdate);

        etName.setText(GlobalService.getInstance().getCurrentTrainee().getName());
        etPhone.setText(GlobalService.getInstance().getCurrentTrainee().getPhone());
        etEmail.setText(GlobalService.getInstance().getCurrentTrainee().getEmail());
        etGender.setText(GlobalService.getInstance().getCurrentTrainee().getGender());

        btnUpdate.setOnClickListener(v -> {
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

            Trainee trainee = GlobalService.getInstance().getCurrentTrainee();
            trainee.setName(name);
            trainee.setPhone(phone);
            trainee.setEmail(email);
            trainee.setGender(gender);

            traineeAPIService.update(trainee.getId(), trainee, new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(EditActivity.this, "Save successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(EditActivity.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(EditActivity.this, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
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