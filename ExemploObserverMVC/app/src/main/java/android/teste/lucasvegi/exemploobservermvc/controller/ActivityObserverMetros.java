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

public class ActivityObserverMetros extends AppCompatActivity implements Observer {

    TextView txtLatitude;
    TextView txtLongitude;
    TextView txtAltura;
    TextView txtPrecisao;
    TextView txtVelocidade;
    TextView txtDistancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_metros);

        //recupera referencias para as views
        txtLatitude = findViewById(R.id.LatitudeMetros);
        txtLongitude = findViewById(R.id.LongitudeMetros);
        txtAltura = findViewById(R.id.AlturaMetros);
        txtPrecisao = findViewById(R.id.PrecisaoMetros);
        txtVelocidade = findViewById(R.id.VelocidadeMetros);
        txtDistancia = findViewById(R.id.DistanciaMetros);

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
            Log.e("UPDATE", e.getMessage() + " atualizar latitude metros!");
        }

        try {
            txtLongitude.setText(loc.getLongitude() + "");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar longitude metros!");
        }

        try {
            txtAltura.setText(loc.getAltura() + " m");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar altura metros!");
        }

        try {
            txtPrecisao.setText(loc.getPrecisao() + " m");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar precisao metros!");
        }

        try {
            txtVelocidade.setText(df.format(loc.getVelocidade()) + " m/s");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar velocidade metros!");
        }

        try {
            txtDistancia.setText(df.format(loc.getDistancia()) + " m");
        }catch (Exception e){
            Log.e("UPDATE", e.getMessage() + " atualizar distancia metros!");
        }

    }
}