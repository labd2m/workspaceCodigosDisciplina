package br.livro.android.cap16.mapview;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

/**
 * HelloWorld MapView
 * 
 * @author ricardo
 *
 */
public class MapaSimples extends MapActivity {
	private MapView mapView;
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		mapView = new MapView(this, "0D02uiEmQKCrWEL2Ujxv-4eZFUp9MoYZ5pXsQOA");
		setContentView(mapView);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean b = super.onTouchEvent(event);

		int x = (int) event.getX();
		int y = (int) event.getY();

		GeoPoint p = mapView.getProjection().fromPixels(x,y);
		Toast.makeText(this, "Latidute: " + p.getLatitudeE6() + " - Longitude: " + p.getLongitudeE6(), Toast.LENGTH_SHORT).show();

		return b;
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
