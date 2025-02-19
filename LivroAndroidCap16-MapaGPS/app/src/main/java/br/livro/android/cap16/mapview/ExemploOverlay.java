package br.livro.android.cap16.mapview;

import android.graphics.Color;
import android.os.Bundle;
import br.livro.android.cap16.Coordenada;
import br.livro.android.cap16.CoordenadaSete;
import br.livro.android.cap16.R;
import br.livro.android.cap16.overlay.BolaOverlay;
import br.livro.android.cap16.overlay.ImagemOverlay;

import com.google.android.maps.MapView;

/**
 * Simples demo de como inseirir um Overlay no mapa
 * 
 * @author ricardo
 *
 */
public class ExemploOverlay extends MapaSimplesXML {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		MapView mapView = (MapView) findViewById(R.id.mapa);

		mapView.getOverlays().add(new BolaOverlay(new Coordenada(-25.443195, -49.280977),Color.BLUE));
		mapView.getOverlays().add(new ImagemOverlay(new Coordenada(-25.442770, -49.279830),R.drawable.ponto)); 

		mapView.getController().setZoom(18);
		
		mapView.getController().setCenter(new CoordenadaSete());
	}
}
