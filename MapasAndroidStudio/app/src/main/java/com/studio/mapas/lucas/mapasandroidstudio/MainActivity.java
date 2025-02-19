package com.studio.mapas.lucas.mapasandroidstudio;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//TODO
public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
//public class MainActivity extends FragmentActivity {
    private GoogleMap map;

    public LatLng vicosa = new LatLng(-20.752946, -42.879097);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtem fragmento de forma síncrona e adiciona marcador
        /*map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMap();
        map.addMarker(new MarkerOptions()
                        .position(vicosa).title("apt viçosa")
                        .snippet("eu morava aqui!")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));*/

        //obtem fragmento de forma assíncrona
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMapAsync(this);

    }

    public void clickVicosa(View v){
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        EditText edtZoom = (EditText) findViewById(R.id.zoom);

        int z = Integer.parseInt(edtZoom.getText().toString());
        //CameraUpdate c = CameraUpdateFactory.newLatLngZoom(vicosa,z);

        CameraUpdate c = CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .target(vicosa)
                        .tilt(60) //inclinação da camera
                        .zoom(z)  //varia de 1 (menor) a 21 (maior)
                        .build());

        map.animateCamera(c);
    }

   @Override
   public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //adiciona marcador ao mapa obtido de forma assíncrona
        map.addMarker(new MarkerOptions()
                .position(vicosa).title("apt viçosa")
                .snippet("eu morava aqui!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }
}
