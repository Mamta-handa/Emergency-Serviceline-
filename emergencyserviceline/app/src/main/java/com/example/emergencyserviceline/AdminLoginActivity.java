package com.example.emergencyserviceline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    EditText etAdminPassword;
    Button btnAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etAdminPassword = findViewById(R.id.etAdminPassword);
        btnAdminLogin = findViewById(R.id.btnAdminLogin);

        btnAdminLogin.setOnClickListener(v -> {
            String pass = etAdminPassword.getText().toString().trim();

            if (pass.equals("1234")) {
                startActivity(new Intent(AdminLoginActivity.this, AdminPanelActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid Admin Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
