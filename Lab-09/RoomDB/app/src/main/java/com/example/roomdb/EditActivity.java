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

public class EditActivity extends AppCompatActivity {
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnUpdate;
    private final String REQUIRE = "Require";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        btnUpdate = findViewById(R.id.btnUpdate);
        etFirstName.setText(GlobalData.getInstance().getCurrentPerson().getFirstName());
        etLastName.setText(GlobalData.getInstance().getCurrentPerson().getLastName());

        btnUpdate.setOnClickListener(v -> {
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
            GlobalData.getInstance().getCurrentPerson().setFirstName(etFirstName.getText().toString());
            GlobalData.getInstance().getCurrentPerson().setLastName(etLastName.getText().toString());
            GlobalData.getInstance().update(GlobalData.getInstance().getCurrentPerson());
            Intent intent = new Intent(EditActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}