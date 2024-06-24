package com.example.summarize;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText username;
    private TextInputEditText password;
    private TextInputEditText confirm;
    private TextView login;
    private Button signUp;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.usr);
        password = findViewById(R.id.pw);
        confirm = findViewById(R.id.confirmPw);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);

//      Register event
        login.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    private boolean checkInput() {
        if(TextUtils.isEmpty(username.getText().toString())) {
            username.setError(REQUIRE);
            return false;
        }

        if(TextUtils.isEmpty(password.getText().toString())) {
            password.setError(REQUIRE);
            return false;
        }

        if(TextUtils.isEmpty(confirm.getText().toString())) {
            confirm.setError(REQUIRE);
            return false;
        }

        if(!TextUtils.equals(password.getText().toString(), confirm.getText().toString())) {
            Toast.makeText(this, "Password are not match", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public void signUp() {
        if(!checkInput()) {
            return;
        }
        Toast.makeText(this, "Created an account successfully", Toast.LENGTH_LONG).show();
        signIn();
    }

    private void signIn() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signUp) {
            signUp();
        } else if (v.getId() == R.id.login) {
            signIn();
        }
    }
}