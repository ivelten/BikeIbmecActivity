package com.example.bikeibmec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

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

public class AgendamentosActivity extends BaseActivity implements OnMapReadyCallback {

    private MapView mapView;
    private ListView listView;
    private Marker currentStartMarker;
    private Marker currentEndMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos);

        mapView = findViewById(R.id.agendamentosMapView);
        listView = findViewById(R.id.agendamentosListView);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        setHomeButton();
    }

    void setAgendamentos(AgendamentosModel agendamentosModel) {

        if (agendamentosModel == null) {
            List<AgendamentoModel> agendamentos = new ArrayList<>();
            Random random = new Random();

            double startLat = -22.950857855820743;
            double startLon = -43.18409697374796;

            double endLat = -22.934175183472234;
            double endLon = -43.17798824716919;

            for (int day = 0; day <= 5; day++) {
                Calendar start = Calendar.getInstance();

                start.add(Calendar.HOUR, day * 24);
                start.add(Calendar.MINUTE, random.nextInt(10));

                agendamentos.add(new AgendamentoModel(
                        start.getTime(),
                        2,
                        new LatLng(startLat - day * 0.003, startLon - day * 0.003),
                        new LatLng(endLat + -day * 0.003, endLon - day * 0.003)));
            }

            agendamentosModel = new AgendamentosModel(agendamentos);
        }

        ArrayAdapter<AgendamentoModel> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                agendamentosModel.getAgendamentos());

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

        AgendamentosModel agendamentosModel = null;

        if (intentExtras != null) {
            agendamentosModel = (AgendamentosModel)intentExtras.get(PedaladasModel.ID);
        }

        setAgendamentos(agendamentosModel);

        listView.setClickable(true);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            AgendamentoModel agendamentoModel = (AgendamentoModel)listView.getItemAtPosition(i);

            LatLng start = agendamentoModel.getLocalRetirada();
            LatLng end = agendamentoModel.getLocalEntrega();

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