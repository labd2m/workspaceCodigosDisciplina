package com.example.requisicaohttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends Activity implements LocationListener {
	
	//URL DO SERVIDOR CONFIGURADO PARA RECEBER REQUISIÇÃO POST
	/*EXEMPLO DE index.php
	  <?php    
    		
    		if($_POST['nome'] != "" && $_POST['sobrenome'] != ""){
    		    $name = 'arquivo.txt';
    		    $text = "Meu nome é: ".$_POST['nome']." ".$_POST['sobrenome']."\r\n";
    		    $file = fopen($name, 'a');
    		    fwrite($file, $text);
    		    fclose($file); 
    		
    		    echo $text;
    		}
    		
    		if($_POST['latitude'] != "" && $_POST['longitude'] != ""){
    		
    		    $name = 'coordenadas.txt';
    		    $text = "Latitude: ".$_POST['latitude']." Longitude: ".$_POST['longitude']."\r\n";
    		    $file = fopen($name, 'a');
    		    fwrite($file, $text);
    		    fclose($file); 
    		    
    		    echo $text;
    		}
	  ?>
    */
	
	//---->>> PARA SERVIDORES LOCAIS E TESTES COMO EMULADORES: http://10.0.2.2/caminhoDesejado	<<<-------
	//private static final String URL = "http://www.forlab.net76.net/android/index.php";
	
	private static final String URL = "http://android.fornut.com.br/index.php";
	public LocationManager lm;
	public String provider;
	public Map paramsLatLong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		//GPS Listener
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
	    criteria.setAccuracy( Criteria.ACCURACY_FINE );    
	    provider = lm.getBestProvider( criteria, true );
	    lm.requestLocationUpdates(provider, 0, 0, this);	
	    
	    /*lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);		
	      if ( provider == null ) {
	        Log.e( TAG, "No location provider found!" );
	        return;
	      }
	   */
	}
	
	public void enviar(View v){
		EnviaRequest envia = new EnviaRequest();
		
		//Inicia Thread para enviar Nome e Sobrenome para servidor
	    Thread threadDoEnviar = new Thread(envia);
	    threadDoEnviar.start();
	}
	
	public void pegarCoordenada(View v){
		//Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		Location loc = lm.getLastKnownLocation(provider);
		Toast.makeText(this, "Latitude: " + loc.getLatitude() + "\nLongitude: " + loc.getLongitude(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onLocationChanged(Location location) {		
	    paramsLatLong = new HashMap();
		
		// configura $_POST['latitude'] e $_POST['longitude'] 
		paramsLatLong.put("latitude", location.getLatitude());
		paramsLatLong.put("longitude", location.getLongitude());
		
		Toast.makeText(this, "Latitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude(), Toast.LENGTH_LONG).show();
		
	    PegarLatLong posicao = new PegarLatLong();
	    
	    //Inicia Thread para enviar Latitude e Longitude para o servidor
	    Thread threadDaPosicao = new Thread(posicao);
	    threadDaPosicao.start();		
	}
	
    public void onProviderDisabled(String provider) {
        // called when the GPS provider is turned off (user turning off the GPS on the phone)
        Log.d("DadosProvedor", "DISABLED");
    }

    public void onProviderEnabled(String provider) {
        // called when the GPS provider is turned on (user turning on the GPS on the phone)
        Log.d("DadosProvedor", "ENABLED");
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // called when the status of the GPS provider changes
        Log.d("DadosProvedor", "CHANGED");
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	public class EnviaRequest implements Runnable {
		  public void run () {
			  	EditText nome = (EditText) findViewById(R.id.edtNome);
				EditText sobrenome = (EditText) findViewById(R.id.edtSobrenome);

				Map params = new HashMap();
				
				// configura $_POST['nome'] e $_POST['sobrenome'] 
				params.put("nome", nome.getText().toString());
				params.put("sobrenome", sobrenome.getText().toString());
				
				String resposta = Http.getInstance(Http.NORMAL).doPost(URL, params);
				
				Log.i("Resposta do servidor", "Resposta:\n" + resposta);
		  }
		}

	public class PegarLatLong implements Runnable {
		  public void run () {			
				
			  	String resposta = Http.getInstance(Http.NORMAL).doPost(URL, paramsLatLong);			
				Log.i("Resposta do servidor", "Resposta:\n" + resposta);
				
		  }
	}

}


