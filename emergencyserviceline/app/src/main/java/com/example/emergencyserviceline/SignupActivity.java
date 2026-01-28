package com.example.emergencyserviceline;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etMobile, etState, etCountry, etAge;
    RadioGroup rgGender;
    Button btnSignup;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etMobile = findViewById(R.id.etMobile);
        etState = findViewById(R.id.etState);
        etCountry = findViewById(R.id.etCountry);
        etAge = findViewById(R.id.etAge);
        rgGender = findViewById(R.id.rgGender);
        btnSignup = findViewById(R.id.btnSignup);

        // DBHelper
        dbHelper = new DBHelper(this);

        btnSignup.setOnClickListener(v -> {

            // Validations
            if (TextUtils.isEmpty(etName.getText())) {
                etName.setError("Enter Name");
                return;
            }

            if (TextUtils.isEmpty(etEmail.getText())) {
                etEmail.setError("Enter Email");
                return;
            }

            if (TextUtils.isEmpty(etPassword.getText())) {
                etPassword.setError("Enter Password");
                return;
            }

            if (TextUtils.isEmpty(etMobile.getText())) {
                etMobile.setError("Enter Mobile");
                return;
            }

            if (rgGender.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get gender
            RadioButton rb = findViewById(rgGender.getCheckedRadioButtonId());
            String gender = rb.getText().toString();

            // Date & Time
            String createdAt = java.text.DateFormat
                    .getDateTimeInstance()
                    .format(new java.util.Date());

            // Insert into SQLite
            boolean inserted = dbHelper.insertUser(
                    etName.getText().toString().trim(),
                    etEmail.getText().toString().trim(),
                    etPassword.getText().toString().trim(),
                    etMobile.getText().toString().trim(),
                    etState.getText().toString().trim(),
                    etCountry.getText().toString().trim(),
                    etAge.getText().toString().trim(),
                    gender,
                    createdAt
            );

            if (inserted) {

                // ✅ SAVE DATA FOR PROFILE (SharedPreferences)
                SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putString("name", etName.getText().toString().trim());
                editor.putString("email", etEmail.getText().toString().trim());
                editor.putString("mobile", etMobile.getText().toString().trim());
                editor.putString("state", etState.getText().toString().trim());
                editor.putString("country", etCountry.getText().toString().trim());
                editor.putString("age", etAge.getText().toString().trim());
                editor.putString("gender", gender);

                editor.apply();

                Toast.makeText(this, "Signup Successful", Toast.LENGTH_LONG).show();
                finish(); // back to Login

            } else {
                Toast.makeText(this, "Signup Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
