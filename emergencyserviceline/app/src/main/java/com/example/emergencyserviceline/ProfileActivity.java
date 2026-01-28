package com.example.emergencyserviceline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tvMobile, tvState, tvCountry, tvAge, tvGender;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvMobile = findViewById(R.id.tvMobile);
        tvState = findViewById(R.id.tvState);
        tvCountry = findViewById(R.id.tvCountry);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);
        btnLogout = findViewById(R.id.btnLogout);

        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);

        tvName.setText("Name : " + sp.getString("name", "NA"));
        tvEmail.setText("Email : " + sp.getString("email", "NA"));
        tvMobile.setText("Mobile : " + sp.getString("mobile", "NA"));
        tvState.setText("State : " + sp.getString("state", "NA"));
        tvCountry.setText("Country : " + sp.getString("country", "NA"));
        tvAge.setText("Age : " + sp.getString("age", "NA"));
        tvGender.setText("Gender : " + sp.getString("gender", "NA"));

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
