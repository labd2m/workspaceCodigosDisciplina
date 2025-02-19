package br.livro.android.cap16.mapview;

import android.os.Bundle;
import br.livro.android.cap16.CoordenadaSete;
import br.livro.android.cap16.R;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Demo do MapView de um XML
 * 
 * @author ricardo
 * 
 */
public class ExemploCentralizaPonto extends ExemploSatelite {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		MapView mapView = (MapView) findViewById(R.id.mapa);

		mapView.setStreetView(true);
		mapView.setClickable(true);

		MapController mc = mapView.getController();

		GeoPoint p = new CoordenadaSete();

		mc.animateTo(p);
		mc.setZoom(16);
	}
}