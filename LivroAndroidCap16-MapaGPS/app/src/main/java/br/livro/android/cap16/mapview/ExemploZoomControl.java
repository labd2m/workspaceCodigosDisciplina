package br.livro.android.cap16.mapview;

import android.os.Bundle;
import br.livro.android.cap16.R;

import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * Simples MapView
 * 
 * @author ricardo
 * 
 */
public class ExemploZoomControl extends ExemploSatelite {
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);

		MapView mapa = (MapView) findViewById(R.id.mapa);
		MapController controlador = mapa.getController();
		controlador.setZoom(14);

		mapa.setBuiltInZoomControls(true);
	}
}
