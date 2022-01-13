package com.example.examenej1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteAirportActivity extends AppCompatActivity {

    private AirportDAO airportDAO;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_airport);

        airportDAO = new AirportDAO(this);
        ArrayList<AirportDTO> airportDTOList = airportDAO.listAirports();

        AirportListAdatper airportListAdatper = new AirportListAdatper(this, R.layout.spinner_row, airportDTOList);

        spinner = findViewById(R.id.spinnerSelect);
        spinner.setAdapter(airportListAdatper);
    }

    public void deleteAirport(View v) {
        boolean result = airportDAO.deleteAirport(((AirportDTO)spinner.getSelectedItem()).getId());
        if (result) {
            Toast.makeText(this, "Borrado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}