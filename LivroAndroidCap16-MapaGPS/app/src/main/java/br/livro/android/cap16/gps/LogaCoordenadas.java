package br.livro.android.cap16.gps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Apenas registra o listener do GPS e loga as coordenadas
 * 
 * @author ricardo
 *
 */
public class LogaCoordenadas extends Activity implements LocationListener {

    private static final String CATEGORIA = "livro";
	private String provider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Recupera o parâmetro passado com o nome do LocProvider
	    Bundle extras = getIntent().getExtras();
	    if(extras != null){
	       provider = extras.getString("provider");
	    }

	    if(provider == null){
	    	//inicia com o defualt 'gps'
	    	provider = LocationManager.GPS_PROVIDER;
	    }

        TextView view = new TextView(this);
        view.setText("Envie uma coordenada");
        setContentView(view);
        
        getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this );
    }

    private LocationManager getLocationManager() {
        return (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //remove o listener para não ficar rodando mesmo depois de sair
        getLocationManager().removeUpdates(this);
    }

    /**
     * @see android.location.LocationListener#onLocationChanged(android.location.Location)
     */
    public void onLocationChanged(Location loc) {
    	Log.d(CATEGORIA, "location changed : " + loc.toString());

    	Toast.makeText(this, "location changed : " + loc.toString(), Toast.LENGTH_LONG).show();
    }

    public void onStatusChanged(java.lang.String arg0, int arg1, Bundle extras) {
    }

    public void onProviderEnabled(java.lang.String arg0) {
    }

    public void onProviderDisabled(java.lang.String arg0) {
    }

}
