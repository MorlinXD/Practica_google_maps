package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity
implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    GoogleMap mapa;
    ArrayList <LatLng> puntos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        puntos = new ArrayList<>();


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setOnMapClickListener(this);
        CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(new LatLng(-1.0126081008669103, -79.46949956799287), 16
        );
        mapa.moveCamera(camUpd1);
        PolylineOptions lineas = new PolylineOptions().add(new LatLng(45.0, -12.0))
                .add(new LatLng(45.0, 5.0))
                .add(new LatLng(34.5, 5.0))
                .add(new LatLng(34.5, -12.0))
                .add(new LatLng(45.0, -12.0));
        lineas.width(8);
        lineas.color(Color.RED);
        mapa.addPolyline(lineas);

    }

    public void ConfigMap(View view) {
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
    }
    public void MoveMap(View view) {
        CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(new LatLng(-1.012900, -79.469452), 20);
        mapa.moveCamera(camUpd1);

    }
    public void TresDMap(View view) {
        LatLng Ciudad = new LatLng(40.417325, -3.683081);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(Ciudad)
                .zoom(19)
                .bearing(45)      //noreste arriba
                .tilt(70)         //punto de vista de la cámara 70 grados
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);

        mapa.animateCamera(camUpd3);

    }
    int contador=0;
    @Override
    public void onMapClick(@NonNull LatLng latLng) {


        LatLng punto = new LatLng(latLng.latitude, latLng.longitude);
        mapa.addMarker(new MarkerOptions().position(punto)
                .title("Punto"));


        /*double lat = punto.latitude;
        double lon = punto.longitude;




        PolylineOptions lineas = new PolylineOptions().add(new LatLng(lat, lon))
                .add(new LatLng(lat, lon+10))
                .add(new LatLng(lat-6, lon+10))
                .add(new LatLng(lat-6, lon))
                .add(new LatLng(lat, lon));
        lineas.width(8);
        lineas.color(Color.RED);
        mapa.addPolyline(lineas);*/
        puntos.add(punto);
        contador++;
        if(contador == 4){
            PolylineOptions lineas = new PolylineOptions()
                    .add(puntos.get(0))
                    .add(puntos.get(1))
                    .add(puntos.get(2))
                    .add(puntos.get(3)).add(puntos.get(0));

            lineas.width(8);
            lineas.color(Color.RED);
            mapa.addPolyline(lineas);
            contador=0;
            puntos.clear();
        }
    }
}