package com.example.emergencyserviceline;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StateEmergencyActivity extends AppCompatActivity {

    TextView tvState, tvPolice, tvFire, tvAmbulance, tvWomen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_emergency);

        tvState = findViewById(R.id.tvStateName);
        tvPolice = findViewById(R.id.tvPolice);
        tvFire = findViewById(R.id.tvFire);
        tvAmbulance = findViewById(R.id.tvAmbulance);
        tvWomen = findViewById(R.id.tvWomen);

        // State name show
        String state = getIntent().getStringExtra("state");
        if (state != null) {
            tvState.setText(state);
        }

        // Click → Dial
        tvPolice.setOnClickListener(v -> openDial("100"));
        tvFire.setOnClickListener(v -> openDial("101"));
        tvAmbulance.setOnClickListener(v -> openDial("108"));
        tvWomen.setOnClickListener(v -> openDial("181"));
    }

    private void openDial(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }
}
