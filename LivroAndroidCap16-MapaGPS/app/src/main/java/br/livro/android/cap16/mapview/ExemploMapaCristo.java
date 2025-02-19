package br.livro.android.cap16.mapview;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Cria um MapView e centraliza no cristo redentor
 * 
 * @author ricardo
 *
 */
public class ExemploMapaCristo extends MapActivity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Cria o MapView
		MapView map = new MapView(this,"0D02uiEmQKCrWEL2Ujxv-4eZFUp9MoYZ5pXsQOA");

		MapController mc = map.getController();

		// Faz zoom (valores de 1 a 21)
		mc.setZoom(22);

		/** Coorenadas GPS do Cristo Rendendor - Rio de Janeiro **/
		double latitude = -22.951285 * 1E6; /* 1000000 */;

		double longitude = -43.211262 * 1E6 /* 1000000 */;

		// Cria o Ponto com a Latitude e Longitude
		GeoPoint point = new GeoPoint((int) latitude, (int) longitude);

		// Centraliza o mapa no Cristo
		mc.setCenter(point);

		// Ativa o modo satélite no Mapa
		map.setSatellite(true);

		// Exibe o MapView na tela
		setContentView(map);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}