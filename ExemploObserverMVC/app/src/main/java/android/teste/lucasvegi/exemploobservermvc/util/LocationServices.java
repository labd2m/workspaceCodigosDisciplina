package android.teste.lucasvegi.exemploobservermvc.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.teste.lucasvegi.exemploobservermvc.controller.MainActivity;
import android.teste.lucasvegi.exemploobservermvc.model.Localizacao;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class LocationServices implements LocationListener {
    private Context ctx;
    private LocationManager lm;
    private Criteria criteria;
    private String provider;
    private int intervaloLocationRequest;
    private int distanciaMinRequest;
    private Location pontoDeReferencia;

    private static Localizacao localizacao; //sujeito que será observado

    public LocationServices(int intervalo, int distancia, Localizacao loc, Context ctx) {
        this.intervaloLocationRequest = intervalo;
        this.distanciaMinRequest = distancia;
        LocationServices.localizacao = loc;
        this.ctx = ctx;

        configuraCriterioRastreamento(this.ctx);
        iniciaRastreamento();
    }

    private void configuraCriterioRastreamento(Context ctx) {
        //Location Manager
        lm = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();

        //Testa se o aparelho tem GPS
        PackageManager packageManager = ctx.getPackageManager();
        boolean hasGPS = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);

        //Estabelece critério de precisão
        if (hasGPS) {
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            Log.i("LOCATION", "Usando GPS");
        } else {
            Log.i("LOCATION", "Usando WI-FI ou dados");
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        }
    }

    @SuppressLint("MissingPermission")
    public void iniciaRastreamento() {
        //Obtem melhor provedor habilitado com o critério estabelecido
        this.provider = lm.getBestProvider(this.criteria, true);

        //cria ponto de referência para o DPI com a finalidade de calcular distancia entre dois pontos
        this.pontoDeReferencia = new Location(this.provider);
        this.pontoDeReferencia.setLatitude(-20.764992);
        this.pontoDeReferencia.setLongitude(-42.868459);

        if (this.provider == null) {
            Log.e("PROVEDOR", "Nenhum provedor encontrado!");
        } else {
            Log.i("PROVEDOR", "Está sendo utilizado o provedor: " + this.provider);

            lm.requestLocationUpdates(this.provider, this.intervaloLocationRequest, this.distanciaMinRequest, this);
        }
    }

    public void pararRastreamento(){
        lm.removeUpdates(this);
        Log.w("PROVEDOR","Provedor " + this.provider + " parado!");
    }

    public static Localizacao getLocalizacao() {
        return localizacao;
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null){
            //altera valores do Subject
            LocationServices.localizacao.setLatitude(location.getLatitude());
            LocationServices.localizacao.setLongitude(location.getLongitude());
            LocationServices.localizacao.setAltura(location.getAltitude());
            LocationServices.localizacao.setPrecisao(location.getAccuracy());
            LocationServices.localizacao.setVelocidade(location.getSpeed());
            LocationServices.localizacao.setDistancia(location.distanceTo(this.pontoDeReferencia));
        }
    }

    @Override
    public void onStatusChanged(String s, int status, Bundle bundle) {
        String estado = "";

        switch (status){
            case LocationProvider.OUT_OF_SERVICE:
                estado = "OUT_OF_SERVICE";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                estado = "TEMPORARILY_UNAVAILABLE";
                break;
            case LocationProvider.AVAILABLE:
                estado = "AVAILABLE";
                break;
            default:
                estado = "DESCONHECIDO";
        }
        Log.d("LOCATION", "Provedor "+ this.provider +" mudou para o estado " + estado);
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d("LOCATION", "Habilitou o provedor " + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d("LOCATION", "Desabilitou o provedor " + s);

    }
}