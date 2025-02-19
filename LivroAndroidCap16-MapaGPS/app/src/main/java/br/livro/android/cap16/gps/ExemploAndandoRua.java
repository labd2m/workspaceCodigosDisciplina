package br.livro.android.cap16.gps;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import br.livro.android.cap16.Coordenada;
import br.livro.android.cap16.R;
import br.livro.android.cap16.overlay.BolaOverlayListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Atualiza as coordenadas do GPS "mock" no Mapa
 * 
 * @author ricardo
 * 
 */
public class ExemploAndandoRua extends MapActivity implements LocationListener {
	private static final String CATEGORIA = "livro";
	private MapController controlador;
	private MapView mapa;
	private BolaOverlayListener bola;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.mapview);
		mapa = (MapView) findViewById(R.id.mapa);
		mapa.setClickable(true);
		mapa.setStreetView(true);

		controlador = mapa.getController();
		controlador.setZoom(16);

		bola = new BolaOverlayListener(null, Color.BLUE);
		mapa.getOverlays().add(bola);

		// Centraliza o mapa na última localização conhecida
		Location loc = getLocationManager().getLastKnownLocation(LocationManager.GPS_PROVIDER);

		// Se existe última localização converte para GeoPoint
		if (loc != null) {
			Coordenada ponto = new Coordenada(loc);
			Log.i(CATEGORIA, "Ultima localizacao: " + ponto);

			bola.setGeoPoint(ponto);
			controlador.setCenter(ponto);
		}

		// GPS listener
		getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	private LocationManager getLocationManager() {
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		return locationManager;
	}

	public void onLocationChanged(Location location) {
		Log.i(CATEGORIA, "latitude: " + location.getLatitude() + " , longitude: " + location.getLongitude());

		GeoPoint geoPoint = new Coordenada(location);

		// Atualiza a coordenada da bolinha
		bola.setGeoPoint(geoPoint);

//		BolaOverlay bola = new BolaOverlayListener(null, Color.BLUE);
//		bola.setGeoPoint(geoPoint);
//		mapa.getOverlays().add(bola);

		// Anima o mapa até a nova localização
		controlador.animateTo(geoPoint);

		// Invalida para desenhar o mapa novamente
		mapa.invalidate();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Remove o listener para não ficar rodando depois de sair
		getLocationManager().removeUpdates(this);
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	public void onProviderDisabled(String provider) {
	}
	public void onProviderEnabled(String provider) {
	}
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}