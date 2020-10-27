package android.teste.lucasvegi.exemploobservermvc.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.teste.lucasvegi.exemploobservermvc.R;
import android.teste.lucasvegi.exemploobservermvc.model.Localizacao;
import android.teste.lucasvegi.exemploobservermvc.util.LocationServices;
import android.teste.lucasvegi.exemploobservermvc.util.RequestPermissions;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    LocationServices locationServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //solicita permissão para location
        //já começa obter localização se tiver permissão
        if(RequestPermissions.requestLocationPermission(MainActivity.this,this))
            locationServices = new LocationServices(2000, 0, new Localizacao(), this);
    }

    @Override
    protected void onDestroy() {
        locationServices.pararRastreamento();
        super.onDestroy();
    }

    //trata resposta do usuário para permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissions.LOCATION_PERMISSION: {
                // Se a requisição é cancelada, um array vazio é retornado
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    // permissão foi concedida. Esse ponto deve conter a ação a ser feita neste momento
                    locationServices = new LocationServices(2000, 0, new Localizacao(), this);

                    Log.i("PERMISSION","Deu a permissão");

                } else {
                    // permissão não foi concedida pelo usuário. Desabilitar recursos que dependem dela
                    Log.i("PERMISSION","Não permitiu");
                }
                return;
            }
        }
    }

    public void observerM(View view) {
        Intent it = new Intent(this,ActivityObserverMetros.class);
        startActivity(it);
    }

    public void observerKm(View view) {
        Intent it = new Intent(this,ActivityObserverQuilometros.class);
        startActivity(it);
    }
}