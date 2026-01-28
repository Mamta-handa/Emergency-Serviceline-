package com.example.emergencyserviceline;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvSignup, tvAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tvSignup);
        tvAdminLogin = findViewById(R.id.tvAdminLogin);

        // 🔹 USER LOGIN (SIMPLE)
        btnLogin.setOnClickListener(v -> {

            if (TextUtils.isEmpty(etEmail.getText())) {
                etEmail.setError("Enter Email");
                return;
            }

            if (TextUtils.isEmpty(etPassword.getText())) {
                etPassword.setError("Enter Password");
                return;
            }

            Toast.makeText(LoginActivity.this,
                    "Login Successful",
                    Toast.LENGTH_SHORT).show();

            // Direct Home
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        });

        // 🔹 SIGN UP
        tvSignup.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        // 🔹 ADMIN LOGIN
        tvAdminLogin.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, AdminLoginActivity.class)));
    }
}
