package android.teste.lucasvegi.exemploobservermvc.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.teste.lucasvegi.exemploobservermvc.R;
import android.teste.lucasvegi.exemploobservermvc.model.Localizacao;
import android.teste.lucasvegi.exemploobservermvc.util.LocationServices;
import android.teste.lucasvegi.exemploobservermvc.util.observer.Observer;
import android.teste.lucasvegi.exemploobservermvc.util.observer.Subject;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ActivityObserverQuilometros extends AppCompatActivity implements Observer {

    TextView txtLatitude;
    TextView txtLongitude;
    TextView txtAltura;
    TextView txtPrecisao;
    TextView txtVelocidade;
    TextView txtDistancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_quilometros);

        //recupera referencias para as views
        txtLatitude = findViewById(R.id.LatitudeKm);
        txtLongitude = findViewById(R.id.LongitudeKm);
        txtAltura = findViewById(R.id.AlturaKm);
        txtPrecisao = findViewById(R.id.PrecisaoKm);
        txtVelocidade = findViewById(R.id.VelocidadeKm);
        txtDistancia = findViewById(R.id.DistanciaKm);

        //Adiciona observador View para o sujeito Model
        LocationServices.getLocalizacao().addObserver(this);
    }

    @Override
    protected void onDestroy() {
        //Remove observador View para o sujeito Model
        LocationServices.getLocalizacao().removeObserver(this);

        super.onDestroy();
    }

    @Override
    public void update(Subject s) {
        Localizacao loc = (Localizacao) s;
        DecimalFormat df = new DecimalFormat("#.##");

        try {
            txtLatitude.setText(loc.getLatitude() + "");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar latitude km!");
        }

        try {
            txtLongitude.setText(loc.getLongitude() + "");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar longitude km!");
        }

        try {
            txtAltura.setText(df.format(loc.getAltura()/1000) + " km");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar altura km!");
        }

        try {
            txtPrecisao.setText(df.format(loc.getPrecisao()/1000) + " km");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar precisao km!");
        }

        try {
            txtVelocidade.setText(df.format(loc.getVelocidade() * 3.6) + " km/h");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar velocidade km!");
        }

        try {
            txtDistancia.setText(df.format(loc.getDistancia()/1000) + " km");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar distancia km!");
        }

    }
}