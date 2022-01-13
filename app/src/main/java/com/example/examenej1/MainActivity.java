package com.example.examenej1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, 1);
            }
        }
    }

    public void createAirport(View v) {
        Intent intent = new Intent(this, NewAirportActivity.class);
        startActivity(intent);
    }
    public void createRoute(View v) {
        Intent intent = new Intent(this, DrawRouteActivity.class);
        startActivity(intent);
    }

    public void deleteAirport(View v) {
        Intent intent = new Intent(this, DeleteAirportActivity.class);
        startActivity(intent);
    }
}