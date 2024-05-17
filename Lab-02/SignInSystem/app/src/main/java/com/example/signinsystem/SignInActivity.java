package com.example.signinsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText username;
    private TextInputEditText password;
    private TextView create;
    private Button signIn;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.usr);
        password = findViewById(R.id.pw);
        create = findViewById(R.id.create);
        signIn = findViewById(R.id.signIn);

//      Register event
        create.setOnClickListener(this);
        signIn.setOnClickListener(this);
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

        return true;
    }

    public void signIn() {
        if(!checkInput()) {
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signIn) {
            signIn();
        } else if (v.getId() == R.id.create) {
            signUp();
        }
    }
}
