package com.example.lucas.exemplolocalizacao;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ActivityLocalizacao extends Activity implements LocationListener {

    public Criteria criterio;
    public LocationManager lm;
    public boolean hasGPS;
    public String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        PackageManager packageManager = getPackageManager();
        hasGPS = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
        criterio = new Criteria();

        if (hasGPS) {
            criterio.setAccuracy(Criteria.ACCURACY_FINE);
            Log.i("LOCATION", "GPS");
        } else {
            criterio.setAccuracy(Criteria.ACCURACY_COARSE);
            Log.i("LOCATION", "rede");
        }
        //criterio.setAccuracy(Criteria.ACCURACY_FINE);

    }

    @Override
    protected void onStart() {
        super.onStart();

        provider = lm.getBestProvider(criterio, true);
        Log.i("LOCATION", "provedor: " + provider);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(provider, 5000, 0, this);
    }

    @Override
    protected void onDestroy() {
        lm.removeUpdates(this);
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("LOCATION", "Atualizei...");
        if(location != null)
            Toast.makeText(this,"Lat: " + location.getLatitude() + "\nLong: " + location.getLongitude(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
