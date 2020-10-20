package br.livro.android.cap16.mapview;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;
import br.livro.android.cap16.R;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

/**
 * Demo do MapView de um XML
 * 
 * @author ricardo
 *
 */
public class MapaSimplesXML extends MapActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mapview);

		// MapView mapView = (MapView) findViewById(R.id.mapa);
		// Fazer algo com o mapView se necessário
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean b = super.onTouchEvent(event);

		int x = (int) event.getX();
		int y = (int) event.getY();

		MapView mapView = (MapView) findViewById(R.id.mapa);
		GeoPoint p = mapView.getProjection().fromPixels(x,y);
		Toast.makeText(this, "Latidute: " + p.getLatitudeE6() + " - Longitude: " + p.getLongitudeE6(), Toast.LENGTH_SHORT).show();

		return b;
	}
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
