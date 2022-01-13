package com.example.examenej1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class DrawRouteActivity extends AppCompatActivity {

    Spinner spinnerFrom;
    Spinner spinnerTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_route);

        AirportDAO airportDAO = new AirportDAO(this);
        ArrayList<AirportDTO> airportDTOList = airportDAO.listAirports();

        AirportListAdatper airportListAdatper = new AirportListAdatper(this, R.layout.spinner_row, airportDTOList);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerFrom.setAdapter(airportListAdatper);

        spinnerTo = findViewById(R.id.spinnerTo);
        spinnerTo.setAdapter(airportListAdatper);
    }

    public void drawRoute(View v) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("point1", ((AirportDTO)spinnerFrom.getSelectedItem()));
        intent.putExtra("point2", ((AirportDTO)spinnerTo.getSelectedItem()));
        startActivity(intent);
        finish();
    }
}