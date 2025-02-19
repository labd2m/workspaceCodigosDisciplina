package br.livro.android.cap16.mapview;

import android.os.Bundle;
import br.livro.android.cap16.CoordenadaSete;
import br.livro.android.cap16.R;
import br.livro.android.cap16.overlay.ImagemOverlay;

import com.google.android.maps.MapView;

/**
 * Simples demo de como inseirir um Overlay no mapa
 * 
 * @author ricardo
 *
 */
public class ExemploImagemOverlay extends MapaSimplesXML {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		MapView mapView = (MapView) findViewById(R.id.mapa);

		ImagemOverlay imagem = new ImagemOverlay(new CoordenadaSete(),R.drawable.ponto);
		mapView.getOverlays().add(imagem);

		mapView.getController().setZoom(18);

		mapView.getController().setCenter(new CoordenadaSete());
	}
}
