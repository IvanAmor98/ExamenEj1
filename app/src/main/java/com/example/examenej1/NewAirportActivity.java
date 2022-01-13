package com.example.examenej1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class NewAirportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_airport);
    }

    public void saveAirport(View v) {
        AirportDAO airportDAO = new AirportDAO(this);
        try {
            airportDAO.saveAirport(new AirportDTO(
                    ((TextView)findViewById(R.id.nameInput)).getText().toString(),
                    new long[]{
                            Long.parseLong(((TextView)findViewById(R.id.latInput)).getText().toString()),
                            Long.parseLong(((TextView)findViewById(R.id.lngInput)).getText().toString()),
                    },
                    new File(((TextView)findViewById(R.id.shieldInput)).getText().toString())
            ));
            finish();
        } catch(Exception e) {
            Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}