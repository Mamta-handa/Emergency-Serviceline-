package com.example.emergencyserviceline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class StateListActivity extends AppCompatActivity {

    ListView listView;

    // India ke sab states
    String[] states = {
            "Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh",
            "Goa","Gujarat","Haryana","Himachal Pradesh","Jharkhand",
            "Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur",
            "Meghalaya","Mizoram","Nagaland","Odisha","Punjab",
            "Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura",
            "Uttar Pradesh","Uttarakhand","West Bengal"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);

        listView = findViewById(R.id.listViewStates);

        // Simple list adapter
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        states);

        listView.setAdapter(adapter);

        // State click
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedState = states[position];

            Intent i = new Intent(StateListActivity.this,
                    StateEmergencyActivity.class);
            i.putExtra("state", selectedState);
            startActivity(i);
        });
    }
}
