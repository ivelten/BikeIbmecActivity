package com.example.bikeibmec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class PedaladasActivity extends BaseActivity implements OnMapReadyCallback {

    private ListView listView;
    private MapView mapView;
    private Marker currentStartMarker;
    private Marker currentEndMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedaladas);

        listView = findViewById(R.id.listView);
        mapView = findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        setHomeButton();
    }

    void setPedaladas(PedaladasModel pedaladasModel) {

        if (pedaladasModel == null) {
            List<PedaladaModel> pedaladas = new ArrayList<>();
            Random random = new Random();

            double startLat = -22.950857855820743;
            double startLon = -43.18409697374796;

            double endLat = -22.934175183472234;
            double endLon = -43.17798824716919;

            for (int day = -5; day <= 0; day++) {
                Calendar start = Calendar.getInstance();

                start.add(Calendar.HOUR, day * 24);
                start.add(Calendar.MINUTE, random.nextInt(10));

                Calendar end = Calendar.getInstance();

                end.add(Calendar.HOUR, day * 24);
                end.add(Calendar.MINUTE, random.nextInt(120) + 50);

                pedaladas.add(new PedaladaModel(
                        startLat + day * 0.003,
                        startLon + day * 0.003,
                        endLat + day * 0.003,
                        endLon + day * 0.003,
                        start.getTime(),
                        end.getTime()));
            }

            Calendar now = Calendar.getInstance();

            pedaladasModel = new PedaladasModel(pedaladas);
        }

        ArrayAdapter<PedaladaModel> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pedaladasModel.getPedaladas());

        listView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent intent = getIntent();
        Bundle intentExtras = intent.getExtras();

        PedaladasModel pedaladasModel = null;

        if (intentExtras != null) {
            pedaladasModel = (PedaladasModel)intentExtras.get(PedaladasModel.ID);
        }

        setPedaladas(pedaladasModel);

        listView.setClickable(true);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            PedaladaModel pedaladaModel = (PedaladaModel)listView.getItemAtPosition(i);

            LatLng start = new LatLng(pedaladaModel.getStartLat(), pedaladaModel.getStartLon());
            LatLng end = new LatLng(pedaladaModel.getEndLat(), pedaladaModel.getEndLon());

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(start);
            builder.include(end);
            LatLngBounds bounds = builder.build();

            if (currentStartMarker != null) {
                currentStartMarker.remove();
            }

            if (currentEndMarker != null) {
                currentEndMarker.remove();
            }

            currentStartMarker = googleMap.addMarker(new MarkerOptions().position(start).title("Início"));
            currentEndMarker = googleMap.addMarker(new MarkerOptions().position(end).title("Fim"));

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 200);
            googleMap.animateCamera(cameraUpdate);
        });
    }
}