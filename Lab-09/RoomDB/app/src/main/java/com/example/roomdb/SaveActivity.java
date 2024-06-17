package com.example.roomdb;

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

import com.example.roomdb.data.GlobalData;
import com.example.roomdb.model.Person;

public class SaveActivity extends AppCompatActivity {
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnSave;
    private final String REQUIRE = "Require";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            if (TextUtils.isEmpty(etFirstName.getText().toString())) {
                etFirstName.setError(REQUIRE);
                Toast.makeText(this, "Please enter the first name!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(etLastName.getText().toString())) {
                etLastName.setError(REQUIRE);
                Toast.makeText(this, "Please enter the last name!", Toast.LENGTH_SHORT).show();
                return;
            }
            GlobalData.getInstance().save(new Person(etFirstName.getText().toString(), etLastName.getText().toString()));
            Intent intent = new Intent(SaveActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}