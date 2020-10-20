package com.studio.mapas.lucas.mapasandroidstudio;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaArvores extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    public LatLng paineira_rosa = new LatLng(-20.757977, -42.871534);
    public LatLng ipe_amarelo = new LatLng(-20.755304, -42.870045);
    public LatLng pau_ferro = new LatLng(-20.763371, -42.868818);
    public LatLng sete_casca = new LatLng(-20.758676, -42.872504);
    public LatLng copaiba = new LatLng(-20.769671, -42.875048);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_arvores);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa_arvore)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        map.addMarker(new MarkerOptions()
                .position(paineira_rosa).title("Paineira Rosa")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_p)));

        map.addMarker(new MarkerOptions()
                .position(ipe_amarelo).title("Ipê amarelo")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_p)));

        map.addMarker(new MarkerOptions()
                .position(pau_ferro).title("Pau-ferro")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_p)));

        map.addMarker(new MarkerOptions()
                .position(sete_casca).title("Sete-casca")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_p)));

        map.addMarker(new MarkerOptions()
                .position(copaiba).title("Copaíba")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_p)));

        CameraUpdate c = CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .target(paineira_rosa)
                        .tilt(60) //inclinação da camera
                        .zoom(17)
                        .build());
        map.animateCamera(c);

    }
}
