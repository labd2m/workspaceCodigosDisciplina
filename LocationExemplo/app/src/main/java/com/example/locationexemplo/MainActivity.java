package com.example.locationexemplo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {
	
	public LocationManager lm;
	public Criteria criteria;
	public String provider;
	public int TEMPO_REQUISICAO_LATLONG = 5000;
	public int DISTANCIA_MIN_METROS = 0; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_main);
		
		//Location Manager
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		criteria = new Criteria();
		
		//Testa se o aparelho tem GPS
		PackageManager packageManager = getPackageManager();
		boolean hasGPS = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
		
		//Estabelece critério de precisão
		if(hasGPS){
			criteria.setAccuracy( Criteria.ACCURACY_FINE );
			Log.i("LOCATION", "Usando GPS");
		}else{
			Log.i("LOCATION", "Usando WI-FI ou dados");
			criteria.setAccuracy( Criteria.ACCURACY_COARSE );
		}
				

	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		//Obtem melhor provedor habilitado com o critério estabelecido
		provider = lm.getBestProvider( criteria, true );
		
		if ( provider == null ){
		    Log.e( "PROVEDOR", "Nenhum provedor encontrado!" );
		}else{
			Log.i( "PROVEDOR", "Está sendo utilizado o provedor: " + provider );	
			
			//Obtem atualizações de posição
			lm.requestLocationUpdates(provider, TEMPO_REQUISICAO_LATLONG, DISTANCIA_MIN_METROS, this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		//interrompe o Location Manager
		//lm.removeUpdates(this);
		//Log.w("PROVEDOR","Provedor " + provider + " parado!");
	}

	@Override
	protected void onDestroy() {
		//interrompe o Location Manager
		lm.removeUpdates(this);
		Log.w("PROVEDOR","Provedor " + provider + " parado!");
		super.onDestroy();
	}
		
	@Override
	public void onLocationChanged(Location location) {		
		if(location != null){
		    //cria ponto aleatório
			final Location pontoAleatorio = new Location(provider); 
			pontoAleatorio.setLatitude(-21.12928053);
			pontoAleatorio.setLongitude(-42.37377405);
			
			//obtem atributos na localização atual
			double altura = location.getAltitude(); //em metros
			double precisao = location.getAccuracy(); //raio de precisao, em metros, com 68% de probabilidade de acerto
		    double latitude = location.getLatitude();
		    double longitude = location.getLongitude();
		    double velocidade = location.getSpeed() * 3.6; //converte para km/h
		    double distancia = location.distanceTo(pontoAleatorio) / 1000; //converte para km		
		    
		    DecimalFormat df = new DecimalFormat("0.##");
		
		    //Exibe atributos
			Toast.makeText(this, "Latitude: " + latitude +
							     "\nLongitude: " + longitude +
							     "\nAltitude: " + altura + " m" +
							     "\nPrecisao: " + precisao + " m" +
								 "\nVelocidade: " + df.format(velocidade) + " km/h" +
								 "\nDistancia: " + df.format(distancia) + " km",
								 										Toast.LENGTH_LONG).show();
		}
	}
	
	public void onProviderDisabled(String provider) {
		Log.d("LOCATION", "Desabilitou o provedor " + provider);
	}

	public void onProviderEnabled(String provider) {
		Log.d("LOCATION", "Habilitou o provedor " + provider);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
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

		Log.d("LOCATION", "Provedor "+ provider +" mudou para o estado " + estado);
	}
	
}



