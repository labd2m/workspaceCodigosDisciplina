package br.livro.android.cap16.mapview;

import android.graphics.Color;
import android.os.Bundle;
import br.livro.android.cap16.CoordenadaSete;
import br.livro.android.cap16.R;
import br.livro.android.cap16.overlay.BolaOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

/**
 * Simples demo de como inseirir um Overlay no mapa
 * 
 * @author ricardo
 * 
 */
public class ExemploBolaOverlay extends MapaSimplesXML {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		MapView mapView = (MapView) findViewById(R.id.mapa);

		BolaOverlay bola = new BolaOverlay(new GeoPoint(-25442550, -49279840), Color.BLUE);
		mapView.getOverlays().add(bola);

		mapView.getController().setZoom(18);

		mapView.getController().setCenter(new CoordenadaSete());
	}
}
