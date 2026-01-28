package com.example.emergencyserviceline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    LinearLayout layoutPolice, layoutFire, layoutAmbulance, layoutWomen, layoutState;
    TextView tvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find views
        layoutPolice = findViewById(R.id.layoutPolice);
        layoutFire = findViewById(R.id.layoutFire);
        layoutAmbulance = findViewById(R.id.layoutAmbulance);
        layoutWomen = findViewById(R.id.layoutWomen);
        layoutState = findViewById(R.id.layoutState);
        tvProfile = findViewById(R.id.tvProfile);

        // National numbers (temporary toast)
        layoutPolice.setOnClickListener(v ->
                Toast.makeText(this, "Police : 100", Toast.LENGTH_SHORT).show());

        layoutFire.setOnClickListener(v ->
                Toast.makeText(this, "Fire : 101", Toast.LENGTH_SHORT).show());

        layoutAmbulance.setOnClickListener(v ->
                Toast.makeText(this, "Ambulance : 108", Toast.LENGTH_SHORT).show());

        layoutWomen.setOnClickListener(v ->
                Toast.makeText(this, "Women Helpline : 181", Toast.LENGTH_SHORT).show());

        // State-wise emergency
        layoutState.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, StateListActivity.class));
        });

        // ✅ Profile page open
        tvProfile.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
        });
    }
}
