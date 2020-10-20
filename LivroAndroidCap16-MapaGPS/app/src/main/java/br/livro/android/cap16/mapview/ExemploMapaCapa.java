package br.livro.android.cap16.mapview;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

/**
 * Cria um MapView e centraliza no cristo redentor
 * 
 * @author ricardo
 *
 */
public class ExemploMapaCapa extends MapActivity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		MapView map = new MapView(this,"0D02uiEmQKCrWEL2Ujxv-4eZFUp9MoYZ5pXsQOA");
		map.setClickable(true);
		map.setEnabled(true);
		map.getOverlays().add(new MyLocationOverlay(this,map));

		MapController mc = map.getController();

		// Zoom
		mc.setZoom(22);

		//Coordenadas
		double latitude = -25.442580 * 1E6; /* 1000000 */;
		double longitude = -49.279840 * 1E6 /* 1000000 */;

		GeoPoint point = new GeoPoint((int) latitude, (int) longitude);

		mc.setCenter(point);
		map.setStreetView(true);

		setContentView(map);
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}