package com.example.examenej1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.examenej1.databinding.ActivityMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapBinding binding;
    private AirportDTO point1;
    private AirportDTO point2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        point1 = getIntent().getParcelableExtra("point1");
        point2 = getIntent().getParcelableExtra("point2");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings settings = mMap.getUiSettings();
        settings.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng marker1 = new LatLng(point1.getPosition()[0], point1.getPosition()[1]);
        LatLng marker2 = new LatLng(point2.getPosition()[0], point2.getPosition()[1]);
        mMap.addMarker(new MarkerOptions().position(marker1).title(point1.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker1, 5));

        mMap.addMarker(new MarkerOptions().position(marker2).title(point2.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(false)
                .add(marker1, marker2));
    }
}